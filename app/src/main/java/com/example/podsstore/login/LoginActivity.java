package com.example.podsstore.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;


import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.CreateLoginUserRequest;
import com.example.podsstore.data.request.LoginUserRequest;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getName();
    private Button logInBtn;
    private EditText usernameEt, passwordEt, emaiEt;
    private TextView createtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

//        if (UserPreference.getUserId() != null) {
//            startActivity(new Intent(this, MainActivity.class));
//            finish();
//        }
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        initViews();


        //   binding.url.setOnClickListener(onClickListener);
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
                    String username = emaiEt.getText().toString();
                    String password = passwordEt.getText().toString();
                   smallCarton(username,password);
                    finish();

                    break;
                case R.id.createtv:
                    Intent createaccount = new Intent(LoginActivity.this, CreateAccountActivity.class);
                    startActivity(createaccount);
                    finish();
                    break;


            }

        }
    };
    @SuppressLint("CheckResult")
    private void smallCarton(String useremail,String password) {
        // binding.progressbar.setVisibility(View.VISIBLE);

        List<LoginUserRequest> list = new ArrayList<>();

        LoginUserRequest r = new LoginUserRequest();
//        r.setUsername(username);
        r.setUseremail(useremail);
        r.setPassword(password);

        list.add(r);




        Log.e("postData", new Gson().toJson(r));

        ApiClient.getApiClient().userlogin(r)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<CreateLoginUserResponse>>() {
                    @Override
                    public void onSuccess(Response<CreateLoginUserResponse> response) {

                        // binding.progressbar.setVisibility(View.GONE);


                        Log.e("onSuccess", String.valueOf(response.body()));
                        if (response.isSuccessful()) {

                            CreateLoginUserResponse successResponse = response.body();
                            Toast.makeText(getApplicationContext(), "logIn id create Successful -- " +successResponse.getId(), Toast.LENGTH_SHORT).show();

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
        createtv = findViewById(R.id.createtv);
        logInBtn.setOnClickListener(onClickListener);
        createtv.setOnClickListener(onClickListener);
        Typeface typeface = ResourcesCompat.getFont(getBaseContext(), R.font.acme);
        usernameEt.setTypeface(typeface);
        passwordEt.setTypeface(typeface);
        usernameEt.addTextChangedListener(usernameTextWatcher);
        passwordEt.addTextChangedListener(passwordTextWatcher);
    }


}
