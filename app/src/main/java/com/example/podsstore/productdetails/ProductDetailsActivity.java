package com.example.podsstore.productdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.prefs.PreferenceManager;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.product.ProductListActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {
ImageView ivproduct;
TextView tvProductname,tvProductprice,tvdetails,tvfeature,tvfunction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Product Details");
        ivproduct=findViewById(R.id.ivproduct);
        tvProductname=findViewById(R.id.tvProductname);
        tvProductprice=findViewById(R.id.tvProductprice);
        tvdetails=findViewById(R.id.tvdetails);
        tvfeature=findViewById(R.id.tvfeature);
        tvfunction=findViewById(R.id.tvfunction);
        loadData();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(getApplicationContext(), ProductListActivity.class);
                startActivity(intent);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("CheckResult")
    private void loadData() {

        Log.e("getssss",PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN)+getIntent().getStringExtra("userid") );

        ApiClient.getApiClient().getproductsdetails(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN),getIntent().getStringExtra("userid")).enqueue(new Callback<List<ProductResponse>>() {
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
                        tvProductprice.setText("$_"+list.get(i).getPrice());
                        tvdetails.setText(list.get(i).getDescription());
                        tvfeature.setText(list.get(i).getFeature());
                        tvfunction.setText(list.get(i).getFunctions());
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

}