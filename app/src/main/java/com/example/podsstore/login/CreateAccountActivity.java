package com.example.podsstore.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.podsstore.R;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        getSupportActionBar().hide();
    }
}