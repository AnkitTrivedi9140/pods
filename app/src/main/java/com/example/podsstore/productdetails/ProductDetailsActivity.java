package com.example.podsstore.productdetails;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.SplashActivity;
import com.example.podsstore.addtocart.AddToCartActivity;
import com.example.podsstore.addtocart.SelectAddressActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.AddressDetailsRequest;
import com.example.podsstore.data.request.AddtocartRequest;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.drower.AddressesActivity;
import com.example.podsstore.prefs.PreferenceManager;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.product.ProductListActivity;
import com.example.podsstore.profile.ProfileActivity;
import com.example.podsstore.search.SearchActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {
ImageView ivproduct;
TextView tvProductname,tvProductprice,tvdetails,tvfeature,tvfunction;
Button logInBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Product Details");
        PreferenceManager.init(ProductDetailsActivity.this);
        ivproduct=findViewById(R.id.ivproduct);
        tvProductname=findViewById(R.id.tvProductname);
        logInBtn=findViewById(R.id.logInBtn);
        tvProductprice=findViewById(R.id.tvProductprice);
        tvdetails=findViewById(R.id.tvdetails);
        tvfeature=findViewById(R.id.tvfeature);
        tvfunction=findViewById(R.id.tvfunction);
        loadData();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:


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



                return true;
            case R.id.menu_item:   //this item has your app icon


                if (!PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
                    Intent cart=new Intent(getApplicationContext(), AddToCartActivity.class);
                    startActivity(cart);
                    finish();

                }else{
                    showAlertDialog();
                }
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

        Log.e("getssss",PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN)+"????"+getIntent().getStringExtra("userid") );

        ApiClient.getApiClient().getproductsdetails(getIntent().getStringExtra("userid")).enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {

               // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getMaterialMasters",String.valueOf(response.code()) );
                if (response.isSuccessful()) {
                    List<ProductResponse> list = response.body();

                    for (int i = 0; i < list.size(); i++) {
                        Log.e("onResponses", list.get(i).getImageurl());
                        Glide.with(getApplicationContext())
                                .load(list.get(i).getImageurl().trim().toString())
                                .into(ivproduct);
                        tvProductname.setText(list.get(i).getProdname());
                        tvProductprice.setText("$_"+list.get(i).getPrice());
                        tvdetails.setText(list.get(i).getDescription());
                        tvfeature.setText(list.get(i).getFeature());
                        tvfunction.setText(list.get(i).getFunctions());
                        int finalI = i;
                        logInBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (!PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
                                    smallCarton(list.get(finalI).getId(),list.get(finalI).getProdname(),Long.parseLong("25"),Long.parseLong("1"));
                                }else{
                                    showAlertDialog();
                                }


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
    @SuppressLint("CheckResult")
    private void smallCarton(Long prodid,String prodname,Long price,Long qty) {
        // binding.progressbar.setVisibility(View.VISIBLE);
        List<AddressDetailsRequest> list = new ArrayList<>();

        AddtocartRequest r = new AddtocartRequest();
        r.setProductid(prodid);
        r.setProductname(prodname);
        r.setPrice(price);
        r.setQuantity(qty);

       // list.add(r);




        Log.e("postData", new Gson().toJson(r));

        ApiClient.getApiClient(). addtocart(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN),PreferenceManager.getStringValue(Preferences.USER_EMAIL),r)
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


    }
}