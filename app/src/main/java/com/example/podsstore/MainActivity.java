package com.example.podsstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.aboutpod.AboutActivity;
import com.example.podsstore.addtocart.AddToCartActivity;
import com.example.podsstore.category.CategoryActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.BestSellingProductResponse;
import com.example.podsstore.data.response.BusinessCatResponse;
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.mainactivityadapters.BestSellingProductAdapter;
import com.example.podsstore.mainactivityadapters.CategoryHorigentalAdapter;
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
    private CategoryHorigentalAdapter productListAdapter;
    private BestSellingProductAdapter bestSellingProductAdapter;
private ImageView ivallproduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Pod");
        getSupportActionBar().setElevation(0);
       // getMenuInflater().inflate(R.menu.main_menu, menu);
        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        about = (RadioButton) findViewById(R.id.about);
        home = (RadioButton) findViewById(R.id.homes);
        categories = (RadioButton) findViewById(R.id.categories);
        profile = (RadioButton) findViewById(R.id.profile);
        ivallproduct =  findViewById(R.id.ivallproduct);
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
        productListAdapter = new CategoryHorigentalAdapter(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);


        bestsellingproductrv.setLayoutManager(layoutManagers);
        bestprisedproductrv.setLayoutManager(layoutManagerss);
//      recyclerView.setEmptyView(binding.emptyView);
        productListAdapter.setAdapterListener(adapterListener);
        bestSellingProductAdapter.setAdapterListener(adapterListeners);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 8);
//        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
//        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(productListAdapter);
bestsellingproductrv.setAdapter(bestSellingProductAdapter);
        bestprisedproductrv.setAdapter(bestSellingProductAdapter);
        categorieslist();
loadDatabestselling();
loadDatabestprisedproduct();
isStoragePermissionGranted();
        ivallproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent category=new Intent(MainActivity.this,CategoryActivity.class);
                startActivity(category);
                finish();
            }
        });
    }
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {

                Log.v("TAGss","Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v("TAG","Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }
    @SuppressLint("CheckResult")
    private void categorieslist() {
        // binding.progress.setVisibility(View.VISIBLE);
        // binding.progress.setVisibility(View.VISIBLE);
        ApiClient.getApiClient().getbusinesscat(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<BusinessCatResponse>>>() {
                    @Override
                    public void onSuccess(Response<List<BusinessCatResponse>> response) {
                        // binding.progress.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
                            List<BusinessCatResponse> list = response.body();
                            Log.e("getProductMasters", String.valueOf(list.size()));
                            productListAdapter.addAll(list);

                        } else {

                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_network_msg), Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {


                    }
                });
    }

    private CategoryHorigentalAdapter.AdapterListener adapterListener = data -> {
        // Toast.makeText(getApplicationContext(), data.getImageurl(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, ProductListActivity.class);
        i.putExtra("userid", data.getId());
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
    private BestSellingProductAdapter.AdapterListener adapterListeners = data -> {
        // Toast.makeText(getApplicationContext(), data.getImageurl(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, ProductDetailsActivity.class);
        i.putExtra("userid", data.getId().trim());
        startActivity(i);


    };


    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch(item.getItemId()){
            case R.id.menu_item:   //this item has your app icon
                Intent intent=new Intent(getApplicationContext(), AddToCartActivity.class);
                startActivity(intent);
                finish();

                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        menu.findItem(R.id.menu_item).setEnabled(true);

        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }
}
