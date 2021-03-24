package com.example.podsstore.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.SubCategoryProductResponce;
import com.example.podsstore.data.response.SubCategoryResponce;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SubCategoryProductActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SubCategoryProductAdapter productListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sub Category Product");
        recyclerView = findViewById(R.id.productrv);
        productListAdapter = new SubCategoryProductAdapter(SubCategoryProductActivity.this);
        recyclerView = findViewById(R.id.productrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(SubCategoryProductActivity.this));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sub Category Products");
//      recyclerView.setEmptyView(binding.emptyView);
        productListAdapter.setAdapterListener(adapterListener);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(productListAdapter);


        loadData();
    }

    @SuppressLint("CheckResult")
    private void loadData() {
        Toast.makeText(getApplicationContext(),getIntent().getStringExtra("userid"),Toast.LENGTH_SHORT).show();
        ApiClient.getApiClient().getproductbycategory(getIntent().getStringExtra("catid"),getIntent().getStringExtra("userid"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<SubCategoryProductResponce>>>() {
                    @Override
                    public void onSuccess(Response<List<SubCategoryProductResponce>> response) {
                        // binding.progress.setVisibility(View.GONE);
                        Log.d("onSuccess: ",String.valueOf(response.code()));
                        if (response.isSuccessful()) {
                            List<SubCategoryProductResponce> list = response.body();
                            Log.e("getProductMasters", String.valueOf(list.size()));
                            productListAdapter.addAll(list);

                        } else {

                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_network_msg), Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e( "onError: ",e.getMessage() );
                    }
                });
    }
    private SubCategoryProductAdapter.AdapterListener adapterListener = data -> {
        // Toast.makeText(getApplicationContext(), data.getId(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(SubCategoryProductActivity.this, SubCategoryProductActivity.class);
        i.putExtra("userid", data.getId());
        i.putExtra("catid",data.getCatid());
        startActivity(i);


    };
}