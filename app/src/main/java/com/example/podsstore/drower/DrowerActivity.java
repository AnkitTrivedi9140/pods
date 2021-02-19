package com.example.podsstore.drower;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.ProfileResponses;
import com.example.podsstore.prefs.PreferenceManager;
import com.example.podsstore.prefs.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrowerActivity extends AppCompatActivity {
TextView tvusername,tvemail;
ImageView ivcut;
Button btnlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drower);
        tvusername=findViewById(R.id.tvusername);
        tvemail=findViewById(R.id.tvemail);
        ivcut=findViewById(R.id.ivcut);
        btnlogout=findViewById(R.id.btnlogout);
        getSupportActionBar().hide();
        loadData();
        ivcut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main =new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
                finish();
            }
        });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
    @SuppressLint("CheckResult")
    private void loadData() {

        Log.e("getfdfd", PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN)+PreferenceManager.getStringValue(Preferences.USER_EMAIL)
        );

        ApiClient.getApiClient().profile(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN),PreferenceManager.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<ProfileResponses>() {
            @Override
            public void onResponse(Call<ProfileResponses> call, Response<ProfileResponses> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getprofile",String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    ProfileResponses list = response.body();
                    for (int i=0; i<list.getAddress().size(); i++) {
                       // tvaddress.setText(list.getAddress().get(i).getAddressline1().toString()+", "+list.getAddress().get(i).getAddressline2().toString()+"\n"+list.getAddress().get(i).getAddressline3().toString());

                    }
                    tvusername.setText(list.getUsername());
                    tvemail.setText(list.getUseremailid());

                }
            }
            @Override
            public void onFailure(Call<ProfileResponses> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
            }
        });
    }
}