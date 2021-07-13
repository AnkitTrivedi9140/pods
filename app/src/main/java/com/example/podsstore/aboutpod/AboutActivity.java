package com.example.podsstore.aboutpod;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.SplashActivity;
import com.example.podsstore.category.CategoryActivity;
import com.example.podsstore.drower.FeedbackActivity;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.profile.ProfileActivity;

public class AboutActivity extends AppCompatActivity {
    RadioGroup radioGroup1;
    RadioButton home,categories,profile,about;
    TextView tvreact,tvheadingpementmethod,tvaddnumber;
    ImageView ivinsta,ivtwiter,ivfacebook,ivyoutube,ivlinked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        PreferenceManagerss.init(AboutActivity.this);
        getSupportActionBar().setTitle("About POD");
        radioGroup1=(RadioGroup)findViewById(R.id.radioGroup1);
        tvreact=findViewById(R.id.tvreact);
        ivinsta=findViewById(R.id.ivinsta);
        tvaddnumber=findViewById(R.id.tvaddnumber);
        ivtwiter=findViewById(R.id.ivtwiter);
        ivfacebook=findViewById(R.id.ivfacebook);
        ivyoutube=findViewById(R.id.ivyoutube);
        ivlinked=findViewById(R.id.ivlinked);

        tvheadingpementmethod=findViewById(R.id.tvheadingpementmethod);
        about = (RadioButton)findViewById(R.id.about);
        home = (RadioButton)findViewById(R.id.homes);
        categories = (RadioButton)findViewById(R.id.categories);
        profile = (RadioButton)findViewById(R.id.profile);
        about.setCompoundDrawablesWithIntrinsicBounds( 0,R.drawable.blueabout, 0,0);
        about.setTextColor(Color.parseColor("#007eff"));
        tvaddnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getApplicationContext(),"ssdfsdfsdfsdf",Toast.LENGTH_SHORT).show();

                // Use format with "tel:" and phoneNumber created is
                // stored in u.
                Uri u = Uri.parse("tel:" + "+1 604 912 0520");

                // Create the intent and set the data for the
                // intent as the phone number.
                Intent i = new Intent(Intent.ACTION_DIAL, u);

                try
                {
                    // Launch the Phone app's dialer with a phone
                    // number to dial a call.
                    startActivity(i);
                }
                catch (SecurityException s)
                {
                    // show() method display the toast with
                    // exception message.
                    Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_LONG)
                            .show();
                }

            }
        });
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

                        if (!PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
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
                Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN) + "///" + PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));
                if (!PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
                    Intent    in = new Intent(getBaseContext(), FeedbackActivity.class);
                    startActivity(in);
                } else {
                    showAlertDialog();
                }


            }
        });
        tvheadingpementmethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent    in = new Intent(getBaseContext(), CategoryActivity.class);
                startActivity(in);
                finish();
            }
        });

        ivinsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("https://www.instagram.com/podshealth/");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try {
                    startActivity(likeIng);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/podshealth/")));
                }
            }
        });
        ivtwiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://mobile.twitter.com/LtdPods")));
            }
        });
        ivfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getPackageManager().getPackageInfo("com.facebook.katana", 0);
                    startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/podshealth/")));
                } catch (Exception e) {
                    startActivity( new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/podshealth/")));
                }
            }
        });
        ivyoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtube.com/channel/UCijKjwEbM0SFX7wCnmog14Q"));
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.setPackage("com.google.android.youtube");
//                startActivity(intent);

                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/channel/UCijKjwEbM0SFX7wCnmog14Q"));
                try {
                    AboutActivity.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });
        ivlinked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.linkedin.com/company/pods-group-ltd";
                Intent linkedInAppIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                linkedInAppIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                startActivity(linkedInAppIntent);

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