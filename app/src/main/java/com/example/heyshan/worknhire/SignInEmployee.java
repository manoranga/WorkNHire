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
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static com.android.volley.Request.Method.POST;

public class SignInEmployee extends AppCompatActivity {

    private Button btnSignUpEmp;
    private EditText Fname, Lname, Email, MobileNum, Password;
    private ProgressDialog pDialog;
    private TextView mResult;
//

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_employee);




        Fname =  findViewById(R.id.etFirstNameInSignInEmp);
        Lname =  findViewById(R.id.etLastNameSignInEmp);
        Email =  findViewById(R.id.etEmailInSignInEmp);
        MobileNum =  findViewById(R.id.etMobileNumInSignInEmp);
        Password = findViewById(R.id.etPasswordInSignInEmp);
        btnSignUpEmp =  findViewById(R.id.btnLogIn);

        btnSignUpEmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(Fname.getText().toString()) && TextUtils.isEmpty(Lname.getText().toString()) && TextUtils.isEmpty(Email.getText().toString()) && TextUtils.isEmpty(MobileNum.getText().toString()) && TextUtils.isEmpty(Password.getText().toString())){

                    Toast.makeText(SignInEmployee.this, "Enter Field ", Toast.LENGTH_SHORT).show();  }
                else
                    new SignInEmployee.PostDataTask().execute("http://138.68.177.4:3000/api/employeeRegister");


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
            }
            else
            {
                Gson g = new Gson();
                AppUser p = g.fromJson(result, AppUser.class);
                //mResult.setText(p.email);
                Intent intent = new Intent(SignInEmployee.this, JobList.class);
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
                dataToSend.put("fname", Fname.getText().toString());
                dataToSend.put("lname", Lname.getText().toString());
                dataToSend.put("email", Email.getText().toString());
                dataToSend.put("mobileno", MobileNum.getText().toString());
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
