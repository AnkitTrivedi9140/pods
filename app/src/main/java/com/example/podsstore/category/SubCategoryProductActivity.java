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
import com.example.podsstore.data.response.SubCategoryProductResponce;
import com.example.podsstore.data.response.SubCategoryResponce;
import com.example.podsstore.productdetails.ProductDetailsActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SubCategoryProductActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SubCategoryProductAdapter productListAdapter;
    ProgressBar progressBar;
    TextView progresstext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category_product);
        progressBar=findViewById(R.id.progress);
        progresstext=findViewById(R.id.progresstext);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("productname"));
        recyclerView = findViewById(R.id.productrv);
        productListAdapter = new SubCategoryProductAdapter(SubCategoryProductActivity.this);
        recyclerView = findViewById(R.id.productrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(SubCategoryProductActivity.this));

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

        Log.d("onResumebackid",getIntent().getStringExtra("catid")+"////"+getIntent().getStringExtra("userid"));

       // Toast.makeText(getApplicationContext(),getIntent().getStringExtra("catid"),Toast.LENGTH_SHORT).show();

     loadData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

      loadData();
    }

    @SuppressLint("CheckResult")
    private void loadData() {
        progressBar.setVisibility(View.VISIBLE);
        progresstext.setVisibility(View.VISIBLE);
       // Toast.makeText(getApplicationContext(),getIntent().getStringExtra("userid"),Toast.LENGTH_SHORT).show();
        ApiClient.getApiClient().getproductbycategory(getIntent().getStringExtra("catid"),getIntent().getStringExtra("userid"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<SubCategoryProductResponce>>>() {
                    @Override
                    public void onSuccess(Response<List<SubCategoryProductResponce>> response) {
                        // binding.progress.setVisibility(View.GONE);
                        progressBar.setVisibility(View.GONE);
                        progresstext.setVisibility(View.GONE);
                        Log.d("onSuccess: ",String.valueOf(response.code()));
                        if (response.isSuccessful()) {
                            List<SubCategoryProductResponce> list = response.body();
                            Log.e("getProductMasters", String.valueOf(list.size()));
                            productListAdapter.clear();
                            productListAdapter.addAll(list);

                        } else {
                            progressBar.setVisibility(View.GONE);
                            progresstext.setVisibility(View.GONE);
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
    private SubCategoryProductAdapter.AdapterListener adapterListener = data -> {
     // Toast.makeText(getApplicationContext(), data.getId().toString(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(SubCategoryProductActivity.this, ProductDetailsActivity.class);
        i.putExtra("userid", data.getId().toString());
        i.putExtra("catid",getIntent().getStringExtra("catid"));
        i.putExtra("prodid",getIntent().getStringExtra("userid"));
        i.putExtra("productname",getIntent().getStringExtra("productname"));
        startActivity(i);


    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
               /* if(getIntent().getStringExtra("userids")==null){
                    Intent i=new Intent(getApplicationContext(), MainActivity.class);

                    startActivity(i);
                    finish();
                }else{
                    Intent i=new Intent(getApplicationContext(), SubCategoryActivity.class);
                    i.putExtra("userid",getIntent().getStringExtra("userids"));
                    i.putExtra("subcategory",getIntent().getStringExtra("productnamess"));
                    startActivity(i);
                    finish();
                }*/
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
       /* if(getIntent().getStringExtra("userids")==null){
            Intent i=new Intent(getApplicationContext(), MainActivity.class);

            startActivity(i);
            finish();
        }
        else if(getIntent().getStringExtra("catid")!=null){
            Intent i=new Intent(getApplicationContext(), SubCategoryActivity.class);
i.putExtra("userid",getIntent().getStringExtra("catid"));
            startActivity(i);
            finish();
        }else{
            Intent i=new Intent(getApplicationContext(), SubCategoryActivity.class);
            i.putExtra("userid",getIntent().getStringExtra("userids"));
            i.putExtra("subcategory",getIntent().getStringExtra("productnamess"));

            startActivity(i);
            finish();
        }
*/
    }
}