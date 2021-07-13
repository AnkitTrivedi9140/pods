package com.example.podsstore.addtocart;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.drower.ShowMakeofferActivity;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;

import java.io.File;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankTransferActivity extends AppCompatActivity {
    DatePickerDialog datePicker;
    Uri fileUrifund;
    TextView btnsave;
    EditText date;
    EditText ettransactionid;
    EditText   etproof;
    EditText etremarka;

    @Override
    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_transfer);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Proof of Funds");


         btnsave =  findViewById(R.id.tvsavepwd);

         date = findViewById(R.id.etdate);

         ettransactionid =findViewById(R.id.ettransationid);
           etproof = findViewById(R.id.etproof);
         etremarka = findViewById(R.id.etremarks);

        etproof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);
            }
        });


        final Calendar calendar = Calendar.getInstance();

        // initialising the layout

        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);


        datePicker = new DatePickerDialog(BankTransferActivity.this);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(BankTransferActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
                        // adding the selected date in the edittext
                        date.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);

                // set maximum date to be selected as today
                datePicker.getDatePicker().setMaxDate(calendar.getTimeInMillis());

                // show the dialog
                datePicker.show();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String remarks = etremarka.getText().toString().trim();
                String dates = date.getText().toString().trim();
                String transaction = ettransactionid.getText().toString().trim();
                String proof = etproof.getText().toString().trim();

                if (TextUtils.isEmpty(dates)) {
                    date.setError("date Can't Blank!");
                } else if (TextUtils.isEmpty(transaction)) {
                    ettransactionid.setError("transaction id Can't Blank!");
                } else if (TextUtils.isEmpty(proof)) {
                    etproof.setError("proof Can't Blank!");
                } else if (TextUtils.isEmpty(remarks)) {
                    etremarka.setError("remarks Can't Blank!");
                }

                //   loadData(et.getText().toString().trim());
                else {

                    uploadDatarerurn(fileUrifund, transaction, PreferenceManagerss.getStringValue(Preferences.USER_EMAIL), remarks);
                    //  placeorder("","","","","","","","");

//                    Intent i=new Intent(getApplicationContext(),PaymentActivity.class);
//                    startActivity(i);
//                    finish();
                }

            }
        });

    }
    @SuppressLint("CheckResult")
    private void uploadDatarerurn(Uri fileUri, String transactionid, String emails, String remarkss) {
        //   progressBar.setVisibility(View.VISIBLE);

        File file = new File(convertMediaUriToPath(fileUri));
        // RequestBody requestUserEmailId = RequestBody.create(MediaType.parse("multipart/form-data"), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));
        MultipartBody.Part requesestImage = null;

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        requesestImage = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        RequestBody txnid = RequestBody.create(MediaType.parse("multipart/form-data"), transactionid);
        RequestBody email = RequestBody.create(MediaType.parse("multipart/form-data"), emails);
        RequestBody remarks = RequestBody.create(MediaType.parse("multipart/form-data"), remarkss);

        ApiClient.getApiClient().uploadImageproofoffunds(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), requesestImage, txnid, email, remarks).enqueue(new Callback<CreateLoginUserResponse>() {
            @Override
            public void onResponse(Call<CreateLoginUserResponse> call, Response<CreateLoginUserResponse> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getimage", String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    CreateLoginUserResponse list = response.body();

                    Log.e("getimageurl", String.valueOf(list.toString()));
                    Toast.makeText(getApplicationContext(), list.getMessage(), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    startActivity(intent);
                    finish();

                }
            }

            @Override
            public void onFailure(Call<CreateLoginUserResponse> call, Throwable t) {
                Log.e("onerrors", t.getMessage());
                //progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
    public String convertMediaUriToPath(Uri uri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return path;

    }
    @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:

                    onBackPressed();
                    return true;
            }

            return super.onOptionsItemSelected(item);
        }

        @Override
        public void onBackPressed() {
            super.onBackPressed();


            Intent intent = new Intent(getApplicationContext(), SelectAddressActivity.class);
            startActivity(intent);
            finish();
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Bundle extras = imageReturnedIntent.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");

                    // ivuser.setImageBitmap(imageBitmap);
                }

                break;
            case 1:
                if (resultCode == RESULT_OK) {

                    Uri selectedImage = imageReturnedIntent.getData();


                    File file = new File(selectedImage.getPath());
                    fileUrifund = selectedImage;
                    // uploadDatarerurn(selectedImage);
                    etproof.setText(selectedImage.getPath());
                    // ivuser.setImageURI(selectedImage);
                }
                break;

        }
    }

}