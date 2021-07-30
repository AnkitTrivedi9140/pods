package com.example.podsstore.drower;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.aboutpod.ConnectwithPodActivity;
import com.example.podsstore.addtocart.AddToCartActivity;
import com.example.podsstore.login.CreateAccountActivity;
import com.example.podsstore.product.ProductListActivity;
import com.example.podsstore.productdetails.DownloadZipActivity;

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


if(getIntent().getStringExtra("tvfaq1")==null){

}else{   if(getIntent().getStringExtra("tvfaq1").equalsIgnoreCase("tvfaq1")){
    SpannableString additional = new SpannableString(tvhelpheadingtxt.getText().toString());
    ClickableSpan clickableSpan4 = new ClickableSpan() {
        @Override
        public void onClick(View textView) {
            //startActivity(new Intent(TermsActivity.this, PrivacyActivity.class));
//            Uri uri = Uri.parse("https://pods.market/contact"); // missing 'http://' will cause crashed
//            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//            startActivity(intent);

            Intent feedback=new Intent(getApplicationContext(), FeedbackActivity.class);
            feedback.putExtra("url","https://pods.market/contact");
            startActivity(feedback);

        }
        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setUnderlineText(true);
        }
    };
    additional.setSpan(clickableSpan4, 1650, 1658, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    tvhelpheadingtxt.setText(additional);
    tvhelpheadingtxt.setMovementMethod(LinkMovementMethod.getInstance());
    tvhelpheadingtxt.setHighlightColor(Color.TRANSPARENT);}}




        if(getIntent().getStringExtra("tvfaq2")==null){

        }else{  }
        if(getIntent().getStringExtra("tvfaq3")==null){

        }else{  }
        if(getIntent().getStringExtra("tvfaq4")==null){

        }else{  }
        if(getIntent().getStringExtra("tvfaq5")==null){

        }else{
            if(getIntent().getStringExtra("tvfaq5").equalsIgnoreCase("tvfaq5")){
                SpannableString additional = new SpannableString(tvhelpheadingtxt.getText().toString());
                ClickableSpan clickableSpan4 = new ClickableSpan() {
                    @Override
                    public void onClick(View textView) {
                        //startActivity(new Intent(TermsActivity.this, PrivacyActivity.class));
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("plain/text");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "info@podshealth.com" });
                        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                        intent.putExtra(Intent.EXTRA_TEXT, "mail body");
                        startActivity(Intent.createChooser(intent, ""));
                    }
                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setUnderlineText(true);
                    }
                };
                additional.setSpan(clickableSpan4, 116, 135, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvhelpheadingtxt.setText(additional);
                tvhelpheadingtxt.setMovementMethod(LinkMovementMethod.getInstance());
                tvhelpheadingtxt.setHighlightColor(Color.TRANSPARENT);}
        }

        if(getIntent().getStringExtra("tvfaq6")==null){

        }else{  }
        if(getIntent().getStringExtra("tvfaq7")==null){

        }else{

            if(getIntent().getStringExtra("tvfaq7").equalsIgnoreCase("tvfaq7")){
                SpannableString additional = new SpannableString(tvhelpheadingtxt.getText().toString());
                ClickableSpan clickableSpan4 = new ClickableSpan() {
                    @Override
                    public void onClick(View textView) {

                        //startActivity(new Intent(TermsActivity.this, PrivacyActivity.class));
//                        Uri uri = Uri.parse("https://pods.market/productinforequirment/"); // missing 'http://' will cause crashed
//                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                        startActivity(intent);
                        Intent browserIntent = new Intent(getApplicationContext(), DownloadZipActivity.class);
                        browserIntent.putExtra("pdf","https://www.pods.market/static/media/Examples%20Permitted%20and%20Prohibited%20Listings.6c044314.pdf");
                        startActivity(browserIntent);
                    }
                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setUnderlineText(true);
                    }
                };
                ClickableSpan clickableSpan14 = new ClickableSpan() {
                    @Override
                    public void onClick(View textView) {
Toast.makeText(getApplicationContext(),"jbjhgj",Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(TermsActivity.this, PrivacyActivity.class));

                    }
                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setUnderlineText(true);
                    }
                };
                //additional.setSpan(clickableSpan14, 61, 94, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                additional.setSpan(clickableSpan4, 161, 194, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvhelpheadingtxt.setText(additional);
                tvhelpheadingtxt.setMovementMethod(LinkMovementMethod.getInstance());
                tvhelpheadingtxt.setHighlightColor(Color.TRANSPARENT);}
        }
        if(getIntent().getStringExtra("tvfaq8")==null){


        }else{

            if(getIntent().getStringExtra("tvfaq8").equalsIgnoreCase("tvfaq8")){
                SpannableString ss = new SpannableString(tvhelpheadingtxt.getText().toString());
//                ClickableSpan clickableSpan = new ClickableSpan() {
//                    @Override
//                    public void onClick(View textView) {
//
//
//                       startActivity(new Intent(HelpActivity.this, ConnectwithPodActivity.class));
////                        Uri uri = Uri.parse("https://pods.market/register"); // missing 'http://' will cause crashed
////                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
////                        startActivity(intent);
//                    }
//                    @Override
//                    public void updateDrawState(TextPaint ds) {
//                        super.updateDrawState(ds);
//                        ds.setUnderlineText(true);
//                    }
//                };
                ClickableSpan clickableSpan3 = new ClickableSpan() {
                    @Override
                    public void onClick(View textView) {
                      startActivity(new Intent(HelpActivity.this, OnlineDemoActivity.class));
//                        Uri uri = Uri.parse("https://pods.market/requestdemo"); // missing 'http://' will cause crashed
//                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                        startActivity(intent);
                    }
                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setUnderlineText(true);
                    }
                };
               // ss.setSpan(clickableSpan, 97, 105, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ss.setSpan(clickableSpan3, 259, 270, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                tvhelpheadingtxt.setText(ss);
                tvhelpheadingtxt.setMovementMethod(LinkMovementMethod.getInstance());
                tvhelpheadingtxt.setHighlightColor(Color.TRANSPARENT);}

        }
        if(getIntent().getStringExtra("tvfaq9")==null){

        }else{  }
        if(getIntent().getStringExtra("tvfaq10")==null){

        }else{

            if(getIntent().getStringExtra("tvfaq10").equalsIgnoreCase("tvfaq10")){
                SpannableString additional = new SpannableString(tvhelpheadingtxt.getText().toString());
                ClickableSpan clickableSpan4 = new ClickableSpan() {
                    @Override
                    public void onClick(View textView) {

                        //startActivity(new Intent(TermsActivity.this, PrivacyActivity.class));
                        Uri uri = Uri.parse("https://pods.market/podsadvertise/"); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setUnderlineText(true);
                    }
                };
                additional.setSpan(clickableSpan4, 34, 47, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvhelpheadingtxt.setText(additional);
                tvhelpheadingtxt.setMovementMethod(LinkMovementMethod.getInstance());
                tvhelpheadingtxt.setHighlightColor(Color.TRANSPARENT);}
        }
        if(getIntent().getStringExtra("tvfaq11")==null){

        }else{  }
        if(getIntent().getStringExtra("tvfaq12")==null){

        }else{  }
        if(getIntent().getStringExtra("tvfaq13")==null){

        }else{  }
        if(getIntent().getStringExtra("tvfaq14")==null){

        }else{



            if(getIntent().getStringExtra("tvfaq14").equalsIgnoreCase("tvfaq14")){
                SpannableString ss = new SpannableString(tvhelpheadingtxt.getText().toString());
                ClickableSpan clickableSpan = new ClickableSpan() {
                    @Override
                    public void onClick(View textView) {


                      startActivity(new Intent(HelpActivity.this, FeedbackActivity.class));
//                        Uri uri = Uri.parse("https://pods.market/contact"); // missing 'http://' will cause crashed
//                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                        startActivity(intent);
                    }
                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setUnderlineText(true);
                    }
                };
                ClickableSpan clickableSpan3 = new ClickableSpan() {
                    @Override
                    public void onClick(View textView) {
                        startActivity(new Intent(HelpActivity.this, FeedbackActivity.class));

//                        Uri uri = Uri.parse("https://pods.market/contact"); // missing 'http://' will cause crashed
//                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                        startActivity(intent);
                    }
                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setUnderlineText(true);
                    }
                };
                ss.setSpan(clickableSpan, 33, 46, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                ss.setSpan(clickableSpan3, 63, 79, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                tvhelpheadingtxt.setText(ss);
                tvhelpheadingtxt.setMovementMethod(LinkMovementMethod.getInstance());
                tvhelpheadingtxt.setHighlightColor(Color.TRANSPARENT);}


        }
        if(getIntent().getStringExtra("tvfaq15")==null){

        }else{  }
        if(getIntent().getStringExtra("tvfaq16")==null){

        }else{  }
        if(getIntent().getStringExtra("tvfaq17")==null){

        }else{  }
        if(getIntent().getStringExtra("tvfaq18")==null){

        }else{  }
        if(getIntent().getStringExtra("tvfaq19")==null){

        }else{  }
        if(getIntent().getStringExtra("tvfaq20")==null){

        }else{  }
        if(getIntent().getStringExtra("tvfaq21")==null){

        }else{  }

        if(getIntent().getStringExtra("tvfaq22")==null){

        }else{

            if(getIntent().getStringExtra("tvfaq22").equalsIgnoreCase("tvfaq22")){
                SpannableString additional = new SpannableString(tvhelpheadingtxt.getText().toString());
                ClickableSpan clickableSpan4 = new ClickableSpan() {
                    @Override
                    public void onClick(View textView) {

                        //startActivity(new Intent(TermsActivity.this, PrivacyActivity.class));
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("plain/text");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "info@podshealth.com" });
                        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
                        intent.putExtra(Intent.EXTRA_TEXT, "mail body");
                        startActivity(Intent.createChooser(intent, ""));
                    }
                    @Override
                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setUnderlineText(true);
                    }
                };
                additional.setSpan(clickableSpan4, 1257, 1272, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tvhelpheadingtxt.setText(additional);
                tvhelpheadingtxt.setMovementMethod(LinkMovementMethod.getInstance());
                tvhelpheadingtxt.setHighlightColor(Color.TRANSPARENT);}

        }
        if(getIntent().getStringExtra("tvfaq23")==null){

        }else{  }
        if(getIntent().getStringExtra("tvfaq24")==null){

        }else{  }

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