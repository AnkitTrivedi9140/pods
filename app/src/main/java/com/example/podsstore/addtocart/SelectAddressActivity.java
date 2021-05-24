package com.example.podsstore.addtocart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.AddressResponse;
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.data.response.ProfileResponses;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.profile.AddressActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectAddressActivity extends AppCompatActivity {
TextView tvchangeaddtess;
Button continuebtn;
    AlertDialog alertsss;
    EditText etaddress;
    String addressid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_address);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Select Address");
        etaddress =findViewById(R.id.etaddress);

        tvchangeaddtess=findViewById(R.id.tvchangeaddtess);
        continuebtn=findViewById(R.id.continuebtn);

        etaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialogaddress();
            }
        });
        tvchangeaddtess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AddressActivity.class);
                intent.putExtra("at","at");
                startActivity(intent);
                finish();
            }
        });

        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(etaddress.getText().toString())){
                    Toast.makeText(getApplicationContext(),"Please choose Address ",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent=new Intent(getApplicationContext(), PaymentActivity.class);
                    intent.putExtra("userid",getIntent().getStringExtra("userid"));
                    intent.putExtra("getbuynowqty",getIntent().getStringExtra("getbuynowqty"));
                    intent.putExtra("addressid",addressid.toString());

                    startActivity(intent);
                    finish();
                }

            }
        });
    }
    private void showAlertDialogaddress() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SelectAddressActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.makeofferaddressdialog, null);
        AddressPopupAdapter addressAdapter;
        ImageView cut=customLayout.findViewById(R.id.ivcut);
        RecyclerView addressrv=customLayout.findViewById(R.id.addressrv);
        alertDialog.setView(customLayout);
        alertsss = alertDialog.create();
        alertsss.setCanceledOnTouchOutside(true);


        addressAdapter = new AddressPopupAdapter(SelectAddressActivity.this);

        addressrv.setLayoutManager(new LinearLayoutManager(SelectAddressActivity.this));
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(getIntent().getStringExtra("addtocart")!=null){
            if(getIntent().getStringExtra("addtocart").equalsIgnoreCase("addtocart")){
                Intent intent = new Intent(getApplicationContext(), AddToCartActivity.class);

                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);
                finish();
            }
        }else{
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            startActivity(intent);
            finish();
        }



    }


 /*   @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }*/
}