package com.example.podsstore.drower;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;

public class HelpAndFAQActivity extends AppCompatActivity {
TextView tvfaq1,tvfaq2,tvfaq3,tvfaq4,tvfaq5,tvfaq6,tvfaq7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_and_f_a_q);
        tvfaq1=findViewById(R.id.tvfaq1);
        tvfaq2=findViewById(R.id.tvfaq2);
        tvfaq3=findViewById(R.id.tvfaq3);

        tvfaq4=findViewById(R.id.tvfaq4);
        tvfaq5=findViewById(R.id.tvfaq5);
        tvfaq6=findViewById(R.id.tvfaq6);
        tvfaq7=findViewById(R.id.tvfaq7);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Help / FAQ");
        tvfaq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","Who is Pods Health, is Pods Health new to the industry?");
                intent.putExtra("ans","Pods Health Inc. has been in the industry for 20 years. Our team consists of international trade and health science experts who use over 20 years of industry experience to execute lasting solutions within today world of unprecedented change.");
                startActivity(intent);
                finish();
            }
        });
        tvfaq2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","Are the products sold by Pods Health certified?");
                intent.putExtra("ans","Yes, all the products that Pods Health manufactures and/or distributes are certified by relevant destination regulations. We guarantee that we supply the best value and qualified products to our customers around the world.");

                startActivity(intent);
                finish();
            }
        });
        tvfaq3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","I want to become a partner of Pods Health, who should I contact?");
                intent.putExtra("ans","Please contact Pods Health Vancouver office.");

                startActivity(intent);
                finish();
            }
        });


        tvfaq4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);

                intent.putExtra("question","Is Pods Health a manufacture?");
                intent.putExtra("ans","Yes, Pods Health owns factories in India to make Pod products. Also, we welcome other manufacturers to become a supplier of Pods Health.");

                startActivity(intent);
                finish();
            }
        });
        tvfaq5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);

                intent.putExtra("question","Does Pods Health provide other services other than products listed on the website?");
                intent.putExtra("ans","Yes, we also provide professional consultancy service to our suppliers and partners on FDA regulations, 510K, NIOSH, CE, ISO, EPA, CDC, OTC, NDC, MEDL, UDI Submission etc. Contact our consultants for detailed information.");

                startActivity(intent);
                finish();
            }
        });
        tvfaq6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","Who are Pods Health customers?");
                intent.putExtra("ans","We supply government organizations in the US and Canada. We also supply schools, hospitals, and other corporate organizations located all over the world.");


                startActivity(intent);
                finish();
            }
        });
        tvfaq7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), HelpActivity.class);
                intent.putExtra("question","What should I do if I have questions regarding the services and product questions after my purchase? ");
                intent.putExtra("ans","For any questions regarding available services or additional product information after purchase, please contact our support team at info@podshealth.com, we will contact you within 24 hours. Please include company, contact person, purchase date, product names.");

                startActivity(intent);
                finish();
            }
        });
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