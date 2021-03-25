package com.example.podsstore.search;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.productdetails.ProductDetailsActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SearchAdapter productListAdapter;
    TextView tvnodata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Search Result");
        recyclerView = findViewById(R.id.productrv);
        tvnodata = findViewById(R.id.tvnodata);
        productListAdapter = new SearchAdapter(SearchActivity.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
//      recyclerView.setEmptyView(binding.emptyView);
    productListAdapter.setAdapterListener(adapterListener);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(productListAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadData();
    }

    @SuppressLint("CheckResult")
    private void loadData() {
        // binding.progress.setVisibility(View.VISIBLE);
        Log.e("getProductMasterssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN));
        ApiClient.getApiClient().search(getIntent().getStringExtra("data"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<ProductResponse>>>() {
                    @Override
                    public void onSuccess(Response<List<ProductResponse>> response) {
                        // binding.progress.setVisibility(View.GONE);
                        Log.d("onSuccess: ",String.valueOf(response.code()));
                        if (response.isSuccessful()) {
                            List<ProductResponse> list = response.body();
                            Log.e("getProduct", String.valueOf(list.size()));
                            productListAdapter.addAll(list);
                          if(  list.isEmpty()){
                              tvnodata.setVisibility(View.VISIBLE);
                          }else{
                              tvnodata.setVisibility(View.GONE);
                          }


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
    private SearchAdapter.AdapterListener adapterListener = data -> {

        Intent i = new Intent(SearchActivity.this, ProductDetailsActivity.class);
        i.putExtra("userid",String.valueOf(data.getId()));
        i.putExtra("search","search");
        //Toast.makeText(getApplicationContext(), String.valueOf(data.getId()), Toast.LENGTH_SHORT).show();
        startActivity(i);


    };
}