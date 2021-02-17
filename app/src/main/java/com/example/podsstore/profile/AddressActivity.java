package com.example.podsstore.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.R;
import com.example.podsstore.aboutpod.AboutActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.AddressDetailsRequest;
import com.example.podsstore.data.request.TellUsMoreResquest;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.prefs.PreferenceManager;
import com.example.podsstore.prefs.Preferences;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class AddressActivity extends AppCompatActivity {
private EditText etaddress1,etaddress2,etaddress3,etzipcode,etcountry;
private TextView tvsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Address Detaills");

        etaddress1=findViewById(R.id.etaddress1);
        etaddress2=findViewById(R.id.etaddress2);
        etaddress3=findViewById(R.id.etaddress3);
        etzipcode=findViewById(R.id.etzipcode);
        etcountry=findViewById(R.id.etcountry);
        tvsubmit=findViewById(R.id.tvsubmit);
        tvsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smallCarton(etaddress1.getText().toString(),etaddress2.getText().toString(),etaddress3.getText().toString(),etzipcode.getText().toString(),etcountry.getText().toString());
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressLint("CheckResult")
    private void smallCarton(String address1,String address2,String address3,String zipcode,String country) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        List<AddressDetailsRequest> list = new ArrayList<>();

        AddressDetailsRequest r = new AddressDetailsRequest();
        r.setAddress1(address1);
        r.setAddress2(address2);
        r.setAddress3(address3);
        r.setZipcode(zipcode);
        r.setCountry(country);
        list.add(r);




        Log.e("postData", new Gson().toJson(r));

        ApiClient.getApiClient(). submitaddress(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN),PreferenceManager.getStringValue(Preferences.USER_EMAIL),r)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<CreateLoginUserResponse>>() {
                    @Override
                    public void onSuccess(Response<CreateLoginUserResponse> response) {

                        // binding.progressbar.setVisibility(View.GONE);


                        Log.e("onSuccess", String.valueOf(response.code()));
                        if (response.isSuccessful()) {

                            CreateLoginUserResponse successResponse = response.body();
                            Toast.makeText(getApplicationContext(),successResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                            Intent login = new Intent(CreateAccountActivity.this, SplashActivity.class);
//                            startActivity(login);
//                            finish();

//                            Log.e("onSuccessaa", successResponse.getChallanid());
                            if (successResponse != null) {

//                                if (successResponse.getMessage().equals("success")) {
//                                    // mappingAdapter.clear();
//
//                                }

                                //  Toaster.show(mContext, successResponse.getMessage());

                            }
                        } else {
                            // Toast.makeText(getApplicationContext(), "server error", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("onError: " , e.getMessage());
                        Toast.makeText(getApplicationContext(), "server error", Toast.LENGTH_SHORT).show();

                        // binding.progressbar.setVisibility(View.GONE);
                        // NetworkHelper.handleNetworkError(e, mContext);
                    }
                });
    }

}