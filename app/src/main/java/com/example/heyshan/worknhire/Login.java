package com.example.heyshan.worknhire;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;
import static com.android.volley.Request.Method.POST;

public class Login extends AppCompatActivity {

    public void toForgetPassword(View view) {

        Intent intent = new Intent(Login.this, ForgetPassword.class);
        startActivity(intent);
        //finish();
    }



    private Button btnLogIn;
    private EditText Email;
    private EditText Password;
    private ProgressDialog pDialog;
    private TextView mResult;
    private UserLocalStorage userLocalStore;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mResult = (TextView) findViewById(R.id.tv_result);

        userLocalStore = new UserLocalStorage(this);
        Email = findViewById(R.id.etEmailInLogin);
        Password = findViewById(R.id.etPasswordInLogin);
        btnLogIn = findViewById(R.id.btnLogIn);

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (TextUtils.isEmpty(Email.getText().toString()) && TextUtils.isEmpty(Password.getText().toString())){
//
//                    Toast.makeText(Login.this, "Enter your Email and Password", Toast.LENGTH_SHORT).show();  }
//                    else
////                        new PostDataTask().execute("http://138.68.177.4:3000/api/client/haha@gmail.com");
               // login();
                Intent intent = new Intent(getApplicationContext(),JobList.class);
                startActivity(intent);
                finish();
            }
        });


        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        //make POST request




    }

//    private void loginUser(String email, String password) {
//        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
//            Toast.makeText(this, "Enter your Email and Password", Toast.LENGTH_SHORT).show();
//
//
//        }
//
//
//
//    }

    public void login(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://138.68.177.4:3000/").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        ApiClient client = retrofit.create(ApiClient.class);
        Call<UserModel> call = client.login(Email.getText().toString());

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, retrofit2.Response<UserModel> response) {
                userLocalStore.setUserLoggedIn(true);
                userLocalStore.setUserDetails(response.body());

                Intent intent = new Intent(getApplicationContext(),JobList.class);
                startActivity(intent);
                finish();
                Toast.makeText(Login.this, response.body().getFname(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });
    }

    class PostDataTask extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();


        }

        @Override
        protected String doInBackground(String... params) {

            try {
                return postData(params[0]);
            } catch (IOException ex) {
                return "Network error !";
            } catch (JSONException ex) {
                return "Data Invalid !";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result == "Network error !")
            {
                mResult.setText("Wrong Email or Password");
                Toast.makeText(Login.this, "Wrong Email or Password", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Gson g = new Gson();
                AppUser p = g.fromJson(result, AppUser.class);
                //mResult.setText(p.email);
                Intent intent = new Intent(Login.this, JobList.class);
                startActivity(intent);
            }


//            if(result.equals(true)){
//
//                Intent intent = new Intent(Login.this, JobList.class);
//                startActivity(intent);
//            }
//            else Toast.makeText(Login.this, "Login Error", Toast.LENGTH_SHORT).show();


        }

        private String postData(String urlPath) throws IOException, JSONException {

            StringBuilder result = new StringBuilder();
            BufferedWriter bufferedWriter = null;
            BufferedReader bufferedReader = null;

            try {
                //Create data to send to server
                JSONObject dataToSend = new JSONObject();
                dataToSend.put("email", Email.getText().toString());
                dataToSend.put("password", Password.getText().toString());


                //Initialize and config request, then connect to server.
                URL url = new URL(urlPath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(10000 /* milliseconds */);
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoOutput(true);  //enable output (body data)
                urlConnection.setRequestProperty("Content-Type", "application/json");// set header
                urlConnection.connect();

                //Write data into server
                OutputStream outputStream = urlConnection.getOutputStream();
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                bufferedWriter.write(dataToSend.toString());
                bufferedWriter.flush();

                //Read data response from server
                InputStream inputStream = urlConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line).append("\n");
                }
            } finally {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            }

            return result.toString();
        }

    }


}
