package com.example.podsstore.buynow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.addtocart.AddToCartActivity;
import com.example.podsstore.addtocart.AddressPopupAdapter;
import com.example.podsstore.addtocart.MyListDataQty;
import com.example.podsstore.addtocart.PaymentActivity;
import com.example.podsstore.addtocart.QtyListAdapter;
import com.example.podsstore.addtocart.SelectAddressActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.AddressDetailsRequest;
import com.example.podsstore.data.request.AddtoCartWithQty;
import com.example.podsstore.data.request.AddtocartRequest;
import com.example.podsstore.data.request.MakeOfferRequest;
import com.example.podsstore.data.request.QtyRequest;
import com.example.podsstore.data.response.AddressResponse;
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.data.response.QtyResponse;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.productdetails.ProductDetailsActivity;
import com.example.podsstore.profile.AddressActivity;
import com.example.podsstore.search.SearchActivity;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyNowActivity extends AppCompatActivity {
    ImageView ivproduct, ivtoggle, ivcart, ivgo,ivgos;
    TextView tvProductpricetotal,tvProductname, tvProductprice, tvdetails, tvfeature, tvfunction, tvcartsize, tvdetailtitle, tvfeaturetitle, tvfunctiontitle;
    TextView logInBtn, tvbuynow;

    EditText tvqtybtn;

    public TextView description, tvAssetType, tvqty, prnumber;
    public ImageView productiv, deleteproductiv;
    public CardView cardView, less, more;
    RelativeLayout wishlist;
    ArrayList<String> arrayList;
    int counter = 1;
    String prodid;
    AlertDialog alertsss;
    EditText etaddress;
    String addressid;
    String productorice;
    RelativeLayout tvmakeoffer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_now);
        getSupportActionBar().hide();
        PreferenceManagerss.init(BuyNowActivity.this);
        ivproduct = findViewById(R.id.ivproduct);
        tvmakeoffer = findViewById(R.id.tvmakeoffer);
        ivgo = findViewById(R.id.ivgo);
        ivgos = findViewById(R.id.ivgos);
        tvqtybtn = findViewById(R.id.tvqtybtn);
        ivtoggle = findViewById(R.id.ivtoggle);
        ivcart = findViewById(R.id.ivcart);
        tvcartsize = findViewById(R.id.tvcartsize);
        tvProductname = findViewById(R.id.tvProductname);
        logInBtn = findViewById(R.id.logInBtn);
        tvProductpricetotal=findViewById(R.id.tvProductpricetotal);
        tvbuynow = findViewById(R.id.tvsignin);
        tvProductprice = findViewById(R.id.tvProductprice);
        tvdetails = findViewById(R.id.tvdetails);
        tvfeature = findViewById(R.id.tvfeature);
        tvfunction = findViewById(R.id.tvfunction);


        tvdetailtitle = findViewById(R.id.tvdetailtitle);
        tvfeaturetitle = findViewById(R.id.tvfeaturetitle);
        tvfunctiontitle = findViewById(R.id.tvfunctiontitle);


        tvqty = findViewById(R.id.tvqty);
        description = findViewById(R.id.description);
        tvAssetType = findViewById(R.id.tvAssetType);
        cardView = findViewById(R.id.cardview);
        productiv = findViewById(R.id.productiv);
        deleteproductiv = findViewById(R.id.deleteproductiv);
        less = findViewById(R.id.less);
        more = findViewById(R.id.more);
        prnumber = findViewById(R.id.prnumber);
        wishlist = findViewById(R.id.rlwishlist);


        less.setOnClickListener(v -> {

            counter = counter - 1;
            if (counter <= 0) {
                prnumber.setText(String.valueOf("1"));
            } else {
                prnumber.setText(String.valueOf(counter));
            }

            int totalPrice = 0;
            String mynum1 = prnumber.getText().toString();


            String mynum2 = tvProductprice.getText().toString();


            totalPrice += Integer.valueOf(mynum1) * Integer.valueOf("25");
            tvProductprice.setText(String.valueOf("$ " + totalPrice));

        });
        ivtoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        more.setOnClickListener(v -> {
            counter = counter + 1;

            if (counter <= 0) {
                prnumber.setText(String.valueOf("1"));
            } else {
                prnumber.setText(String.valueOf(counter));
            }
            //  prnumber.setText(String.valueOf(counter));

            int totalPrice = 0;
            String mynum1 = prnumber.getText().toString();


            String mynum2 = tvProductprice.getText().toString();


            totalPrice += Integer.valueOf(mynum1) * Integer.valueOf("25");
            tvProductprice.setText(String.valueOf("$ " + totalPrice));
            for (int j = 0; j < arrayList.size(); j++) {

/*
                        update(arrayList.get(j),productResponseList.get(i).getProductid().toString());*/


            }

        });
        arrayList = new ArrayList<>();
        arrayList.add(prnumber.getText().toString());


        //  tvtotaltxt.setText(String.valueOf(totalPrice));
        //  Toast.makeText(getApplicationContext(),t
        tvqtybtn.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Double v1 = Double.parseDouble(!tvqtybtn.getText().toString().isEmpty() ?
                        tvqtybtn.getText().toString() : "0");
                Double v2 = Double.parseDouble(!productorice.isEmpty() ?
                       productorice : "0");
                Double value = v1 * v2;
                //ettotalamount.setText(value.toString());
                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                tvProductpricetotal.setText(String.valueOf(df.format(value)));
            }
        });

        loadData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    private void showAlertDialog(String prodtype,String prodid) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(BuyNowActivity.this);
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
        etaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogaddress();
            }
        });
        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
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
    @SuppressLint("CheckResult")
    private void makeoffer(String prodid,String actualammount,String offeramount,String amountperunit,String quantitydetails,String remarks) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        List<MakeOfferRequest> list = new ArrayList<>();

        MakeOfferRequest r = new MakeOfferRequest();

        r.setProductid(Long.valueOf(prodid));
        r.setActualamount(String.valueOf(actualammount));
        r.setOfferamount(String.valueOf(offeramount));
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


    private void showAlertDialogaddress() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(BuyNowActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.makeofferaddressdialog, null);
        AddressPopupAdapter addressAdapter;
        ImageView cut=customLayout.findViewById(R.id.ivcut);
        RecyclerView addressrv=customLayout.findViewById(R.id.addressrv);
        alertDialog.setView(customLayout);
        alertsss = alertDialog.create();
        alertsss.setCanceledOnTouchOutside(true);


        addressAdapter = new AddressPopupAdapter(BuyNowActivity.this);

        addressrv.setLayoutManager(new LinearLayoutManager(BuyNowActivity.this));
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





    private void showAlertDialogqty(String productid) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(BuyNowActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.qtydialoglayout, null);

        // prodid=productid;
        alertDialog.setView(customLayout);
        MyListDataQty[] myListData = new MyListDataQty[]{
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

        RecyclerView recyclerView = customLayout.findViewById(R.id.rvqty);
        ImageView ivcut = customLayout.findViewById(R.id.ivcut);
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
        addqty(data.toString(), prodid);
    };


    @SuppressLint("CheckResult")
    private void addqty(String qty, String prodid) {


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
Intent i=new Intent(BuyNowActivity.this,BuyNowActivity.class);
startActivity(i);


                            tvqtybtn.setCursorVisible(false);
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

    @SuppressLint("CheckResult")
    private void loadData() {

        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN) + "????" + getIntent().getStringExtra("userid"));

        ApiClient.getApiClient().getproductsdetails(getIntent().getStringExtra("userid")).enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getMaterialMasters", String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    List<ProductResponse> list = response.body();

                    for (int i = 0; i < list.size(); i++) {

                        Log.e("onResponses", list.get(i).getImageurl());
                        Glide.with(getApplicationContext())
                                .load(list.get(i).getImageurl().trim().toString())
                                .into(ivproduct);

                        prodid = list.get(i).getId().toString();

                        tvProductname.setText(list.get(i).getProdtype());
                        tvProductprice.setText("$ "+list.get(i).getPrice());
                        tvdetails.setText(list.get(i).getDescription());
                        tvfeature.setText(list.get(i).getFeature());
                        tvfunction.setText(list.get(i).getFunctions());
                       // tvqtybtn.setText(list.get(i).getq);
                        productorice=list.get(i).getPrice();
                        int finalI1 = i;
                        tvmakeoffer.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showAlertDialog(String.valueOf(list.get(finalI1).getId().toString()),list.get(finalI1).getProdtype().toString());
                            }
                        });
                        if (tvfunction.getText().toString().length() > 4) {
                            tvfunctiontitle.setVisibility(View.VISIBLE);
                            tvfunction.setVisibility(View.VISIBLE);
                            // Toast.makeText(getApplicationContext(),tvfunction.getText().toString(),Toast.LENGTH_SHORT).show();
                        } else {
                            tvfunctiontitle.setVisibility(View.GONE);
                            tvfunction.setVisibility(View.GONE);
                        }
                        if (tvfeature.getText().toString().length() > 4) {
                            tvfeaturetitle.setVisibility(View.VISIBLE);
                            tvfeature.setVisibility(View.VISIBLE);
                        } else {
                            tvfeaturetitle.setVisibility(View.GONE);
                            tvfeature.setVisibility(View.GONE);
                        }
                        if (tvdetails.getText().toString().length() > 4) {
                            tvdetails.setVisibility(View.VISIBLE);
                            tvdetailtitle.setVisibility(View.VISIBLE);
                        } else {

                            tvdetails.setVisibility(View.GONE);
                            tvdetailtitle.setVisibility(View.GONE);

                            // Toast.makeText(getApplicationContext(),"niotna",Toast.LENGTH_SHORT).show();
                        }


                        int finalI = i;


                        tvbuynow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(tvqtybtn.getText().toString().equalsIgnoreCase("") ||tvqtybtn.getText().toString()==null||tvqtybtn.getText().toString().equalsIgnoreCase("0")){
                                    Toast.makeText(getApplicationContext(),"Please check your Quantity!",Toast.LENGTH_SHORT).show();

                                }else {
                                     if (Integer.valueOf(tvqtybtn.getText().toString()) < Integer.valueOf(list.get(finalI).getMinqty().toString())) {
                                        Toast.makeText(getApplicationContext(),"Minimum Quantity of this product is"+list.get(finalI).getMinqty().toString(),Toast.LENGTH_SHORT).show();

                                    }else{


                                        if (!PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
                                            //  Toast.makeText(getApplicationContext(),prnumber.getText().toString(),Toast.LENGTH_SHORT).show();
                                            smallCartonbuy(list.get(finalI).getId(), list.get(finalI).getProdname(), list.get(finalI).getPrice().toString(), Long.parseLong(prnumber.getText().toString()));
                                        } else {
                                            // showAlertDialog();
                                        }
                                    }
                                }


                            }
                        });
                        ivgos.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
