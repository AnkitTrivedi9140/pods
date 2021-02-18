package com.example.podsstore.aboutpod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.category.CategoryActivity;
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

                        in = new Intent(getBaseContext(), ProfileActivity.class);
                        startActivity(in);
                        overridePendingTransition(0, 0);
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
            Intent    in = new Intent(getBaseContext(), ConnectwithPodActivity.class);
                startActivity(in);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),AboutActivity.class);
        startActivity(i);
        finish();
    }
}