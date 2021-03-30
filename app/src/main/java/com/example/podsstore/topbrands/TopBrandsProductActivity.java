package com.example.podsstore.topbrands;

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
import com.example.podsstore.category.SubCategoryProductActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.TopBrandsProductResponse;
import com.example.podsstore.productdetails.ProductDetailsActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class TopBrandsProductActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TopBrandsProductAdapter productListAdapter;
    ProgressBar progressBar;
    TextView progresstext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_brands);
        progressBar=findViewById(R.id.progress);
        progresstext=findViewById(R.id.progresstext);
        recyclerView = findViewById(R.id.productrv);
        productListAdapter = new TopBrandsProductAdapter(TopBrandsProductActivity.this);
        recyclerView = findViewById(R.id.productrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(TopBrandsProductActivity.this));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Top Brands Products");
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
        progressBar.setVisibility(View.VISIBLE);
        progresstext.setVisibility(View.VISIBLE);
        //Toast.makeText(getApplicationContext(),getIntent().getStringExtra("userid"),Toast.LENGTH_SHORT).show();
        Log.e( "loadData: ",getIntent().getStringExtra("userid") );
        ApiClient.getApiClient().gettopbrandproduct(getIntent().getStringExtra("userid"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<TopBrandsProductResponse>>>() {
                    @Override
                    public void onSuccess(Response<List<TopBrandsProductResponse>> response) {
                        progressBar.setVisibility(View.GONE);
                        progresstext.setVisibility(View.GONE);
                        // binding.progress.setVisibility(View.GONE);
                        Log.d("onSuccess: ",String.valueOf(response.code()));
                        if (response.isSuccessful()) {
                            List<TopBrandsProductResponse> list = response.body();
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

    private TopBrandsProductAdapter.AdapterListener adapterListener = data -> {
        // Toast.makeText(getApplicationContext(), data.getId().toString()+"///"+data.getCatid().toString(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(TopBrandsProductActivity.this, ProductDetailsActivity.class);
        i.putExtra("userid", data.getId().toString());
        i.putExtra("catid",data.getCatid().toString());
        i.putExtra("userids",getIntent().getStringExtra("userid"));
        startActivity(i);


    };
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
}