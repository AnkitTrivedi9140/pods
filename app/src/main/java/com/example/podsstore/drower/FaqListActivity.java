package com.example.podsstore.drower;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.BannerResponse;
import com.example.podsstore.data.response.FaqResponse;
import com.example.podsstore.mainactivityadapters.AddressAdapter;
import com.example.podsstore.mainactivityadapters.FaqAdapter;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class FaqListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FaqAdapter addressAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Help / FAQs");

        recyclerView = findViewById(R.id.addressrv);
        addressAdapter = new FaqAdapter(FaqListActivity.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(FaqListActivity.this));
//      recyclerView.setEmptyView(binding.emptyView);
        addressAdapter.setAdapterListener(adapterListener);

        recyclerView.setAdapter(addressAdapter);



        loadDatabanner();
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
    private void loadDatabanner() {

        // binding.progress.setVisibility(View.VISIBLE);
        Log.e("getProductMasterssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN));
        ApiClient.getApiClient().getfaq()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<FaqResponse>>>() {
                    @Override
                    public void onSuccess(Response<List<FaqResponse>> response) {

                        Log.d("onSuccess: ", String.valueOf(response.code()));
                        if (response.isSuccessful()) {
                            List<FaqResponse> list = response.body();
                            Log.e("getProduct", String.valueOf(list.size()));
                            addressAdapter.addAll(list);


                        } else {

                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_network_msg), Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("geterre", String.valueOf(e.getMessage()));

                    }
                });
    }
    private FaqAdapter.AdapterListener adapterListener = data -> {

//Toast.makeText(getApplicationContext(),data.getAnswer().toString(),Toast.LENGTH_SHORT).show();
Intent intent=new Intent(getApplicationContext(),HelpActivity.class);
intent.putExtra("question",data.getQuestion().toString());
intent.putExtra("ans",data.getAnswer().toString());
startActivity(intent);
finish();
    };
}