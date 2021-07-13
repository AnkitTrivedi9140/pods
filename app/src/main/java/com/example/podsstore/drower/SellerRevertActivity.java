package com.example.podsstore.drower;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.podsstore.R;

import com.example.podsstore.addtocart.PaymentActivity;
import com.example.podsstore.addtocart.WebViewActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.request.EditMakeOfferRequest;

import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.MakeOfferResponse;
import com.example.podsstore.data.response.MakeofferhistoryResponse;

import com.example.podsstore.data.response.VedioResponse;
import com.example.podsstore.mainactivityadapters.MakeOfferHistoryAdapter;

import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;

import com.example.podsstore.productdetails.ShowPDFActivity;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.File;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
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

public class SellerRevertActivity extends AppCompatActivity {
    EditText etproof;
    Uri fileUrifund;
    RecyclerView recyclerView;
    MakeOfferHistoryAdapter productListAdapter;
    TextView tvnodata,tvproductname;
    ProgressBar progressBar;
    TextView tvproductofferstatus,tvproductofferadd,progresstext,tvproductlistedamount,tvproductofferamount;
    ImageView ivproduct;

    // Progress Dialog
    private ProgressDialog pDialog;
    public static final int progress_bar_type = 0;
    VideoView videoView;
    // File url to download

    String ss;
    private static String file_url = "https://pods.market/PodsStoreAPI/makerOfferRest/downloadZip?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_revert);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Your Offer Details");
        tvproductofferstatus = findViewById(R.id.tvproductofferstatus);
        tvproductofferadd = findViewById(R.id.tvproductofferadd);
        tvproductofferamount = findViewById(R.id.tvproductofferamount);
        tvproductlistedamount = findViewById(R.id.tvproductlistedamount);
        recyclerView = findViewById(R.id.productrv);
        productListAdapter = new MakeOfferHistoryAdapter(SellerRevertActivity.this);
        tvnodata = findViewById(R.id.tvnodata);
        progressBar=findViewById(R.id.progress);
        progresstext=findViewById(R.id.progresstext);
        tvproductname=findViewById(R.id.tvproductname);
        ivproduct=findViewById(R.id.ivproduct);
        recyclerView.setLayoutManager(new LinearLayoutManager(SellerRevertActivity.this));
//      recyclerView.setEmptyView(binding.emptyView);
     productListAdapter.setAdapterListener(adapterListener);
        productListAdapter.setAcceptAdapterListener(acceptAdapterListener);

        productListAdapter.setVideoAdapterListener(videoAdapterListener);
        productListAdapter.setDocAdapterListener(docAdapterListener);

        productListAdapter.setyesAdapterListener(yesAdapterListener);
        productListAdapter.setnoAdapterListener(noAdapterListener);
        productListAdapter.setAdapterListeners(adapterListeners);
        productListAdapter.setAdapterListenersedit(adapterListenersedit);
        productListAdapter.setAdapterListenerplaceorder(adapterListenerplaceorder);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
