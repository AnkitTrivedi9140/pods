package com.example.podsstore.addtocart;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.data.response.ProfileResponses;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.profile.AddressActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectAddressActivity extends AppCompatActivity {
TextView tvname,tvaddress,tvchangeaddtess;
Button continuebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Select Address");
        tvname=findViewById(R.id.tvname);
        tvaddress=findViewById(R.id.tvaddress);
        tvchangeaddtess=findViewById(R.id.tvchangeaddtess);
        continuebtn=findViewById(R.id.continuebtn);
        loadData();
        tvchangeaddtess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AddressActivity.class);
                intent.putExtra("at","at");
                startActivity(intent);
                finish();
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(getApplicationContext(), AddToCartActivity.class);
                startActivity(intent);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressLint("CheckResult")
    private void loadData() {

        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+"????"+getIntent().getStringExtra("userid") );

        ApiClient.getApiClient().profile(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<List<ProfileResponses>>() {
            @Override
            public void onResponse(Call<List<ProfileResponses>> call, Response<List<ProfileResponses>> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getMaterialMasters",String.valueOf(response.code()) );
                if (response.isSuccessful()) {
                    List<ProfileResponses> list = response.body();

                  for (int i = 0; i < list.size(); i++) {
                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getprofile",String.valueOf(response.code()));
                if (response.isSuccessful()) {

                   // for (int i=0; i<list.getAddress().size(); i++) {
                    if(list.get(i).getAddress()!=null){
                        tvaddress.setText(list.get(i).getAddress().getAddressline1().toString()+", "+list.get(i).getAddress().getAddressline2().toString()+"\n"+list.get(i).getAddress().getAddressline3().toString());

                    }

                    //}
                    if(list.get(i).getUsername()!=null){
                        tvname.setText(list.get(i).getUsername());
                    }

if(tvaddress.length()<5){
    Toast.makeText(getApplicationContext(),"Add Address first!",Toast.LENGTH_SHORT).show();
}else{
    continuebtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(getApplicationContext(), PaymentActivity.class);
            intent.putExtra("userid",getIntent().getStringExtra("userid"));
            intent.putExtra("getbuynowqty",getIntent().getStringExtra("getbuynowqty"));
            startActivity(intent);
            finish();
        }
    });
}

                }
                  }




                }
            }

            @Override
            public void onFailure(Call<List<ProfileResponses>> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
            }
        });
//        Log.e("getfdfd", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+ PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)
//        );
//
//        ApiClient.getApiClient().profile(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<ProfileResponses>() {
//            @Override
//            public void onResponse(Call<ProfileResponses> call, Response<ProfileResponses> response) {
//
//                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
//                Log.e("getprofile",String.valueOf(response.code()));
//                if (response.isSuccessful()) {
//                    ProfileResponses list = response.body();
//                   // for (int i=0; i<list.getAddress().size(); i++) {
//                     tvaddress.setText(list.getAddress().getAddressline1().toString()+", "+list.getAddress().getAddressline2().toString()+"\n"+list.getAddress().getAddressline3().toString());
//
//                    //}
//                tvname.setText(list.getUsername());
//if(tvaddress.length()<5){
//    Toast.makeText(getApplicationContext(),"Add Address first!",Toast.LENGTH_SHORT).show();
//}else{
//    continuebtn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Intent intent=new Intent(getApplicationContext(), PaymentActivity.class);
//            intent.putExtra("userid",getIntent().getStringExtra("userid"));
//            intent.putExtra("getbuynowqty",getIntent().getStringExtra("getbuynowqty"));
//            startActivity(intent);
//            finish();
//        }
//    });
//}
//
//                }
//            }
//            @Override
//            public void onFailure(Call<ProfileResponses> call, Throwable t) {
//                Log.e("onerrors",t.getMessage());
//            }
//        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), AddToCartActivity.class);
        startActivity(intent);
        finish();
    }
}