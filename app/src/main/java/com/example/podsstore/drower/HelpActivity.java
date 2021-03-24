package com.example.podsstore.drower;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.addtocart.AddToCartActivity;
import com.example.podsstore.product.ProductListActivity;

public class HelpActivity extends AppCompatActivity {
TextView tvhelpheadingtxt,tvhelpheading,podno,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        tvhelpheading=findViewById(R.id.tvhelpheading) ;
        tvhelpheadingtxt=findViewById(R.id.tvhelpheadingtxt) ;
        podno=findViewById(R.id.podno) ;
        email=findViewById(R.id.email) ;
      tvhelpheading.setText(getIntent().getStringExtra("question"));
        tvhelpheadingtxt.setText(getIntent().getStringExtra("ans"));
        podno.setOnClickListener(new View.OnClickListener() {
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


//
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                callIntent.setData(Uri.parse("tel:"+"9140129967"));
//                startActivity(callIntent);
            }
        });
        email.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Intent intent = new Intent(Intent.ACTION_SEND);
                                         intent.setType("plain/text");
                                         intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "info@podshealth.com" });
                                         intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                                         intent.putExtra(Intent.EXTRA_TEXT, "mail body");
                                         startActivity(Intent.createChooser(intent, ""));
                                     }
                                 }
        );
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(getApplicationContext(), HelpAndFAQActivity.class);
                startActivity(intent);
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(getApplicationContext(), HelpAndFAQActivity.class);
        startActivity(intent);
        finish();
    }
}