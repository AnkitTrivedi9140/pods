package com.example.podsstore.getorder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.addtocart.AddToCartActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.AddressDetailsRequest;
import com.example.podsstore.data.request.AddtocartRequest;
import com.example.podsstore.data.request.ReturnRequest;
import com.example.podsstore.data.request.ReviewRequest;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.OrderResponse;
import com.example.podsstore.mainactivityadapters.MyOrderAdapter;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.productdetails.ProductDetailsActivity;
import com.example.podsstore.topbrands.TopBrandsProductActivity;
import com.example.podsstore.topbrands.TopBrandsProductAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyOrderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyOrderAdapter productListAdapter;
    TextView tvnodata;
    ProgressBar progressBar;
    TextView progresstext;
    String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("My Order");
        recyclerView = findViewById(R.id.productrv);
        tvnodata = findViewById(R.id.tvnodata);
        productListAdapter = new MyOrderAdapter(MyOrderActivity.this);
        progressBar=findViewById(R.id.progress);
        progresstext=findViewById(R.id.progresstext);
        recyclerView.setLayoutManager(new LinearLayoutManager(MyOrderActivity.this));
//      recyclerView.setEmptyView(binding.emptyView);
        //  productListAdapter.setAdapterListener(adapterListener);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
//        recyclerView.setLayoutManager(gridLayoutManager);
        productListAdapter.setAdapterListener(adapterListener);
        productListAdapter.setAdapterListenerviewdetails(adapterListenerviewdetails);
        productListAdapter.setAdapterListenerreview(adapterListenerreview);
        productListAdapter.setAdapterListenerreturn(adapterListenerreturn);
//        productListAdapter.setAdapterListeners(listener);
        recyclerView.setAdapter(productListAdapter);
