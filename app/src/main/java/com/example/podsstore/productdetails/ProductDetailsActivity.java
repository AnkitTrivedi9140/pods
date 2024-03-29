package com.example.podsstore.productdetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.SplashActivity;
import com.example.podsstore.addtocart.AddToCartActivity;
import com.example.podsstore.addtocart.PaymentActivity;
import com.example.podsstore.buynow.BuyNowActivity;
import com.example.podsstore.category.SubCategoryProductActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.AddressDetailsRequest;
import com.example.podsstore.data.request.AddtocartRequest;
import com.example.podsstore.data.request.LoginUserRequest;
import com.example.podsstore.data.request.ProductReviewRequest;
import com.example.podsstore.data.response.AddressResponse;
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.LoginResponse;
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.data.response.ReviewResponse;
import com.example.podsstore.getorder.MyOrderActivity;
import com.example.podsstore.login.LoginActivity;
import com.example.podsstore.mainactivityadapters.MyOrderAdapter;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.product.ProductListActivity;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CookieManager;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity implements DownloadFile.Listener{
ImageView ivproduct,ivtoggle,ivcart;
TextView tvmaterialtitle,tvmaterial,tvserialno,tvserialnotitle,tvcertificationtitle,tvcertification,tvmanufacture,
        tvmanufacturetitle,tvstanderd,tvstanderdtitle,tvbrand,tvbrandtitle,tvcountry,
        tvcountrytitle,tvreviewhead,tvProductname,tvProductprice,tvdetails,tvfeature
        ,tvfunction,tvcartsize,tvdetailtitle,tvfeaturetitle,tvfunctiontitle,tvproductlocationtitle,tvproductlocation;
    TextView logInBtn,tvbuynow,tvrating,tvreturnpolicy;
    RecyclerView recyclerView;
    ReviewAdapter productListAdapter;
    SliderLayout slider;
    // Progress Dialog
    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;
    private RemotePDFViewPager remotePDFViewPager;

    private PDFPagerAdapter pdfPagerAdapter;
    // File url to download
    private static String file_url = "http://www.qwikisoft.com/demo/ashade/20001.kml";

String productidpdf;

RelativeLayout rlproductdetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        getSupportActionBar().hide();
        PreferenceManagerss.init(ProductDetailsActivity.this);

        productListAdapter = new ReviewAdapter(ProductDetailsActivity.this);

        slider=findViewById(R.id.slider);
        rlproductdetails=findViewById(R.id.rlproductdetails);

        tvproductlocation=findViewById(R.id.tvproductlocation);
        tvproductlocationtitle=findViewById(R.id.tvproductlocationtitle);
        tvserialno=findViewById(R.id.tvserialno);
        tvmaterial=findViewById(R.id.tvmaterial);
        tvserialnotitle=findViewById(R.id.tvserialnotitle);
        tvmaterialtitle=findViewById(R.id.tvmaterialtitle);
        tvcertificationtitle=findViewById(R.id.tvcertificationtitle);

        tvcertification=findViewById(R.id.tvcertification);


        tvmanufacture=findViewById(R.id.tvmanufacture);

        tvmanufacturetitle=findViewById(R.id.tvmanufacturetitle);

        tvstanderd=findViewById(R.id.tvstanderd);

        tvstanderdtitle=findViewById(R.id.tvstanderdtitle);

        tvbrand=findViewById(R.id.tvbrand);

        tvbrandtitle=findViewById(R.id.tvbrandtitle);

        tvcountry=findViewById(R.id.tvcountry);
        tvcountrytitle=findViewById(R.id.tvcountrytitle);

        tvreturnpolicy=findViewById(R.id.tvreturnpolicy);
        tvrating=findViewById(R.id.tvrating);
        tvreviewhead=findViewById(R.id.tvreviewhead);
        ivproduct=findViewById(R.id.ivproduct);
        recyclerView=findViewById(R.id.rvreview);
        ivtoggle=findViewById(R.id.ivtoggle);
        ivcart=findViewById(R.id.ivcart);
        tvcartsize=findViewById(R.id.tvcartsize);
        tvProductname=findViewById(R.id.tvProductname);
        logInBtn=findViewById(R.id.logInBtn);
        tvbuynow=findViewById(R.id.tvsignin);
        tvProductprice=findViewById(R.id.tvProductprice);
        tvdetails=findViewById(R.id.tvdetails);
        tvfeature=findViewById(R.id.tvfeature);
        tvfunction=findViewById(R.id.tvfunction);


        tvdetailtitle=findViewById(R.id.tvdetailtitle);
        tvfeaturetitle=findViewById(R.id.tvfeaturetitle);
        tvfunctiontitle=findViewById(R.id.tvfunctiontitle);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(ProductDetailsActivity.this));
        recyclerView.setAdapter(productListAdapter);

        loadData();


        ivtoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

