package com.example.podsstore.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.podsstore.R;
import com.example.podsstore.SplashActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.CreateLoginUserRequest;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CreateAccountActivity extends AppCompatActivity {
    private Button logInBtn;
    private EditText usernameEt, passwordEt, emaiEt,reenterpasswordet;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        getSupportActionBar().hide();

        initViews();
    }
    private TextWatcher usernameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            if (s.toString().isEmpty()) {
                usernameEt.setError(getString(R.string.error_msg_username));
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private TextWatcher passwordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            if (s.toString().isEmpty()) {
                passwordEt.setError(getString(R.string.error_msg_password));
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.logInBtn:
                    String username = usernameEt.getText().toString();
                    String email=emaiEt.getText().toString();
                    String password = passwordEt.getText().toString();
                    String reenterpassword = reenterpasswordet.getText().toString();
                    //Toast.makeText(getApplicationContext(),username,Toast.LENGTH_LONG).show();
//                    Intent login = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(login);
//                    finish();

                  //  smallCarton(username,email,password,reenterpassword);
                    break;

                case R.id.ivback:

                    Intent login = new Intent(CreateAccountActivity.this, SplashActivity.class);
                    startActivity(login);
                    finish();


                    break;


            }

        }
    };

    @SuppressLint("CheckResult")
    private void smallCarton(String username,String useremail,String password,String passwordagin) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        List<CreateLoginUserRequest> list = new ArrayList<>();
//        List<String> strings = new ArrayList<>();
//        List<String> ownershiplist = new ArrayList<>();
//        List<String> fromdatelist = new ArrayList<>();
//        List<String> todatelist = new ArrayList<>();
//        List<String> qty = new ArrayList<>();
//        ArrayList<OutwardScanBarcodeReq> barcodeDataArrayList = new ArrayList<>();
//        for (ScanBarcodeEntity d : haziraAdapter.get()) {
//            strings.add(d.getBarcode());
//        }
//        for (ScanBarcodeEntity d : haziraAdapter.get()) {
//            ownershiplist.add(d.getOwnershiptype());
//        }
//        for (ScanBarcodeEntity d : haziraAdapter.get()) {
//            fromdatelist.add(d.getFromdate());
//        }
//        for (ScanBarcodeEntity d : haziraAdapter.get()) {
//            todatelist.add(d.getTodate());
//        }
//        for (ScanBarcodeEntity d : haziraAdapter.get()) {
//            qty.add(d.getQuantity());
//        }
//
//        for (String s : strings) {
//            OutwardScanBarcodeReq bd = new OutwardScanBarcodeReq();
//            for (String ownership : ownershiplist) {
//
//                bd.setOwnershiptype(ownership);
//
//            }
//            for (String fromdate : fromdatelist) {
//
//                bd.setOwnershipfromdate(fromdate);
//
//            }
//            for (String todate : todatelist) {
//
//                bd.setOwnershiptodate(todate);
//
//            }
//            for (String qtyy : qty) {
//
//                bd.setQty(qtyy);
//
//            }
//            bd.setTag((s));
//            barcodeDataArrayList.add(bd);
//        }
//
//        Log.e("smallCarton", barcodeDataArrayList.toString());
        CreateLoginUserRequest r = new CreateLoginUserRequest();
        r.setUsername(username);
        r.setUseremail(useremail);
        r.setPassword(password);
        r.setReenterpassword(passwordagin);
        list.add(r);




        Log.e("postData", new Gson().toJson(r));

        ApiClient.getApiClient().createuserregister(r)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<CreateLoginUserResponse>>() {
                    @Override
                    public void onSuccess(Response<CreateLoginUserResponse> response) {

                        // binding.progressbar.setVisibility(View.GONE);


                        Log.e("onSuccess", String.valueOf(response.body()));
                        if (response.isSuccessful()) {

                            CreateLoginUserResponse successResponse = response.body();
                            Toast.makeText(getApplicationContext(), "id create Successful -- " +successResponse.getId(), Toast.LENGTH_SHORT).show();

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

    private void initViews() {
        logInBtn = findViewById(R.id.logInBtn);
        usernameEt = findViewById(R.id.usernameEt);
        passwordEt = findViewById(R.id.passwordEt);
        emaiEt = findViewById(R.id.emailEt);
        back=findViewById(R.id.ivback);
        reenterpasswordet = findViewById(R.id.passwordagainEt);
        logInBtn.setOnClickListener(onClickListener);
        back.setOnClickListener(onClickListener);
        Typeface typeface = ResourcesCompat.getFont(getBaseContext(), R.font.acme);
        usernameEt.setTypeface(typeface);
        passwordEt.setTypeface(typeface);
        usernameEt.addTextChangedListener(usernameTextWatcher);
        passwordEt.addTextChangedListener(passwordTextWatcher);
    }
}