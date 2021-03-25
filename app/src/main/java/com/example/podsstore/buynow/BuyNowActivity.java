package com.example.podsstore.buynow;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.podsstore.R;
import com.example.podsstore.addtocart.PaymentActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.AddressDetailsRequest;
import com.example.podsstore.data.request.AddtocartRequest;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.productdetails.ProductDetailsActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyNowActivity extends AppCompatActivity {
    ImageView ivproduct,ivtoggle,ivcart;
    TextView tvProductname,tvProductprice,tvdetails,tvfeature,tvfunction,tvcartsize,tvdetailtitle,tvfeaturetitle,tvfunctiontitle;
    TextView logInBtn,tvbuynow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_now);
        getSupportActionBar().hide();
        PreferenceManagerss.init(BuyNowActivity.this);
        ivproduct=findViewById(R.id.ivproduct);
        ivtoggle=findViewById(R.id.ivtoggle);
        ivcart=findViewById(R.id.ivcart);
        tvcartsize=findViewById(R.id.tvcartsize);
        tvProductname=findViewById(R.id.tvProductname);
        logInBtn=findViewById(R.id.logInBtn);
        tvbuynow=findViewById(R.id.tvsignin);
        tvProductprice=findViewById(R.id.tvProductprice);
        tvdetails=findViewById(R.id.tvdetails);
        tvfeature=findViewById(R.id.tvfeature);
        tvfunction=findViewById(R.id.tvfunction);


        tvdetailtitle=findViewById(R.id.tvdetailtitle);
        tvfeaturetitle=findViewById(R.id.tvfeaturetitle);
        tvfunctiontitle=findViewById(R.id.tvfunctiontitle);

loadData();
    }
    @SuppressLint("CheckResult")
    private void loadData() {

        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+"????"+getIntent().getStringExtra("userid") );

        ApiClient.getApiClient().getproductsdetails(getIntent().getStringExtra("userid")).enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getMaterialMasters",String.valueOf(response.code()) );
                if (response.isSuccessful()) {
                    List<ProductResponse> list = response.body();

                    for (int i = 0; i < list.size(); i++) {
                        Log.e("onResponses", list.get(i).getImageurl());
                        Glide.with(getApplicationContext())
                                .load(list.get(i).getImageurl().trim().toString())
                                .into(ivproduct);
                        tvProductname.setText(list.get(i).getProdname());
                        tvProductprice.setText("$ "+list.get(i).getPrice());
                        tvdetails.setText(list.get(i).getDescription());
                        tvfeature.setText(list.get(i).getFeature());
                        tvfunction.setText(list.get(i).getFunctions());

                        if(tvfunction.getText().toString().length()>4){
                            tvfunctiontitle.setVisibility(View.VISIBLE);
                            tvfunction.setVisibility(View.VISIBLE);
                            // Toast.makeText(getApplicationContext(),tvfunction.getText().toString(),Toast.LENGTH_SHORT).show();
                        } else {
                            tvfunctiontitle.setVisibility(View.GONE);
                            tvfunction.setVisibility(View.GONE);
                        } if (tvfeature.getText().toString().length()>4){
                            tvfeaturetitle.setVisibility(View.VISIBLE);
                            tvfeature.setVisibility(View.VISIBLE);
                        }
                        else {
                            tvfeaturetitle.setVisibility(View.GONE);
                            tvfeature.setVisibility(View.GONE);
                        }
                        if(tvdetails.getText().toString().length()>4){
                            tvdetails.setVisibility(View.VISIBLE);
                            tvdetailtitle.setVisibility(View.VISIBLE);
                        }
                        else{

                            tvdetails.setVisibility(View.GONE);
                            tvdetailtitle.setVisibility(View.GONE);

                            // Toast.makeText(getApplicationContext(),"niotna",Toast.LENGTH_SHORT).show();
                        }


                        int finalI = i;


                        tvbuynow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (!PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
                                    smallCartonbuy(list.get(finalI).getId(),list.get(finalI).getProdname(),Long.parseLong("25"),Long.parseLong("1"));
                                }else{
                                   // showAlertDialog();
                                }


                            }
                        });
                    }
                    if (list != null) {


                    }

                }
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
            }
        });
    }
    @SuppressLint("CheckResult")
    private void smallCartonbuy(Long prodid,String prodname,Long price,Long qty) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        List<AddressDetailsRequest> list = new ArrayList<>();

        AddtocartRequest r = new AddtocartRequest();
        r.setProductid(prodid);
        r.setProductname(prodname);
        r.setPrice(price);
        r.setQuantity(qty);

        // list.add(r);




        Log.e("postData", new Gson().toJson(r));

        ApiClient.getApiClient(). addtocart(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL),r)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<CreateLoginUserResponse>>() {
                    @Override
                    public void onSuccess(Response<CreateLoginUserResponse> response) {
                        CreateLoginUserResponse successResponse = response.body();
                        // binding.progressbar.setVisibility(View.GONE);

                        Intent intent=new Intent(getApplicationContext(), PaymentActivity.class);
                        intent.putExtra("userid",getIntent().getStringExtra("userid"));
                        startActivity(intent);
                        finish();
                        Log.e("onSuccess", String.valueOf(response.code()));
                        if (response.isSuccessful()) {


                            Toast.makeText(getApplicationContext(),successResponse.getMessage(), Toast.LENGTH_SHORT).show();
                           // loadDatacart();

//                            Log.e("onSuccessaa", successResponse.getChallanid());
                            if (successResponse != null) {

//                                if (successResponse.getMessage().equals("success")) {
//                                    // mappingAdapter.clear();
//
//                                }

                                //  Toaster.show(mContext, successResponse.getMessage());

                            }

                        } else {
                            Toast.makeText(getApplicationContext(),"Item already in cart.", Toast.LENGTH_SHORT).show();

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