package com.example.podsstore.drower;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.addtocart.AddtocartAdapter;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.data.response.MakeOfferResponse;
import com.example.podsstore.mainactivityadapters.ShowMakeofferAdapter;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.wishlist.WishListActivity;
import com.example.podsstore.wishlist.WishListAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowMakeofferActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ShowMakeofferAdapter productListAdapter;
    TextView tvnodata;
    ProgressBar progressBar;
    TextView progresstext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_makeoffer);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Your Offers");
        recyclerView = findViewById(R.id.productrv);
        productListAdapter = new ShowMakeofferAdapter(ShowMakeofferActivity.this);
        tvnodata = findViewById(R.id.tvnodata);
        progressBar=findViewById(R.id.progress);
        progresstext=findViewById(R.id.progresstext);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowMakeofferActivity.this));
//      recyclerView.setEmptyView(binding.emptyView);
       productListAdapter.setAdapterListener(adapterListener);
        productListAdapter.setAdapterListeners(adapterListeners);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
//        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(productListAdapter);

        loadData();
    }

    private ShowMakeofferAdapter.AdapterListener adapterListener = data -> {

       // deletecart(String.valueOf(data.getProductid().toString()));
//Toast.makeText(getApplicationContext(),data.getOfferid().toString(),Toast.LENGTH_SHORT).show();
Intent intent=new Intent(getApplicationContext(),ShowMakeofferdetailsActivity.class);
intent.putExtra("offerid",data.getOfferid().toString());
startActivity(intent);

    };
    private ShowMakeofferAdapter.InventoryAdapterListener adapterListeners = data -> {

        // deletecart(String.valueOf(data.getProductid().toString()));
//Toast.makeText(getApplicationContext(),data.getOfferid().toString(),Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getApplicationContext(),SellerRevertActivity.class);
        intent.putExtra("offerid",data.getOfferid().toString());
        startActivity(intent);

    };
    @SuppressLint("CheckResult")
    private void loadData() {
        progressBar.setVisibility(View.VISIBLE);
        progresstext.setVisibility(View.VISIBLE);
        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+"///"+ PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));
        ApiClient.getApiClient().getalloffers(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<List<MakeOfferResponse>>() {
            @Override
            public void onResponse(Call<List<MakeOfferResponse>> call, Response<List<MakeOfferResponse>> response) {
                progressBar.setVisibility(View.GONE);
                progresstext.setVisibility(View.GONE);
                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("cartaaamake",String.valueOf(response.code()) );
                if (response.isSuccessful()) {
                    List<MakeOfferResponse> list = response.body();
                    productListAdapter.addAll(list);

                    if(  list.isEmpty()){
                        tvnodata.setVisibility(View.VISIBLE);
                    }else{
                        tvnodata.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<MakeOfferResponse>> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
                progressBar.setVisibility(View.GONE);
                progresstext.setVisibility(View.GONE);
            }
        });
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}