/*
                if(getIntent().getStringExtra("main")==null && getIntent().getStringExtra("search")==null ){

                    Intent intent=new Intent(getApplicationContext(), ProductListActivity.class);
                    startActivity(intent);
                    finish();
                }else{

                }
                if(getIntent().getStringExtra("productlist")==null &&getIntent().getStringExtra("search")==null) {

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{

                }

                if (getIntent().getStringExtra("productlist")==null&&getIntent().getStringExtra("main")==null) {

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();

                }else{

                }
                if(getIntent().getStringExtra("prodid")!=null || getIntent().getStringExtra("catid")!=null )  {
                    Intent intent = new Intent(getApplicationContext(), SubCategoryProductActivity.class);
                    intent.putExtra("userid",getIntent().getStringExtra("prodid").toString());
                    intent.putExtra("productname",getIntent().getStringExtra("productname"));
                    intent.putExtra("catid",getIntent().getStringExtra("catid").toString());
                    startActivity(intent);
                    finish();
                }else if(getIntent().getStringExtra("prodid")==null || getIntent().getStringExtra("catid")==null) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }*/
            }
        });
        ivcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
                    Intent cart=new Intent(getApplicationContext(), AddToCartActivity.class);
                    startActivity(cart);
                    finish();

                }else{
                    showAlertDialog();
                }
            }
        });

