package com.example.podsstore.addtocart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.local.viewmodel.QuantityViewModel;
import com.example.podsstore.data.request.AddressDetailsRequest;
import com.example.podsstore.data.request.AddtoCartWithQty;
import com.example.podsstore.data.request.AddtocartRequest;
import com.example.podsstore.data.request.ChangePasswordRequest;
import com.example.podsstore.data.request.MakeOfferRequest;
import com.example.podsstore.data.request.QtyRequest;
import com.example.podsstore.data.response.AddressResponse;
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.QtyResponse;
import com.example.podsstore.drower.AddressesActivity;
import com.example.podsstore.login.LoginActivity;
import com.example.podsstore.mainactivityadapters.AddressAdapter;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.productdetails.ProductDetailsActivity;
import com.example.podsstore.profile.ProfileActivity;
import com.google.gson.Gson;
import com.hbb20.CountryCodePicker;

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
String prodid;
    EditText etcoupon;
    String qtyclick="aa";
    private QuantityViewModel viewModel;
    int finalI= 0;
    AlertDialog alertsss;
    EditText etaddress;
    String addressid;
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
        productListAdapter.setAdapterListenerqty(adapterListenerqty);
        productListAdapter.setAdapterListeners(listener);
        productListAdapter.setAdapterListenerplus(adapterListenerpluss);
        productListAdapter.setAdapterListenersless(adapterListenerless);
        productListAdapter.setAdapterListenercart(adapterListenercart);



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
                        totalPrice += (list.get(i).getPrice() * Integer.valueOf(list.get(i).getQty().toString()));


                           // totalPrice += (list.get(i).getPrice());


                        Log.e("onResponses", list.get(i).getQty().toString());
                        tvsubtotaltxt.setText(String.valueOf(totalPrice));
                        tvtotaltxt.setText(String.valueOf(totalPrice));
                        //  Toast.makeText(getApplicationContext(),totalPrice,Toast.LENGTH_SHORT).show();

                        AddtoCartWithQty addtoCartWithQty = new AddtoCartWithQty();
                        addtoCartWithQty.setOrderid("1");
                        addtoCartWithQty.setProductid(list.get(i).getProductid().toString());
                        addtoCartWithQty.setProductimage(list.get(i).getProductname());
                        addtoCartWithQty.setProductimage(list.get(i).getImageUrl());
                        addtoCartWithQty.setQuantity(list.get(i).getQty().toString());


                         finalI = i;




                    }
                    placeorderbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (productListAdapter.getSize() == 0) {
                                Toast.makeText(getApplicationContext(), "Please add some items in cart", Toast.LENGTH_SHORT).show();
                            }else{

                                for(int j=0;j<list.size();j++){
                                 int aa=   Integer.valueOf(list.get(j).getQty().toString());
                                    int bb=  Integer.valueOf(list.get(j).getMinqty());
                                //    Log.e("onClick: ", list.get(j).getQty().toString());
                                    if(aa<bb){
                                        qtyclick="bb";
                                       // Log.e("onClick: ", list.get(j).getQty().toString());
                                        Intent intent = new Intent(getApplicationContext(), AddToCartActivity.class);

                                    startActivity(intent);
                                    finish();
                                        Toast.makeText(getApplicationContext(),list.get(j).getProducttype()+" and Qty will be "+list.get(j).getMinqty().toString(),Toast.LENGTH_LONG).show();
                                    } else if(qtyclick.equalsIgnoreCase("aa")){
                                        Intent intent = new Intent(getApplicationContext(), SelectAddressActivity.class);
                                        intent.putExtra("addtocart","addtocart");
                                        startActivity(intent);
                                        finish();
                                    }else {

                                        Toast.makeText(getApplicationContext(),list.get(j).getProducttype()+" and Qty will be "+list.get(j).getMinqty().toString(),Toast.LENGTH_LONG).show();


                                    }


                                }
                            }

                        }
                    });
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
//                for(int i=0;i<qtylist.size();i++){
//                    addqty(qtylist.get(i).getQuantity().toString(),qtylist.get(i).getProductid());
//                }

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
    private AddtocartAdapter.QtyAdapterListener adapterListenerqty = data -> {
        prodid=data.getProductid().toString();
showAlertDialogqty(data.getProductid().toString());


//Toast.makeText(getApplicationContext(),data.getProductid().toString(),Toast.LENGTH_SHORT).show();

    };
    private AddtocartAdapter.AdapterListenercart adapterListenercart = data -> {

        Intent i = new Intent(getApplicationContext(), ProductDetailsActivity.class);
        i.putExtra("userid", data.getProductid().toString());
        i.putExtra("cart", "cart");
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




    private void showAlertDialogqty(String productid) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddToCartActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.qtydialoglayout, null);

