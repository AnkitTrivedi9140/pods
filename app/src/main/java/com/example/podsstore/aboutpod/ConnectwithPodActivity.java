package com.example.podsstore.aboutpod;

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
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.CreateLoginUserRequest;
import com.example.podsstore.data.request.TellUsMoreResquest;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.prefs.PreferenceManager;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.product.ProductListActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ConnectwithPodActivity extends AppCompatActivity {
EditText etusername,etphone,etemail,etquery;
TextView btnsubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connectwith_pod);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Connect with POD");

        etusername=findViewById(R.id.eturname);
        etphone=findViewById(R.id.etphone);
        etemail=findViewById(R.id.etemail);
        etquery=findViewById(R.id.etquery);
        btnsubmit=findViewById(R.id.tvsubmit);
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(etusername.getText().toString())) {
                    etusername.setError("Name Can't Blank!");
                } else if (TextUtils.isEmpty(etphone.getText().toString())) {
                    etphone.setError("Mobile Number Can't Blank!");

                }else if (TextUtils.isEmpty(etemail.getText().toString())) {
                    etemail.setError("Email Address Can't Blank!");

                }
                else if (TextUtils.isEmpty(etquery.getText().toString())) {
                    etquery.setError("Query Can't Blank!");

                }else{
                    smallCarton(etusername.getText().toString(),etphone.getText().toString(),etemail.getText().toString(),etquery.getText().toString());
                }

                //smallCarton(etusername.getText().toString(),etphone.getText().toString(),etemail.getText().toString(),etquery.getText().toString());
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("CheckResult")
    private void smallCarton(String username,String mobile,String email,String query) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        List<TellUsMoreResquest> list = new ArrayList<>();

        TellUsMoreResquest r = new TellUsMoreResquest();
        r.setYourfullname(username);
        r.setYourmobileno(mobile);
        r.setEmailaddress(email);
        r.setYourquery(query);
        list.add(r);




        Log.e("postData", new Gson().toJson(r));

        ApiClient.getApiClient(). tellusmore(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN),r)
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