//        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(productListAdapter);
loadData();
loadDataimage();
    }



    @SuppressLint("CheckResult")
    private void loadDataimage() {

        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+"///"+ PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));
        ApiClient.getApiClient().getofferdetails(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL),getIntent().getStringExtra("offerid")).enqueue(new Callback<List<MakeOfferResponse>>() {
            @Override
            public void onResponse(Call<List<MakeOfferResponse>> call, Response<List<MakeOfferResponse>> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("cartaaamake",String.valueOf(response.code()) );
                if (response.isSuccessful()) {
                    List<MakeOfferResponse> list = response.body();
                    for(int i=0;i<list.size();i++){

                        tvproductname.setText(list.get(i).getProductname());
                        tvproductlistedamount.setText("List Price: "+list.get(i).getActualamount());
                           tvproductofferadd.setText(list.get(i).getOfferaddress());
                        tvproductofferamount.setText("Offer Price: "+list.get(i).getFinalupdatedamount());
                        Glide.with(getApplicationContext())
                                .load(list.get(i).getOfferimage())
                                .into(ivproduct);
                        Double aa=Double.valueOf(list.get(i).getQuantitydetails())*Double.valueOf(list.get(i).getFirstbidamount());
                        tvproductofferstatus.setText("Offer Total: "+String.valueOf(aa));

                        ivproduct.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

//                              Intent intent=new Intent(getApplicationContext(), ProductDetailsActivity.class);
//                              intent.putExtra("userid",list.get(0).getProductid().toString());
//                              startActivity(intent);
//                              finish();
                            }
                        });
                    }

//                    if(  list.isEmpty()){
//                        tvnodata.setVisibility(View.VISIBLE);
//                    }else{
//                        tvnodata.setVisibility(View.GONE);
//                    }
                }
            }

            @Override
            public void onFailure(Call<List<MakeOfferResponse>> call, Throwable t) {
                Log.e("onerrors",t.getMessage());

            }
        });
    }


    private MakeOfferHistoryAdapter.AcceptAdapterListener acceptAdapterListener = data -> {


showAlertDialogmakeoffer(data.getOfferid().toString());

    };

    private MakeOfferHistoryAdapter.VideoAdapterListener videoAdapterListener = data -> {


        loadDatavedio(data.getOfferid().toString());

    };
    private MakeOfferHistoryAdapter.DocAdapterListener docAdapterListener = data -> {
//Intent intent=new Intent(getApplicationContext(), WebViewActivity.class);
//startActivity(intent);
//finish();
        //arvind1997jha@gmail.com769281
        new DownloadFileFromURL().execute(file_url+"userEmailId="+PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)+"&offerid="+data.getOfferid());
    };

    private MakeOfferHistoryAdapter.YesAdapterListener yesAdapterListener = data -> {

        makeofferyes("yes");

    };
    private MakeOfferHistoryAdapter.NoAdapterListener noAdapterListener = data -> {
//Intent intent=new Intent(getApplicationContext(), WebViewActivity.class);
//startActivity(intent);
//finish();
      //  new DownloadFileFromURL().execute(file_url);
makeofferyes("no");
    };



    @SuppressLint("CheckResult")
    private void loadDatavedio(String offerid) {

        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+"///"+ PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));
        ApiClient.getApiClient().getvideo(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL),offerid).enqueue(new Callback<List<VedioResponse>>() {
            @Override
            public void onResponse(Call<List<VedioResponse>> call, Response<List<VedioResponse>> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("cartaaamake",String.valueOf(response.body()) );
                if (response.isSuccessful()) {
                    List<VedioResponse> list = response.body();

                    for(int i=0;i<list.size();i++){
                    Log.d( "onRespons",list.get(i).getFileurl());


ss=list.get(i).getFileurl().toString();

                    }
                    Intent intent =new Intent(SellerRevertActivity.this, ShowPDFActivity.class);
                    intent.putExtra("video",ss);
                    startActivity(intent);
                    finish();
                  //  showAlertDialogvideo(ss);
                }
            }

            @Override
            public void onFailure(Call<List<VedioResponse>> call, Throwable t) {
                Log.e("onerrors",t.getMessage());

            }
        });
    }
    @SuppressLint("CheckResult")
    private void uploadData(Uri fileUri,String offerid) {
        progressBar.setVisibility(View.VISIBLE);

        File file = new File(convertMediaUriToPath(fileUri));
        RequestBody requestUserEmailId = RequestBody.create(MediaType.parse("multipart/form-data"), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));
        RequestBody requestofferid = RequestBody.create(MediaType.parse("multipart/form-data"), offerid);


        MultipartBody.Part requesestImage = null;

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        requesestImage = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        ApiClient.getApiClient().uploadImagemakeoffer(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), requesestImage, requestUserEmailId,requestofferid).enqueue(new Callback<CreateLoginUserResponse>() {
            @Override
            public void onResponse(Call<CreateLoginUserResponse> call, Response<CreateLoginUserResponse> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getimage", String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    CreateLoginUserResponse list = response.body();
                    Log.e("getimageurl", String.valueOf(list.toString()));
                    Toast.makeText(getApplicationContext(), list.getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<CreateLoginUserResponse> call, Throwable t) {
                Log.e("onerrors", t.getMessage());
                progressBar.setVisibility(View.VISIBLE);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Bundle extras = imageReturnedIntent.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");

                }

                break;
            case 1:
                if (resultCode == RESULT_OK) {

                    Uri selectedImage = imageReturnedIntent.getData();
                    fileUrifund = selectedImage;

                    File file = new File(selectedImage.getPath());
                    etproof.setText(selectedImage.getPath());

                }
                break;

        }
    }
    @Override
    protected void onStart() {
        super.onStart();

        // Load the media each time onStart() is called.
//        videoView.setVideoURI(Uri.parse(getIntent().getStringExtra("video")));
      //  videoView.requestFocus();
       // videoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // In Android versions less than N (7.0, API 24), onPause() is the
        // end of the visual lifecycle of the app.  Pausing the video here
        // prevents the sound from continuing to play even after the app
        // disappears.
        //
        // This is not a problem for more recent versions of Android because
        // onStop() is now the end of the visual lifecycle, and that is where
        // most of the app teardown should take place.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            videoView.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Media playback takes a lot of resources, so everything should be
        // stopped and released at this time.
//        videoView.stopPlayback();
    }
    @SuppressLint("NewApi")
    private void showAlertDialogvideo(String paths) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SellerRevertActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.videodialog, null);

        Log.e( "showAlertDialogvideo: ",paths );
        alertDialog.setView(customLayout);

        ImageView cut = customLayout.findViewById(R.id.ivcut);


         videoView = customLayout.findViewById(R.id.videoView);



        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);


        videoView.setVideoPath(paths);
        videoView.start();


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.start();
                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        MediaController mediaController = new MediaController(SellerRevertActivity.this);
                        videoView.setMediaController(mediaController);
                        mediaController.setAnchorView(videoView);
                        mediaController.show();
                    }
                });
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


        alert.show();
    }
    @SuppressLint("NewApi")
    private void showAlertDialogmakeoffer(String paths) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SellerRevertActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.paymentmethoddialog, null);


        alertDialog.setView(customLayout);
        TextView btnsave = (TextView) customLayout.findViewById(R.id.tvsavepwd);
        ImageView cut = customLayout.findViewById(R.id.ivcut);



        etproof = customLayout.findViewById(R.id.etproof);



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



                String proof = etproof.getText().toString().trim();

                if (TextUtils.isEmpty(proof)) {
                    etproof.setError("proof Can't Blank!");
                }

                //   loadData(et.getText().toString().trim());
                else {

                    //    uploadDatarerurn(fileUrifund, transaction,PreferenceManagerss.getStringValue(Preferences.USER_EMAIL) , remarks);
                    //  placeorder("","","","","","","","");
                    uploadData(fileUrifund,paths);
                    alert.dismiss();
//                    Intent i=new Intent(getApplicationContext(),PaymentActivity.class);
//                    startActivity(i);
//                    finish();
                }

            }
        });

        alert.show();
    }

    private MakeOfferHistoryAdapter.AdapterListener adapterListener = data -> {
        AlertDialog.Builder dialog = new AlertDialog.Builder(SellerRevertActivity.this);
        dialog.setCancelable(true);
        dialog.setTitle("Alert!");
        dialog.setMessage("Do you want to accept this offer?");
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //Action for "Delete".
                // PreferenceManagerss.logout();
                              /*  finish();
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);*/
                makeofferaccept();
            }
        })
                .setNegativeButton("NO ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Action for "Cancel".
                        dialog.cancel();
                    }
                });

        final AlertDialog alert = dialog.create();
        alert.show();

    };
    private MakeOfferHistoryAdapter.EditAdapterListener adapterListenersedit = data -> {
showAlertDialog(data.getOfferid());

    };
    private MakeOfferHistoryAdapter.PlaceorderAdapterListener adapterListenerplaceorder = data -> {
     Intent intent=new Intent(SellerRevertActivity.this,PaymentActivity.class);
     intent.putExtra("offerid",data.getOfferid());
     startActivity(intent);

    };
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type: // we set this to 0
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }

    /**
     * Background Async Task to download file
     * */
    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Bar Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progress_bar_type);
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();

                // this will be useful so that you can show a tipical 0-100%
                // progress bar
                int lenghtOfFile = connection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(),
                        8192);

                // Output stream
                OutputStream output = new FileOutputStream(Environment
                        .getExternalStorageDirectory().toString()
                        + "/"+"OfferId_"+getIntent().getStringExtra("offerid")+".zip");

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        /**
         * Updating progress bar
         * */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            dismissDialog(progress_bar_type);

        }

    }
    @SuppressLint("NewApi")
    private void showAlertDialog(String offerid) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SellerRevertActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.editmakeofferdialog, null);


        alertDialog.setView(customLayout);
        TextView  btnsave = (TextView) customLayout.findViewById(R.id.tvsavepwd);
        ImageView cut=customLayout.findViewById(R.id.ivcut);
        EditText etofferid=customLayout.findViewById(R.id.etofferid);

        EditText etofferamount =customLayout.findViewById(R.id.etofferamount);
        EditText  etofferqty =customLayout.findViewById(R.id.etofferqty);
      //  EditText etremarka =customLayout.findViewById(R.id.etremarks);


        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(true);


        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
