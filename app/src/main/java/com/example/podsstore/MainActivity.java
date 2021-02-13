package com.example.podsstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.aboutpod.AboutActivity;
import com.example.podsstore.category.CategoryActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.BestSellingProductResponse;
import com.example.podsstore.data.response.BusinessCatResponse;
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.mainactivityadapters.BestSellingProductAdapter;
import com.example.podsstore.mainactivityadapters.ProductHorizontalAdapter;
import com.example.podsstore.prefs.PreferenceManager;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.product.ProductListActivity;
import com.example.podsstore.product.ProductListAdapter;
import com.example.podsstore.productdetails.ProductDetailsActivity;
import com.example.podsstore.profile.ProfileActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView toproduct;
    RadioGroup radioGroup1;
    RadioButton home, categories, profile, about;
    private RecyclerView recyclerView,bestsellingproductrv,bestprisedproductrv;
    private ProductHorizontalAdapter productListAdapter;
    private BestSellingProductAdapter bestSellingProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Pod");
        getSupportActionBar().setElevation(0);
        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        about = (RadioButton) findViewById(R.id.about);
        home = (RadioButton) findViewById(R.id.homes);
        categories = (RadioButton) findViewById(R.id.categories);
        profile = (RadioButton) findViewById(R.id.profile);
        home.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.bluehome, 0, 0);
        home.setTextColor(Color.parseColor("#007eff"));
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Intent in;
                Log.i("matching", "matching inside1 bro" + checkedId);
                switch (checkedId) {
                    case R.id.homes:
                        Log.i("matching", "matching inside1 matching" + checkedId);
                        in = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(in);

                        break;
                    case R.id.categories:
                        Log.i("matching", "matching inside1 watchlistAdapter" + checkedId);

                        in = new Intent(getBaseContext(), CategoryActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);

                        break;
                    case R.id.profile:
                        Log.i("matching", "matching inside1 rate" + checkedId);

                        in = new Intent(getBaseContext(), ProfileActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.about:
                        Log.i("matching", "matching inside1 deals" + checkedId);
                        in = new Intent(getBaseContext(), AboutActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                    default:
                        break;
                }
            }
        });
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagers
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManagerss
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        bestprisedproductrv= findViewById(R.id.bestprisedproductrv);
        bestsellingproductrv = findViewById(R.id.bestsellingproductrv);
        recyclerView = findViewById(R.id.productrv);
        bestSellingProductAdapter = new BestSellingProductAdapter(MainActivity.this);
        productListAdapter = new ProductHorizontalAdapter(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);


        bestsellingproductrv.setLayoutManager(layoutManagers);
        bestprisedproductrv.setLayoutManager(layoutManagerss);
//      recyclerView.setEmptyView(binding.emptyView);
        productListAdapter.setAdapterListener(adapterListener);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 8);
//        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
//        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(productListAdapter);
bestsellingproductrv.setAdapter(bestSellingProductAdapter);
        bestprisedproductrv.setAdapter(bestSellingProductAdapter);
       productlist();
loadDatabestselling();
loadDatabestprisedproduct();
    }

    @SuppressLint("CheckResult")
    private void productlist() {
        // binding.progress.setVisibility(View.VISIBLE);
        Log.e("getProductMasterssss", PreferenceManager.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN));
        ApiClient.getApiClient().getproducts(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<ProductResponse>>>() {
                    @Override
                    public void onSuccess(Response<List<ProductResponse>> response) {
                        // binding.progress.setVisibility(View.GONE);
                        Log.d("onSuccess: ", String.valueOf(response.code()));
                        if (response.isSuccessful()) {
                            List<ProductResponse> list = response.body();
                            Log.e("getProduct", String.valueOf(list.size()));
                            productListAdapter.addAll(list);


                        } else {

                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_network_msg), Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("geterre", String.valueOf(e.getMessage()));

                    }
                });
    }

    private ProductHorizontalAdapter.AdapterListener adapterListener = data -> {
        // Toast.makeText(getApplicationContext(), data.getImageurl(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, ProductDetailsActivity.class);
        i.putExtra("userid", data.getId().trim());
        startActivity(i);


    };

    @SuppressLint("CheckResult")
    private void loadDatabestselling() {
        // binding.progress.setVisibility(View.VISIBLE);
        ApiClient.getApiClient().getbestsellingproducts(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<BestSellingProductResponse>>>() {
                    @Override
                    public void onSuccess(Response<List<BestSellingProductResponse>> response) {
                        // binding.progress.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
                            List<BestSellingProductResponse> list = response.body();
                            Log.e("getbestseddd", String.valueOf(list.toString()));
                       bestSellingProductAdapter.addAll(list);

                        } else {

                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_network_msg), Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("geterror", String.valueOf(e.getMessage()));

                    }
                });
    }



    @SuppressLint("CheckResult")
    private void loadDatabestprisedproduct() {
        // binding.progress.setVisibility(View.VISIBLE);
        ApiClient.getApiClient().getbestpricedproduct(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<BestSellingProductResponse>>>() {
                    @Override
                    public void onSuccess(Response<List<BestSellingProductResponse>> response) {
                        // binding.progress.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
                            List<BestSellingProductResponse> list = response.body();
                            Log.e("getbestprised", String.valueOf(list.toString()));
                            bestSellingProductAdapter.addAll(list);

                        } else {

                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_network_msg), Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("geterror", String.valueOf(e.getMessage()));

                    }
                });
    }
}
