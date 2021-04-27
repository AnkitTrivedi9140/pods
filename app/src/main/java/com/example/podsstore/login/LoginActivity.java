package com.example.podsstore.login;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.SplashActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.AddressDetailsRequest;
import com.example.podsstore.data.request.ChangePasswordRequest;
import com.example.podsstore.data.request.LoginUserRequest;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.LoginResponse;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getName();
    private Button logInBtn,btncontinue;
    private EditText usernameEt, passwordEt, emaiEt,otpEt;
    private TextView createtv,forgettv;
    private ImageView ivshow;
ImageView back,tvicon;
RelativeLayout rlaccountconfirmation;
    String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        PreferenceManagerss.init(LoginActivity.this);
        if (!PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        //    Toast.makeText(getApplicationContext(),PreferenceManager.getStringValue(Preferences.USER_EMAIL),Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }

        getSupportActionBar().hide();
        initViews();

        Glide.with(this).load(R.drawable.podgif).into(tvicon);
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
                        if (email.matches(regex)) {

                            smallCarton(email,password);
                        }else {
                            emaiEt.setError("Email Address not correct!");
                        }

                    }




                    break;
                case R.id.btncontinue:

                    String otp = otpEt.getText().toString();


                    if (TextUtils.isEmpty(otp)) {
                        emaiEt.setError("OTP Can't Blank!");
                    } else{

                    }
                    confirmotp(otp.trim());



                    break;
                case R.id.createtv:
                    Intent createaccount = new Intent(LoginActivity.this, CreateAccountActivity.class);
                    startActivity(createaccount);
                    finish();
                    break;
                case R.id.forgettv:

showAlertDialog();
               // loadData();
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
    private void confirmotp(String otp) {
        Toast.makeText(getApplicationContext(), PreferenceManagerss.getStringValue(Preferences.USER_OTP_EMAIL) ,Toast.LENGTH_SHORT).show();
        /*Log.e("getfdfd", PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN)+PreferenceManager.getStringValue(Preferences.USER_EMAIL)
        );*/
      //  if (!PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
        ApiClient.getApiClient().confirmotp(PreferenceManagerss.getStringValue(Preferences.USER_OTP_EMAIL) , otp).enqueue(new Callback<CreateLoginUserResponse>() {
        //ApiClient.getApiClient().confirmotp("fff", otp).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<CreateLoginUserResponse> call, Response<CreateLoginUserResponse> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("onResp ",String.valueOf(response.code() ));


                if (response.isSuccessful()) {


                   // Toast.makeText(getApplicationContext(), String.valueOf(response.toString()), Toast.LENGTH_SHORT).show();
                    showAlertDialogconfirm();
                } else {
                    Toast.makeText(getApplicationContext(), "Code is not correct!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateLoginUserResponse> call, Throwable t) {

                Log.e("onerrors", t.getMessage());
            }
        });
    }

    @SuppressLint("CheckResult")
    private void loadData(String emailid) {


        Log.e("getfdfd", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+ PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)
        );
        ApiClient.getApiClient().forgotpassword(emailid).enqueue(new Callback<CreateLoginUserResponse>() {
            @Override
            public void onResponse(Call<CreateLoginUserResponse> call, Response<CreateLoginUserResponse> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getprofile",String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    PreferenceManagerss.setStringValue(Preferences.USER_OTP_EMAIL, emailid);
                    CreateLoginUserResponse list = response.body();

                    Toast.makeText(getApplicationContext(),list.getMessage(),Toast.LENGTH_SHORT).show();

                    rlaccountconfirmation.setVisibility(View.VISIBLE);
                }else {
                    Toast.makeText(getApplicationContext(),"Email ID is not correct",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<CreateLoginUserResponse> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
                rlaccountconfirmation.setVisibility(View.GONE);
            }
        });
    }

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


                        Log.e("onSuccess", String.valueOf(response.code()));
                        if (response.isSuccessful()) {

                            LoginResponse successResponse = response.body();
                         //   Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();

                            PreferenceManagerss.setStringValue(Preferences.USER_EMAIL, response.body().getUserEmailId());
                            PreferenceManagerss.setStringValue(Preferences.TOKEN_TYPE, response.body().getTokenType());
                            PreferenceManagerss.setStringValue(Preferences.ACCESS_TOKEN, response.body().getAccessToken());
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
        tvicon = findViewById(R.id.tvicon);
        ivshow = findViewById(R.id.ivshow);
        logInBtn = findViewById(R.id.logInBtn);
        btncontinue = findViewById(R.id.btncontinue);
        usernameEt = findViewById(R.id.usernameEt);
        passwordEt = findViewById(R.id.passwordEt);
        emaiEt = findViewById(R.id.emailEt);
        otpEt = findViewById(R.id.otpEt);
        createtv = findViewById(R.id.createtv);
        rlaccountconfirmation=findViewById(R.id.rlaccountconfirmation);
        forgettv = findViewById(R.id.forgettv);
        logInBtn.setOnClickListener(onClickListener);
        btncontinue.setOnClickListener(onClickListener);
        forgettv.setOnClickListener(onClickListener);
        back=findViewById(R.id.ivback);
        createtv.setOnClickListener(onClickListener);
        back.setOnClickListener(onClickListener);
        ivshow.setOnClickListener(onClickListener);
//        Typeface typeface = ResourcesCompat.getFont(getBaseContext(), R.font.acme);
//        usernameEt.setTypeface(typeface);
//        passwordEt.setTypeface(typeface);

    }
    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.password_dialog, null);


        alertDialog.setView(customLayout);
        TextView  btnsave = (TextView) customLayout.findViewById(R.id.tvsave);
        ImageView cut=customLayout.findViewById(R.id.ivcut);
        EditText et =customLayout.findViewById(R.id.etmobile);


        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(true);

        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String number = et.getText().toString().trim();
                //Toast.makeText(getApplicationContext(),username,Toast.LENGTH_LONG).show();
