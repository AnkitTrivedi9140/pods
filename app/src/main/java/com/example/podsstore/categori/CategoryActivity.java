package com.example.podsstore.categori;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.BusinessCatResponse;

import com.example.podsstore.product.ProductListActivity;
import com.example.podsstore.productdetails.ProductDetailsActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CategoryAdapter productListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        recyclerView = findViewById(R.id.productrv);
        productListAdapter = new CategoryAdapter(CategoryActivity.this);
        recyclerView = findViewById(R.id.productrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(CategoryActivity.this));
//      recyclerView.setEmptyView(binding.emptyView);
        productListAdapter.setAdapterListener(adapterListener);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(productListAdapter);

        getSupportActionBar().setTitle("Category");
        loadData();
    }


    @SuppressLint("CheckResult")
    private void loadData() {
        // binding.progress.setVisibility(View.VISIBLE);
        ApiClient.getApiClient().getbusinesscat()
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

    private CategoryAdapter.AdapterListener adapterListener = data -> {
       // Toast.makeText(getApplicationContext(), data.getId(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(CategoryActivity.this, ProductListActivity.class);
        i.putExtra("userid", data.getId().trim());
        startActivity(i);


    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(getApplicationContext(), "GoTo Main", Toast.LENGTH_LONG).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}