loadData();
    }

    @SuppressLint("CheckResult")
    private void loadData() {
        progressBar.setVisibility(View.VISIBLE);
        progresstext.setVisibility(View.VISIBLE);
        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+"///"+ PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));

        ApiClient.getApiClient().getplaceorder(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<List<OrderResponse>>() {
            @Override
            public void onResponse(Call<List<OrderResponse>> call, Response<List<OrderResponse>> response) {
                progressBar.setVisibility(View.GONE);
                progresstext.setVisibility(View.GONE);
                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("cartaaa",String.valueOf(response.code()) );
                if (response.isSuccessful()) {
                    List<OrderResponse> list = response.body();
                    productListAdapter.addAll(list);
                    getSupportActionBar().setTitle("Your Order List"+" ("+list.size()+")");
                    int totalPrice = 0;
                    for (int i = 0; i < list.size(); i++) {
                       // totalPrice += list.get(i).getPrice();
                        //Log.e("onResponses", list.get(i).getPrice().toString());
//                        tvsubtotaltxt.setText(String.valueOf(totalPrice));
//                        tvtotaltxt.setText(String.valueOf(totalPrice));

                    }
                    if(  list.isEmpty()){
                        tvnodata.setVisibility(View.VISIBLE);
                    }else{
                        tvnodata.setVisibility(View.GONE);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<OrderResponse>> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
                progressBar.setVisibility(View.GONE);
                progresstext.setVisibility(View.GONE);
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

    private MyOrderAdapter.AdapterListener adapterListener = data -> {
        // Toast.makeText(getApplicationContext(), data.getId().toString()+"///"+data.getCatid().toString(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MyOrderActivity.this, ProductDetailsActivity.class);
        i.putExtra("userid", data.getProductid().toString());

        startActivity(i);


    };
    private MyOrderAdapter.AdapterListenerviewdetails adapterListenerviewdetails = data -> {
        // Toast.makeText(getApplicationContext(), data.getId().toString()+"///"+data.getCatid().toString(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MyOrderActivity.this, OrderDetailsActivity.class);
        i.putExtra("userid", data.getOrderid().toString());

        startActivity(i);


    };
    private MyOrderAdapter.InventoryAdapterListenerreview adapterListenerreview = data -> {
     // Toast.makeText(getApplicationContext(), "review", Toast.LENGTH_SHORT).show();
        showAlertDialogReview(data.getProductid().toString());
     /*   Intent i = new Intent(MyOrderActivity.this, ProductDetailsActivity.class);
        i.putExtra("userid", data.getProductid().toString());

        startActivity(i);*/


    };
    private MyOrderAdapter.InventoryAdapterListenerreturn adapterListenerreturn = data -> {
       // Toast.makeText(getApplicationContext(),"return", Toast.LENGTH_SHORT).show();
returninit("return".toString(),data.getOrderid().toString());


    };


    @SuppressLint("CheckResult")
    private void addReview(String fullname, String email, String rating, String remark,String prodictid) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        List<ReviewRequest> list = new ArrayList<>();

        ReviewRequest r = new ReviewRequest();
        r.setFullname(fullname);
        r.setEmail(email);
        r.setRating(rating);
        r.setRemark(remark);
        r.setProductid(prodictid);

        // list.add(r);

        Log.e("postData", new Gson().toJson(r));

        ApiClient.getApiClient().addreview(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL), r)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<CreateLoginUserResponse>>() {
                    @Override
                    public void onSuccess(Response<CreateLoginUserResponse> response) {

                        // binding.progressbar.setVisibility(View.GONE);


                        Log.e("onSuccesswish", String.valueOf(response.code()));
                        if (response.isSuccessful()) {

                            CreateLoginUserResponse successResponse = response.body();
                            Toast.makeText(getApplicationContext(), successResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                            Intent login = new Intent(CreateAccountActivity.this, SplashActivity.class);
//                            startActivity(login);
//                            finish();

//                            Log.e("onSuccessaa", successResponse.getChallanid());
                            if (successResponse != null) {

//                                if (successResponse.getMessage().equals("success")) {
//                                    // mappingAdapter.clear();
//
//                                }

                                //  Toaster.show(mContext, successResponse.getMessage());

                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Item already in wishlist", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("onError: ", e.getMessage());
                        Toast.makeText(getApplicationContext(), "server error", Toast.LENGTH_SHORT).show();

                        // binding.progressbar.setVisibility(View.GONE);
                        // NetworkHelper.handleNetworkError(e, mContext);
                    }
                });
    }
    @SuppressLint("CheckResult")
    private void returninit(String orderstatus, String orderid) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        List<ReturnRequest> list = new ArrayList<>();

        ReturnRequest r = new ReturnRequest();
        r.setOrderstatus(orderstatus);
        r.setOrderid(orderid);


        // list.add(r);

        Log.e("postData", new Gson().toJson(r));

        ApiClient.getApiClient().prodreturn(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL), r)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<CreateLoginUserResponse>>() {
                    @Override
                    public void onSuccess(Response<CreateLoginUserResponse> response) {

                        // binding.progressbar.setVisibility(View.GONE);


                        Log.e("onSuccesswish", String.valueOf(response.code()));
                        if (response.isSuccessful()) {

                            CreateLoginUserResponse successResponse = response.body();
                            Toast.makeText(getApplicationContext(), successResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent login = new Intent(MyOrderActivity.this, MyOrderActivity.class);
                            startActivity(login);
                            finish();

//                            Log.e("onSuccessaa", successResponse.getChallanid());
                            if (successResponse != null) {

//                                if (successResponse.getMessage().equals("success")) {
//                                    // mappingAdapter.clear();
//
//                                }

                                //  Toaster.show(mContext, successResponse.getMessage());

                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Item already in wishlist", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("onError: ", e.getMessage());
                        Toast.makeText(getApplicationContext(), "server error", Toast.LENGTH_SHORT).show();

                        // binding.progressbar.setVisibility(View.GONE);
                        // NetworkHelper.handleNetworkError(e, mContext);
                    }
                });
    }

    private void showAlertDialogReview(String productid) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MyOrderActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.reviewdialoge, null);


        alertDialog.setView(customLayout);
        RatingBar ratingBar=customLayout.findViewById(R.id.ratingBar);
        TextView  btnsave = (TextView) customLayout.findViewById(R.id.tvsavepwd);
        ImageView cut=customLayout.findViewById(R.id.ivcut);
        EditText etfullname =customLayout.findViewById(R.id.etfullname);
        EditText etemail =customLayout.findViewById(R.id.etemail);
        EditText etrating =customLayout.findViewById(R.id.etrating);
        EditText etremarks =customLayout.findViewById(R.id.etremarks);
        etemail.setText(PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));
ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        etrating.setText(String.valueOf(ratingBar.getRating()));
    }
});


        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(true);

        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String prodid = etfullname.getText().toString().trim();
                String qty = etemail.getText().toString().trim();


                String offer = etrating.getText().toString().trim();


                String remarks = etremarks.getText().toString().trim();


                    if (TextUtils.isEmpty(prodid)) {
                        etfullname.setError("Name Can't Blank!");
                    }else if(TextUtils.isEmpty(qty)){
                        etemail.setError("Email id Can't Blank!");
                    }else if(!qty.matches(regex)){
                        etemail.setError("Email id is not correct!");
                    }
                    else if(TextUtils.isEmpty(offer)){
                        Toast.makeText(getApplicationContext(),"please give ratings",Toast.LENGTH_SHORT).show();

                    }

                    else if(TextUtils.isEmpty(remarks)){
                        etremarks.setError("Remarks Can't Blank!");
                    }

                    //   loadData(et.getText().toString().trim());
                    else {
                        addReview(etfullname.getText().toString(),etemail.getText().toString(),etrating.getText().toString(),etremarks.getText().toString(),productid);

                        alert.dismiss();
                    }



            }
        });
        alert.show();
    }


}