prodid=productid;
        alertDialog.setView(customLayout);
        MyListDataQty[] myListData = new MyListDataQty[] {
                new MyListDataQty("1"),
                new MyListDataQty("2"),
                new MyListDataQty("3"),
                new MyListDataQty("4"),
                new MyListDataQty("5"),
                new MyListDataQty("6"),
                new MyListDataQty("7"),
                new MyListDataQty("8"),
                new MyListDataQty("9"),
                new MyListDataQty("10"),
                new MyListDataQty("11"),
                new MyListDataQty("12"),

                new MyListDataQty("13"),
                new MyListDataQty("14"),
                new MyListDataQty("15"),
                new MyListDataQty("16"),
                new MyListDataQty("17"),
                new MyListDataQty("18"),
                new MyListDataQty("19"),
                new MyListDataQty("20"),
                new MyListDataQty("21"),
                new MyListDataQty("22"),

                new MyListDataQty("23"),
                new MyListDataQty("24"),
                new MyListDataQty("25"),
                new MyListDataQty("26"),
                new MyListDataQty("27"),
                new MyListDataQty("28"),
                new MyListDataQty("29"),
                new MyListDataQty("30"),


                new MyListDataQty("31"),
                new MyListDataQty("32"),

                new MyListDataQty("33"),
                new MyListDataQty("34"),
                new MyListDataQty("35"),
                new MyListDataQty("36"),
                new MyListDataQty("37"),
                new MyListDataQty("38"),
                new MyListDataQty("39"),
                new MyListDataQty("40"),




                new MyListDataQty("41"),
                new MyListDataQty("42"),

                new MyListDataQty("43"),
                new MyListDataQty("44"),
                new MyListDataQty("45"),
                new MyListDataQty("46"),
                new MyListDataQty("47"),
                new MyListDataQty("48"),
                new MyListDataQty("49"),
                new MyListDataQty("50"),



                new MyListDataQty("51"),
                new MyListDataQty("52"),

                new MyListDataQty("53"),
                new MyListDataQty("54"),
                new MyListDataQty("55"),
                new MyListDataQty("56"),
                new MyListDataQty("57"),
                new MyListDataQty("58"),
                new MyListDataQty("59"),
                new MyListDataQty("60"),
                new MyListDataQty("61"),
                new MyListDataQty("62"),

                new MyListDataQty("63"),
                new MyListDataQty("64"),
                new MyListDataQty("65"),
                new MyListDataQty("66"),
                new MyListDataQty("67"),
                new MyListDataQty("68"),
                new MyListDataQty("69"),
                new MyListDataQty("70"),


                new MyListDataQty("71"),
                new MyListDataQty("72"),

                new MyListDataQty("73"),
                new MyListDataQty("74"),
                new MyListDataQty("75"),
                new MyListDataQty("76"),
                new MyListDataQty("77"),
                new MyListDataQty("78"),
                new MyListDataQty("79"),
                new MyListDataQty("80"),
                new MyListDataQty("100"),
                new MyListDataQty("110"),
                new MyListDataQty("120"),
                new MyListDataQty("130"),
                new MyListDataQty("140"),
                new MyListDataQty("150"),

        };

        RecyclerView recyclerView = customLayout. findViewById(R.id.rvqty);
        ImageView ivcut=customLayout.findViewById(R.id.ivcut);
        QtyListAdapter adapter = new QtyListAdapter(myListData);
        adapter.setAdapterListenerqty(listenerqty);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(true);
