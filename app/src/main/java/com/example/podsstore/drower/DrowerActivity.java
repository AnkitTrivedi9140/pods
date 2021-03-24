package com.example.podsstore.drower;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.addtocart.SelectAddressActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.ProfileResponses;
import com.example.podsstore.getorder.MyOrderActivity;
import com.example.podsstore.prefs.PreferenceManager;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.profile.AddressActivity;
import com.example.podsstore.profile.ProfileActivity;
import com.example.podsstore.wishlist.WishListActivity;
import com.mikhaellopez.circularimageview.CircularImageView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DrowerActivity extends AppCompatActivity {
TextView tvusername,tvemail;
ImageView ivcut;
CircularImageView productiv;
Button btnlogout;

RelativeLayout rlorder,rladdress,rlwishlist,rlsettings,rlsavedcard,rlchoosecountry,rlhelp,rluserimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drower);
        tvusername=findViewById(R.id.tvusername);
        tvemail=findViewById(R.id.tvemail);
        productiv=findViewById(R.id.productiv);
        ivcut=findViewById(R.id.ivcut);
        btnlogout=findViewById(R.id.btnlogout);

        rlorder=findViewById(R.id.rlorder);
        rladdress=findViewById(R.id.rladdress);
        rlwishlist=findViewById(R.id.rlwishlist);
        rlsettings=findViewById(R.id.rlsettings);
        rlsavedcard=findViewById(R.id.rlsavedcard);
        rlchoosecountry=findViewById(R.id.rlchoosecountry);
        rluserimage=findViewById(R.id.rluserimage);
        rlhelp=findViewById(R.id.rlhelp);

        getSupportActionBar().hide();
        loadData();
        ivcut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main =new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
                finish();
            }
        });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder dialog = new AlertDialog.Builder(DrowerActivity.this);
                dialog.setCancelable(true);
                dialog.setTitle("Exit from Pod!");
                dialog.setMessage("Are you sure you want to exit from application?" );
                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Action for "Delete".
                        PreferenceManager.logout();
                        finish();
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

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
        rlorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent help=new Intent(getApplicationContext(), MyOrderActivity.class);
                startActivity(help);
                finish();
            }
        });
        rlhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent help=new Intent(getApplicationContext(),HelpAndFAQActivity.class);
                startActivity(help);
                finish();
            }
        });
        rluserimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile=new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(profile);
                finish();
            }
        });
        rladdress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile=new Intent(getApplicationContext(), AddressesActivity.class);
                startActivity(profile);
                finish();
            }
        });
        rlwishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile=new Intent(getApplicationContext(), WishListActivity.class);
                startActivity(profile);
                finish();
            }
        });
        rlchoosecountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profile=new Intent(getApplicationContext(), ChooseCountryActivity.class);
                startActivity(profile);
                finish();
            }
        });
    }
    @SuppressLint("CheckResult")
    private void loadData() {

        Log.e("getfdfd", PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN)+PreferenceManager.getStringValue(Preferences.USER_EMAIL)
        );

        ApiClient.getApiClient().profile(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN),PreferenceManager.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<ProfileResponses>() {
            @Override
            public void onResponse(Call<ProfileResponses> call, Response<ProfileResponses> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getprofile",String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    ProfileResponses list = response.body();
                    for (int i=0; i<list.getAddress().size(); i++) {
                       // tvaddress.setText(list.getAddress().get(i).getAddressline1().toString()+", "+list.getAddress().get(i).getAddressline2().toString()+"\n"+list.getAddress().get(i).getAddressline3().toString());

                    }

                    for (int i = 0; i < list.getData().size(); i++) {
                        Log.e("getprofilesss", String.valueOf(list.getData().get(i).getUserimageurl()));
                        GlideUrl glideUrl = new GlideUrl(list.getData().get(i).getUserimageurl(),
                                new LazyHeaders.Builder()
                                        .addHeader("Authorization", PreferenceManager.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN))

                                        .build());

                        Glide.with(getApplicationContext())
                                .load(glideUrl)
                                .into(productiv);
                    }
                    tvusername.setText(list.getUsername());
                    tvemail.setText(list.getUseremailid());

                }
            }
            @Override
            public void onFailure(Call<ProfileResponses> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
            }
        });
    }
}