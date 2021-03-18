package com.example.podsstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.podsstore.login.CreateAccountActivity;
import com.example.podsstore.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
private TextView tvicon,tvwelcomedetails,tvwelcome,tvwelcomedetailsnext,tvsignin,tvsignup;
ImageView ivlogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        tvwelcomedetails=findViewById(R.id.tvwelcomedetails);
        tvwelcome=findViewById(R.id.tvwelcode);
        tvwelcomedetailsnext=findViewById(R.id.tvwelcomedetailsnext);
        tvsignin=findViewById(R.id.tvsignin);
        tvsignup=findViewById(R.id.tvsignup);
        ivlogo=findViewById(R.id.ivlogo);
        getSupportActionBar().hide();
        Typeface typeface= ResourcesCompat.getFont(getApplicationContext(),R.font.architects_daughter);
        Typeface typefacewelcome= ResourcesCompat.getFont(getApplicationContext(),R.font.bentham_rt);
        Typeface typefacewelcomedetails= ResourcesCompat.getFont(getApplicationContext(),R.font.advent_pro);

        tvwelcome.setTypeface(typefacewelcome);
        tvwelcomedetails.setTypeface(typefacewelcomedetails);
        tvwelcomedetailsnext.setTypeface(typefacewelcomedetails);
        tvsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin=new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(signin);
                finish();
            }
        });
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup=new Intent(SplashActivity.this, CreateAccountActivity.class);
                startActivity(signup);
                finish();
            }
        });

    }

}