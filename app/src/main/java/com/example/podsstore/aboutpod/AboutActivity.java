package com.example.podsstore.aboutpod;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.SplashActivity;
import com.example.podsstore.addtocart.AddToCartActivity;
import com.example.podsstore.category.CategoryActivity;
import com.example.podsstore.prefs.PreferenceManager;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.product.ProductListActivity;
import com.example.podsstore.profile.ProfileActivity;

public class AboutActivity extends AppCompatActivity {
    RadioGroup radioGroup1;
    RadioButton home,categories,profile,about;
    TextView tvreact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        PreferenceManager.init(AboutActivity.this);
        getSupportActionBar().setTitle("About POD");
        radioGroup1=(RadioGroup)findViewById(R.id.radioGroup1);
        tvreact=findViewById(R.id.tvreact);
        about = (RadioButton)findViewById(R.id.about);
        home = (RadioButton)findViewById(R.id.homes);
        categories = (RadioButton)findViewById(R.id.categories);
        profile = (RadioButton)findViewById(R.id.profile);
        about.setCompoundDrawablesWithIntrinsicBounds( 0,R.drawable.blueabout, 0,0);
        about.setTextColor(Color.parseColor("#007eff"));

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
        tvreact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("getssss", PreferenceManager.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN) + "///" + PreferenceManager.getStringValue(Preferences.USER_EMAIL));
                if (!PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
                    Intent    in = new Intent(getBaseContext(), ConnectwithPodActivity.class);
                    startActivity(in);
                } else {
                    showAlertDialog();
                }


            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }
    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AboutActivity.this);
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

}