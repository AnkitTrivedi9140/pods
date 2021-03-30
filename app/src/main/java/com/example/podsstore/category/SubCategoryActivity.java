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
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
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
    ProgressBar progressBar;
    TextView progresstext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        progressBar=findViewById(R.id.progress);
        progresstext=findViewById(R.id.progresstext);
        recyclerView = findViewById(R.id.productrv);
        productListAdapter = new SubCategoryAdapter(SubCategoryActivity.this);
        recyclerView = findViewById(R.id.productrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(SubCategoryActivity.this));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("subcategory"));
//      recyclerView.setEmptyView(binding.emptyView);
      productListAdapter.setAdapterListener(adapterListener);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(productListAdapter);


        loadData();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Log.e( "onResume: ",getIntent().getStringExtra("userid") );
        ApiClient.getApiClient().getsubcategory(getIntent().getStringExtra("userid"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<SubCategoryResponce>>>() {
                    @Override
                    public void onSuccess(Response<List<SubCategoryResponce>> response) {
                        progressBar.setVisibility(View.GONE);
                        progresstext.setVisibility(View.GONE);
                        // binding.progress.setVisibility(View.GONE);
                        Log.d("onSuccess: ",String.valueOf(response.code()));
                        if (response.isSuccessful()) {
                            List<SubCategoryResponce> list = response.body();
                            Log.e("getProductMasters", String.valueOf(list.size()));
                            productListAdapter.clear();
                            productListAdapter.addAll(list);

                        } else {

                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_network_msg), Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.setVisibility(View.GONE);
                        progresstext.setVisibility(View.GONE);
                        Log.e( "onError: ",e.getMessage() );
                    }
                });
    }

    @SuppressLint("CheckResult")
    private void loadData() {
        progressBar.setVisibility(View.VISIBLE);
        progresstext.setVisibility(View.VISIBLE);
  //Toast.makeText(getApplicationContext(),getIntent().getStringExtra("userid"),Toast.LENGTH_SHORT).show();
        Log.e( "loadData: ",getIntent().getStringExtra("userid") );
        ApiClient.getApiClient().getsubcategory(getIntent().getStringExtra("userid"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<SubCategoryResponce>>>() {
                    @Override
                    public void onSuccess(Response<List<SubCategoryResponce>> response) {
                        progressBar.setVisibility(View.GONE);
                        progresstext.setVisibility(View.GONE);
                        // binding.progress.setVisibility(View.GONE);
                        Log.d("onSuccess: ",String.valueOf(response.code()));
                        if (response.isSuccessful()) {
                            List<SubCategoryResponce> list = response.body();
                            Log.e("getProductMasters", String.valueOf(list.size()));
                            productListAdapter.clear();
                            productListAdapter.addAll(list);

                        } else {

                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_network_msg), Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        progressBar.setVisibility(View.GONE);
                        progresstext.setVisibility(View.GONE);
                        Log.e( "onError: ",e.getMessage() );
                    }
                });
    }

    private SubCategoryAdapter.AdapterListener adapterListener = data -> {
      // Toast.makeText(getApplicationContext(), data.getId().toString()+"///"+data.getCatid().toString(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(SubCategoryActivity.this, SubCategoryProductActivity.class);
        i.putExtra("userid", data.getId().toString());
        i.putExtra("catid",data.getCatid().toString());
        i.putExtra("userids",getIntent().getStringExtra("userid"));
        i.putExtra("productname",data.getProductname().toString());
        i.putExtra("productnamess",getIntent().getStringExtra("subcategory"));
        startActivity(i);


    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if(getIntent().getStringExtra("cat")==null){
                    Intent i=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent i=new Intent(getApplicationContext(), CategoryActivity.class);

                    startActivity(i);
                    finish();
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getIntent().getStringExtra("cat")==null){
            Intent i=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        }else{
            Intent i=new Intent(getApplicationContext(), CategoryActivity.class);
            startActivity(i);
            finish();
        }

    }
}