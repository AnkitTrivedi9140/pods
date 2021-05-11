package com.example.podsstore.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.podsstore.R;
import com.example.podsstore.addtocart.SelectAddressActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.AddressDetailsRequest;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.drower.AddressesActivity;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.google.gson.Gson;
import com.hbb20.CountryCodePicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class AddressActivity extends AppCompatActivity {
private EditText etaddress1,etaddress2,etaddress3,etzipcode,etcountry,etphone,etstate;
private TextView tvsubmit;

    String pinCode;
    CountryCodePicker  countryCodePicker;
    // creating a variable for request queue.

    private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Address Details");
        mRequestQueue = Volley.newRequestQueue(AddressActivity.this);
        countryCodePicker=findViewById(R.id.et1);
        etaddress1=findViewById(R.id.etaddress1);
        etaddress2=findViewById(R.id.etaddress2);

        etaddress3=findViewById(R.id.etaddress3);
        etphone=findViewById(R.id.etphone);
        etstate=findViewById(R.id.etstate);
        etzipcode=findViewById(R.id.etzipcode);
        etcountry=findViewById(R.id.etcountry);
        tvsubmit=findViewById(R.id.tvsubmit);

        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                etcountry.setText(countryCodePicker.getSelectedCountryName().toString());
            }
        });
        tvsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String address1 = etaddress1.getText().toString();
                String address2=etaddress2.getText().toString();
                String address3 = etaddress3.getText().toString();
                String zipcode = etzipcode.getText().toString();
                String country = etcountry.getText().toString();
                String phone = etphone.getText().toString();
                String state = etstate.getText().toString();


                if (TextUtils.isEmpty(address1)) {
                    etaddress1.setError("Address Can't Blank!");
                }
                else if (TextUtils.isEmpty(address2)) {
                    etaddress2.setError("Address Can't Blank!");

                }
                else if (TextUtils.isEmpty(address3)) {
                    etaddress3.setError("Address Can't Blank!");

                } else if (TextUtils.isEmpty(phone)) {
                    etphone.setError("Phone number Can't Blank!");

                }
                else if (TextUtils.isEmpty(zipcode)) {
                    etzipcode.setError("zipcode Can't Blank!");

                } else if (TextUtils.isEmpty(country)) {
                    etcountry.setError("please choose country Dropdown!");

                }
                else if (TextUtils.isEmpty(state)) {
                    etstate.setError("state can't blank!");

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
    /*private void getDataFromPinCode(String pinCode) {

        // clearing our cache of request queue.
        mRequestQueue.getCache().clear();

        // below is the url from where we will be getting
        // our response in the json format.
        String url = "http://www.postalpincode.in/api/pincode/" + pinCode;

        // below line is use to initialize our request queue.
        RequestQueue queue = Volley.newRequestQueue(AddressActivity.this);

        // in below line we are creating a
        // object request using volley.
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // inside this method we will get two methods
                // such as on response method
                // inside on response method we are extracting
                // data from the json format.
                try {
                    // we are getting data of post office
                    // in the form of JSON file.
                    JSONArray postOfficeArray = response.getJSONArray("PostOffice");
                    if (response.getString("Status").equals("Error")) {
                        // validating if the response status is success or failure.
                        // in this method the response status is having error and
                        // we are setting text to TextView as invalid pincode.
                        etzipcode.setText("Pin code is not valid.");
                    } else {
                        // if the status is success we are calling this method
                        // in which we are getting data from post office object
                        // here we are calling first object of our json array.
                        JSONObject obj = postOfficeArray.getJSONObject(0);

                        // inside our json array we are getting district name,
                        // state and country from our data.
                        String district = obj.getString("District");
                        String state = obj.getString("State");
                        String country = obj.getString("Country");

                        // after getting all data we are setting this data in
                        // our text view on below line.
                        etcountry.setText("Details of pin code is : \n" + "District is : " + district + "\n" + "State : "
                                + state + "\n" + "Country : " + country);
                    }
                } catch (JSONException e) {
                    // if we gets any error then it
                    // will be printed in log cat.
                    e.printStackTrace();
                    etcountry.setText("Pin code is not valid");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // below method is called if we get
                // any error while fetching data from API.
                // below line is use to display an error message.
                Toast.makeText(AddressActivity.this, "Pin code is not valid.", Toast.LENGTH_SHORT).show();
                etcountry.setText("Pin code is not valid");
            }
        });
        // below line is use for adding object
        // request to our request queue.
        queue.add(objectRequest);
    }*/
}