//        etproof.setText(fileUrifund.getEncodedPath());
        //   etproof.setText(paths);
        etofferid.setText(offerid);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               // String remarks = etremarka.getText().toString().trim();
                String offerid = etofferid.getText().toString().trim();
                String offerqty = etofferqty.getText().toString().trim();
                String offeramount = etofferamount.getText().toString().trim();

                if(TextUtils.isEmpty(offerid)){
                    etofferid.setError("Offer id Can't Blank!");
                }
                else if(TextUtils.isEmpty(offeramount)){
                    etofferamount.setError("offer amount id Can't Blank!");
                }
                else if(TextUtils.isEmpty(offerqty)){
                    etofferqty.setError("Quantity Can't Blank!");
                }
//                else if(TextUtils.isEmpty(remarks)){
//                    etremarka.setError("remarks Can't Blank!");
//                }

                else {

               editmakeoffer(etofferqty.getText().toString(),etofferid.getText().toString(),etofferamount.getText().toString(),"");
                    alert.dismiss();

                }

            }
        });

        alert.show();
    }


    @SuppressLint("CheckResult")
    private void editmakeoffer( String qty, String offerid,String offeramount,String remarks) {


        EditMakeOfferRequest r = new EditMakeOfferRequest();
        r.setQuantityDetails(qty);
        r.setOfferAmount(offeramount);
        r.setOfferid(offerid);
        r.setRemark(remarks);


        // list.add(r);

        Log.e("postDataeditmakeoffer", new Gson().toJson(r));

        ApiClient.getApiClient().editmakeoffer(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL), r)
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



