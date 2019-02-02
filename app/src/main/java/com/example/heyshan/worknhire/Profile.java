package com.example.heyshan.worknhire;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {
    private CircleImageView profImage;
    private TextView tvName, tvRoute, tvNic, tvTelephone, tvEmail, tvAddress;
    private ImageButton imgBtnCall;
    private Context mContext;

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
        imgBtnCall = findViewById(R.id.imgBtnCaller);


        imgBtnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), tvName.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0123456789"));
                startActivity(intent);
            }
        });
    }
}
