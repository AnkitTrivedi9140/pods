package com.example.podsstore.addtocart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.local.viewmodel.QuantityViewModel;
import com.example.podsstore.data.request.AddressDetailsRequest;
import com.example.podsstore.data.request.AddtoCartWithQty;
import com.example.podsstore.data.request.AddtocartRequest;
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.productdetails.ProductDetailsActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddToCartActivity extends AppCompatActivity implements AddtocartAdapter.DataTransferInterface {
    private RecyclerView recyclerView;
    private AddtocartAdapter productListAdapter;
    TextView tvsubtotaltxt, tvtotaltxt, tvapply, tvdiscounttxt, tvcartempty;
    Button placeorderbtn;
    ArrayList<String> arrPackage;
    ArrayList<AddtoCartWithQty> qtylist;

    EditText etcoupon;
    private QuantityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        viewModel = ViewModelProviders.of(AddToCartActivity.this).get(QuantityViewModel.class);

        loadData();
        arrPackage = new ArrayList<>();
        qtylist = new ArrayList<>();
        tvcartempty = findViewById(R.id.tvcartempty);
        tvsubtotaltxt = findViewById(R.id.tvsubtotaltxt);
        tvtotaltxt = findViewById(R.id.tvtotaltxt);
        recyclerView = findViewById(R.id.productrv);
        placeorderbtn = findViewById(R.id.placeorderbtn);
        tvapply = findViewById(R.id.tvapply);
        etcoupon = findViewById(R.id.etcoupon);
        tvdiscounttxt = findViewById(R.id.tvdiscounttxt);
        productListAdapter = new AddtocartAdapter(AddToCartActivity.this, this);

        recyclerView.setLayoutManager(new LinearLayoutManager(AddToCartActivity.this));
//      recyclerView.setEmptyView(binding.emptyView);
        recyclerView.setAdapter(productListAdapter);
        productListAdapter.setAdapterListener(adapterListener);
        productListAdapter.setAdapterListeners(listener);
        productListAdapter.setAdapterListenerplus(adapterListenerpluss);
        productListAdapter.setAdapterListenersless(adapterListenerless);
        productListAdapter.setAdapterListenercart(adapterListenercart);
        placeorderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productListAdapter.getSize() == 0) {
                    Toast.makeText(getApplicationContext(), "Please add some items in cart", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), SelectAddressActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        });


        tvapply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = etcoupon.getText().toString().trim();

                if (TextUtils.isEmpty(number)) {
                    etcoupon.setError("Coupon Can't Blank!");
                } else {
                    getcoupon(etcoupon.getText().toString());
                }

            }
        });
    }

    @SuppressLint("CheckResult")
    private void loadData() {

        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN) + "///" + PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));

        ApiClient.getApiClient().getcartdetails(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<List<CartResponse>>() {
            @Override
            public void onResponse(Call<List<CartResponse>> call, Response<List<CartResponse>> response) {

                tvcartempty.setVisibility(View.VISIBLE);
                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("cartaaa", String.valueOf(response.code()));

                if (response.isSuccessful()) {

                    List<CartResponse> list = response.body();
                    productListAdapter.clear();
                    productListAdapter.addAll(list);
                    getSupportActionBar().setTitle("Cart" + " (" + list.size() + ")");
                    int totalPrice = 0;
                    for (int i = 0; i < list.size(); i++) {
                        String lastqty = viewModel.getqty(list.get(i).getProductid().toString());
                        if (lastqty == null) {
                            totalPrice += (list.get(i).getPrice());
                        } else {
                            totalPrice += (list.get(i).getPrice() * Integer.valueOf(lastqty));
                        }

                        //Log.e("onResponses", list.get(i).getPrice().toString());
                        tvsubtotaltxt.setText(String.valueOf(totalPrice));
                        tvtotaltxt.setText(String.valueOf(totalPrice));
                        //  Toast.makeText(getApplicationContext(),totalPrice,Toast.LENGTH_SHORT).show();

                        AddtoCartWithQty addtoCartWithQty = new AddtoCartWithQty();
                        addtoCartWithQty.setOrderid("1");
                        addtoCartWithQty.setProductid(list.get(i).getProductid().toString());
                        addtoCartWithQty.setProductimage(list.get(i).getProductname());
                        addtoCartWithQty.setProductimage(list.get(i).getImageUrl());
                        addtoCartWithQty.setQuantity(list.get(i).getQty().toString());

                    }
                    if (list.isEmpty()) {

                        tvcartempty.setVisibility(View.VISIBLE);
                        tvcartempty.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        });
                    } else {
                        tvcartempty.setVisibility(View.GONE);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<CartResponse>> call, Throwable t) {
                Log.e("onerrors", t.getMessage());
                tvcartempty.setVisibility(View.VISIBLE);
                tvcartempty.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });

            }
        });
    }

    private int grandTotal(List<CartResponse> items) {

        int totalPrice = 0;
        for (int i = 0; i < items.size(); i++) {
            totalPrice += items.get(i).getPrice();
        }

        return totalPrice;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                if (getIntent().getStringExtra("main") == null) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    startActivity(intent);
                    finish();
                } else {
                    if (getIntent().getStringExtra("main").equalsIgnoreCase("main")) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getIntent().getStringExtra("main") == null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            if (getIntent().getStringExtra("main").equalsIgnoreCase("main")) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }

        }
    }

    @SuppressLint("CheckResult")
    private void deletecart(String productid) {

        Log.e("getfdfd", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN) + PreferenceManagerss.getStringValue(Preferences.USER_EMAIL) + "lllll" + productid
        );

        ApiClient.getApiClient().deletecart(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL), productid).enqueue(new Callback<CreateLoginUserResponse>() {
            @Override
            public void onResponse(Call<CreateLoginUserResponse> call, Response<CreateLoginUserResponse> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getdelete", String.valueOf(response.code()));
                overridePendingTransition(0, 0);
                startActivity(getIntent());
                overridePendingTransition(0, 0);
                productListAdapter.notifyDataSetChanged();
                if (response.isSuccessful()) {
                    CreateLoginUserResponse list = response.body();

                    Toast.makeText(getApplicationContext(), list.getMessage(), Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call<CreateLoginUserResponse> call, Throwable t) {
                Log.e("onerrors", t.getMessage());
            }
        });
    }

    private AddtocartAdapter.AdapterListener adapterListener = data -> {

        deletecart(String.valueOf(data.getProductid().toString()));
//Toast.makeText(getApplicationContext(),data.getProductid().toString(),Toast.LENGTH_SHORT).show();

    };
    private AddtocartAdapter.AdapterListenercart adapterListenercart = data -> {

        Intent i = new Intent(getApplicationContext(), ProductDetailsActivity.class);
        i.putExtra("userid", data.getProductid().toString());
        startActivity(i);
        finish();

    };
    //    private AddtocartAdapter.AdapterListenerplus adapterListenerplus = data -> {
//
//
//Toast.makeText(getApplicationContext(),data.getProductid().toString(),Toast.LENGTH_SHORT).show();
//
//    };
    private AddtocartAdapter.AdapterListenerplus adapterListenerpluss = new AddtocartAdapter.AdapterListenerplus() {


        @Override
        public void onItemClickplus(CartResponse data, String qty) {

            //  Toast.makeText(getApplicationContext(), qty.toString(), Toast.LENGTH_SHORT).show();
            tvdiscounttxt.setText("0.00");
            String aa = viewModel.isexist(data.getProductid().toString());

            if (aa == null) {
                // Toast.makeText(getApplicationContext(), "insert", Toast.LENGTH_SHORT).show();
                insert(data.getProductid().toString(), qty, "1");
            } else {
                //Toast.makeText(getApplicationContext(), "update", Toast.LENGTH_SHORT).show();
                update(qty, data.getProductid().toString());
            }
        }


    };
    private AddtocartAdapter.AdapterListenerless adapterListenerless = new AddtocartAdapter.AdapterListenerless() {


        @Override
        public void onItemClickless(CartResponse data, String qty) {
            tvdiscounttxt.setText("0.00");
            String aa = viewModel.isexist(data.getProductid().toString());
            if (aa == null) {
                //  Toast.makeText(getApplicationContext(), "insert", Toast.LENGTH_SHORT).show();
                insert(data.getProductid().toString(), qty, "1");
            } else {
                // Toast.makeText(getApplicationContext(), "update", Toast.LENGTH_SHORT).show();
                update(qty, data.getProductid().toString());
            }
        }

    };

    public void insert(String productid, String qty, String userid) {

        Completable.fromAction(() -> viewModel.insert(productid, qty, userid))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.i("onComplete: ", "completessss");

                        loadData();
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("onError: ", e.getMessage());
                    }
                });
    }

    public void update(String qty, String productid) {
        //   Toast.makeText(context,"hogya",Toast.LENGTH_SHORT).show();
        Completable.fromAction(() -> viewModel.update(qty, productid))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.e("onupdate: ", "complete");
                        loadData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onErrorup: ", e.getMessage());
                    }
                });


    }

    @SuppressLint("CheckResult")
    private void addtowishlist(Long prodid, String prodname, Long price, Long qty) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        List<AddressDetailsRequest> list = new ArrayList<>();

        AddtocartRequest r = new AddtocartRequest();
        r.setProductid(prodid);
        r.setProductname(prodname);
        r.setPrice(price);
        r.setQuantity(qty);

        // list.add(r);

        Log.e("postData", new Gson().toJson(r));

        ApiClient.getApiClient().addtowishlist(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL), r)
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

    private AddtocartAdapter.InventoryAdapterListener listener = data -> {

        addtowishlist(data.getProductid(), data.getProductname(), data.getPrice(), data.getQty());


    };

    @Override
    public void onSetValues(ArrayList<String> al) {
        //Toast.makeText(getApplicationContext(),al.toString(),Toast.LENGTH_SHORT).show();

    }

    @SuppressLint("CheckResult")
    private void getcoupon(String code) {

        Log.e("getfdfd", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN) + PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));

        ApiClient.getApiClient().getcoupon(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL), code).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("onRespo ", String.valueOf(response.body()));
                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {


                    Toast.makeText(getApplicationContext(), "success coupon", Toast.LENGTH_SHORT).show();
                    Log.e("getprofile", String.valueOf(response.body()));
                    tvdiscounttxt.setText(String.valueOf(response.body()));
                    String totalprice = tvtotaltxt.getText().toString();
                    int totaltv = Integer.valueOf(totalprice) - Integer.valueOf(tvdiscounttxt.getText().toString());
                    tvtotaltxt.setText(String.valueOf(totaltv));
                    etcoupon.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Coupon code is not correct!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("onerrors", t.getMessage());
            }
        });
    }
}