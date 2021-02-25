package com.example.podsstore.addtocart;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.AddressDetailsRequest;
import com.example.podsstore.data.request.AddtocartRequest;
import com.example.podsstore.data.request.PlaceOrderRequest;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.prefs.PreferenceManager;
import com.example.podsstore.prefs.Preferences;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Payment");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

              Intent intent=new Intent(getApplicationContext(),SelectAddressActivity.class);
              startActivity(intent);
              finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(),SelectAddressActivity.class);
        startActivity(intent);
        finish();
    }

    @SuppressLint("CheckResult")
    private void smallCarton(String orderid,String productid,String productname,String productimage,String qty,String totalprice,String subtotal) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        List<PlaceOrderRequest> list = new ArrayList<>();

        PlaceOrderRequest r = new PlaceOrderRequest();
        r.setOrderid(orderid);
        r.setProductid(productid);
        r.setProductname(productname);
        r.setProductimage(productimage);
        r.setQuantity(qty);
        r.setTotalprice(totalprice);
        r.setSubtotal(subtotal);
       ;

        // list.add(r);




        Log.e("postData", new Gson().toJson(r));

        ApiClient.getApiClient(). placeOrder(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN),PreferenceManager.getStringValue(Preferences.USER_EMAIL),r)
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


}