package com.example.podsstore.drower;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.example.podsstore.data.request.ContactUsRequest;
import com.example.podsstore.data.request.DemoRequest;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.google.gson.Gson;
import com.hbb20.CountryCodePicker;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class OnlineDemoActivity extends AppCompatActivity {
EditText etname,etemail,etcompany,etphone,etcountry,etstate,etpincode;
TextView tvsubmit;
CountryCodePicker countryCodePicker;
    String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_demo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Online Demo");

        etname=findViewById(R.id.etname);
        etemail=findViewById(R.id.etemail);
        etcompany=findViewById(R.id.etcompany);
        etphone=findViewById(R.id.etphone);
        etcountry=findViewById(R.id.etcountry);
        etstate=findViewById(R.id.etstate);
        etpincode=findViewById(R.id.etpincode);
        tvsubmit=findViewById(R.id.tvsubmit);
        countryCodePicker=findViewById(R.id.et1);

countryCodePicker.showNameCode(false);
//countryCodePicker.setShowPhoneCode(false);
        countryCodePicker.setCountryForNameCode("US");
        etcountry.setText(countryCodePicker.getSelectedCountryName().toString());

        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                etcountry.setText(countryCodePicker.getSelectedCountryName().toString());

            }
        });
tvsubmit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (TextUtils.isEmpty(etname.getText().toString())) {
            etname.setError("Name Can't Blank!");
        } else if (TextUtils.isEmpty(etemail.getText().toString())) {
            etemail.setError("Email Address Can't Blank!");

        }
        else if (!etemail.getText().toString().matches(regex)) {
            etemail.setError("Email Address is not valid!");

        }
        else if (TextUtils.isEmpty(etphone.getText().toString())) {
            etphone.setError("Mobile Number Can't Blank!");

        }
        else if (TextUtils.isEmpty(etstate.getText().toString())) {
            etstate.setError("State Can't Blank!");

        }else if (TextUtils.isEmpty(etpincode.getText().toString())) {
            etpincode.setError("Zip code Can't Blank!");

        }else{

         smallCarton(etname.getText().toString(),etphone.getText().toString(),etemail.getText().toString(),etcountry.getText().toString(),"buyer",etstate.getText().toString(),etpincode.getText().toString(),etcompany.getText().toString());
        }

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
    private void smallCarton(String username,String mobile,String email,String country,String usertype,String state,String zipcode,String company) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        List<DemoRequest> list = new ArrayList<>();

        DemoRequest r = new DemoRequest();
        r.setName(username);
        r.setEmail(email);
        r.setMobile(mobile);
        r.setCompany(company);
        r.setCountry(country);
        r.setState(state);
        r.setUsertype(usertype);
        r.setZipcode(zipcode);
        list.add(r);




        Log.e("postData", new Gson().toJson(r));

        ApiClient.getApiClient(). demoonline(r)
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
}