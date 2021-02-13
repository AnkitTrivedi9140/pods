package com.example.podsstore.profile;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.aboutpod.AboutActivity;
import com.example.podsstore.category.CategoryActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.data.response.ProfileResponses;
import com.example.podsstore.prefs.PreferenceManager;
import com.example.podsstore.prefs.Preferences;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    RadioGroup radioGroup1;
    RadioButton home,categories,profile,about;
    TextView tvname,tvemail,tvaddress,tvaddressedit,tvmobile,tvmobileedit,tvemailtxt,tvemailedit,tvpassword,tvpasswordedit;
    CircularImageView ivuser,ivcamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("My Profile");
        getSupportActionBar().setElevation(0);
        radioGroup1=(RadioGroup)findViewById(R.id.radioGroup1);
        about = (RadioButton)findViewById(R.id.about);
        home = (RadioButton)findViewById(R.id.homes);
        categories = (RadioButton)findViewById(R.id.categories);
        profile = (RadioButton)findViewById(R.id.profile);
        tvname = findViewById(R.id.tvname);
        tvemail = findViewById(R.id.tvemail);
        tvaddress = findViewById(R.id.tvaddress);
        tvaddressedit = findViewById(R.id.tvaddressedit);
        tvmobile = findViewById(R.id.tvmobile);
        tvmobileedit = findViewById(R.id.tvmobileedit);
        tvemailtxt = findViewById(R.id.tvemailtxt);
        tvemailedit = findViewById(R.id.tvemailedit);
        tvpassword = findViewById(R.id.tvpassword);
        tvpasswordedit = findViewById(R.id.tvpasswordedit);
        ivuser = findViewById(R.id.ivlogo);
        ivcamera = findViewById(R.id.ivcamera);

        profile.setCompoundDrawablesWithIntrinsicBounds( 0,R.drawable.blueprofile, 0,0);
        profile.setTextColor(Color.parseColor("#007eff"));

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

//                        in = new Intent(getBaseContext(),CategoryActivity.class);
//                        startActivity(in);
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

        ivcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);


//                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(takePicture, 0);
            }
        });

    //  loadData();
    }
    @SuppressLint("CheckResult")
    private void loadData() {

        ApiClient.getApiClient().profile(PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN),PreferenceManager.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<List<ProfileResponses>>() {
            @Override
            public void onResponse(Call<List<ProfileResponses>> call, Response<List<ProfileResponses>> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getprofile",String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    List<ProfileResponses> list = response.body();

                    for (int i = 0; i < list.size(); i++) {


//                        tvProductname.setText(list.get(i).getProdname());
//                        tvProductprice.setText("$_"+list.get(i).getPrice());
//                        tvdetails.setText(list.get(i).getDescription());
//                        tvfeature.setText(list.get(i).getFeature());
//                        tvfunction.setText(list.get(i).getFunctions());

                        if (list != null) {


                        }

                    }

                }
            }
            @Override
            public void onFailure(Call<List<ProfileResponses>> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
            }
        });
    }

@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Bundle extras = imageReturnedIntent.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    ivuser.setImageBitmap(imageBitmap);
                }

                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    ivuser.setImageURI(selectedImage);
                }
                break;
        }
    }
}