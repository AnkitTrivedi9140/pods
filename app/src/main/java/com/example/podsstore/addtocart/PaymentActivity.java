package com.example.podsstore.addtocart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.local.viewmodel.QuantityViewModel;
import com.example.podsstore.data.request.PlaceOrderRequest;
import com.example.podsstore.data.response.CartResponse;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity implements AddtocartAdapter.DataTransferInterface {
    private RecyclerView recyclerView;
    private AddtocartAdapter productListAdapter;
    TextView placeorderbtn,placeorderbtnbuynow;
    private QuantityViewModel viewModel;
    RadioGroup radioGroup;
    RadioButton radioButton;
    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        placeorderbtn = findViewById(R.id.placeorderbtn);
        radioGroup = findViewById(R.id.radioGroup);

        placeorderbtnbuynow = findViewById(R.id.placeorderbtnbuynow);
        productListAdapter = new AddtocartAdapter(PaymentActivity.this, this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Payment");
        viewModel = ViewModelProviders.of(PaymentActivity.this).get(QuantityViewModel.class);
        loadData();
        if(getIntent().getStringExtra("userid")==null) {
            placeorderbtn.setVisibility(View.VISIBLE);

        }else {
            placeorderbtnbuynow.setVisibility(View.VISIBLE);
        }
placeorderbtnbuynow.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        singleproductdetails();
    }
});

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = (RadioButton) group.findViewById(checkedId);
//                if (null != rb ) {
//
//                }else {
//                    fromdaterl.setVisibility(View.GONE);
//                    todaterl.setVisibility(View.GONE);
//                }
                switch (checkedId) {
                    case R.id.radiocorporate:
                       showAlertDialog();   break;
                    case R.id.radioincorporate:
showAlertDialog();
                        break;

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(getApplicationContext(), SelectAddressActivity.class);
                startActivity(intent);
                finish();
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
                            for (int i = 0; i < list.size(); i++) {
                                int selectedId = radioGroup.getCheckedRadioButtonId();

                                // find the radiobutton by returned id
                                  radioButton =(RadioButton) findViewById(selectedId);

                                Log.d("onClickgg ", list.get(i).getProductid().toString());

                                String businesstype=  radioButton.getText().toString().trim();
                                Toast.makeText(getApplicationContext(),businesstype,Toast.LENGTH_LONG).show();


                                    String lastqty = viewModel.getqty(list.get(i).getProductid().toString());
                                    if(lastqty==null) {
                                        placeorder(
                                                "1", String.valueOf(list.get(i).getProductid().toString()), String.valueOf(list.get(i).getProductname()), String.valueOf(list.get(i).getImageUrl()), "1", String.valueOf(list.get(i).getTotalprice()), String.valueOf(list.get(i).getPrice().toString()));

                                    }else {
                                        placeorder("1", String.valueOf(list.get(i).getProductid().toString()), String.valueOf(list.get(i).getProductname()), String.valueOf(list.get(i).getImageUrl()), lastqty, String.valueOf(list.get(i).getTotalprice()), String.valueOf(list.get(i).getPrice().toString()));

                                    }



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
    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(PaymentActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.paymentmethoddialog, null);


        alertDialog.setView(customLayout);
        TextView  btnsave = (TextView) customLayout.findViewById(R.id.tvsavepwd);
        ImageView cut=customLayout.findViewById(R.id.ivcut);
EditText date=customLayout.findViewById(R.id.etdate);

        EditText etremarka =customLayout.findViewById(R.id.etremarks);


        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(true);
        DatePickerDialog.OnDateSetListener dates = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                date.setText(sdf.format(myCalendar.getTime()));
            }

        };

        date.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        new DatePickerDialog(PaymentActivity.this, dates, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }
});


        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });



        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String remarks = etremarka.getText().toString().trim();
/*Integer aaa=Integer.valueOf(qty)*Integer.valueOf(offer);
ettotalamount.setText(aaa.toString());*/

/*

                String mynum1=etqty.getText().toString();
                float mnum1= Float.parseFloat(mynum1);

                String mynum2=etofferamount.getText().toString();
                float mnum2= Float.parseFloat(mynum2);

                float res=mnum1*mnum2;
                etremarka.setText(String.valueOf(res));
*/



                 if(TextUtils.isEmpty(remarks)){
                    etremarka.setError("remarks Can't Blank!");
                }

                //   loadData(et.getText().toString().trim());
                else {
                   alert.dismiss();
                }
            }
        });
        alert.show();
    }

    @SuppressLint("CheckResult")
    private void placeorder(String orderid, String productid, String productname, String productimage, String qty, String totalprice, String subtotal) {

        List<PlaceOrderRequest> list = new ArrayList<>();

        PlaceOrderRequest r = new PlaceOrderRequest();
        r.setOrderid(orderid);
        r.setProductid(productid);
        r.setProductname(productname);
        r.setProductimage(productimage);
        r.setQuantity(qty);
        r.setTotalprice(totalprice);
        r.setSubtotal(subtotal);


        list.add(r);


        Log.e("postDatalist", list.toString());


        Log.e("postData", new Gson().toJson(list));

        ApiClient.getApiClient().placeOrder(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL), list)
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


    @Override
    public void onSetValues(ArrayList<String> al) {
        Toast.makeText(getApplicationContext(), al.toString(), Toast.LENGTH_SHORT).show();
    }

    @SuppressLint("CheckResult")
    private void singleproductdetails() {

        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+"????"+getIntent().getStringExtra("userid") );

        ApiClient.getApiClient().getproductsdetails(getIntent().getStringExtra("userid")).enqueue(new Callback<List<ProductResponse>>() {
            @Override
            public void onResponse(Call<List<ProductResponse>> call, Response<List<ProductResponse>> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getMaterialMasters",String.valueOf(response.code()) );
                if (response.isSuccessful()) {
                    List<ProductResponse> list = response.body();

//                    for (int i = 0; i < list.size(); i++) {
                        placeorder("1", String.valueOf(list.get(0).getId().toString()), String.valueOf(list.get(0).getProdname()), String.valueOf(list.get(0).getImageurl()), getIntent().getStringExtra("getbuynowqty"), String.valueOf(list.get(0).getPrice()), String.valueOf(list.get(0).getPrice().toString()));

                 //   }
                    if (list != null) {


                    }

                }
            }

            @Override
            public void onFailure(Call<List<ProductResponse>> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
            }
        });
    }
}