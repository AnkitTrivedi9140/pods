package com.example.podsstore.getorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.data.response.OrderResponse;
import com.example.podsstore.drower.HelpAndFAQActivity;
import com.example.podsstore.mainactivityadapters.MyOrderAdapter;
import com.example.podsstore.prefs.PreferenceManager;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.wishlist.WishListActivity;
import com.example.podsstore.wishlist.WishListAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyOrderAdapter productListAdapter;
    TextView tvnodata;
    ProgressBar progressBar;
    TextView progresstext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Order");
        recyclerView = findViewById(R.id.productrv);
        tvnodata = findViewById(R.id.tvnodata);
        productListAdapter = new MyOrderAdapter(MyOrderActivity.this);
        progressBar=findViewById(R.id.progress);
        progresstext=findViewById(R.id.progresstext);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyOrderActivity.this));
//      recyclerView.setEmptyView(binding.emptyView);
        //  productListAdapter.setAdapterListener(adapterListener);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
//        recyclerView.setLayoutManager(gridLayoutManager);
//        productListAdapter.setAdapterListener(adapterListener);
//        productListAdapter.setAdapterListeners(listener);
        recyclerView.setAdapter(productListAdapter);
loadData();
    }

    @SuppressLint("CheckResult")
    private void loadData() {
        progressBar.setVisibility(View.VISIBLE);
        progresstext.setVisibility(View.VISIBLE);
        Log.e("getssss", PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN)+"///"+PreferenceManager.getStringValue(Preferences.USER_EMAIL));

        ApiClient.getApiClient().getplaceorder(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN),PreferenceManager.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<List<OrderResponse>>() {
            @Override
            public void onResponse(Call<List<OrderResponse>> call, Response<List<OrderResponse>> response) {
                progressBar.setVisibility(View.GONE);
                progresstext.setVisibility(View.GONE);
                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("cartaaa",String.valueOf(response.code()) );
                if (response.isSuccessful()) {
                    List<OrderResponse> list = response.body();
                    productListAdapter.addAll(list);
                    getSupportActionBar().setTitle("Your Order List"+" ("+list.size()+")");
                    int totalPrice = 0;
                    for (int i = 0; i < list.size(); i++) {
                       // totalPrice += list.get(i).getPrice();
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
            public void onFailure(Call<List<OrderResponse>> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
                progressBar.setVisibility(View.GONE);
                progresstext.setVisibility(View.GONE);
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
    }}