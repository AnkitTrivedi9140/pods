package com.example.podsstore.drower;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.AddressResponse;
import com.example.podsstore.data.response.BusinessCatResponse;
import com.example.podsstore.data.response.ProfileResponses;
import com.example.podsstore.mainactivityadapters.AddressAdapter;
import com.example.podsstore.prefs.PreferenceManager;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.product.ProductListActivity;
import com.example.podsstore.product.ProductListAdapter;
import com.example.podsstore.profile.AddressActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressesActivity extends AppCompatActivity {
    TextView tvchangeaddtess;
    Button continuebtn;
    private RecyclerView recyclerView;
    private AddressAdapter addressAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addresses);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Saved Address");

        tvchangeaddtess=findViewById(R.id.tvchangeaddtess);
        tvchangeaddtess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AddressActivity.class);
                intent.putExtra("add","add");
                startActivity(intent);
                finish();
            }
        });


        recyclerView = findViewById(R.id.addressrv);
        addressAdapter = new AddressAdapter(AddressesActivity.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(AddressesActivity.this));
//      recyclerView.setEmptyView(binding.emptyView);
        //addressAdapter.setAdapterListener(adapterListener);

        recyclerView.setAdapter(addressAdapter);



        loadData();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressLint("CheckResult")
    private void loadData() {

        Log.e("getfdfd", PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN)+PreferenceManager.getStringValue(Preferences.USER_EMAIL)
        );
        ApiClient.getApiClient().getalladdress(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN),PreferenceManager.getStringValue(Preferences.USER_EMAIL))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<AddressResponse>>>() {
                    @Override
                    public void onSuccess(Response<List<AddressResponse>> response) {
                        // binding.progress.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
                            List<AddressResponse> list = response.body();
                            Log.e("getProductMasters", String.valueOf(list.size()));
                            addressAdapter.addAll(list);

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
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}