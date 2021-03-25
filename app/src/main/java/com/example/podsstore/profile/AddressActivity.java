package com.example.podsstore.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.R;
import com.example.podsstore.addtocart.SelectAddressActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.AddressDetailsRequest;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.drower.AddressesActivity;
import com.example.podsstore.prefs.PreferenceManagerss;
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
        getSupportActionBar().setTitle("Address Details");

        etaddress1=findViewById(R.id.etaddress1);
        etaddress2=findViewById(R.id.etaddress2);
        etaddress3=findViewById(R.id.etaddress3);
        etzipcode=findViewById(R.id.etzipcode);
        etcountry=findViewById(R.id.etcountry);
        tvsubmit=findViewById(R.id.tvsubmit);
        tvsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String address1 = etaddress1.getText().toString();
                String address2=etaddress2.getText().toString();
                String address3 = etaddress3.getText().toString();
                String zipcode = etzipcode.getText().toString();
                String country = etcountry.getText().toString();
                //Toast.makeText(getApplicationContext(),username,Toast.LENGTH_LONG).show();
//                    Intent login = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(login);
//                    finish();
                if (TextUtils.isEmpty(address1)) {
                    etaddress1.setError("Address Can't Blank!");
                } else if (TextUtils.isEmpty(address2)) {
                    etaddress2.setError("Address Can't Blank!");

                }else if (TextUtils.isEmpty(address3)) {
                    etaddress3.setError("Address Can't Blank!");

                }
                else if (TextUtils.isEmpty(zipcode)) {
                    etzipcode.setError("zipcode Can't Blank!");

                } else if (TextUtils.isEmpty(country)) {
                    etcountry.setError("country Can't Blank!");

                }else{
                    smallCarton(etaddress1.getText().toString(),etaddress2.getText().toString(),etaddress3.getText().toString(),etzipcode.getText().toString(),etcountry.getText().toString());

                }




                      }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                if(getIntent().getStringExtra("add")==null && getIntent().getStringExtra("at")==null ){

                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    startActivity(intent);
                    finish();
                }else{

                }
                if(getIntent().getStringExtra("at")==null &&getIntent().getStringExtra("profile")==null) {

                    Intent intent = new Intent(getApplicationContext(), AddressesActivity.class);
                    startActivity(intent);
                    finish();
                }else{

                }

                if (getIntent().getStringExtra("add")==null&&getIntent().getStringExtra("profile")==null) {

                    Intent intent = new Intent(getApplicationContext(), SelectAddressActivity.class);
                    startActivity(intent);
                    finish();

                }else{

                }
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

        ApiClient.getApiClient(). submitaddress(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL),r)
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
                            if(getIntent().getStringExtra("add")==null && getIntent().getStringExtra("at")==null ){

                                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                                startActivity(intent);
                                finish();
                            }else{

                            }
                            if(getIntent().getStringExtra("at")==null &&getIntent().getStringExtra("profile")==null) {

                                Intent intent = new Intent(getApplicationContext(), AddressesActivity.class);
                                startActivity(intent);
                                finish();
                            }else{

                            }

                            if (getIntent().getStringExtra("add")==null&&getIntent().getStringExtra("profile")==null) {

                                Intent intent = new Intent(getApplicationContext(), SelectAddressActivity.class);
                                startActivity(intent);
                                finish();

                            }else{

                            }

//                            Log.e("onSuccessaa", successResponse.getChallanid());
                            if (successResponse != null) {

//                                if (successResponse.getMessage().equals("success")) {
//                                    // mappingAdapter.clear();
//
//                                }

                                //  Toaster.show(mContext, successResponse.getMessage());

                            }
                        } else {
 Toast.makeText(getApplicationContext(), "server error", Toast.LENGTH_SHORT).show();

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getIntent().getStringExtra("add")==null && getIntent().getStringExtra("at")==null ){

            Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
            startActivity(intent);
            finish();
        }else{

        }
        if(getIntent().getStringExtra("at")==null &&getIntent().getStringExtra("profile")==null) {

            Intent intent = new Intent(getApplicationContext(), AddressesActivity.class);
            startActivity(intent);
            finish();
        }else{

        }

        if (getIntent().getStringExtra("add")==null&&getIntent().getStringExtra("profile")==null) {

            Intent intent = new Intent(getApplicationContext(), SelectAddressActivity.class);
            startActivity(intent);
            finish();

        }else{

        }

    }
}