//                            Log.e("onSuccessaa", successResponse.getChallanid());


                            if (successResponse != null) {

//                                if (successResponse.getMessage().equals("success")) {
//                                    // mappingAdapter.clear();
//
//                                }

                                //  Toaster.show(mContext, successResponse.getMessage());

                            }
                        } else {
                            //Toast.makeText(getApplicationContext(), "Item already in wishlist", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("onError: ", e.getMessage());
                        Toast.makeText(getApplicationContext(), "server error", Toast.LENGTH_SHORT).show();

                        // binding.progressbar.setVisibility(View.GONE);
                        // NetworkHelper.handleNetworkError(e, mContext);
                    }
                });
        // binding.progressbar.setVisibility(View.VISIBLE);

    }




    private MakeOfferHistoryAdapter.InventoryAdapterListener adapterListeners = data -> {



        AlertDialog.Builder dialog = new AlertDialog.Builder(SellerRevertActivity.this);
        dialog.setCancelable(true);
        dialog.setTitle("Alert!");
        dialog.setMessage("Do you want to decline this offer?");
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //Action for "Delete".
                // PreferenceManagerss.logout();
                              /*  finish();
                                Intent intent = new Intent(Intent.ACTION_MAIN);
                                intent.addCategory(Intent.CATEGORY_HOME);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);*/
                makeofferdeclined();
            }
        })
                .setNegativeButton("NO ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Action for "Cancel".
                        dialog.cancel();
                    }
                });

        final AlertDialog alert = dialog.create();
        alert.show();
    };
    @SuppressLint("CheckResult")
    private void loadData() {
        progressBar.setVisibility(View.VISIBLE);
        progresstext.setVisibility(View.VISIBLE);
        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+"///"+ PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));
        ApiClient.getApiClient().makeofferhistory(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), getIntent().getStringExtra("offerid")).enqueue(new Callback<List<MakeofferhistoryResponse>>() {
            @Override
            public void onResponse(Call<List<MakeofferhistoryResponse>> call, Response<List<MakeofferhistoryResponse>> response) {
                progressBar.setVisibility(View.GONE);
                progresstext.setVisibility(View.GONE);
                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("cartaaamake",String.valueOf(response.code()) );
                if (response.isSuccessful()) {
                    List<MakeofferhistoryResponse> list = response.body();
                    productListAdapter.addAll(list);

                    if(  list.isEmpty()){
                        tvnodata.setVisibility(View.VISIBLE);
                    }else{
                        tvnodata.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<MakeofferhistoryResponse>> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
                progressBar.setVisibility(View.GONE);
                progresstext.setVisibility(View.GONE);
            }
        });
    }
    @SuppressLint("CheckResult")
    private void makeofferdeclined() {
        // Toast.makeText(getApplicationContext(), PreferenceManagerss.getStringValue(Preferences.USER_OTP_EMAIL) ,Toast.LENGTH_SHORT).show();
        /*Log.e("getfdfd", PreferenceManager.getStringValue(Preferences.TOKEN_TYPE)+" "+PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN)+PreferenceManager.getStringValue(Preferences.USER_EMAIL)
        );*/
        //  if (!PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
        ApiClient.getApiClient().makeofferdeclined(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL),getIntent().getStringExtra("offerid"),"remarks").enqueue(new Callback<CreateLoginUserResponse>() {
            //ApiClient.getApiClient().confirmotp("fff", otp).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<CreateLoginUserResponse> call, Response<CreateLoginUserResponse> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("onResp ",String.valueOf(response.code() ));


                if (response.isSuccessful()) {

                    CreateLoginUserResponse list = response.body();

                    Toast.makeText(getApplicationContext(),list.getMessage(),Toast.LENGTH_SHORT).show();
                    // showAlertDialogconfirm();
                } else {
                    Toast.makeText(getApplicationContext(), "Code is not correct!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateLoginUserResponse> call, Throwable t) {

                Log.e("onerrors", t.getMessage());
            }
        });
    }


    @SuppressLint("CheckResult")
    private void makeofferaccept() {
        //  if (!PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
        ApiClient.getApiClient().makeofferaccept(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL),getIntent().getStringExtra("offerid"),"remarks").enqueue(new Callback<CreateLoginUserResponse>() {
            //ApiClient.getApiClient().confirmotp("fff", otp).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<CreateLoginUserResponse> call, Response<CreateLoginUserResponse> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("onResp ",String.valueOf(response.code() ));


                if (response.isSuccessful()) {

                    CreateLoginUserResponse list = response.body();

                    Toast.makeText(getApplicationContext(),list.getMessage(),Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Code is not correct!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateLoginUserResponse> call, Throwable t) {

                Log.e("onerrors", t.getMessage());
            }
        });
    }

    @SuppressLint("CheckResult")
    private void makeofferyes(String flag) {
        //  if (!PreferenceManager.getStringValue(Preferences.ACCESS_TOKEN).isEmpty()) {
        ApiClient.getApiClient().makeofferyes(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL),getIntent().getStringExtra("offerid"),flag).enqueue(new Callback<CreateLoginUserResponse>() {
            //ApiClient.getApiClient().confirmotp("fff", otp).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<CreateLoginUserResponse> call, Response<CreateLoginUserResponse> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("onResp ",String.valueOf(response.code() ));


                if (response.isSuccessful()) {

                    CreateLoginUserResponse list = response.body();

                    Toast.makeText(getApplicationContext(),list.getMessage(),Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "Code is not correct!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateLoginUserResponse> call, Throwable t) {

                Log.e("onerrors", t.getMessage());
            }
        });
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent(getApplicationContext(), ShowMakeofferActivity.class);
                startActivity(intent);
                finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}