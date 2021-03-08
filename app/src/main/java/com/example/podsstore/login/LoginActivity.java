package com.example.podsstore.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;


import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.SplashActivity;
import com.example.podsstore.category.CategoryActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.LoginUserRequest;
import com.example.podsstore.data.response.LoginResponse;
import com.example.podsstore.prefs.PreferenceManager;
import com.example.podsstore.prefs.Preferences;
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
    private ImageView ivshow;
ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        PreferenceManager.init(LoginActivity.this);
        if (!PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        //    Toast.makeText(getApplicationContext(),PreferenceManager.getStringValue(Preferences.USER_EMAIL),Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }

        getSupportActionBar().hide();
        initViews();


        //   binding.url.setOnClickListener(onClickListener);
    }




    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.logInBtn:
                    String email = emaiEt.getText().toString();
                    String password = passwordEt.getText().toString();

                    if (TextUtils.isEmpty(email)) {
                        emaiEt.setError("Email Address Can't Blank!");
                    } else if (TextUtils.isEmpty(password)) {
                        passwordEt.setError("Password Can't Blank!");

                    }else{
                        smallCarton(email,password);
                    }




                    break;
                case R.id.createtv:
                    Intent createaccount = new Intent(LoginActivity.this, CreateAccountActivity.class);
                    startActivity(createaccount);
                    finish();
                    break;
                case R.id.ivback:

                    Intent login = new Intent(LoginActivity.this, SplashActivity.class);
                    startActivity(login);
                    finish();


                    break;
                case R.id.ivshow:

                    passwordEt.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

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
                .subscribeWith(new DisposableSingleObserver<Response<LoginResponse>>() {
                    @Override
                    public void onSuccess(Response<LoginResponse> response) {

                        // binding.progressbar.setVisibility(View.GONE);


                        Log.e("onSuccess", String.valueOf(response.body()));
                        if (response.isSuccessful()) {

                            LoginResponse successResponse = response.body();
                         //   Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();

                            PreferenceManager.setStringValue(Preferences.USER_EMAIL, response.body().getUserEmailId());
                            PreferenceManager.setStringValue(Preferences.TOKEN_TYPE, response.body().getTokenType());
                            PreferenceManager.setStringValue(Preferences.ACCESS_TOKEN, response.body().getAccessToken());
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                     Toast.makeText(getApplicationContext(), "Please check your email id or password.", Toast.LENGTH_SHORT).show();

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
        ivshow = findViewById(R.id.ivshow);
        logInBtn = findViewById(R.id.logInBtn);
        usernameEt = findViewById(R.id.usernameEt);
        passwordEt = findViewById(R.id.passwordEt);
        emaiEt = findViewById(R.id.emailEt);
        createtv = findViewById(R.id.createtv);
        logInBtn.setOnClickListener(onClickListener);
        back=findViewById(R.id.ivback);
        createtv.setOnClickListener(onClickListener);
        back.setOnClickListener(onClickListener);
        ivshow.setOnClickListener(onClickListener);
//        Typeface typeface = ResourcesCompat.getFont(getBaseContext(), R.font.acme);
//        usernameEt.setTypeface(typeface);
//        passwordEt.setTypeface(typeface);

    }


}