ivcut.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        alert.dismiss();
    }
});

        alert.show();
        alert.getWindow().setLayout(500, 888);
    }



    private QtyListAdapter.QtyAdapterListenertxt listenerqty = data -> {

//Toast.makeText(getApplicationContext(),data.toString()+prodid,Toast.LENGTH_LONG).show();
addqty(data.toString(),prodid);
    };




    @SuppressLint("CheckResult")
    private void addqty( String qty, String prodid) {


            QtyRequest r = new QtyRequest();
            r.setQuantity(qty);
            r.setProductid(prodid);


            // list.add(r);

            Log.e("postData", new Gson().toJson(r));

            ApiClient.getApiClient().addqty(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL), r)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<Response<QtyResponse>>() {
                        @Override
                        public void onSuccess(Response<QtyResponse> response) {

                            // binding.progressbar.setVisibility(View.GONE);


                            Log.e("onSuccess", String.valueOf(response.code()));
                            if (response.isSuccessful()) {

                                QtyResponse successResponse = response.body();
                                Toast.makeText(getApplicationContext(), successResponse.getMessage(), Toast.LENGTH_SHORT).show();

                            Intent login = new Intent(AddToCartActivity.this, AddToCartActivity.class);
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
                                //Toast.makeText(getApplicationContext(), "Item already in wishlist", Toast.LENGTH_SHORT).show();

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
        // binding.progressbar.setVisibility(View.VISIBLE);

    }









    private AddtocartAdapter.InventoryAdapterListener listener = data -> {

        //addtowishlist(data.getProductid(), data.getProductname(), data.getPrice(), data.getQty());
showAlertDialog(data.getProductid().toString(),data.getProducttype().toString());

    };
    private void showAlertDialog(String prodtype,String prodid) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddToCartActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.makeoffer_dialoge, null);


        alertDialog.setView(customLayout);
        TextView  btnsave = (TextView) customLayout.findViewById(R.id.tvsavepwd);
        ImageView cut=customLayout.findViewById(R.id.ivcut);
        EditText etprodid =customLayout.findViewById(R.id.etpassword);
        EditText etqty =customLayout.findViewById(R.id.etqty);
        EditText etofferamount =customLayout.findViewById(R.id.etofferammount);

        EditText ettotalamount =customLayout.findViewById(R.id.ettotalammount);
       etaddress =customLayout.findViewById(R.id.etaddress);

        EditText etremarka =customLayout.findViewById(R.id.etremarks);

        etprodid.setText(prodid);
        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(true);

        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });

etaddress.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        showAlertDialogaddress();
    }
});

        etqty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Double v1 = Double.parseDouble(!etqty.getText().toString().isEmpty() ?
                        etqty.getText().toString() : "0");
                Double v2 = Double.parseDouble(!etofferamount.getText().toString().isEmpty() ?
                        etofferamount.getText().toString() : "0");
                Double value = v1 * v2;
                ettotalamount.setText(value.toString());
            }
        });


        etofferamount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Double v1 = Double.parseDouble(!etofferamount.getText().toString().isEmpty() ?
                        etofferamount.getText().toString() : "0");

                Double v2 = Double.parseDouble(!etqty.getText().toString().isEmpty() ?
                        etqty.getText().toString() : "0");
                Double value = v1 * v2;
                ettotalamount.setText(value.toString());
            }
        });





        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String prodid = etprodid.getText().toString().trim();
                String qty = etqty.getText().toString().trim();


                String offer = etofferamount.getText().toString().trim();
                String total = ettotalamount.getText().toString().trim();

                String remarks = etremarka.getText().toString().trim();


                String address = etaddress.getText().toString().trim();
/*Integer aaa=Integer.valueOf(qty)*Integer.valueOf(offer);
ettotalamount.setText(aaa.toString());*/

/*

                String mynum1=etqty.getText().toString();
                float mnum1= Float.parseFloat(mynum1);

                String mynum2=etofferamount.getText().toString();
                float mnum2= Float.parseFloat(mynum2);

                float res=mnum1*mnum2;
                etremarka.setText(String.valueOf(res));
*/



                if (TextUtils.isEmpty(prodid)) {
                    etprodid.setError("product name Can't Blank!");
                }else if(TextUtils.isEmpty(qty)){
                    etqty.setError("Quantity Can't Blank!");
                }
                else if(qty.equalsIgnoreCase("0")){
                    etqty.setError("Quantity is not correct!");
                }
                else if(offer.equalsIgnoreCase("0")){
                    etofferamount.setError("Amount is not correct!");
                }
                else if(TextUtils.isEmpty(offer)){
                    etofferamount.setError("offer amount Can't Blank!");
                }
                else if(TextUtils.isEmpty(total)){
                    ettotalamount.setError("total amount Can't Blank!");
                }
                else if(TextUtils.isEmpty(address)){
                    etaddress.setError("Please choose address!");
                }
                else if(TextUtils.isEmpty(remarks)){
                    etremarka.setError("remarks Can't Blank!");
                }

                //   loadData(et.getText().toString().trim());
                else {
makeoffer(prodtype.toString(),ettotalamount.getText().toString(),etofferamount.getText().toString(),"288",etqty.getText().toString(),etremarka.getText().toString());

               alert.dismiss();
                }
            }
        });
        alert.show();
    }
    private void showAlertDialogaddress() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddToCartActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.makeofferaddressdialog, null);
        AddressPopupAdapter addressAdapter;
        ImageView cut=customLayout.findViewById(R.id.ivcut);
        RecyclerView addressrv=customLayout.findViewById(R.id.addressrv);
        alertDialog.setView(customLayout);
        alertsss = alertDialog.create();
        alertsss.setCanceledOnTouchOutside(true);


        addressAdapter = new AddressPopupAdapter(AddToCartActivity.this);

        addressrv.setLayoutManager(new LinearLayoutManager(AddToCartActivity.this));
