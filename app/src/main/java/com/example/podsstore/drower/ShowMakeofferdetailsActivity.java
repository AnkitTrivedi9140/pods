package com.example.podsstore.drower;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.MakeOfferResponse;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.productdetails.ProductDetailsActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowMakeofferdetailsActivity extends AppCompatActivity {
    ImageView ivproduct,ivdeclined,ivedit,ivaccept;
    TextView tvofferprice, tvdelieverestimate,tvorderstatus,tvorderdate,tvorderid,tvordertotal,tvdelieverydate,tvproductname,tvproductqty,tvproductprise,tvproductaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_makeofferdetails);

        tvofferprice=findViewById(R.id.tvofferprice);
        tvorderdate=findViewById(R.id.tvorderdate);
        tvorderstatus=findViewById(R.id.tvorderstatus);
        ivproduct=findViewById(R.id.ivproduct);
        tvdelieverestimate=findViewById(R.id.tvdelieverestimate);
        getSupportActionBar().setTitle("Your Offer Summary");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvorderid=findViewById(R.id.tvorderid);
        tvordertotal=findViewById(R.id.tvordertotal);
        tvdelieverydate=findViewById(R.id.tvdelieverydate);
        tvproductname=findViewById(R.id.tvproductname);
        tvproductqty=findViewById(R.id.tvproductqty);
        tvproductprise=findViewById(R.id.tvproductprise);
        tvproductaddress=findViewById(R.id.tvproductaddress);
        ivdeclined=findViewById(R.id.ivdeclined);
        ivedit=findViewById(R.id.ivedit);
        ivaccept=findViewById(R.id.ivaccept);

        ivdeclined.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  makeofferdeclined();

                AlertDialog.Builder dialog = new AlertDialog.Builder(ShowMakeofferdetailsActivity.this);
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
            }
        });
        ivedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
Intent intent=new Intent(getApplicationContext(),SellerRevertActivity.class);
intent.putExtra("offerid",getIntent().getStringExtra("offerid"));
startActivity(intent);
finish();
            }
        });
        ivaccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder dialog = new AlertDialog.Builder(ShowMakeofferdetailsActivity.this);
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
            }
        });

        loadData();
    }

    @SuppressLint("CheckResult")
    private void loadData() {

        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+"///"+ PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));
        ApiClient.getApiClient().getofferdetails(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL),getIntent().getStringExtra("offerid")).enqueue(new Callback<List<MakeOfferResponse>>() {
            @Override
            public void onResponse(Call<List<MakeOfferResponse>> call, Response<List<MakeOfferResponse>> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("cartaaamake",String.valueOf(response.code()) );
                if (response.isSuccessful()) {
                    List<MakeOfferResponse> list = response.body();
                  for(int i=0;i<list.size();i++){
                      tvorderdate.setText(list.get(i).getOffercreatedat());
                      tvorderid.setText(list.get(i).getOfferid().toString());
                      tvordertotal.setText(list.get(i).getActualamount());
                      tvofferprice.setText(list.get(i).getFirstbidamount());
                      tvproductname.setText(list.get(i).getProductname());
                      tvproductqty.setText("Qty: "+list.get(i).getQuantitydetails());
                      tvorderstatus.setText("Offer Status: "+list.get(i).getOfferstatus());
//tvdelieverestimate.setText(list.get(i).getOfferaddress());
                      tvproductprise.setText(list.get(i).getRemarks());
                      if(list.get(i).getSellerremark()==null) {
                          tvproductaddress.setText("");
                      }else{
                          tvproductaddress.setText(list.get(i).getOfferaddress());
                      }

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