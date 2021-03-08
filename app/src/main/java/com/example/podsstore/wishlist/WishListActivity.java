package com.example.podsstore.wishlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.addtocart.AddtocartAdapter;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.prefs.PreferenceManager;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.product.ProductListActivity;
import com.example.podsstore.product.ProductListAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishListActivity extends AppCompatActivity {
RecyclerView recyclerView;
    WishListAdapter productListAdapter;
    TextView tvnodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Your Wishlist");
        recyclerView = findViewById(R.id.productrv);
        productListAdapter = new WishListAdapter(WishListActivity.this);
        tvnodata = findViewById(R.id.tvnodata);
        recyclerView.setLayoutManager(new LinearLayoutManager(WishListActivity.this));
//      recyclerView.setEmptyView(binding.emptyView);
      //  productListAdapter.setAdapterListener(adapterListener);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
//        recyclerView.setLayoutManager(gridLayoutManager);
        productListAdapter.setAdapterListener(adapterListener);
        productListAdapter.setAdapterListeners(listener);
        recyclerView.setAdapter(productListAdapter);

        loadData();
    }
    @SuppressLint("CheckResult")
    private void loadData() {

        Log.e("getssss", PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN)+"///"+PreferenceManager.getStringValue(Preferences.USER_EMAIL));

        ApiClient.getApiClient().getwishlist(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN),PreferenceManager.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<List<CartResponse>>() {
            @Override
            public void onResponse(Call<List<CartResponse>> call, Response<List<CartResponse>> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("cartaaa",String.valueOf(response.code()) );
                if (response.isSuccessful()) {
                    List<CartResponse> list = response.body();
                    productListAdapter.addAll(list);
                    getSupportActionBar().setTitle("Your Wishlist"+" ("+list.size()+")");
                    int totalPrice = 0;
                    for (int i = 0; i < list.size(); i++) {
                        totalPrice += list.get(i).getPrice();
                        //Log.e("onResponses", list.get(i).getPrice().toString());
//                        tvsubtotaltxt.setText(String.valueOf(totalPrice));
//                        tvtotaltxt.setText(String.valueOf(totalPrice));

                    }
                    if(  list.isEmpty()){
                        tvnodata.setVisibility(View.VISIBLE);
                    }else{
                        tvnodata.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CartResponse>> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
    private WishListAdapter.AdapterListener adapterListener = data -> {

deletewishlish(data.getProductid().toString());


    };
    private WishListAdapter.InventoryAdapterListener listener = data -> {

        movetoCart(data.getProductid().toString());


    };
    @SuppressLint("CheckResult")
    private void deletewishlish(String mobilenumber) {

        Log.e("getfdfd", PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN)+PreferenceManager.getStringValue(Preferences.USER_EMAIL)
        );

        ApiClient.getApiClient().deletewishlist(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN),PreferenceManager.getStringValue(Preferences.USER_EMAIL),mobilenumber).enqueue(new Callback<CreateLoginUserResponse>() {
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
    @SuppressLint("CheckResult")
    private void movetoCart(String mobilenumber) {

        Log.e("getfdfd", PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN)+PreferenceManager.getStringValue(Preferences.USER_EMAIL)
        );

        ApiClient.getApiClient().movetocart(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN),PreferenceManager.getStringValue(Preferences.USER_EMAIL),mobilenumber).enqueue(new Callback<CreateLoginUserResponse>() {
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
                else{
                    Toast.makeText(getApplicationContext(),"Item already in cart",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<CreateLoginUserResponse> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
            }
        });
    }

}