loadDatacart();


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (slider != null){
            slider.startAutoCycle();
        }
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

                    tvcartsize.setText(String.valueOf(list.size()));
                }
            }

            @Override
            public void onFailure(Call<List<CartResponse>> call, Throwable t) {
                Log.e("onerrors", t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:



                return true;
            case R.id.menu_item:   //this item has your app icon


                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProductDetailsActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.alertlogin, null);


        alertDialog.setView(customLayout);
        TextView  btnsave = (TextView) customLayout.findViewById(R.id.tvsave);
        ImageView cut=customLayout.findViewById(R.id.ivcut);
        //     EditText et =customLayout.findViewById(R.id.etmobile);


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
                Intent intent=new Intent(getApplicationContext(), SplashActivity.class);
                startActivity(intent);
                finish();

            }
        });
        alert.show();
    }
    @SuppressLint("CheckResult")
    private void loadData() {

        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+"????"+getIntent().getStringExtra("userid") );

        ApiClient.getApiClient().getproductsdetails(getIntent().getStringExtra("userid")).enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {

               // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getMaterialMasters",String.valueOf(response.body()) );
                if (response.isSuccessful()) {
                    List<ProductResponse> list = response.body();
                    uploadbanner(list);
                    for (int i = 0; i < list.size(); i++) {
                        Log.e("onResponses", list.get(i).getImageurl());


//                        Glide.with(getApplicationContext())
//                                .load(list.get(i).getImageurl().trim().toString())
//                                .into(ivproduct);
productidpdf=list.get(i).getReturnpolicyurl().toString();
                        tvProductname.setText(list.get(i).getProdtype());
                        tvProductprice.setText("$ "+list.get(i).getPrice());
                        tvdetails.setText(list.get(i).getDescription());
                        tvfeature.setText(list.get(i).getFeature());
                        tvfunction.setText(list.get(i).getFunctions());

                        tvcertification.setText(list.get(i).getCertifications().toString());
                        tvcountry.setText(list.get(i).getCountry().toString());
                        tvstanderd.setText(list.get(i).getStandards().toString());
                        if(list.get(i).getSerialno()==null){
                                }else{
                            tvserialno.setText(list.get(i).getSerialno().toString());

                        }
                        if(list.get(i).getMaterials()==null){
                        }else{
                            tvmaterial.setText(list.get(i).getMaterials().toString());

                        }
                        if(list.get(i).getLocation()==null){
                        }else{
                            tvproductlocation.setText(list.get(i).getLocation().toString());

                        }
                        tvmanufacture.setText(list.get(i).getManufacturer().toString());
                        tvbrand.setText(list.get(i).getBrand().toString());



                        getreview(list.get(i).getId().toString());


                        if(tvcountry.getText().toString().length()>2){
                            tvcountrytitle.setVisibility(View.VISIBLE);
                            tvcountry.setVisibility(View.VISIBLE);
                            // Toast.makeText(getApplicationContext(),tvfunction.getText().toString(),Toast.LENGTH_SHORT).show();
                        } else {
                            tvcountrytitle.setVisibility(View.GONE);
                            tvcountry.setVisibility(View.GONE);
                        }


                        if(tvbrand.getText().toString().length()>2){
                            tvbrandtitle.setVisibility(View.VISIBLE);
                            tvbrand.setVisibility(View.VISIBLE);
                            // Toast.makeText(getApplicationContext(),tvfunction.getText().toString(),Toast.LENGTH_SHORT).show();
                        } else {
                            tvbrandtitle.setVisibility(View.GONE);
                            tvbrand.setVisibility(View.GONE);
                        }


                        if(tvstanderd.getText().toString().length()>2){
                            tvstanderdtitle.setVisibility(View.VISIBLE);
                            tvstanderd.setVisibility(View.VISIBLE);
                            // Toast.makeText(getApplicationContext(),tvfunction.getText().toString(),Toast.LENGTH_SHORT).show();
                        } else {
                            tvstanderd.setVisibility(View.GONE);
                            tvstanderdtitle.setVisibility(View.GONE);
                        }


                        if(tvmanufacture.getText().toString().length()>2){
                            tvmanufacturetitle.setVisibility(View.VISIBLE);
                            tvmanufacture.setVisibility(View.VISIBLE);
                            // Toast.makeText(getApplicationContext(),tvfunction.getText().toString(),Toast.LENGTH_SHORT).show();
                        } else {
                            tvmanufacture.setVisibility(View.GONE);
                            tvmanufacturetitle.setVisibility(View.GONE);
                        }



                        if(tvcertification.getText().toString().length()>2){
                            tvcertificationtitle.setVisibility(View.VISIBLE);
                            tvcertification.setVisibility(View.VISIBLE);
                            // Toast.makeText(getApplicationContext(),tvfunction.getText().toString(),Toast.LENGTH_SHORT).show();
                        } else {
                            tvcertification.setVisibility(View.GONE);
                            tvcertificationtitle.setVisibility(View.GONE);
                        }

                        if(tvserialno.getText().toString().length()>2){
                            tvserialno.setVisibility(View.VISIBLE);
                            tvserialnotitle.setVisibility(View.VISIBLE);
                            // Toast.makeText(getApplicationContext(),tvfunction.getText().toString(),Toast.LENGTH_SHORT).show();
                        } else {
                            tvserialno.setVisibility(View.GONE);
                            tvserialnotitle.setVisibility(View.GONE);
                        }

                        if(tvmaterial.getText().toString().length()>2){
                            tvmaterial.setVisibility(View.VISIBLE);
                            tvmaterialtitle.setVisibility(View.VISIBLE);
                            // Toast.makeText(getApplicationContext(),tvfunction.getText().toString(),Toast.LENGTH_SHORT).show();
                        } else {
                            tvmaterial.setVisibility(View.GONE);
                            tvmaterialtitle.setVisibility(View.GONE);
                        }
                        if(tvproductlocation.getText().toString().length()>2){
                            tvproductlocation.setVisibility(View.VISIBLE);
                            tvproductlocationtitle.setVisibility(View.VISIBLE);
                            // Toast.makeText(getApplicationContext(),tvfunction.getText().toString(),Toast.LENGTH_SHORT).show();
                        } else {
                            tvproductlocation.setVisibility(View.GONE);
                            tvproductlocationtitle.setVisibility(View.GONE);
                        }






                        if(tvfunction.getText().toString().length()>4){
                            tvfunctiontitle.setVisibility(View.VISIBLE);
                            tvfunction.setVisibility(View.VISIBLE);
                            // Toast.makeText(getApplicationContext(),tvfunction.getText().toString(),Toast.LENGTH_SHORT).show();
                        } else {
                            tvfunctiontitle.setVisibility(View.GONE);
                            tvfunction.setVisibility(View.GONE);
                        } if (tvfeature.getText().toString().length()>4){
                            tvfeaturetitle.setVisibility(View.VISIBLE);
                            tvfeature.setVisibility(View.VISIBLE);
                        }
                        else {
                            tvfeaturetitle.setVisibility(View.GONE);
                            tvfeature.setVisibility(View.GONE);
                        }
                        if(tvdetails.getText().toString().length()>4){
                            tvdetails.setVisibility(View.VISIBLE);
                            tvdetailtitle.setVisibility(View.VISIBLE);
                        }
                        else{

                            tvdetails.setVisibility(View.GONE);
                            tvdetailtitle.setVisibility(View.GONE);

                            // Toast.makeText(getApplicationContext(),"niotna",Toast.LENGTH_SHORT).show();
                        }


                        int finalI = i;
                        logInBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (!PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
                                    smallCarton(list.get(finalI).getId(),list.get(finalI).getProdname(),list.get(finalI).getPrice().toString(),Long.parseLong("1"));
                                }else{
                                    showAlertDialog();
                                }


                            }
                        });

                        tvbuynow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (!PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
                                    Intent intent=new Intent(getApplicationContext(), BuyNowActivity.class);
                                    intent.putExtra("userid",getIntent().getStringExtra("userid"));
                                    startActivity(intent);
                                    finish();
                                }else{
                                    showAlertDialog();
                                }


                            }
                        });


                         int finalI1 = i;
                        tvreturnpolicy.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                  //       new DownloadFileFromURL().execute(list.get(finalI1).getRedirecturl());
                                Log.d("onClickfff: ",String.valueOf(list.get(finalI1).getReturnpolicyurl()+"//"+String.valueOf(list.get(finalI1).getId().toString())));
              //       remotePDFViewPager = new RemotePDFViewPager(ProductDetailsActivity.this, productidpdf, ProductDetailsActivity.this);
