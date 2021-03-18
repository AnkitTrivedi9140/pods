package com.example.podsstore.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.SplashActivity;
import com.example.podsstore.aboutpod.AboutActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.BusinessCatResponse;

import com.example.podsstore.prefs.PreferenceManager;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.product.ProductListActivity;
import com.example.podsstore.profile.ProfileActivity;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CategoryAdapter productListAdapter;
    private  RadioGroup radioGroup1;
    private RadioButton home,categories,profile,about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        PreferenceManager.init(CategoryActivity.this);
        recyclerView = findViewById(R.id.productrv);
        productListAdapter = new CategoryAdapter(CategoryActivity.this);
        recyclerView = findViewById(R.id.productrv);
        recyclerView.setLayoutManager(new LinearLayoutManager(CategoryActivity.this));
//      recyclerView.setEmptyView(binding.emptyView);
        productListAdapter.setAdapterListener(adapterListener);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(productListAdapter);

        getSupportActionBar().setTitle("Category");
        loadData();
        radioGroup1=(RadioGroup)findViewById(R.id.radioGroup1);
        about = (RadioButton)findViewById(R.id.about);
        home = (RadioButton)findViewById(R.id.homes);
        categories = (RadioButton)findViewById(R.id.categories);
        profile = (RadioButton)findViewById(R.id.profile);
        categories.setCompoundDrawablesWithIntrinsicBounds( 0,R.drawable.bluecategory, 0,0);
        categories.setTextColor(Color.parseColor("#007eff"));


        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                Intent in;
                Log.i("matching", "matching inside1 bro" + checkedId);
                switch (checkedId)
                {
                    case R.id.homes:
                        Log.i("matching", "matching inside1 matching" +  checkedId);
                        in=new Intent(getBaseContext(), MainActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.categories:
                        Log.i("matching", "matching inside1 watchlistAdapter" + checkedId);

                        in = new Intent(getBaseContext(), CategoryActivity.class);
                        startActivity(in);
                      overridePendingTransition(0, 0);

                        break;
                    case R.id.profile:
                        Log.i("matching", "matching inside1 rate" + checkedId);
                        if (!PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
                            in = new Intent(getBaseContext(), ProfileActivity.class);
                            startActivity(in);
                            overridePendingTransition(0, 0);
                        }else{
                            showAlertDialog();
                        }

                        break;

                    case R.id.about:
                        Log.i("matching", "matching inside1 deals" + checkedId);
                        in = new Intent(getBaseContext(), AboutActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
                        break;
                    default:
                        break;
                }
            }
        });
    }
    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CategoryActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.alertlogin, null);


        alertDialog.setView(customLayout);
        TextView btnsave = (TextView) customLayout.findViewById(R.id.tvsave);
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
        // binding.progress.setVisibility(View.VISIBLE);
        ApiClient.getApiClient().getbusinesscat()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<BusinessCatResponse>>>() {
                    @Override
                    public void onSuccess(Response<List<BusinessCatResponse>> response) {
                        // binding.progress.setVisibility(View.GONE);

                        if (response.isSuccessful()) {
                            List<BusinessCatResponse> list = response.body();
                            Log.e("getProductMasters", String.valueOf(list.size()));
                            productListAdapter.addAll(list);

                        } else {

                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_network_msg), Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {


                    }
                });
    }

    private CategoryAdapter.AdapterListener adapterListener = data -> {
       // Toast.makeText(getApplicationContext(), data.getId(), Toast.LENGTH_SHORT).show();
        Intent i = new Intent(CategoryActivity.this, ProductListActivity.class);
        i.putExtra("userid", data.getId());
        startActivity(i);


    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(getApplicationContext(), "GoTo Main", Toast.LENGTH_LONG).show();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }
}