addqty(tvqtybtn.getText().toString(),list.get(finalI).getId().toString());
                            }
                        });
                        tvqtybtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //  showAlertDialogqty(list.get(finalI).getId().toString());
                            }
                        });
                    }
                    if (list != null) {


                    }

                }
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
                Log.e("onerrors", t.getMessage());
            }
        });
    }

    @SuppressLint("CheckResult")
    private void smallCartonbuy(Long prodid, String prodname, String price, Long qty) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        List<AddressDetailsRequest> list = new ArrayList<>();

        AddtocartRequest r = new AddtocartRequest();
        r.setProductid(prodid);
        r.setProductname(prodname);
        r.setPrice(price);
        r.setQuantity(qty);

        // list.add(r);


        Log.e("postData", new Gson().toJson(r));

        ApiClient.getApiClient().addtocart(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL), r)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<CreateLoginUserResponse>>() {
                    @Override
                    public void onSuccess(Response<CreateLoginUserResponse> response) {
                        CreateLoginUserResponse successResponse = response.body();
                        // binding.progressbar.setVisibility(View.GONE);

                        Intent intent = new Intent(getApplicationContext(), SelectAddressActivity.class);
                        intent.putExtra("userid", getIntent().getStringExtra("userid"));
                        intent.putExtra("getbuynowqty", tvqtybtn.getText().toString());
                        startActivity(intent);
                        finish();
                        Log.e("onSuccess", String.valueOf(response.code()));
                        if (response.isSuccessful()) {


                            Toast.makeText(getApplicationContext(), successResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            // loadDatacart();
                         //   addqty(tvqtybtn.getText().toString(), String.valueOf(prodid));
//                            Log.e("onSuccessaa", successResponse.getChallanid());
                            if (successResponse != null) {


                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Waiting for seller acceptance", Toast.LENGTH_SHORT).show();

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
    private void loadDatacart() {

        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN) + "///" + PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));

        ApiClient.getApiClient().getcartdetails(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<List<CartResponse>>() {
            @Override
            public void onResponse(Call<List<CartResponse>> call, Response<List<CartResponse>> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("cartaaa", String.valueOf(response.code()));

                if (response.isSuccessful()) {

                    List<CartResponse> list = response.body();

                    getSupportActionBar().setTitle("Cart" + " (" + list.size() + ")");
                    int totalPrice = 0;

                    for (int i = 0; i < list.size(); i++) {
                        totalPrice += (Double.valueOf(String.valueOf(list.get(i).getPrice().toString())) *Double.valueOf( String.valueOf(list.get(i).getQty().toString())));


                        // totalPrice += (list.get(i).getPrice());


                        Log.e("onResponses", list.get(i).getQty().toString());

                        //  Toast.makeText(getApplicationContext(),totalPrice,Toast.LENGTH_SHORT).show();

                        AddtoCartWithQty addtoCartWithQty = new AddtoCartWithQty();
                        addtoCartWithQty.setOrderid("1");
                        addtoCartWithQty.setProductid(list.get(i).getProductid().toString());
                        addtoCartWithQty.setProductimage(list.get(i).getProductname());
                        addtoCartWithQty.setProductimage(list.get(i).getImageUrl());
                        addtoCartWithQty.setQuantity(list.get(i).getQty().toString());


                        int finalI = i;


                    }
                    if (list.isEmpty()) {


                    } else {

                    }

                }
            }

            @Override
            public void onFailure(Call<List<CartResponse>> call, Throwable t) {
                Log.e("onerrors", t.getMessage());


            }
        });
    }

}