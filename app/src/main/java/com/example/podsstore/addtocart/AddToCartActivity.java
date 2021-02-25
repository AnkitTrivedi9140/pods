package com.example.podsstore.addtocart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.category.CategoryActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.AddressDetailsRequest;
import com.example.podsstore.data.request.AddtocartRequest;
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.prefs.PreferenceManager;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.product.ProductListActivity;
import com.example.podsstore.product.ProductListAdapter;
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

public class AddToCartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AddtocartAdapter productListAdapter;
    TextView tvsubtotaltxt,tvtotaltxt;
Button placeorderbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadData();
        tvsubtotaltxt = findViewById(R.id.tvsubtotaltxt);
        tvtotaltxt = findViewById(R.id.tvtotaltxt);
        recyclerView = findViewById(R.id.productrv);
        placeorderbtn = findViewById(R.id.placeorderbtn);
        productListAdapter = new AddtocartAdapter(AddToCartActivity.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(AddToCartActivity.this));
//      recyclerView.setEmptyView(binding.emptyView);
        recyclerView.setAdapter(productListAdapter);
        productListAdapter.setAdapterListener(adapterListener);
     productListAdapter.setAdapterListeners(listener);
        placeorderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), SelectAddressActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @SuppressLint("CheckResult")
    private void loadData() {

        Log.e("getssss", PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN)+"///"+PreferenceManager.getStringValue(Preferences.USER_EMAIL));

        ApiClient.getApiClient().getcartdetails(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN),PreferenceManager.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<List<CartResponse>>() {
            @Override
            public void onResponse(Call<List<CartResponse>> call, Response<List<CartResponse>> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("cartaaa",String.valueOf(response.code()) );
                if (response.isSuccessful()) {
                    List<CartResponse> list = response.body();
                    productListAdapter.addAll(list);
                    getSupportActionBar().setTitle("Cart"+" ("+list.size()+")");
                    int totalPrice = 0;
                    for (int i = 0; i < list.size(); i++) {
                        totalPrice += list.get(i).getPrice();
                        //Log.e("onResponses", list.get(i).getPrice().toString());
                     tvsubtotaltxt.setText(String.valueOf(totalPrice));
                     tvtotaltxt.setText(String.valueOf(totalPrice));

                    }
                    if (list != null) {


                    }

                }
            }

            @Override
            public void onFailure(Call<List<CartResponse>> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
            }
        });
    }
    private int grandTotal(List<CartResponse> items){

        int totalPrice = 0;
        for(int i = 0 ; i < items.size(); i++) {
            totalPrice += items.get(i).getPrice();
        }

        return totalPrice;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                if(getIntent().getStringExtra("main")==null){
                    Intent intent=new Intent(getApplicationContext(), ProductListActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    if(getIntent().getStringExtra("main").equalsIgnoreCase("main")){
                        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else{ Intent intent=new Intent(getApplicationContext(), ProductListActivity.class);
                        startActivity(intent);
                        finish();}

                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getIntent().getStringExtra("main")==null){
            Intent intent=new Intent(getApplicationContext(), ProductListActivity.class);
            startActivity(intent);
            finish();
        }else{
        if(getIntent().getStringExtra("main").equalsIgnoreCase("main")){
            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }else{ Intent intent=new Intent(getApplicationContext(), ProductListActivity.class);
            startActivity(intent);
            finish();}

    }}
    @SuppressLint("CheckResult")
    private void deletecart(String mobilenumber) {

        Log.e("getfdfd", PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN)+PreferenceManager.getStringValue(Preferences.USER_EMAIL)
        );

        ApiClient.getApiClient().deletecart(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN),PreferenceManager.getStringValue(Preferences.USER_EMAIL),mobilenumber).enqueue(new Callback<CreateLoginUserResponse>() {
            @Override
            public void onResponse(Call<CreateLoginUserResponse> call, Response<CreateLoginUserResponse> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getprofile",String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    CreateLoginUserResponse list = response.body();

                    Toast.makeText(getApplicationContext(),list.getMessage(),Toast.LENGTH_SHORT).show();

                    overridePendingTransition( 0, 0);
                    startActivity(getIntent());
                    overridePendingTransition( 0, 0);
                    productListAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<CreateLoginUserResponse> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
            }
        });
    }

    private AddtocartAdapter.AdapterListener adapterListener = data -> {

        deletecart(data.getProductid().toString());


    };
    @SuppressLint("CheckResult")
    private void addtowishlist(String prodid,String prodname,String price,String qty) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        List<AddressDetailsRequest> list = new ArrayList<>();

        AddtocartRequest r = new AddtocartRequest();
        r.setProductid(prodid);
        r.setProductname(prodname);
        r.setPrice(price);
        r.setQuantity(qty);

        // list.add(r);




        Log.e("postData", new Gson().toJson(r));

        ApiClient.getApiClient(). addtowishlist(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN),PreferenceManager.getStringValue(Preferences.USER_EMAIL),r)
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

    private AddtocartAdapter.InventoryAdapterListener listener = data -> {

        addtowishlist(data.getProductid().toString(),data.getProductname(),data.getPrice().toString(),data.getQty().toString());


    };

}