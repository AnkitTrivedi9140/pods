package com.example.podsstore.addtocart;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.podsstore.CheckoutActivityJava;
import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.local.viewmodel.QuantityViewModel;
import com.example.podsstore.data.request.NotificationRequest;
import com.example.podsstore.data.request.PlaceOrderNotificationRequest;
import com.example.podsstore.data.request.PlaceOrderRequest;
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.drower.ShowMakeofferActivity;
import com.example.podsstore.notification.ApiClientNoti;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity implements AddtocartAdapter.DataTransferInterface {
    private RecyclerView recyclerView;
    private AddtocartAdapter productListAdapter;
    TextView placeorderbtn, placeorderbtnbuynow, placeordermakeoffer;
    private QuantityViewModel viewModel;
    RadioGroup radioGroup;
    RadioButton radioButton;
    final Calendar myCalendar = Calendar.getInstance();
    String mode;
    String path;
    String txnid;
    Uri fileUrifund;
    EditText etproof;
    DatePickerDialog datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        placeorderbtn = findViewById(R.id.placeorderbtn);
        placeordermakeoffer = findViewById(R.id.placeordermakeoffer);
        radioGroup = findViewById(R.id.radioGroup);
        int selectedId = radioGroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioButton = (RadioButton) findViewById(selectedId);
        List<CartResponse> listone = new ArrayList<>();
        String businesstype = radioButton.getText().toString().trim();
        mode = businesstype;
        placeorderbtnbuynow = findViewById(R.id.placeorderbtnbuynow);
        productListAdapter = new AddtocartAdapter(PaymentActivity.this, this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Payment");
        viewModel = ViewModelProviders.of(PaymentActivity.this).get(QuantityViewModel.class);
        loadData();
        if (getIntent().getStringExtra("userid") == null) {
            placeorderbtn.setVisibility(View.VISIBLE);

        } else {
            placeorderbtnbuynow.setVisibility(View.VISIBLE);
        }
        if (getIntent().getStringExtra("offerid") == null) {
            placeordermakeoffer.setVisibility(View.GONE);

        } else {
            placeordermakeoffer.setVisibility(View.VISIBLE);
        }
        placeorderbtnbuynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), CheckoutActivityJava.class);
