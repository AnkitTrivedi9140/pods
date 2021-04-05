package com.example.podsstore.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.SplashActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.CreateLoginUserRequest;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CreateAccountActivity extends AppCompatActivity {
    private Button logInBtn,btncontinue;
TextView signintv,tvduns,skiptv;
    RelativeLayout rlaccountconfirmation;
    CheckBox checkBoxterms;
    private EditText usernameEt, passwordEt, emaiEt,reenterpasswordet,numberofemployeeEt,turnoveret;
    ImageView back,tvicon;
    String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        getSupportActionBar().hide();

        initViews();
        Glide.with(this).load(R.drawable.podgif).into(tvicon);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.logInBtn:
                    String username = usernameEt.getText().toString();
                    String email=emaiEt.getText().toString();
                    String password = passwordEt.getText().toString();
                    String reenterpassword = reenterpasswordet.getText().toString();

                    String emp = numberofemployeeEt.getText().toString();
                    String turn = turnoveret.getText().toString();
                    //Toast.makeText(getApplicationContext(),username,Toast.LENGTH_LONG).show();
//                    Intent login = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(login);
//                    finish();

                if (TextUtils.isEmpty(username)) {
                        usernameEt.setError("Name Can't Blank!");
                    } else if (TextUtils.isEmpty(email)) {
                        emaiEt.setError("Email Address Can't Blank!");

                    }

                else if (TextUtils.isEmpty(password)) {
                        passwordEt.setError("Password Can't Blank!");

                    }
                    else if (TextUtils.isEmpty(reenterpassword)) {
                        reenterpasswordet.setError("Re_password Can't Blank!");

                    }

                else if (TextUtils.isEmpty(emp)) {
                    passwordEt.setError("Employees Can't Blank!");

                }
                else if (TextUtils.isEmpty(turn)) {
                    reenterpasswordet.setError("Turnover Can't Blank!");

                }
                    /*else if(passwordEt.getText().toString()!=reenterpasswordet.getText().toString()){
                    Toast.makeText(getApplicationContext(),"Please add same password.",Toast.LENGTH_LONG).show();
                }*/else{
                    if(checkBoxterms.isChecked()==false){

                        Toast.makeText(getApplicationContext(),"Please click on checkBox to continue.",Toast.LENGTH_LONG).show();




                    }
                   else {
                if(passwordEt.getText().toString().equals(reenterpasswordet.getText().toString())){

                     if (email.matches(regex)) {

                         smallCarton(username,email,password,reenterpassword);
                    }else {
                         emaiEt.setError("Email Address not correct!");
                     }


                                  }else {
                    Toast.makeText(getApplicationContext(),"Please add same password.",Toast.LENGTH_LONG).show();

                }

                                }

                    }

                    break;

                case R.id.ivback:

                    Intent login = new Intent(CreateAccountActivity.this, SplashActivity.class);
                    startActivity(login);
                    finish();


                    break;
                case R.id.tvduns:

                   Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");
                    startActivityForResult(intent, 7);

                    break;

                case R.id.skiptv:

                    Intent skip = new Intent(CreateAccountActivity.this, MainActivity.class);
                    startActivity(skip);
                    finish();

                    break;

                case R.id.btncontinue:

                    Intent logins = new Intent(CreateAccountActivity.this, LoginActivity.class);
                    startActivity(logins);
                    finish();


                    break;
                case R.id.signintv:

                    Intent  signin = new Intent(CreateAccountActivity.this, LoginActivity.class);
                    startActivity(signin);
                    finish();


                    break;
            }

        }
    };

    @SuppressLint("CheckResult")
    private void smallCarton(String username,String useremail,String password,String passwordagin) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        List<CreateLoginUserRequest> list = new ArrayList<>();

        CreateLoginUserRequest r = new CreateLoginUserRequest();
        r.setUsername(username);
        r.setUseremail(useremail);
        r.setPassword(password);
        r.setReenterpassword(passwordagin);
        list.add(r);




        Log.e("postData", new Gson().toJson(r));

        ApiClient.getApiClient(). createuserregister(r)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<CreateLoginUserResponse>>() {
                    @Override
                    public void onSuccess(Response<CreateLoginUserResponse> response) {

                        // binding.progressbar.setVisibility(View.GONE);


                        Log.e("onSuccess", String.valueOf(response.code()));
                        if (response.isSuccessful()) {

                            CreateLoginUserResponse successResponse = response.body();
                            Toast.makeText(getApplicationContext(), successResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                            Intent login = new Intent(CreateAccountActivity.this, SplashActivity.class);
//                            startActivity(login);
//                            finish();

                                  rlaccountconfirmation.setVisibility(View.VISIBLE);
//                            Log.e("onSuccessaa", successResponse.getChallanid());
                            if (successResponse != null) {

//                                if (successResponse.getMessage().equals("success")) {
//                                    // mappingAdapter.clear();
//
//                                }

                              //  Toaster.show(mContext, successResponse.getMessage());

                            }
                        } else {
                         Toast.makeText(getApplicationContext(), "server error!", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                   Log.e("onError: " , e.getMessage());
                        Toast.makeText(getApplicationContext(), "server error", Toast.LENGTH_SHORT).show();
                        rlaccountconfirmation.setVisibility(View.GONE);

                    }
                });
    }

    private void initViews() {
        rlaccountconfirmation = findViewById(R.id.rlaccountconfirmation);
        logInBtn = findViewById(R.id.logInBtn);

        skiptv = findViewById(R.id.skiptv);

        btncontinue = findViewById(R.id.btncontinue);
        usernameEt = findViewById(R.id.usernameEt);
        passwordEt = findViewById(R.id.passwordEt);
        numberofemployeeEt = findViewById(R.id.numberofemployeeEt);
        turnoveret = findViewById(R.id.turnoverEt);
        tvduns = findViewById(R.id.tvduns);
        emaiEt = findViewById(R.id.emailEt);
        signintv = findViewById(R.id.signintv);
        checkBoxterms = findViewById(R.id.checkBoxterms);
        back=findViewById(R.id.ivback);
        tvicon=findViewById(R.id.tvicon);
        reenterpasswordet = findViewById(R.id.passwordagainEt);
        logInBtn.setOnClickListener(onClickListener);
        btncontinue.setOnClickListener(onClickListener);
        back.setOnClickListener(onClickListener);
        signintv.setOnClickListener(onClickListener);
        tvduns.setOnClickListener(onClickListener);

        skiptv.setOnClickListener(onClickListener);
//        Typeface typeface = ResourcesCompat.getFont(getBaseContext(), R.font.acme);
//        usernameEt.setTypeface(typeface);
//        passwordEt.setTypeface(typeface);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),SplashActivity.class);
        startActivity(i);
        finish();
    }
}