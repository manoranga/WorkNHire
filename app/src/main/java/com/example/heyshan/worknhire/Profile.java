package com.example.heyshan.worknhire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {
    private CircleImageView profImage;
    private TextView tvName, tvRoute,tvNic, tvTelephone, tvEmail, tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profImage = findViewById(R.id.profile_image);
        tvName = findViewById(R.id.tv_sales_rep_name);
        tvRoute = findViewById(R.id.tv_route);
        tvNic = findViewById(R.id.tv_nic);
        tvTelephone = findViewById(R.id.tv_telephone);
        tvEmail = findViewById(R.id.tv_email);
        tvAddress = findViewById(R.id.tv_address);
    }
}