intent.putExtra("userid",getIntent().getStringExtra("userid"));
                intent.putExtra("getbuynowqty", getIntent().getStringExtra("getbuynowqty"));

                startActivity(intent);
                finish();
  //  singleproductdetails();
            }
        });
        placeordermakeoffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makeofferplaceorder();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radiosme:

                        //showAlertDialog(path);
                        break;
                    case R.id.radiocorporate:

                        showAlertDialog(path);
                        break;
                    case R.id.radioincorporate:
                        showAlertDialog(path);
                        break;

                }
            }
        });
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
        if (getIntent().getStringExtra("offerid") == null) {
            Intent intent = new Intent(getApplicationContext(), SelectAddressActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(getApplicationContext(), ShowMakeofferActivity.class);
            startActivity(intent);
            finish();
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

                    placeorderbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Toast.makeText(getApplicationContext(),"Order placed successfully",Toast.LENGTH_SHORT).show();
                            for (int i = 0; i < list.size(); i++) {
                                CartResponse cartResponse = list.get(i);


                                Log.d("onClickgg ", list.get(i).getProductid().toString());
                                int selectedId = radioGroup.getCheckedRadioButtonId();

                                // find the radio button by returned id
                                RadioButton radioButton = (RadioButton) findViewById(selectedId);

//                                Toast.makeText(MainActivity.this,
//                                        radioButton.getText(), Toast.LENGTH_SHORT).show();


//Toast.makeText(getApplicationContext(),radioButton.getText().toString(),Toast.LENGTH_SHORT).show();
                              //  placeorder("1", String.valueOf(list.get(i).getProductid().toString()), String.valueOf(list.get(i).getProductname()), String.valueOf(list.get(i).getImageUrl()), String.valueOf(list.get(i).getQty()), String.valueOf(list.get(i).getTotalprice()), String.valueOf(list.get(i).getPrice().toString()), radioButton.getText().toString());

                                Intent in = new Intent(PaymentActivity.this,CheckoutActivityJava.class);

                                startActivity(in);
                                finish();
                            }

                        }
                    });
                    if (list != null) {


                    }

                }
            }

            @Override
            public void onFailure(Call<List<CartResponse>> call, Throwable t) {
                Log.e("onerrors", t.getMessage());
            }
        });
    }

    @SuppressLint("NewApi")
    private void showAlertDialog(String paths) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(PaymentActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.paymentmethoddialog, null);


        alertDialog.setView(customLayout);
        TextView btnsave = (TextView) customLayout.findViewById(R.id.tvsavepwd);
        ImageView cut = customLayout.findViewById(R.id.ivcut);
        EditText date = customLayout.findViewById(R.id.etdate);

        EditText ettransactionid = customLayout.findViewById(R.id.ettransationid);
        etproof = customLayout.findViewById(R.id.etproof);
        EditText etremarka = customLayout.findViewById(R.id.etremarks);


        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(true);
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


        datePicker = new DatePickerDialog(PaymentActivity.this);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(PaymentActivity.this, new DatePickerDialog.OnDateSetListener() {
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


        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
//        etproof.setText(fileUrifund.getEncodedPath());
        //   etproof.setText(paths);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String remarks = etremarka.getText().toString().trim();
                String dates = date.getText().toString().trim();
                String transaction = ettransactionid.getText().toString().trim();
                String proof = etproof.getText().toString().trim();

                txnid = transaction;
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

                    uploadDatarerurn(fileUrifund, transaction, proof, remarks);
                    //  placeorder("","","","","","","","");
                    alert.dismiss();
//                    Intent i=new Intent(getApplicationContext(),PaymentActivity.class);
//                    startActivity(i);
//                    finish();
                }

            }
        });

        alert.show();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch(requestCode){
//
//            case 7:
//
//                if(resultCode==RESULT_OK){
//
//                    String PathHolder = data.getData().getPath();
//
//                    Toast.makeText(PaymentActivity.this, PathHolder , Toast.LENGTH_LONG).show();
//path=PathHolder;
//showAlertDialog(PathHolder);
//                }
//                break;
//
//        }
//    }

//    private void placeholder(List<CartResponse> list){
//        for(CartResponse response : list){
//
//        }
//    }

    public String convertMediaUriToPath(Uri uri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return path;

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
                    //  progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<CreateLoginUserResponse> call, Throwable t) {
                Log.e("onerrors", t.getMessage());
                //progressBar.setVisibility(View.VISIBLE);
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
    private void placeorder(String orderid, String productid, String productname, String productimage, String qty, String totalprice, String subtotal, String mode) {
//Toast.makeText(getApplicationContext(),"mila nhi",Toast.LENGTH_SHORT).show();
        List<PlaceOrderRequest> list = new ArrayList<>();

        PlaceOrderRequest r = new PlaceOrderRequest();
        // r.setAddress(getIntent().getStringExtra("addressid").toString());
        r.setOrderid(orderid);
        r.setProductid(productid);
        r.setProductname(productname);
        r.setProductimage(productimage);
        r.setQuantity(qty);
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


                        Log.e("postData", String.valueOf(response.body()));
                        if (response.isSuccessful()) {

                            CreateLoginUserResponse successResponse = response.body();
                            // if(successResponse.getMessage().equalsIgnoreCase()){}
                            Log.e("onSuccesseeee", successResponse.getMessage());
                            regNoti(orderid.toString());
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
    private void regNoti(String orderid) {

        List<PlaceOrderNotificationRequest> list = new ArrayList<>();

        PlaceOrderNotificationRequest r = new PlaceOrderNotificationRequest();

        r.setGcmtoken(FirebaseInstanceId.getInstance().getToken());
        r.setOrderid("");
        list.add(r);


        Log.e("postData", new Gson().toJson(r));

        ApiClientNoti.getApiClients().ordernoti(r)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<CreateLoginUserResponse>>() {
                    @Override
                    public void onSuccess(Response<CreateLoginUserResponse> response) {

                        // binding.progressbar.setVisibility(View.GONE);


                        Log.e("onSuccess", String.valueOf(response.code()));
                        if (response.isSuccessful()) {

                            CreateLoginUserResponse successResponse = response.body();
                            //     Toast.makeText(getApplicationContext(), successResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                            Intent login = new Intent(CreateAccountActivity.this, SplashActivity.class);
//                            startActivity(login);
//                            finish();

//                            Log.e("onSuccessaa", successResponse.getChallanid());
                            if (successResponse != null) {

//                                if (successResponse.getMessage().equals("success")) {
//                                    // mappingAdapter.clear();
//
//                                }

                                //  Toaster.show(mContext, successResponse.getMessage());

                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Please check your email id...", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("onError: ", e.getMessage());
                        Toast.makeText(getApplicationContext(), "server error", Toast.LENGTH_SHORT).show();


                    }
                });


    }

    @Override
    public void onSetValues(ArrayList<String> al) {
        Toast.makeText(getApplicationContext(), al.toString(), Toast.LENGTH_SHORT).show();
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
                    int selectedId = radioGroup.getCheckedRadioButtonId();

                    // find the radio button by returned id
                    RadioButton radioButton = (RadioButton) findViewById(selectedId);

                placeorder("1", String.valueOf(list.get(0).getId().toString()), String.valueOf(list.get(0).getProdname()), String.valueOf(list.get(0).getImageurl()), getIntent().getStringExtra("getbuynowqty"), String.valueOf(list.get(0).getPrice()), String.valueOf(list.get(0).getPrice().toString()), radioButton.getText().toString());


                    if (list != null) {


                    }

                }
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
                Log.e("onerrors", t.getMessage());
            }
        });
    }


    @SuppressLint("CheckResult")
    private void makeofferplaceorder() {
        // Toast.makeText(getApplicationContext(), PreferenceManagerss.getStringValue(Preferences.USER_OTP_EMAIL) ,Toast.LENGTH_SHORT).show();
        Log.e("getfdfd", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN) + PreferenceManagerss.getStringValue(Preferences.USER_EMAIL) + getIntent().getStringExtra("offerid")
                + mode + txnid);
        //  if (!PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
        ApiClient.getApiClient().makeofferplaceorder(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL), getIntent().getStringExtra("offerid"), mode, txnid).enqueue(new Callback<CreateLoginUserResponse>() {
            //ApiClient.getApiClient().confirmotp("fff", otp).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<CreateLoginUserResponse> call, Response<CreateLoginUserResponse> response) {

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

                Log.e("onerrors", t.getMessage());
            }
        });
    }


}