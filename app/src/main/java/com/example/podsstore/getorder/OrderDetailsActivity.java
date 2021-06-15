package com.example.podsstore.getorder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.LoginUserRequest;
import com.example.podsstore.data.request.OrderInfoRequest;
import com.example.podsstore.data.response.OrderInfoResponse;
import com.example.podsstore.data.response.OrderResponse;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.productdetails.ProductDetailsActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsActivity extends AppCompatActivity {

    ImageView ivproduct;
    TextView tvdelieverestimate,tvorderstatus,tvorderdate,tvorderid,tvordertotal,tvdelieverydate,tvproductname,tvproductqty,tvproductprise,tvproductaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        tvorderdate=findViewById(R.id.tvorderdate);
        tvorderstatus=findViewById(R.id.tvorderstatus);
        ivproduct=findViewById(R.id.ivproduct);
        tvdelieverestimate=findViewById(R.id.tvdelieverestimate);
        getSupportActionBar().setTitle("Order Summary");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvorderid=findViewById(R.id.tvorderid);
        tvordertotal=findViewById(R.id.tvordertotal);
        tvdelieverydate=findViewById(R.id.tvdelieverydate);
        tvproductname=findViewById(R.id.tvproductname);
        tvproductqty=findViewById(R.id.tvproductqty);
        tvproductprise=findViewById(R.id.tvproductprise);
        tvproductaddress=findViewById(R.id.tvproductaddress);

loadData(getIntent().getStringExtra("userid"));
    }

    @SuppressLint("CheckResult")
    private void loadData(String orderid) {
        OrderInfoRequest r = new OrderInfoRequest();
        r.setOrderid(orderid);
        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+"///"+ PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));

        ApiClient.getApiClient().getplaceorderinfo(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL),r).enqueue(new Callback<List<OrderInfoResponse>>() {
            @Override
            public void onResponse(Call<List<OrderInfoResponse>> call, Response<List<OrderInfoResponse>> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("cartaaa",String.valueOf(response.code()) );
                if (response.isSuccessful()) {
                    List<OrderInfoResponse> list = response.body();

                    int totalPrice = 0;
                    for (int i = 0; i < list.size(); i++) {

                        tvorderdate.setText(list.get(i).getOrderdate().toString());
                          tvorderid.setText("#"+list.get(i).getOrderid().toString());
                        tvordertotal.setText("$ "+list.get(i).getTotalprice().toString());
                        tvdelieverydate.setText("Wednesday 5 June 2021");
                        tvorderstatus.setText("Order Status: "+list.get(i).getCurrentorderstatus().toString());
                        tvproductqty.setText("Qty: "+list.get(i).getQty().toString());
                        tvproductname.setText(list.get(i).getProducttype()+"("+list.get(i).getProductname().toString()+")");
                        tvproductprise.setText("Price: "+"$"+list.get(i).getPrice().toString());
                        if(list.get(i).getOrderaddress()==null){

                        }else{
                            tvproductaddress.setText(""+list.get(i).getOrderaddress());

                        }
                        if(list.get(i).getMode()==null){

                        }else{
                            tvdelieverestimate.setText("Payment Method-"+list.get(i).getMode().toString());


                        }

                        Glide.with(getApplicationContext())
                                .load(list.get(i).getProductimage())
                                .into(ivproduct);
                        ivproduct.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent intent=new Intent(getApplicationContext(), ProductDetailsActivity.class);
                                intent.putExtra("userid",list.get(0).getProductid().toString());
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<List<OrderInfoResponse>> call, Throwable t) {
                Log.e("onerrors",t.getMessage());

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(getApplicationContext(), MyOrderActivity.class);
                startActivity(intent);
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), MyOrderActivity.class);
        startActivity(intent);
        finish();
    }
}