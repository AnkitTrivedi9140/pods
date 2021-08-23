package com.example.podsstore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.SslErrorHandler;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.podsstore.addtocart.SelectAddressActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.CheckoutRequest;
import com.example.podsstore.data.request.EditMakeOfferRequest;
import com.example.podsstore.data.request.NotificationRequest;
import com.example.podsstore.data.request.PlaceOrderNotificationRequest;
import com.example.podsstore.data.request.PlaceOrderRequest;
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.data.response.CheckoutResponse;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.drower.AddressesActivity;
import com.example.podsstore.notification.ApiClientNoti;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.example.podsstore.profile.ProfileActivity;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hbb20.CountryCodePicker;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.PaymentIntentResult;
import com.stripe.android.Stripe;
import com.stripe.android.model.ConfirmPaymentIntentParams;
import com.stripe.android.model.PaymentIntent;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.view.CardInputWidget;
import com.stripe.android.view.CardMultilineWidget;


import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import okhttp3.OkHttpClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CheckoutActivityJava extends AppCompatActivity {

    private static final String BACKEND_URL = "https://pods.market/PodsStoreAPI/paymentRest/placeOrderApp?userEmailId=ankittrivedi9140@gmail.com&addressId=1";
    // private static final String STRIPE_PUBLISHABLE_KEY = "pk_test_51It6KYB4QGW1QrwN8uSaeQuRPEsham3BM4z7B2HgYezxen1p6sQOMqdY0l1Pw99GH7nrTgmzFMdycjhncQhDPXfe00l8DlxB3G";

    private static final String STRIPE_PUBLISHABLE_KEY = "pk_live_51It6KYB4QGW1QrwNpUBWGHpThuen6sVHvcXkDzksy2pA3eQHXyLxT07wnPGwkrfztwLskpAIIKNsggj7mpdVl7E200fSo5PHa1";

    TextView amounttotal,eachprice;
    private Stripe stripe;
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_java);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Add Card Details");
        eachprice = findViewById(R.id.eachprice);
        amounttotal = findViewById(R.id.amounttotal);
        webview = findViewById(R.id.webview);

        stripe = new Stripe(
                getApplicationContext(),
                Objects.requireNonNull(STRIPE_PUBLISHABLE_KEY)
        );
        // startCheckout();

        if (getIntent().getStringExtra("offerid") != null) {
            makeofferplaceorder();
        } else if (getIntent().getStringExtra("getbuynowqty") != null) {
            singleproductdetails();
        } else {

            loadData();
        }

       // regNotiplaceorder();
    }

    @SuppressLint("CheckResult")
    private void singleproductdetails() {

        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN) + "????" + getIntent().getStringExtra("userid"));

        ApiClient.getApiClient().getproductsdetails(getIntent().getStringExtra("userid")).enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, retrofit2.Response<List<ProductResponse>> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getMaterialMasters", String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    List<ProductResponse> list = response.body();
                    //  Toast.makeText(getApplicationContext(),"Order placed successfully",Toast.LENGTH_SHORT).show();
                    //  int selectedId = radioGroup.getCheckedRadioButtonId();

                    // find the radio button by returned id
                    //RadioButton radioButton = (RadioButton) findViewById(selectedId);

                    //  placeorder("1", String.valueOf(list.get(0).getId().toString()), String.valueOf(list.get(0).getProdname()), String.valueOf(list.get(0).getImageurl()), getIntent().getStringExtra("getbuynowqty"), String.valueOf(list.get(0).getPrice()), String.valueOf(list.get(0).getPrice().toString()), radioButton.getText().toString());
                    Double totalPrice = 0.0;
                    Double eachPrice = 0.0;


                    totalPrice += (Float.valueOf(String.valueOf(list.get(0).getPrice().toString())) * Float.valueOf(String.valueOf(getIntent().getStringExtra("getbuynowqty"))));


                    DecimalFormat df = new DecimalFormat();
                    df.setMaximumFractionDigits(2);
                    eachPrice = totalPrice / Float.valueOf(String.valueOf(getIntent().getStringExtra("getbuynowqty")));
                    amounttotal.setText(String.valueOf("$" + df.format(totalPrice)));
                    eachprice.setText("Qty " + String.valueOf(getIntent().getStringExtra("getbuynowqty")) + ", " + "$" + String.valueOf(df.format(eachPrice)) + " each");

                    placeordersingle(String.valueOf(list.get(0).getId()), getIntent().getStringExtra("getbuynowqty"));
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
    private void loadData() {

        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN) + "///" + PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));

        ApiClient.getApiClient().getcartdetails(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<List<CartResponse>>() {
            @Override
            public void onResponse(retrofit2.Call<List<CartResponse>> call, retrofit2.Response<List<CartResponse>> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("cartaaa", String.valueOf(response.body()));
                if (response.isSuccessful()) {
                    List<CartResponse> list = response.body();
                    Log.e("list", String.valueOf(list));
                    //     getSupportActionBar().setTitle("Cart" + " (" + list.size() + ")");

                    List<CheckoutRequest> req = new ArrayList<>();

                    if (list != null) {

                        if (list.size() > 0) {

                            Double totalPrice = 0.0;
                            int totalqty = 0;
                            Double eachPrice = 0.0;
                            DecimalFormat df = new DecimalFormat();
                            df.setMaximumFractionDigits(2);
                            for (int i = 0; i < list.size(); i++) {
                                CheckoutRequest check = new CheckoutRequest();
                                check.setProductid(String.valueOf(list.get(i).getProductid()));
                                check.setQuantity(String.valueOf(list.get(i).getQty()));

                                req.add(check);

                                totalqty += list.get(i).getQty();
                                totalPrice += (Float.valueOf(String.valueOf(list.get(i).getPrice().toString())) * Float.valueOf(String.valueOf(list.get(i).getQty().toString())));
                                eachPrice = totalPrice / totalqty;
                                amounttotal.setText("$" + String.valueOf(df.format(totalPrice)));
                                eachprice.setText("Qty " + String.valueOf(totalqty) + ", " + "$" + String.valueOf(df.format(eachPrice)) + " each");
                                Log.d("onResponse: Qty", String.valueOf(totalqty));
                            }

                            placeorder(req);
                            Log.d("onResponse: log", String.valueOf(req));
                        }

                    }

                }
            }

            @Override
            public void onFailure(Call<List<CartResponse>> call, Throwable t) {
                Log.e("onerrors", t.getMessage());
            }
        });
    }

    @SuppressLint("CheckResult")
    private void placeorder(List<CheckoutRequest> req) {

       /* List<CheckoutRequest> list = new ArrayList<>();

        CheckoutRequest r = new CheckoutRequest();
        r.setProductid(productid);
        r.setQuantity(qty);



        list.add(r);*/


        Log.e("postDatadddd", new Gson().toJson(req));

        ApiClient.getApiClient().editmakeoffercheckout(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL), "1", req)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<retrofit2.Response<CheckoutResponse>>() {
                    @Override
                    public void onSuccess(retrofit2.Response<CheckoutResponse> response) {

                        // binding.progressbar.setVisibility(View.GONE);


                        Log.e("postDatacart", String.valueOf(response.code()));
                        if (response.isSuccessful()) {
                            CheckoutResponse rer = response.body();
                            Button payButton = findViewById(R.id.payButton);
                            payButton.setOnClickListener((View view) -> {
                                CardMultilineWidget cardInputWidget = findViewById(R.id.cardInputWidget);
                                PaymentMethodCreateParams params = cardInputWidget.getPaymentMethodCreateParams();
                                if (params != null) {
                                    ConfirmPaymentIntentParams confirmParams = ConfirmPaymentIntentParams
                                            .createWithPaymentMethodCreateParams(params, rer.getPaymentIntent());
                                    stripe.confirmPayment(CheckoutActivityJava.this, confirmParams);
                                }
                            });

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
    private void placeordersingle(String productid, String qty) {

        List<CheckoutRequest> list = new ArrayList<>();

        CheckoutRequest r = new CheckoutRequest();
        r.setProductid(productid);
        r.setQuantity(qty);


        list.add(r);


        // Log.e("postDatadddd", new Gson().toJson(req));

        ApiClient.getApiClient().editmakeoffercheckout(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL), "1", list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<retrofit2.Response<CheckoutResponse>>() {
                    @Override
                    public void onSuccess(retrofit2.Response<CheckoutResponse> response) {

                        // binding.progressbar.setVisibility(View.GONE);


                        Log.e("postDatacode", String.valueOf(response.code()));
                        if (response.isSuccessful()) {
                            CheckoutResponse rer = response.body();
                            Button payButton = findViewById(R.id.payButton);
                            payButton.setOnClickListener((View view) -> {
                                CardMultilineWidget cardInputWidget = findViewById(R.id.cardInputWidget);
                                PaymentMethodCreateParams params = cardInputWidget.getPaymentMethodCreateParams();
                                if (params != null) {
                                    ConfirmPaymentIntentParams confirmParams = ConfirmPaymentIntentParams
                                            .createWithPaymentMethodCreateParams(params, rer.getPaymentIntent());
                                    stripe.confirmPayment(CheckoutActivityJava.this, confirmParams);
                                }
                            });

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
    private void makeofferplaceorder() {


        // Log.e("postDatadddd", new Gson().toJson(req));

        ApiClient.getApiClient().makeoffercheckout(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL), getIntent().getStringExtra("offerid"), "pay online", "888")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<retrofit2.Response<CheckoutResponse>>() {
                    @Override
                    public void onSuccess(retrofit2.Response<CheckoutResponse> response) {

                        // binding.progressbar.setVisibility(View.GONE);


                        Log.e("postData", String.valueOf(response.body()));
                        if (response.isSuccessful()) {
                            CheckoutResponse rer = response.body();
                            Button payButton = findViewById(R.id.payButton);
                            payButton.setOnClickListener((View view) -> {
                                CardMultilineWidget cardInputWidget = findViewById(R.id.cardInputWidget);
                                PaymentMethodCreateParams params = cardInputWidget.getPaymentMethodCreateParams();
                                if (params != null) {
                                    ConfirmPaymentIntentParams confirmParams = ConfirmPaymentIntentParams
                                            .createWithPaymentMethodCreateParams(params, rer.getPaymentIntent());
                                    stripe.confirmPayment(CheckoutActivityJava.this, confirmParams);
                                }
                            });

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

    private void displayAlert(@NonNull String title,
                              @Nullable String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message);
        builder.setPositiveButton("Ok", null);
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Handle the result of stripe.confirmPayment
        Log.d("onActivityResult: ", String.valueOf(requestCode) + "///" + String.valueOf(resultCode));

        stripe.onPaymentResult(requestCode, data, new PaymentResultCallback(this));


    }


    private final class PaymentResultCallback
            implements ApiResultCallback<PaymentIntentResult> {
        @NonNull
        private final WeakReference<CheckoutActivityJava> activityRef;

        PaymentResultCallback(@NonNull CheckoutActivityJava activity) {
            activityRef = new WeakReference<>(activity);
        }

        @Override
        public void onSuccess(@NonNull PaymentIntentResult result) {
            final CheckoutActivityJava activity = activityRef.get();
            if (activity == null) {
                return;
            }
            PaymentIntent paymentIntent = result.getIntent();
            PaymentIntent.Status status = paymentIntent.getStatus();
            String clientSecret = String.valueOf(paymentIntent.getClientSecret());
            if (status == PaymentIntent.Status.Succeeded) {
                // Payment completed successfully
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
//                activity.displayAlert(
//                        "Payment completed",
//                        gson.toJson(paymentIntent)
//
//                );
                Log.d("onSuccessdddd: ", String.valueOf(clientSecret));
                String offerurl = "https://pods.market/PodsStoreAPI/paymentRest/successoffer?session_id=" + clientSecret;

                String url = "https://pods.market/PodsStoreAPI/paymentRest/success?session_id=" + clientSecret;
                if (getIntent().getStringExtra("offerid") != null) {
                    webview.setVisibility(View.VISIBLE);
                    webview.loadUrl(offerurl);
                    showAlertDialog();
                    regNotiplaceorder();
                    webview.setWebViewClient(new WebViewClient() {
                        @Override
                        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                            handler.proceed();
                        }
                    });

                } else {
                    webview.setVisibility(View.VISIBLE);
                    webview.loadUrl(url);
                    showAlertDialog();
                    regNotiplaceorder();
                    webview.setWebViewClient(new WebViewClient() {
                        @Override
                        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                            handler.proceed();
                        }
                    });


                }

            } else if (status == PaymentIntent.Status.RequiresPaymentMethod) {
                // Payment failed – allow retrying using a different payment method
                activity.displayAlert(
                        "Payment failed",
                        Objects.requireNonNull(paymentIntent.getLastPaymentError()).getMessage()
                );
                String offerurl = "https://pods.market/PodsStoreAPI/paymentRest/failoffer?session_id=" + clientSecret;

                String url = "https://pods.market/PodsStoreAPI/paymentRest/fail?session_id=" + clientSecret;
                if (getIntent().getStringExtra("offerid") != null) {
                    webview.setVisibility(View.VISIBLE);
                    webview.loadUrl(offerurl);
                    showAlertDialog();
                    regNotiplaceorder();
                    webview.setWebViewClient(new WebViewClient() {
                        @Override
                        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                            handler.proceed();
                        }
                    });

                } else {
                    webview.setVisibility(View.VISIBLE);
                    webview.loadUrl(url);
                    showAlertDialogfailed();
                    webview.setWebViewClient(new WebViewClient() {
                        @Override
                        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                            handler.proceed();
                        }
                    });
                }

            }
        }

        @Override
        public void onError(@NonNull Exception e) {
            final CheckoutActivityJava activity = activityRef.get();

            Log.d( "onError: ",String.valueOf(e));
            if (activity == null) {
                return;
            }

            showAlertDialogfailed();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CheckoutActivityJava.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showAlertDialog() {
        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(CheckoutActivityJava.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.paymentsuccessdialog, null);


        alertDialog.setView(customLayout);
        TextView btnsave = (TextView) customLayout.findViewById(R.id.tvsave);
        ImageView cut = customLayout.findViewById(R.id.successgif);


        android.app.AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);


        Glide.with(this).load(R.drawable.successpay).into(cut);
//        cut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alert.dismiss();
//            }
//        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });
        alert.show();
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


    private void showAlertDialogfailed() {
        android.app.AlertDialog.Builder alertDialog = new android.app.AlertDialog.Builder(CheckoutActivityJava.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.paymentfaileddialog, null);


        alertDialog.setView(customLayout);
        TextView btnsave = (TextView) customLayout.findViewById(R.id.tvsave);
        ImageView cut = customLayout.findViewById(R.id.successgif);


        android.app.AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);


        Glide.with(this).load(R.drawable.error).into(cut);
//        cut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alert.dismiss();
//            }
//        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });
        alert.show();
    }
    @SuppressLint("CheckResult")
    private void regNotiplaceorder() {

        List<PlaceOrderNotificationRequest> list = new ArrayList<>();

        PlaceOrderNotificationRequest r = new PlaceOrderNotificationRequest();
        Log.d("regNotiplaceorder",String.valueOf(FirebaseInstanceId.getInstance().getToken()));
        r.setGcmtoken(FirebaseInstanceId.getInstance().getToken());

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
                            Toast.makeText(getApplicationContext(), successResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                            Intent login = new Intent(CreateAccountActivity.this, SplashActivity.class);
//                            startActivity(login);

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

                        Log.e("onError: " , e.getMessage());
                        Toast.makeText(getApplicationContext(), "server error", Toast.LENGTH_SHORT).show();


                    }
                });


    }

    private void testurl(String url) {
        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion

                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        //  Toaster.show(getApplicationContext(), "Enter url is not correct!");

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}