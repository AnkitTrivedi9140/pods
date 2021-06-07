package com.example.podsstore.drower;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.example.podsstore.getorder.MyOrderActivity;

public class TermsActivity extends AppCompatActivity {
TextView tvprivacytext,tvyouraccounttxt;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        getSupportActionBar().setTitle("Terms And Conditions");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvprivacytext=findViewById(R.id.tvprivacytext);
        tvyouraccounttxt=findViewById(R.id.tvyouraccounttxt);



        SpannableString ss = new SpannableString(tvprivacytext.getText().toString());
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(TermsActivity.this, PrivacyActivity.class));
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
        ss.setSpan(clickableSpan, 18, 36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        tvprivacytext.setText(ss);
        tvprivacytext.setMovementMethod(LinkMovementMethod.getInstance());
        tvprivacytext.setHighlightColor(Color.TRANSPARENT);

        SpannableString ss1 = new SpannableString(tvyouraccounttxt.getText().toString());
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                //startActivity(new Intent(TermsActivity.this, PrivacyActivity.class));
                Uri uri = Uri.parse("https://www.pods.market "); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(true);
            }
        };
        ss1.setSpan(clickableSpan1, 328, 355, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        tvyouraccounttxt.setText(ss1);
        tvyouraccounttxt.setMovementMethod(LinkMovementMethod.getInstance());
        tvyouraccounttxt.setHighlightColor(Color.TRANSPARENT);

//doTask();
    }



    protected void doTask(){
        // Specify the text/word to highlight inside TextView
        String textToHighlight = "Our Privacy Policy";

        // Construct the formatted text
        String replacedWith = "<font color='red'>" + textToHighlight + "</font>";

        // Get the text from TextView
        String originalString = tvprivacytext.getText().toString();

        // Replace the specified text/word with formatted text/word
        String modifiedString = originalString.replaceAll(textToHighlight,replacedWith);

        // Update the TextView text
        tvprivacytext.setText(Html.fromHtml(modifiedString));
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
}