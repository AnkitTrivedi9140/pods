package com.example.podsstore.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.SplashActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.CreateLoginUserRequest;
import com.example.podsstore.data.request.NotificationRequest;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.notification.ApiClientNoti;
import com.example.podsstore.profile.ProfileActivity;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.hbb20.CountryCodePicker;

import org.json.JSONException;
import org.json.JSONObject;

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
    private EditText usernameEt, passwordEt, emaiEt,reenterpasswordet,phoneEt,companyEt;
    ImageView back,tvicon;
    String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    String regexphone ="\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
    AlertDialog alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        getSupportActionBar().hide();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        initViews();
       // Glide.with(this).load(R.drawable.podgif).into(tvicon);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.logInBtn:
                  // showAlertDialog();

                    String email=emaiEt.getText().toString();
                    String password = passwordEt.getText().toString();
                    String reenterpassword = reenterpasswordet.getText().toString();
                    String phone = phoneEt.getText().toString();
                    String company = companyEt.getText().toString();
                    //Toast.makeText(getApplicationContext(),username,Toast.LENGTH_LONG).show();
//                    Intent login = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(login);
//                    finish();

                if (TextUtils.isEmpty(email)) {
                        emaiEt.setError("Email Address Can't Blank!");

                    }

                else if (TextUtils.isEmpty(password)) {
                        passwordEt.setError("Password Can't Blank!");

                    }
                else if (TextUtils.isEmpty(phone)) {
                    phoneEt.setError("Phone Can't Blank!");

                }

                else if (TextUtils.isEmpty(company)) {
                    companyEt.setError("Company Can't Blank!");

                }
                    else if (TextUtils.isEmpty(reenterpassword)) {
                        reenterpasswordet.setError("Re_password Can't Blank!");

                    }
                    else{
                    if(checkBoxterms.isChecked()==false){

                        Toast.makeText(getApplicationContext(),"Please click on checkBox to continue.",Toast.LENGTH_LONG).show();




                    }
                   else {
                if(passwordEt.getText().toString().equals(reenterpasswordet.getText().toString())) {


                    if (phone.toString().trim().length()<6) {
                        phoneEt.setError("Phone number is not correct!");

                    }else{
                        if (email.matches(regex)) {
                            showAlertDialog();

                        }else {
                            emaiEt.setError("Email Address not correct!");
                        }
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

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateAccountActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.radiodialogpopup, null);


        alertDialog.setView(customLayout);
        TextView  btnsave = (TextView) customLayout.findViewById(R.id.tvreg);
        ImageView cut=customLayout.findViewById(R.id.ivcut);
RadioGroup radioGroup=customLayout.findViewById(R.id.radioGroup);


    alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);

        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                RadioButton  radioButton = (RadioButton)customLayout. findViewById(selectedId);
               /* Toast.makeText(CreateAccountActivity.this,
                       radioButton.getText().toString(), Toast.LENGTH_SHORT).show();*/



                String email=emaiEt.getText().toString();
                String password = passwordEt.getText().toString();
                String reenterpassword = reenterpasswordet.getText().toString();
                String phone = phoneEt.getText().toString();
                String company = companyEt.getText().toString();
                String businesstype=radioButton.getText().toString().trim();
                if(businesstype==null){
                    Toast.makeText(CreateAccountActivity.this,
                            "Please select one", Toast.LENGTH_SHORT).show();
                }else {
                    smallCarton(email,phone,company,"buyer",businesstype,password,reenterpassword);

                }




            }
        });
        alert.show();
    }
    @SuppressLint("CheckResult")
    private void smallCarton(String useremail,String phone,String company,String usertype,String businesstype,String password,String passwordagin) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        List<CreateLoginUserRequest> list = new ArrayList<>();

        CreateLoginUserRequest r = new CreateLoginUserRequest();
        //r.setUsername(username);
        r.setUseremail(useremail);
        r.setPhoneno(phone);
        r.setCompanyname(company);
        r.setUsertype(usertype);
        r.setBusinesstype(businesstype);
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
                                   regNoti();
                            //                            Intent login = new Intent(CreateAccountActivity.this, SplashActivity.class);
//                            startActivity(login);
//                            finish();
                             alert.dismiss();

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
                         Toast.makeText(getApplicationContext(), "Please check your email id...", Toast.LENGTH_SHORT).show();

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
    @SuppressLint("CheckResult")
    private void regNoti() {

                List<NotificationRequest> list = new ArrayList<>();

        NotificationRequest r = new NotificationRequest();

            r.setGcmtoken(FirebaseInstanceId.getInstance().getToken());

            list.add(r);




            Log.e("postData", new Gson().toJson(r));

            ApiClientNoti.getApiClients().createuserregisternotification(r)
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



//    public void postData() {
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        JSONObject object = new JSONObject();
//        try {
//            //input your API parameters
//            object.put("parameter","value");
//            object.put("parameter","value");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        // Enter the correct url for your api service site
//        String url = "getResources().getString(R.string.url)";
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, object,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                       // resultTextView.setText("String Response : "+ response.toString());
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //resultTextView.setText("Error getting response");
//            }
//        });
//        requestQueue.add(jsonObjectRequest);
//    }

    private void initViews() {
        rlaccountconfirmation = findViewById(R.id.rlaccountconfirmation);
        logInBtn = findViewById(R.id.logInBtn);

        skiptv = findViewById(R.id.skiptv);
        companyEt = findViewById(R.id.companyEt);
        phoneEt = findViewById(R.id.phoneEt);
        btncontinue = findViewById(R.id.btncontinue);
        usernameEt = findViewById(R.id.usernameEt);
        passwordEt = findViewById(R.id.passwordEt);

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