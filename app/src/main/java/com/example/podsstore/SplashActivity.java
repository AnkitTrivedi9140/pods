package com.example.podsstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.podsstore.login.CreateAccountActivity;
import com.example.podsstore.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
private TextView tvicon,tvwelcomedetails,tvwelcome,tvwelcomedetailsnext,tvsignin,tvsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        tvicon=findViewById(R.id.tvicon);
        tvwelcomedetails=findViewById(R.id.tvwelcomedetails);
        tvwelcome=findViewById(R.id.tvwelcode);
        tvwelcomedetailsnext=findViewById(R.id.tvwelcomedetailsnext);
        tvsignin=findViewById(R.id.tvsignin);
        tvsignup=findViewById(R.id.tvsignup);
        getSupportActionBar().hide();
        Typeface typeface= ResourcesCompat.getFont(getApplicationContext(),R.font.architects_daughter);
        Typeface typefacewelcome= ResourcesCompat.getFont(getApplicationContext(),R.font.bentham_rt);
        Typeface typefacewelcomedetails= ResourcesCompat.getFont(getApplicationContext(),R.font.advent_pro);
        tvicon.setTypeface(typeface);
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