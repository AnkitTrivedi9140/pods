package com.example.podsstore.drower;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.podsstore.R;
import com.example.podsstore.addtocart.AddToCartActivity;
import com.example.podsstore.addtocart.PaymentActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.EditMakeOfferRequest;
import com.example.podsstore.data.request.QtyRequest;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.MakeOfferResponse;
import com.example.podsstore.data.response.MakeofferhistoryResponse;
import com.example.podsstore.data.response.QtyResponse;
import com.example.podsstore.mainactivityadapters.MakeOfferHistoryAdapter;
import com.example.podsstore.mainactivityadapters.ShowMakeofferAdapter;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerRevertActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MakeOfferHistoryAdapter productListAdapter;
    TextView tvnodata,tvproductname;
    ProgressBar progressBar;
    TextView progresstext;
    ImageView ivproduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_revert);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Your Offers");
        recyclerView = findViewById(R.id.productrv);
        productListAdapter = new MakeOfferHistoryAdapter(SellerRevertActivity.this);
        tvnodata = findViewById(R.id.tvnodata);
        progressBar=findViewById(R.id.progress);
        progresstext=findViewById(R.id.progresstext);
        tvproductname=findViewById(R.id.tvproductname);
        ivproduct=findViewById(R.id.ivproduct);
        recyclerView.setLayoutManager(new LinearLayoutManager(SellerRevertActivity.this));
//      recyclerView.setEmptyView(binding.emptyView);
     productListAdapter.setAdapterListener(adapterListener);
        productListAdapter.setAdapterListeners(adapterListeners);
        productListAdapter.setAdapterListenersedit(adapterListenersedit);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
//        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(productListAdapter);
loadData();
loadDataimage();
    }



    @SuppressLint("CheckResult")
    private void loadDataimage() {

        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+"///"+ PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));
        ApiClient.getApiClient().getofferdetails(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL),getIntent().getStringExtra("offerid")).enqueue(new Callback<List<MakeOfferResponse>>() {
            @Override
            public void onResponse(Call<List<MakeOfferResponse>> call, Response<List<MakeOfferResponse>> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("cartaaamake",String.valueOf(response.code()) );
                if (response.isSuccessful()) {
                    List<MakeOfferResponse> list = response.body();
                    for(int i=0;i<list.size();i++){

                        tvproductname.setText(list.get(i).getProductname());


                        Glide.with(getApplicationContext())
                                .load(list.get(i).getOfferimage())
                                .into(ivproduct);
                        ivproduct.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

//                              Intent intent=new Intent(getApplicationContext(), ProductDetailsActivity.class);
//                              intent.putExtra("userid",list.get(0).getProductid().toString());
//                              startActivity(intent);
//                              finish();
                            }
                        });
                    }

//                    if(  list.isEmpty()){
//                        tvnodata.setVisibility(View.VISIBLE);
//                    }else{
//                        tvnodata.setVisibility(View.GONE);
//                    }
                }
            }

            @Override
            public void onFailure(Call<List<MakeOfferResponse>> call, Throwable t) {
                Log.e("onerrors",t.getMessage());

            }
        });
    }





    private MakeOfferHistoryAdapter.AdapterListener adapterListener = data -> {
        AlertDialog.Builder dialog = new AlertDialog.Builder(SellerRevertActivity.this);
        dialog.setCancelable(true);
        dialog.setTitle("Alert!");
        dialog.setMessage("Do you want to accept this offer?");
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //Action for "Delete".
                // PreferenceManagerss.logout();
                              /*  finish();
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);*/
                makeofferaccept();
            }
        })
                .setNegativeButton("NO ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Action for "Cancel".
                        dialog.cancel();
                    }
                });

        final AlertDialog alert = dialog.create();
        alert.show();

    };
    private MakeOfferHistoryAdapter.EditAdapterListener adapterListenersedit = data -> {
showAlertDialog(data.getOfferid());

    };


    @SuppressLint("NewApi")
    private void showAlertDialog(String offerid) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SellerRevertActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.editmakeofferdialog, null);


        alertDialog.setView(customLayout);
        TextView  btnsave = (TextView) customLayout.findViewById(R.id.tvsavepwd);
        ImageView cut=customLayout.findViewById(R.id.ivcut);
        EditText etofferid=customLayout.findViewById(R.id.etofferid);

        EditText etofferamount =customLayout.findViewById(R.id.etofferamount);
        EditText  etofferqty =customLayout.findViewById(R.id.etofferqty);
        EditText etremarka =customLayout.findViewById(R.id.etremarks);


        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(true);


        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
