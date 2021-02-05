package com.example.podsstore.product;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.ProductResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
 loadData();
    }
//
//
//    private void getAllInventoryMaster() {
//        ApiClient.getApiClient().getproducts().enqueue(new Callback<List<ProductResponse>>() {
//            @Override
//            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {
//
//
//                if (response.isSuccessful()) {
//                    List<ProductResponse> list = response.body();
//                    Log.e("getMaterialMasters", response.body().toString());
//                    for (int i = 0; i < list.size(); i++) {
//                        //   Log.e("onResponses", list.get(i).getBundleNo());
//                    }
//
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
//
//            }
//        });
//    }



    @SuppressLint("CheckResult")
    private void loadData() {
       // binding.progress.setVisibility(View.VISIBLE);
        ApiClient.getApiClient().getproducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<ProductResponse>>>() {
                    @Override
                    public void onSuccess(Response<List<ProductResponse>> response) {
                       // binding.progress.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
                            List<ProductResponse> list = response.body();
                            Log.e("getMaterialMasters", list.toString());


                        } else {
                            //Toaster.show(mContext, getResources().getString(R.string.error_network_msg));

                        }


                    }

                    @Override
                    public void onError(Throwable e) {
//
//                        binding.progress.setVisibility(View.GONE);
//                        NetworkHelper.handleNetworkError(e, mContext);

                    }
                });
    }

}