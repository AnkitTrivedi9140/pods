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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.podsstore.CheckoutActivityJava;
import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.CheckoutRequest;
import com.example.podsstore.data.request.PlaceOrderRequest;
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.data.response.CheckoutResponse;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.drower.ShowMakeofferActivity;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.google.gson.Gson;
import com.stripe.android.model.ConfirmPaymentIntentParams;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.view.CardMultilineWidget;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
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
    ProgressBar progressBar;
    TextView progresstext;
    @Override
    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_transfer);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Proof of Funds");

        progressBar=findViewById(R.id.progress);
        progresstext=findViewById(R.id.progresstext);
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
        progressBar.setVisibility(View.VISIBLE);
        progresstext.setVisibility(View.VISIBLE);
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

                    if( ettransactionid.getText().toString()==null){

                    }else
                    {
                        if (getIntent().getStringExtra("getbuynowqty") != null) {
                            singleproductdetails();
                        }else if(getIntent().getStringExtra("offerid") != null){
                            makeofferplaceorder();
                        } else {

                            loadData();
                        }
                    }

//                    Toast.makeText(getApplicationContext(), getIntent().getStringExtra("getbuynowqty") , Toast.LENGTH_SHORT).show();



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
            Intent intent = new Intent(getApplicationContext(), PaymentActivity.class);
            intent.putExtra("userid", getIntent().getStringExtra("userid"));
            intent.putExtra("getbuynowqty", getIntent().getStringExtra("getbuynowqty"));
            intent.putExtra("offerid", getIntent().getStringExtra("offerid"));
            intent.putExtra("addressid",  getIntent().getStringExtra("addressid"));
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

    @SuppressLint("CheckResult")
    private void loadData() {

        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN) + "///" + PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));

        ApiClient.getApiClient().getcartdetails(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<List<CartResponse>>() {
            @Override
            public void onResponse(Call<List<CartResponse>> call, Response<List<CartResponse>> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("cartaaa", String.valueOf(response.body()));
                if (response.isSuccessful()) {
                    List<CartResponse> list = response.body();
                    Log.e("list", String.valueOf(list));

                    //     getSupportActionBar().setTitle("Cart" + " (" + list.size() + ")");
                    List<PlaceOrderRequest> req = new ArrayList<>();

                            // Toast.makeText(getApplicationContext(),"Order placed successfully",Toast.LENGTH_SHORT).show();
                            for (int i = 0; i < list.size(); i++) {
                                CartResponse cartResponse = list.get(i);
                                PlaceOrderRequest placeOrderRequest=new PlaceOrderRequest();

                                Log.d("onClickgg ", list.get(i).getProductid().toString());

//                                Toast.makeText(MainActivity.this,
//                                        radioButton.getText(), Toast.LENGTH_SHORT).show();

                                Log.d("onResponseplace","1"+ String.valueOf(list.get(i).getProductid().toString())+ String.valueOf(list.get(i).getProductname())+ String.valueOf(list.get(i).getImageUrl())+ String.valueOf(list.get(i).getQty())+String.valueOf(list.get(i).getTotalprice())+ String.valueOf(list.get(i).getPrice().toString()));
                                //Toast.makeText(getApplicationContext(),radioButton.getText().toString(),Toast.LENGTH_SHORT).show();
                                placeOrderRequest.setOrderid("22");
                           placeOrderRequest.setProductid(Long.valueOf(list.get(i).getProductid().toString()));
                                placeOrderRequest.setProductname(list.get(i).getProductname().toString());
                                placeOrderRequest.setProductimage(list.get(i).getImageUrl().toString());
                                placeOrderRequest.setQuantity(Integer.valueOf(list.get(i).getQty().toString()));
                                placeOrderRequest.setTotalprice("tot11");
                                placeOrderRequest.setSubtotal(list.get(i).getPrice().toString());



                                req.add(placeOrderRequest);
                            }
                    placeordercart(req);


                    if (list != null) {


                    }

                }
            }

            @Override
            public void onFailure(Call<List<CartResponse>> call, Throwable t) {
                Log.e("onerrors", t.getMessage());
                progressBar.setVisibility(View.GONE);
                progresstext.setVisibility(View.GONE);
            }
        });
    }
    @SuppressLint("CheckResult")
    private void placeordercart(List<PlaceOrderRequest> list ) {
//Toast.makeText(getApplicationContext(),"mila nhi",Toast.LENGTH_SHORT).show();

        // Log.e("postDatalist", txnid);


        Log.e("postData", new Gson().toJson(list));

        ApiClient.getApiClient().placeOrder(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL), getIntent().getStringExtra("addressid").toString(), "Bank Transfer", ettransactionid.getText().toString(), list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<CreateLoginUserResponse>>() {
                    @Override
                    public void onSuccess(Response<CreateLoginUserResponse> response) {

                        // binding.progressbar.setVisibility(View.GONE);

                        progressBar.setVisibility(View.GONE);
                        progresstext.setVisibility(View.GONE);
                        Log.e("postDataggg", String.valueOf(response.code()));
                        if (response.isSuccessful()) {

                            CreateLoginUserResponse successResponse = response.body();
                            // if(successResponse.getMessage().equalsIgnoreCase()){}
                            Log.e("onSuccesseeee", successResponse.getMessage());

                            //    Toast.makeText(getApplicationContext(), successResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent main = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(main);
                            finish();
                            if (successResponse != null) {


                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "server error", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("onError: ", e.getMessage());
                        Toast.makeText(getApplicationContext(), "server error", Toast.LENGTH_SHORT).show();

                    }
                });
    }
    @SuppressLint("CheckResult")
    private void placeorder(String orderid, String productid, String productname, String productimage, String qty, String totalprice, String subtotal, String mode,String txnid) {
//Toast.makeText(getApplicationContext(),"mila nhi",Toast.LENGTH_SHORT).show();
        List<PlaceOrderRequest> list = new ArrayList<>();

        PlaceOrderRequest r = new PlaceOrderRequest();
        // r.setAddress(getIntent().getStringExtra("addressid").toString());
        r.setOrderid(orderid);
        r.setProductid(Long.valueOf(productid));
        r.setProductname(productname);
        r.setProductimage(productimage);
        r.setQuantity(Integer.valueOf(qty));
        r.setTotalprice(totalprice);
        r.setSubtotal(subtotal);


        list.add(r);


        // Log.e("postDatalist", txnid);


        Log.e("postData", new Gson().toJson(list));

        ApiClient.getApiClient().placeOrder(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL), getIntent().getStringExtra("addressid").toString(), mode, txnid, list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<CreateLoginUserResponse>>() {
                    @Override
                    public void onSuccess(Response<CreateLoginUserResponse> response) {

                        // binding.progressbar.setVisibility(View.GONE);

                        progressBar.setVisibility(View.GONE);
                        progresstext.setVisibility(View.GONE);
                        Log.e("postDataggg", String.valueOf(response.code()));
                        if (response.isSuccessful()) {

                            CreateLoginUserResponse successResponse = response.body();
                            // if(successResponse.getMessage().equalsIgnoreCase()){}
                            Log.e("onSuccesseeee", successResponse.getMessage());

                            //    Toast.makeText(getApplicationContext(), successResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent main = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(main);
                            finish();
                            if (successResponse != null) {


                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "server error", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("onError: ", e.getMessage());
                        Toast.makeText(getApplicationContext(), "server error", Toast.LENGTH_SHORT).show();

                    }
                });
    }

    @SuppressLint("CheckResult")
    private void singleproductdetails() {

        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN) + getIntent().getStringExtra("userid"));

        ApiClient.getApiClient().getproductsdetails(getIntent().getStringExtra("userid")).enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getMaterialMasters", String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    List<ProductResponse> list = response.body();
                    //  Toast.makeText(getApplicationContext(),"Order placed successfully",Toast.LENGTH_SHORT).show();

                    placeorder("1", String.valueOf(list.get(0).getId().toString()), String.valueOf(list.get(0).getProdname()), String.valueOf(list.get(0).getImageurl()), getIntent().getStringExtra("getbuynowqty"), String.valueOf(list.get(0).getPrice()), String.valueOf(list.get(0).getPrice().toString()), "Bank Tranfer",ettransactionid.getText().toString().trim());


                    if (list != null) {


                    }

                }
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
                Log.e("onerrors", t.getMessage());
                progressBar.setVisibility(View.GONE);
                progresstext.setVisibility(View.GONE);
            }
        });
    }

    @SuppressLint("CheckResult")
    private void makeofferplaceorder() {

        // Toast.makeText(getApplicationContext(), PreferenceManagerss.getStringValue(Preferences.USER_OTP_EMAIL) ,Toast.LENGTH_SHORT).show();
//        Log.e("getfdfd", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN) + PreferenceManagerss.getStringValue(Preferences.USER_EMAIL) + getIntent().getStringExtra("offerid")
//                + mode + txnid);
        //  if (!PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
        ApiClient.getApiClient().makeofferplaceorder(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL), getIntent().getStringExtra("offerid"), "Bank Transfer", ettransactionid.getText().toString()).enqueue(new Callback<CreateLoginUserResponse>() {
            //ApiClient.getApiClient().confirmotp("fff", otp).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<CreateLoginUserResponse> call, Response<CreateLoginUserResponse> response) {
                progressBar.setVisibility(View.GONE);
                progresstext.setVisibility(View.GONE);
                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("onResp ", String.valueOf(response.code()));


                if (response.isSuccessful()) {

                    CreateLoginUserResponse list = response.body();

                    Toast.makeText(getApplicationContext(), list.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "No Offer Accepted for current offerid", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateLoginUserResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                progresstext.setVisibility(View.GONE);
                Log.e("onerrors", t.getMessage());
            }
        });
    }


}