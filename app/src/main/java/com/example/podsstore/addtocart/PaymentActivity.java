package com.example.podsstore.addtocart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.AddressDetailsRequest;
import com.example.podsstore.data.request.AddtocartRequest;
import com.example.podsstore.data.request.PlaceOrderRequest;
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.prefs.PreferenceManager;
import com.example.podsstore.prefs.Preferences;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity implements AddtocartAdapter.DataTransferInterface {
    private RecyclerView recyclerView;
    private AddtocartAdapter productListAdapter;
TextView placeorderbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        placeorderbtn=findViewById(R.id.placeorderbtn);
        productListAdapter = new AddtocartAdapter(PaymentActivity.this,this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Payment");
        loadData();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

              Intent intent=new Intent(getApplicationContext(),SelectAddressActivity.class);
              startActivity(intent);
              finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(),SelectAddressActivity.class);
        startActivity(intent);
        finish();
    }
    @SuppressLint("CheckResult")
    private void loadData() {

        Log.e("getssss", PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN)+"///"+PreferenceManager.getStringValue(Preferences.USER_EMAIL));

        ApiClient.getApiClient().getcartdetails(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN),PreferenceManager.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<List<CartResponse>>() {
            @Override
            public void onResponse(Call<List<CartResponse>> call, Response<List<CartResponse>> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("cartaaa",String.valueOf(response.code()) );
                if (response.isSuccessful()) {
                    List<CartResponse> list = response.body();

                    getSupportActionBar().setTitle("Cart"+" ("+list.size()+")");
                    int totalPrice = 0;
                    for (int i = 0; i < list.size(); i++) {

                        int finalI = i;
                        placeorderbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                List<String> questions = new ArrayList<String>();
                                questions = (ArrayList<String>)getIntent().getSerializableExtra("QuestionListExtra");
                                placeorder("1",String.valueOf(list.get(finalI).getProductid().toString()),String.valueOf(list.get(finalI).getProductname()),String.valueOf(list.get(finalI).getImageUrl()),"21",String.valueOf(list.get(finalI).getTotalprice()),String.valueOf(list.get(finalI).getPrice().toString()));
                            }
                        });
                    }

                    if (list != null) {


                    }

                }
            }

            @Override
            public void onFailure(Call<List<CartResponse>> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
            }
        });
    }
    @SuppressLint("CheckResult")
    private void placeorder(String orderid,String productid,String productname,String productimage,String qty,String totalprice,String subtotal) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        List<PlaceOrderRequest> list = new ArrayList<>();

        PlaceOrderRequest r = new PlaceOrderRequest();
        r.setOrderid(orderid);
        r.setProductid(productid);
        r.setProductname(productname);
        r.setProductimage(productimage);
        r.setQuantity(qty);
        r.setTotalprice(totalprice);
        r.setSubtotal(subtotal);


     list.add(r);


        Log.e("postDatalist", list.toString());


        Log.e("postData", new Gson().toJson(r));

        ApiClient.getApiClient(). placeOrder(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN),PreferenceManager.getStringValue(Preferences.USER_EMAIL),list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<CreateLoginUserResponse>>() {
                    @Override
                    public void onSuccess(Response<CreateLoginUserResponse> response) {

                        // binding.progressbar.setVisibility(View.GONE);


                        Log.e("onSuccess", String.valueOf(response.code()));
                        if (response.isSuccessful()) {

                            CreateLoginUserResponse successResponse = response.body();
                            Toast.makeText(getApplicationContext(),successResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(getApplicationContext(), "server error", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("onError: " , e.getMessage());
                        Toast.makeText(getApplicationContext(), "server error", Toast.LENGTH_SHORT).show();

                        // binding.progressbar.setVisibility(View.GONE);
                        // NetworkHelper.handleNetworkError(e, mContext);
                    }
                });
    }


    @Override
    public void onSetValues(ArrayList<String> al) {
        Toast.makeText(getApplicationContext(), al.toString(), Toast.LENGTH_SHORT).show();
    }
}