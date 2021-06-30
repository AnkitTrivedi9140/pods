package com.example.podsstore.addtocart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.podsstore.R;

import java.util.HashMap;
import java.util.Map;

public class WebViewActivity extends AppCompatActivity {
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view2);
        webview=findViewById(R.id.webview);
        webview.loadUrl("https://pods.market/PodsStoreAPI/paymentRest/success?session_id="+getIntent().getStringExtra("testVar"));
    }
}