//        etproof.setText(fileUrifund.getEncodedPath());
        //   etproof.setText(paths);
        etofferid.setText(offerid);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String remarks = etremarka.getText().toString().trim();
                String offerid = etofferid.getText().toString().trim();
                String offerqty = etofferqty.getText().toString().trim();
                String offeramount = etofferamount.getText().toString().trim();

                if(TextUtils.isEmpty(offerid)){
                    etofferid.setError("Offer id Can't Blank!");
                }
                else if(TextUtils.isEmpty(offeramount)){
                    etofferamount.setError("offer amount id Can't Blank!");
                }
                else if(TextUtils.isEmpty(offerqty)){
                    etofferqty.setError("Quantity Can't Blank!");
                }
                else if(TextUtils.isEmpty(remarks)){
                    etremarka.setError("remarks Can't Blank!");
                }

                else {

               editmakeoffer(etofferqty.getText().toString(),etofferid.getText().toString(),etofferamount.getText().toString(),etremarka.getText().toString());
                    alert.dismiss();

                }

            }
        });

        alert.show();
    }


    @SuppressLint("CheckResult")
    private void editmakeoffer( String qty, String offerid,String offeramount,String remarks) {


        EditMakeOfferRequest r = new EditMakeOfferRequest();
        r.setQuantityDetails(qty);
        r.setOfferAmount(offeramount);
        r.setOfferid(offerid);
        r.setRemark(remarks);


        // list.add(r);

        Log.e("postDataeditmakeoffer", new Gson().toJson(r));

        ApiClient.getApiClient().editmakeoffer(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL), r)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<CreateLoginUserResponse>>() {
                    @Override
                    public void onSuccess(Response<CreateLoginUserResponse> response) {

                        // binding.progressbar.setVisibility(View.GONE);


                        Log.e("onSuccess", String.valueOf(response.code()));
                        if (response.isSuccessful()) {

                            CreateLoginUserResponse successResponse = response.body();
                            Toast.makeText(getApplicationContext(), successResponse.getMessage(), Toast.LENGTH_SHORT).show();



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




    private MakeOfferHistoryAdapter.InventoryAdapterListener adapterListeners = data -> {



        AlertDialog.Builder dialog = new AlertDialog.Builder(SellerRevertActivity.this);
        dialog.setCancelable(true);
        dialog.setTitle("Alert!");
        dialog.setMessage("Do you want to decline this offer?");
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //Action for "Delete".
                // PreferenceManagerss.logout();
                              /*  finish();
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);*/
                makeofferdeclined();
            }
        })
                .setNegativeButton("NO ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Action for "Cancel".
                        dialog.cancel();
                    }
                });

        final AlertDialog alert = dialog.create();
        alert.show();
    };
    @SuppressLint("CheckResult")
    private void loadData() {
        progressBar.setVisibility(View.VISIBLE);
        progresstext.setVisibility(View.VISIBLE);
        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+"///"+ PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));
        ApiClient.getApiClient().makeofferhistory(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), getIntent().getStringExtra("offerid")).enqueue(new Callback<List<MakeofferhistoryResponse>>() {
            @Override
            public void onResponse(Call<List<MakeofferhistoryResponse>> call, Response<List<MakeofferhistoryResponse>> response) {
                progressBar.setVisibility(View.GONE);
                progresstext.setVisibility(View.GONE);
                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("cartaaamake",String.valueOf(response.code()) );
                if (response.isSuccessful()) {
                    List<MakeofferhistoryResponse> list = response.body();
                    productListAdapter.addAll(list);

                    if(  list.isEmpty()){
                        tvnodata.setVisibility(View.VISIBLE);
                    }else{
                        tvnodata.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<MakeofferhistoryResponse>> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
                progressBar.setVisibility(View.GONE);
                progresstext.setVisibility(View.GONE);
            }
        });
    }
    @SuppressLint("CheckResult")
    private void makeofferdeclined() {
        // Toast.makeText(getApplicationContext(), PreferenceManagerss.getStringValue(Preferences.USER_OTP_EMAIL) ,Toast.LENGTH_SHORT).show();
        /*Log.e("getfdfd", PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN)+PreferenceManager.getStringValue(Preferences.USER_EMAIL)
        );*/
        //  if (!PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
        ApiClient.getApiClient().makeofferdeclined(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL),getIntent().getStringExtra("offerid"),"remarks").enqueue(new Callback<CreateLoginUserResponse>() {
            //ApiClient.getApiClient().confirmotp("fff", otp).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<CreateLoginUserResponse> call, Response<CreateLoginUserResponse> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("onResp ",String.valueOf(response.code() ));


                if (response.isSuccessful()) {

                    CreateLoginUserResponse list = response.body();

                    Toast.makeText(getApplicationContext(),list.getMessage(),Toast.LENGTH_SHORT).show();
                    // showAlertDialogconfirm();
                } else {
                    Toast.makeText(getApplicationContext(), "Code is not correct!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateLoginUserResponse> call, Throwable t) {

                Log.e("onerrors", t.getMessage());
            }
        });
    }


    @SuppressLint("CheckResult")
    private void makeofferaccept() {
        //  if (!PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
        ApiClient.getApiClient().makeofferaccept(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL),getIntent().getStringExtra("offerid"),"remarks").enqueue(new Callback<CreateLoginUserResponse>() {
            //ApiClient.getApiClient().confirmotp("fff", otp).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<CreateLoginUserResponse> call, Response<CreateLoginUserResponse> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("onResp ",String.valueOf(response.code() ));


                if (response.isSuccessful()) {

                    CreateLoginUserResponse list = response.body();

                    Toast.makeText(getApplicationContext(),list.getMessage(),Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Code is not correct!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateLoginUserResponse> call, Throwable t) {

                Log.e("onerrors", t.getMessage());
            }
        });
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(getApplicationContext(), ShowMakeofferActivity.class);
                startActivity(intent);
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), ShowMakeofferActivity.class);
        startActivity(intent);
        finish();
    }
}