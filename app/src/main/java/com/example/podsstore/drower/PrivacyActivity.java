package com.example.podsstore.drower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;

public class PrivacyActivity extends AppCompatActivity {
TextView tvcookiestext,tvaccessreview,tvadditional,tvquestion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        getSupportActionBar().setTitle("Global Privacy Policy");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvcookiestext=findViewById(R.id.tvcookiestext);
        tvaccessreview=findViewById(R.id.tvaccessreview);
        tvadditional=findViewById(R.id.tvadditional);
        tvquestion=findViewById(R.id.tvquestion);

        SpannableString ss6 = new SpannableString(tvquestion.getText().toString());
        ClickableSpan clickableSpan6 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                //startActivity(new Intent(TermsActivity.this, PrivacyActivity.class));
                Uri uri = Uri.parse("https://pods.market/reviewUs"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
        ss6.setSpan(clickableSpan6, 150, 178, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        tvquestion.setText(ss6);
        tvquestion.setMovementMethod(LinkMovementMethod.getInstance());
        tvquestion.setHighlightColor(Color.TRANSPARENT);










        SpannableString ss1 = new SpannableString(tvcookiestext.getText().toString());
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                //startActivity(new Intent(TermsActivity.this, PrivacyActivity.class));
                Uri uri = Uri.parse("www.google.com/policies/privacy/partners/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
        ss1.setSpan(clickableSpan1, 2691, 2734, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        tvcookiestext.setText(ss1);
        tvcookiestext.setMovementMethod(LinkMovementMethod.getInstance());
        tvcookiestext.setHighlightColor(Color.TRANSPARENT);



        SpannableString ss = new SpannableString(tvaccessreview.getText().toString());
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                //startActivity(new Intent(TermsActivity.this, PrivacyActivity.class));
                Uri uri = Uri.parse("https://pods.market/reviewUs"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
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
                //startActivity(new Intent(TermsActivity.this, PrivacyActivity.class));
                Uri uri = Uri.parse("support@pods.market"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
        ss.setSpan(clickableSpan, 181, 209, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clickableSpan3, 234, 253, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvaccessreview.setText(ss);
        tvaccessreview.setMovementMethod(LinkMovementMethod.getInstance());
        tvaccessreview.setHighlightColor(Color.TRANSPARENT);





        SpannableString additional = new SpannableString(tvadditional.getText().toString());
        ClickableSpan clickableSpan4 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                //startActivity(new Intent(TermsActivity.this, PrivacyActivity.class));
                Uri uri = Uri.parse("https://pods.market/contact"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
        additional.setSpan(clickableSpan4, 6574, 6601, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
       // additional.setSpan(clickableSpan4, 1917, 2222, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ClickableSpan clickableSpan5 = new ClickableSpan() {
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
        additional.setSpan(clickableSpan5, 6644, 6663, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        tvadditional.setText(additional);
        tvadditional.setMovementMethod(LinkMovementMethod.getInstance());
        tvadditional.setHighlightColor(Color.TRANSPARENT);




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



    protected void doTask(){
        // Specify the text/word to highlight inside TextView
        String textToHighlight = "NAI";

        // Construct the formatted text
        String replacedWith = "<font color='red'>" + textToHighlight + "</font>";

        // Get the text from TextView
        String originalString = tvcookiestext.getText().toString();

        // Replace the specified text/word with formatted text/word
        String modifiedString = originalString.replaceAll(textToHighlight,replacedWith);

        // Update the TextView text
        tvcookiestext.setText(Html.fromHtml(modifiedString));
    }
}