//                    Intent login = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(login);
//                    finish();
                if (TextUtils.isEmpty(number)) {
                    et.setError("Email Can't Blank!");
                }else{

                    if(et.getText().toString().matches(regex)){
                        loadData(et.getText().toString().trim());
                        alert.dismiss();
                    }else {
                        et.setError("Email Address Not Correct!");
                    }

                }

            }
        });
        alert.show();
    }
    private void showAlertDialogconfirm() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(LoginActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.confirm_password_dialog, null);


        alertDialog.setView(customLayout);
        TextView  btnsave = (TextView) customLayout.findViewById(R.id.tvsavepwd);
        ImageView cut=customLayout.findViewById(R.id.ivcut);
        EditText etpassword =customLayout.findViewById(R.id.etpassword);
        EditText etagainpassword =customLayout.findViewById(R.id.etagainpassword);

        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(true);

        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String password = etpassword.getText().toString().trim();
                String passwordagain = etagainpassword.getText().toString().trim();
                //Toast.makeText(getApplicationContext(),username,Toast.LENGTH_LONG).show();
//                    Intent login = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(login);
//                    finish();
                if (TextUtils.isEmpty(password)) {
                    etpassword.setError("password Can't Blank!");
                }else if(TextUtils.isEmpty(passwordagain)){
                    etagainpassword.setError("password Can't Blank!");
                }

                 //   loadData(et.getText().toString().trim());
                   else {

changepassword(password,passwordagain);
                }
            }
        });
        alert.show();
    }

    @SuppressLint("CheckResult")
    private void changepassword( String newpwd, String confirmpwd) {
        if(newpwd.equals(confirmpwd)){      List<AddressDetailsRequest> list = new ArrayList<>();

            ChangePasswordRequest r = new ChangePasswordRequest();
            r.setNewpassword(newpwd);
            r.setConfirmpassword(confirmpwd);


            // list.add(r);

            Log.e("postData", new Gson().toJson(r));

            ApiClient.getApiClient().pwdsuccess( PreferenceManagerss.getStringValue(Preferences.USER_OTP_EMAIL), r)
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

//                            Log.e("onSuccessaa", successResponse.getChallanid());

                                finish();
                                overridePendingTransition( 0, 0);
                                startActivity(getIntent());
                                overridePendingTransition( 0, 0);
                                if (successResponse != null) {

//                                if (successResponse.getMessage().equals("success")) {
//                                    // mappingAdapter.clear();
//
//                                }

                                    //  Toaster.show(mContext, successResponse.getMessage());

                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Item already in wishlist", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                            Log.e("onError: ", e.getMessage());
                            Toast.makeText(getApplicationContext(), "server error", Toast.LENGTH_SHORT).show();

                            // binding.progressbar.setVisibility(View.GONE);
                            // NetworkHelper.handleNetworkError(e, mContext);
                        }
                    });}
        else {
            Toast.makeText(getApplicationContext(), "Please add same password.", Toast.LENGTH_LONG).show();

        }
        // binding.progressbar.setVisibility(View.VISIBLE);

    }

}
