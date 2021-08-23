package com.example.podsstore.drower;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.R;
import com.example.podsstore.aboutpod.AboutActivity;
import com.example.podsstore.aboutpod.ConnectwithPodActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.ContactUsRequest;
import com.example.podsstore.data.request.CustomNotificationRequest;
import com.example.podsstore.data.request.TellUsMoreResquest;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.notification.ApiClientNoti;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class FeedbackActivity extends AppCompatActivity {
    EditText etusername,etphone,etemail,etquery,etconcern,etbusiness;
    TextView btnsubmit;
    String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    CountryCodePicker countryCodePicker;
    String phonecode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Contact Us");
        countryCodePicker=findViewById(R.id.et1);
        etbusiness=findViewById(R.id.etbusiness);
        etconcern=findViewById(R.id.etconcern);
        etusername=findViewById(R.id.eturname);
        etphone=findViewById(R.id.etphone);
        etemail=findViewById(R.id.etemail);
        etquery=findViewById(R.id.etquery);
        btnsubmit=findViewById(R.id.tvsubmit);
       countryCodePicker.setCountryForNameCode("US");
       phonecode=countryCodePicker.getSelectedCountryCode();
        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                phonecode=countryCodePicker.getSelectedCountryCode();
               // etphone.setText(countryCodePicker.getSelectedCountryName().toString());
            }
        });
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
                else if (!etemail.getText().toString().matches(regex)) {
                    etemail.setError("Email Address is not valid!");

                }else if (TextUtils.isEmpty(etbusiness.getText().toString())) {
                    etbusiness.setError("Business Can't Blank!");

                }
                else if (TextUtils.isEmpty(etconcern.getText().toString())) {
                    etconcern.setError("Concern Can't Blank!");

                }
                else if (TextUtils.isEmpty(etquery.getText().toString())) {
                    etquery.setError("Query Can't Blank!");

                }else{

                   smallCarton(etusername.getText().toString(),phonecode+etphone.getText().toString(),etemail.getText().toString(),etbusiness.getText().toString(),etconcern.getText().toString(),etquery.getText().toString());
                }

                //   smallCarton(etusername.getText().toString(),etphone.getText().toString(),etemail.getText().toString(),etquery.getText().toString());
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @SuppressLint("CheckResult")
    private void smallCarton(String username,String mobile,String email,String business,String concern,String remark) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        List<ContactUsRequest> list = new ArrayList<>();

        ContactUsRequest r = new ContactUsRequest();
        r.setContactUsName(username);
        r.setContactUsEmail(email);
        r.setContactUsNumber(mobile);
        r.setContactUsBusiness(business);
        r.setContactUsConcern(concern);
        r.setContactUsRemark(remark);
        list.add(r);




        Log.e("postData", new Gson().toJson(r));

        ApiClient.getApiClient(). contactus(r)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<CreateLoginUserResponse>>() {
                    @Override
                    public void onSuccess(Response<CreateLoginUserResponse> response) {

                        // binding.progressbar.setVisibility(View.GONE);


                        Log.e("onSuccesstellusmore", String.valueOf(response.code()));
                        if (response.isSuccessful()) {

                            CreateLoginUserResponse successResponse = response.body();
                            Toast.makeText(getApplicationContext(),successResponse.getMessage(), Toast.LENGTH_SHORT).show();
                          //  Toast.makeText(getApplicationContext(),"Your feedback submit successfully",Toast.LENGTH_LONG).show();
                            onBackPressed();
                            customNotification("contact us");
                            //                            Intent login = new Intent(ConnectwithPodActivity.this, AboutActivity.class);
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
    @SuppressLint("CheckResult")
    private void customNotification(String event) {

        List<CustomNotificationRequest> list = new ArrayList<>();

        CustomNotificationRequest r = new CustomNotificationRequest();
        Log.d("regNotiplaceorder",String.valueOf(FirebaseInstanceId.getInstance().getToken()));
        r.setGcmtoken(FirebaseInstanceId.getInstance().getToken());
        r.setEvent(event);

        list.add(r);




        Log.e("postData", new Gson().toJson(r));

        ApiClientNoti.getApiClients().customnoti(r)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<CreateLoginUserResponse>>() {
                    @Override
                    public void onSuccess(Response<CreateLoginUserResponse> response) {
                        Log.e("onSuccess", String.valueOf(response.code()));
                        if (response.isSuccessful()) {

                            CreateLoginUserResponse successResponse = response.body();
                            //Toast.makeText(getApplicationContext(), successResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                            Intent login = new Intent(CreateAccountActivity.this, SplashActivity.class);
//                            startActivity(login);

//                            Log.e("onSuccessaa", successResponse.getChallanid());
                            if (successResponse != null) {

//                                if (successResponse.getMessage().equals("success")) {
//                                    // mappingAdapter.clear();
//
//                                }

                                //  Toaster.show(mContext, successResponse.getMessage());

                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Please check your email id...", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("onError: " , e.getMessage());
                        Toast.makeText(getApplicationContext(), "server error", Toast.LENGTH_SHORT).show();


                    }
                });


    }
}