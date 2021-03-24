package com.example.podsstore.category;

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

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.BusinessCatResponse;
import com.example.podsstore.data.response.SubCategoryResponce;
import com.example.podsstore.product.ProductListActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SubCategoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SubCategoryAdapter productListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        recyclerView = findViewById(R.id.productrv);
        productListAdapter = new SubCategoryAdapter(SubCategoryActivity.this);
        recyclerView = findViewById(R.id.productrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(SubCategoryActivity.this));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sub Category");
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
        ApiClient.getApiClient().getsubcategory(getIntent().getStringExtra("userid"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<SubCategoryResponce>>>() {
                    @Override
                    public void onSuccess(Response<List<SubCategoryResponce>> response) {
                        // binding.progress.setVisibility(View.GONE);
                        Log.d("onSuccess: ",String.valueOf(response.code()));
                        if (response.isSuccessful()) {
                            List<SubCategoryResponce> list = response.body();
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

    private SubCategoryAdapter.AdapterListener adapterListener = data -> {
        // Toast.makeText(getApplicationContext(), data.getId(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(SubCategoryActivity.this, SubCategoryProductActivity.class);
        i.putExtra("userid", data.getId());
        i.putExtra("catid",data.getCatid());
        startActivity(i);


    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i=new Intent(getApplicationContext(), CategoryActivity.class);
                startActivity(i);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(), CategoryActivity.class);
        startActivity(i);
        finish();
    }
}