//      recyclerView.setEmptyView(binding.emptyView);
        addressAdapter.setAdapterListener(adapterListeneraddress);

        addressrv.setAdapter(addressAdapter);

        cut.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              alertsss.dismiss();
          }
      });

        ApiClient.getApiClient().getalladdress(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<AddressResponse>>>() {
                    @Override
                    public void onSuccess(Response<List<AddressResponse>> response) {
                        // binding.progress.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
                            List<AddressResponse> list = response.body();
                            Log.e("getProductMasters", String.valueOf(list.toString()));
                            addressAdapter.addAll(list);

                        } else {

                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_network_msg), Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {


                    }
                });


        alertsss.show();
    }
    private AddressPopupAdapter.AdapterListener adapterListeneraddress = data -> {

        //deletecart(String.valueOf(data.getAddressid().toString()));
//Toast.makeText(getApplicationContext(),data.getAddressid().toString(),Toast.LENGTH_SHORT).show();
etaddress.setText(data.getUseraddressline1()+","+data.getUseraddressline1()+"("+data.getUserzipcode()+")");
addressid=data.getAddressid().toString();
        alertsss.dismiss();
    };

    @SuppressLint("CheckResult")
    private void makeoffer(String prodid,String actualammount,String offeramount,String amountperunit,String quantitydetails,String remarks) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        //Toast.makeText(getApplicationContext(),">>>>>"+addressid,Toast.LENGTH_SHORT).show();
        List<MakeOfferRequest> list = new ArrayList<>();

        MakeOfferRequest r = new MakeOfferRequest();
        r.setProductid(Long.valueOf(prodid));
        r.setActualamount(Double.valueOf(actualammount));
        r.setOfferamount(Double.valueOf(offeramount));
        r.setAmountperunit(Integer.parseInt( amountperunit));
        r.setQuantitydetails(Integer.valueOf(quantitydetails));
        r.setRemarks(remarks);
        list.add(r);




        Log.e("postdata", new Gson().toJson(r));

        ApiClient.getApiClient(). makeoffer(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL),addressid,r)
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

//                            Log.e("onSuccessaa", successResponse.getChallanid());
                            if (successResponse != null) {

//                                if (successResponse.getMessage().equals("success")) {
//                                    // mappingAdapter.clear();
//
//                                }

                                //  Toaster.show(mContext, successResponse.getMessage());

                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Some problems getting from server", Toast.LENGTH_SHORT).show();

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

    @SuppressLint("CheckResult")
    private void updateoffer(String prodid,String actualammount,String offeramount,String amountperunit,String quantitydetails,String remarks) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        List<MakeOfferRequest> list = new ArrayList<>();

        MakeOfferRequest r = new MakeOfferRequest();
        r.setProductid(Long.valueOf(prodid));
        r.setActualamount(Double.valueOf(actualammount));
        r.setOfferamount(Double.valueOf(offeramount));
        r.setAmountperunit(Integer.valueOf(amountperunit));
        r.setQuantitydetails(Integer.valueOf(quantitydetails));
        r.setRemarks(remarks);
        list.add(r);






        Log.e("postData", new Gson().toJson(r));

        ApiClient.getApiClient(). updateoffer(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL),r)
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
                            if(getIntent().getStringExtra("add")==null && getIntent().getStringExtra("at")==null ){

                                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                                startActivity(intent);
                                finish();
                            }else{

                            }
                            if(getIntent().getStringExtra("at")==null &&getIntent().getStringExtra("profile")==null) {

                                Intent intent = new Intent(getApplicationContext(), AddressesActivity.class);
                                startActivity(intent);
                                finish();
                            }else{

                            }

                            if (getIntent().getStringExtra("add")==null&&getIntent().getStringExtra("profile")==null) {

                                Intent intent = new Intent(getApplicationContext(), SelectAddressActivity.class);
                                startActivity(intent);
                                finish();

                            }else{

                            }

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