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
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.category.SubCategoryActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.CountryResponse;

import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.ProfileResponses;
import com.example.podsstore.mainactivityadapters.CategoryHorigentalAdapter;
import com.example.podsstore.mainactivityadapters.CountryAdapter;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;


import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChooseCountryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CountryAdapter addressAdapter;
    ImageView ivcart,ivtoggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_country);
        getSupportActionBar().hide();

        ivtoggle = findViewById(R.id.ivtoggle);
        ivcart = findViewById(R.id.ivcart);
        recyclerView = findViewById(R.id.countryrv);
        addressAdapter = new CountryAdapter(ChooseCountryActivity.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(ChooseCountryActivity.this));
//      recyclerView.setEmptyView(binding.emptyView);
       addressAdapter.setAdapterListener(adapterListener);

        recyclerView.setAdapter(addressAdapter);


loadData();
loadDataprofile();
        ivtoggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private CountryAdapter.AdapterListener adapterListener = data -> {
        // Toast.makeText(getApplicationContext(), data.getImageurl(), Toast.LENGTH_SHORT).show();

changenumber( data.getCountryid().toString());
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                return true;

        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressLint("CheckResult")
    private void loadData() {

        Log.e("getfdfd", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+ PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)
        );
        ApiClient.getApiClient().getcountry()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<CountryResponse>>>() {
                    @Override
                    public void onSuccess(Response<List<CountryResponse>> response) {
                        // binding.progress.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
                            List<CountryResponse> list = response.body();
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

    @SuppressLint("CheckResult")
    private void changenumber(String mobilenumber) {

        Log.e("getfdfd", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+ PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)
        );

        ApiClient.getApiClient().selectcountry(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL),mobilenumber).enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getprofile",String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    loadDataprofile();
                    CountryResponse list = response.body();
                    PreferenceManagerss.setStringValue(Preferences.USER_COUNTRY_IMAGE, list.getCountryid().toString());
                 Toast.makeText(getApplicationContext(),"Country Selected",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ChooseCountryActivity.this, MainActivity.class);

                    // i.putExtra("subcategory", data.getProductname().toString());
                    startActivity(i);
finish();
                }
            }
            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
            }
        });
    }
    @SuppressLint("CheckResult")
    private void loadDataprofile() {

        Log.e("getfdfd", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+ PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)
        );

        ApiClient.getApiClient().profile(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<ProfileResponses>() {
            @Override
            public void onResponse(Call<ProfileResponses> call, Response<ProfileResponses> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getprofile",String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    ProfileResponses list = response.body();
                    for (int i=0; i<list.getAddress().size(); i++) {
                        // tvaddress.setText(list.getAddress().get(i).getAddressline1().toString()+", "+list.getAddress().get(i).getAddressline2().toString()+"\n"+list.getAddress().get(i).getAddressline3().toString());

                    }


                       // Log.e("getprofilesss", String.valueOf(list.getData().get(i).getUserimageurl()));
                        GlideUrl glideUrl = new GlideUrl(list.getCountryname().getImageurl().toString(),
                                new LazyHeaders.Builder()
                                        .addHeader("Authorization", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN))

                                        .build());

                        Glide.with(getApplicationContext())
                                .load(glideUrl)
                                .into(ivcart);


                }
            }
            @Override
            public void onFailure(Call<ProfileResponses> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
            }
        });
    }
}