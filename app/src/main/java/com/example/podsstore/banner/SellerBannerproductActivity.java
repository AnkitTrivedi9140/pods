package com.example.podsstore.banner;

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
import com.example.podsstore.data.response.TopBrandsProductResponse;
import com.example.podsstore.mainactivityadapters.BestSellingProductAdapter;
import com.example.podsstore.mainactivityadapters.BestSellingProductBannerAdapter;
import com.example.podsstore.product.ProductListActivity;
import com.example.podsstore.product.ProductListAdapter;
import com.example.podsstore.productdetails.ProductDetailsActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SellerBannerproductActivity extends AppCompatActivity {
RecyclerView bestsellingproductrv;
    private BestSellingProductBannerAdapter bestSellingProductAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerproduct);
        bestsellingproductrv=findViewById(R.id.rvbestsellingproduct);
        bestSellingProductAdapter = new BestSellingProductBannerAdapter(SellerBannerproductActivity.this);

        getSupportActionBar().setTitle("Best Sellers");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


      //  bestSellingProductAdapter.setAdapterListener(adapterListeners);
        bestSellingProductAdapter.setAdapterListener(adapterListener);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        bestsellingproductrv.setLayoutManager(gridLayoutManager);

        bestsellingproductrv.setAdapter(bestSellingProductAdapter);
        loadDatabestselling();
    }
    private BestSellingProductBannerAdapter.AdapterListener adapterListener = data -> {

        Intent i = new Intent(SellerBannerproductActivity.this, ProductDetailsActivity.class);
        i.putExtra("userid",String.valueOf(data.getId()));
        i.putExtra("productlist","productlist");
        //  Toast.makeText(getApplicationContext(), String.valueOf(data.getId()), Toast.LENGTH_SHORT).show();
        startActivity(i);


    };
    @SuppressLint("CheckResult")
    private void loadDatabestselling() {
        // binding.progress.setVisibility(View.VISIBLE);
        ApiClient.getApiClient().getbestsellingproducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<TopBrandsProductResponse>>>() {
                    @Override
                    public void onSuccess(Response<List<TopBrandsProductResponse>> response) {
                        // binding.progress.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
                            List<TopBrandsProductResponse> list = response.body();
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