//                                String yyy=productidpdf;
//                                Uri uri = Uri.parse(yyy); // missing 'http://' will cause crashed
//                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                                startActivity(intent);
                                Intent browserIntent = new Intent(getApplicationContext(),DownloadZipActivity.class);
                                browserIntent.putExtra("pdf",productidpdf);
                                startActivity(browserIntent);

                              //  new DownloadFileFromURL().execute(String.valueOf(list.get(finalI1).getReturnpolicyurl()));
                            }
                        });
                    }
                    if (list != null) {


                    }

                }
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
            }
        });
    }

    public void uploadbanner(List<ProductResponse> list)
    {
       //  Hash_file_maps = new HashMap<String, String>();
       // Hash_file_maps=Helper.bannermap;
        ArrayList<String> sliderImages = new ArrayList<>();

        for (ProductResponse name : list){

            sliderImages.add(name.getImageurl());
            sliderImages.add(name.getImageurl1());
            sliderImages.add(name.getImageurl2());
            sliderImages.add(name.getImageurl3());
            sliderImages.add(name.getImageurl4());
        }


        for (String s : sliderImages) {
            DefaultSliderView sliderView = new DefaultSliderView(this);
            sliderView.image(s);
            slider.addSlider(sliderView);
        }

        slider.movePrevPosition(false);
        slider.moveNextPosition(false);
        slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        slider.setCustomAnimation(new DescriptionAnimation());



        slider.getPagerIndicator().setDefaultIndicatorColor(Color.parseColor("#01309A"), Color.parseColor("#cecdcd"));
       // slider.setPresetTransformer(SliderLayout.Transformer.Default);
        //slider.setPresetIndicator(com.daimajia.slider.library.SliderLayout.PresetIndicators.Center_Bottom);
        slider.setDuration(6000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (slider != null){
            slider.startAutoCycle();
        }
    }

    @SuppressLint("CheckResult")
    private void smallCarton(Long prodid,String prodname,String price,Long qty) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        List<AddressDetailsRequest> list = new ArrayList<>();

        AddtocartRequest r = new AddtocartRequest();
        r.setProductid(prodid);
        r.setProductname(prodname);
        r.setPrice(price);
        r.setQuantity(qty);

       // list.add(r);




        Log.e("postData", new Gson().toJson(r));

        ApiClient.getApiClient(). addtocart(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL),r)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<CreateLoginUserResponse>>() {
                    @Override
                    public void onSuccess(Response<CreateLoginUserResponse> response) {
                        CreateLoginUserResponse successResponse = response.body();
                        // binding.progressbar.setVisibility(View.GONE);



                        if (response.isSuccessful()) {


                            Toast.makeText(getApplicationContext(),successResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            loadDatacart();

                        } else  {
                    Toast.makeText(getApplicationContext(),"Product already present in cart!", Toast.LENGTH_SHORT).show();

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
    private void smallCartonbuy(Long prodid,String prodname,String price,Long qty) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        List<AddressDetailsRequest> list = new ArrayList<>();

        AddtocartRequest r = new AddtocartRequest();
        r.setProductid(prodid);
        r.setProductname(prodname);
        r.setPrice(price);
        r.setQuantity(qty);

        // list.add(r);




        Log.e("postData", new Gson().toJson(r));

        ApiClient.getApiClient(). addtocart(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL),r)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<CreateLoginUserResponse>>() {
                    @Override
                    public void onSuccess(Response<CreateLoginUserResponse> response) {
                        CreateLoginUserResponse successResponse = response.body();
                        // binding.progressbar.setVisibility(View.GONE);


                        Log.e("onSuccess", String.valueOf(response.code()));
                        if (response.isSuccessful()) {


                            Toast.makeText(getApplicationContext(),successResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            loadDatacart();

//                            Log.e("onSuccessaa", successResponse.getChallanid());
                            if (successResponse != null) {

//                                if (successResponse.getMessage().equals("success")) {
//                                    // mappingAdapter.clear();
//
//                                }

                                //  Toaster.show(mContext, successResponse.getMessage());

                            }

                        } else {
                            Toast.makeText(getApplicationContext(),"Item already in cart.", Toast.LENGTH_SHORT).show();

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
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        menu.findItem(R.id.menu_item).setEnabled(true);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

      if(getIntent().getStringExtra("cart")!=null ){

            Intent intent=new Intent(getApplicationContext(), AddToCartActivity.class);
            startActivity(intent);
            finish();
        }else{
          finish();
        }
         /* if(getIntent().getStringExtra("productlist")==null &&getIntent().getStringExtra("search")==null) {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }else{

        }

        if (getIntent().getStringExtra("productlist")==null&&getIntent().getStringExtra("main")==null) {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();

        }else{

        }
   if(getIntent().getStringExtra("prodid")==null || getIntent().getStringExtra("catid")==null) {
    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
    startActivity(intent);
    finish();
}      else if(getIntent().getStringExtra("prodid")!=null || getIntent().getStringExtra("catid")!=null )  {
       Intent intent = new Intent(getApplicationContext(), SubCategoryProductActivity.class);
       intent.putExtra("userid",getIntent().getStringExtra("prodid").toString());
       intent.putExtra("catid",getIntent().getStringExtra("catid").toString());
       intent.putExtra("productname",getIntent().getStringExtra("productname"));
       startActivity(intent);
       finish();
   }*/

    }

    @SuppressLint("CheckResult")
    private void getreview(String productid) {

        List<ProductReviewRequest> list = new ArrayList<>();

        ProductReviewRequest r = new ProductReviewRequest();
//        r.setUsername(username);
        r.setProductid(productid);


        list.add(r);




        Log.e("postData", new Gson().toJson(r));

        Log.e("getfdfd", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+ PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)
        );
        ApiClient.getApiClient().getproductReview(r)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<ReviewResponse>>>() {
                    @Override
                    public void onSuccess(Response<List<ReviewResponse>> response) {
                        // binding.progress.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
                            List<ReviewResponse> list = response.body();
                            Log.e("getProductMasters", String.valueOf(list.toString()));
                            productListAdapter.addAll(list);
                            Double totalrating=0.0;
                            Double averate=0.0;
                            for(int i=0;i<list.size();i++){
                                totalrating=totalrating+Double.valueOf(list.get(i).getRating().toString());
                            }
                            DecimalFormat df = new DecimalFormat();
                            df.setMaximumFractionDigits(1);
                            averate=totalrating/list.size();
                            tvrating.setText(String.valueOf(df.format(averate)));

                            if(list.isEmpty()){
                                tvrating.setVisibility(View.GONE);
                                tvreviewhead.setVisibility(View.GONE);
                            }else {
                                tvreviewhead.setVisibility(View.VISIBLE);
                                tvrating.setVisibility(View.VISIBLE);
                            }


                        } else {

                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_network_msg), Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {


                    }
                });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type: // we set this to 0
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }

    @Override
    public void onSuccess(String url, String destinationPath) {
        pdfPagerAdapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));

        remotePDFViewPager.setAdapter(pdfPagerAdapter);

setContentView(remotePDFViewPager);
        //updateLayout();
    }
    private void updateLayout() {

        rlproductdetails.addView(remotePDFViewPager,
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    }
    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        if (pdfPagerAdapter != null) {
            pdfPagerAdapter.close();
        }
    }


}