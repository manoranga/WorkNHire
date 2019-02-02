package com.example.heyshan.worknhire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class User extends AppCompatActivity {

    public void toUser(View view) {

        Intent intent = new Intent(User.this, SignInUser.class);
        startActivity(intent);
        //finish();
    }

    public void toEmployee(View view) {

        Intent intent = new Intent(User.this, SignInEmployee.class);
        startActivity(intent);
        //finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }
}
