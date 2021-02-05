package com.example.podsstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.podsstore.product.ProductListActivity;

public class MainActivity extends AppCompatActivity {
private TextView toproduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toproduct=findViewById(R.id.toproduct);
        toproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, ProductListActivity.class);
                startActivity(i);
            }
        });
    }
}