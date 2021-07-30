package com.example.podsstore.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.podsstore.MainActivity;
import com.example.podsstore.R;
import com.example.podsstore.aboutpod.AboutActivity;
import com.example.podsstore.category.CategoryActivity;
import com.example.podsstore.data.ApiClient;
import com.example.podsstore.data.response.CreateLoginUserResponse;
import com.example.podsstore.data.response.ProductResponse;
import com.example.podsstore.data.response.ProfileResponses;
import com.example.podsstore.prefs.PreferenceManagerss;
import com.example.podsstore.prefs.Preferences;
import com.hbb20.CountryCodePicker;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    RadioGroup radioGroup1;
    RadioButton home, categories, profile, about;
    TextView tvname, tvemail, tvaddress, tvaddressedit, tvmobile, tvmobileedit, tvemailtxt, tvemailedit, tvpassword, tvpasswordedit;
    CircularImageView ivuser, ivcamera;
    ProgressBar progressBar;

    ImageView ivlogo,tvicons;
    //dricpt
    public static final String PROVIDER = "BC";

    public static final int IV_LENGTH = 16;
    public static final int PBE_ITERATION_COUNT = 100;

    private static final String PBE_ALGORITHM = "PBEWithSHA256And256BitAES-CBC-BC";
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    public static final String SECRET_KEY_ALGORITHM = "AES";
    private static final String TAG = "EncryptionPassword";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().hide();
        radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
        about = (RadioButton) findViewById(R.id.about);
        home = (RadioButton) findViewById(R.id.homes);
        progressBar=findViewById(R.id.progress);
        categories = (RadioButton) findViewById(R.id.categories);
        profile = (RadioButton) findViewById(R.id.profile);
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
        tvicons = findViewById(R.id.tvicons);
        profile.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.blueprofile, 0, 0);
        profile.setTextColor(Color.parseColor("#007eff"));

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Intent in;
                Log.i("matching", "matching inside1 bro" + checkedId);
                switch (checkedId) {
                    case R.id.homes:
                        Log.i("matching", "matching inside1 matching" + checkedId);
                        in = new Intent(getBaseContext(), MainActivity.class);
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

                        in = new Intent(getBaseContext(), ProfileActivity.class);
                        startActivity(in);
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

//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
//                Manifest.permission.READ_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
//                    Uri.parse("package:" + getPackageName()));
//            finish();
//            startActivity(intent);
//            return;
//        }



        ivcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);




                //              Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //             startActivityForResult(i, 100);

//                Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(takePicture, 0);
            }
        });
        tvaddressedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent address = new Intent(ProfileActivity.this, AddressActivity.class);
                address.putExtra("profile","profile");
                startActivity(address);
                finish();
            }
        });
        tvmobileedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
        loadData();
        String aa=decryptAndGetPassword();
        //Glide.with(this).load(R.drawable.gif).into(tvicons);
       // Toast.makeText(getApplicationContext(),aa,Toast.LENGTH_LONG).show();
    }
    private String decryptAndGetPassword() {
        SharedPreferences prefs = getSharedPreferences("pswd", MODE_PRIVATE);
        String encryptedPasswrd ="$2a$10$ZPiu97Vhi6C2N1VeJfYCPecQ7LVYOwEaQ8.V47Y2qJsIVqJox/Ob";
        String passwrd = "";
        if (encryptedPasswrd!=null && !encryptedPasswrd.isEmpty()) {
            try {
                String output = prefs.getString("S_KEY", "");
                byte[] encoded = hexStringToByteArray(output);
                SecretKey aesKey = new SecretKeySpec(encoded, SECRET_KEY_ALGORITHM);
                passwrd = decrypt(aesKey, encryptedPasswrd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return passwrd;
    }
    public static String decrypt(SecretKey secret, String encrypted) throws Exception {
        try {
            Cipher decryptionCipher = Cipher.getInstance(CIPHER_ALGORITHM, PROVIDER);
            String ivHex = encrypted.substring(0, IV_LENGTH * 2);
            String encryptedHex = encrypted.substring(IV_LENGTH * 2);
            IvParameterSpec ivspec = new IvParameterSpec(hexStringToByteArray(ivHex));
            decryptionCipher.init(Cipher.DECRYPT_MODE, secret, ivspec);
            byte[] decryptedText = decryptionCipher.doFinal(hexStringToByteArray(encryptedHex));
            String decrypted = new String(decryptedText, "UTF-8");
            return decrypted;
        } catch (Exception e) {
            Log.e("SecurityException", e.getCause().getLocalizedMessage());
            throw new Exception("Unable to decrypt", e);
        }
    }
    public static String byteArrayToHexString(byte[] b) {
        StringBuffer sb = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    public static byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }

    public static SecretKey getSecretKey(String password, String salt) throws Exception {
        try {
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), hexStringToByteArray(salt), PBE_ITERATION_COUNT, 256);
            SecretKeyFactory factory = SecretKeyFactory.getInstance(PBE_ALGORITHM, PROVIDER);
            SecretKey tmp = factory.generateSecret(pbeKeySpec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), SECRET_KEY_ALGORITHM);
            return secret;
        } catch (Exception e) {
            throw new Exception("Unable to get secret key", e);
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ProfileActivity.this);
        final View customLayout = getLayoutInflater().inflate(R.layout.mobile_dialog, null);


        alertDialog.setView(customLayout);
        TextView  btnsave = (TextView) customLayout.findViewById(R.id.tvsave);
        ImageView cut=customLayout.findViewById(R.id.ivcut);
        EditText et =customLayout.findViewById(R.id.etmobile);
        CountryCodePicker  countryCodePicker=customLayout.findViewById(R.id.et1);


        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(true);

        cut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String number = et.getText().toString().trim();
                //Toast.makeText(getApplicationContext(),username,Toast.LENGTH_LONG).show();
//                    Intent login = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(login);
//                    finish();
                if (TextUtils.isEmpty(number)) {
                    et.setError("Number Can't Blank!");
                }else if(number.length() < 6 || number.length() > 20){
                    et.setError("Number is not correct!");
                }else {
                  changenumber("+"+countryCodePicker.getFullNumberWithPlus().toString()+"-"+et.getText().toString().trim());
                }

            }
        });
        alert.show();
    }
    @SuppressLint("CheckResult")
    private void loadData() {

        Log.e("getssss", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+"????"+getIntent().getStringExtra("userid") );

        ApiClient.getApiClient().profile(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<List<ProfileResponses>>() {
            @Override
            public void onResponse(Call<List<ProfileResponses>> call, Response<List<ProfileResponses>> response) {


                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getprofile", String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    List<ProfileResponses> list = response.body();

                   for (int i = 0; i < list.size(); i++) {
                       if(list.get(i).getAddress()!=null){
                           tvaddress.setText(list.get(i).getAddress().getAddressline1().toString()+", "+list.get(i).getAddress().getAddressline2());

                       }

                       if(list.get(i).getData()!=null){
                           Log.e("getprofilesss", String.valueOf(list.get(i).getData().getUserimageurl()));
                           GlideUrl glideUrl = new GlideUrl(list.get(i).getData().getUserimageurl(),
                                   new LazyHeaders.Builder()
                                           .addHeader("Authorization", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN))

                                           .build());

                           Glide.with(getApplicationContext())
                                   .load(glideUrl)
                                   .into(ivuser);
                       }
                    //   for (int i = 0; i < list.getData().size(); i++) {

                    //   }

                    tvname.setText(list.get(i).getUsername());
                    tvemail.setText(list.get(i).getUseremailid());
                    tvmobile.setText(list.get(i).getMobilenumber());
                    tvemailtxt.setText(list.get(i).getUseremailid());
                    tvpassword.setText(list.get(i).getPassword());
                   }
                }
            }

            @Override
            public void onFailure(Call<List<ProfileResponses>> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
            }
        });



       /* Log.e("getfdfd", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN) + PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)
        );

        ApiClient.getApiClient().profile(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)).enqueue(new Callback<ProfileResponses>() {
            @Override
            public void onResponse(Call<ProfileResponses> call, Response<ProfileResponses> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getprofile", String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    ProfileResponses list = response.body();

                   // for (int i = 0; i < list.getAddress().size(); i++) {
                        tvaddress.setText(list.getAddress().getAddressline1().toString()+", "+list.getAddress().getAddressline2());

                   // }
                 //   for (int i = 0; i < list.getData().size(); i++) {
                        Log.e("getprofilesss", String.valueOf(list.getData().getUserimageurl()));
                        GlideUrl glideUrl = new GlideUrl(list.getData().getUserimageurl(),
                                new LazyHeaders.Builder()
                                        .addHeader("Authorization", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN))

                                        .build());

                        Glide.with(getApplicationContext())
                                .load(glideUrl)
                                .into(ivuser);
                 //   }

                    tvname.setText(list.getUsername());
                    tvemail.setText(list.getUseremailid());
                    tvmobile.setText(list.getMobilenumber());
                    tvemailtxt.setText(list.getUseremailid());
                    tvpassword.setText(list.getPassword());

                }
            }

            @Override
            public void onFailure(Call<ProfileResponses> call, Throwable t) {
                Log.e("onerrors", t.getMessage());
            }
        });*/
    }
    @SuppressLint("CheckResult")
    private void changenumber(String mobilenumber) {

        Log.e("getfdfd", PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN)+ PreferenceManagerss.getStringValue(Preferences.USER_EMAIL)
        );

        ApiClient.getApiClient().changeno(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE)+" "+ PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL),mobilenumber).enqueue(new Callback<CreateLoginUserResponse>() {
            @Override
            public void onResponse(Call<CreateLoginUserResponse> call, Response<CreateLoginUserResponse> response) {

                // Toast.makeText(getApplicationContext(),"calll",Toast.LENGTH_SHORT).show();
                Log.e("getprofile",String.valueOf(response.code()));
                if (response.isSuccessful()) {
                    CreateLoginUserResponse list = response.body();

                   Toast.makeText(getApplicationContext(),list.getMessage(),Toast.LENGTH_SHORT).show();
                    finish();
                    overridePendingTransition( 0, 0);
                    startActivity(getIntent());
                    overridePendingTransition( 0, 0);
                }
            }
            @Override
            public void onFailure(Call<CreateLoginUserResponse> call, Throwable t) {
                Log.e("onerrors",t.getMessage());
            }
        });
    }

    //    public static String decrypt(String value) throws Exception
//    {
//        Key key = generateKey();
//        Cipher cipher = Cipher.getInstance(AES);
//        cipher.init(Cipher.DECRYPT_MODE, key);
//        byte[] decryptedValue64 = Base64.decode(value, Base64.DEFAULT);
//        byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
//        String decryptedValue = new String(decryptedByteValue,"utf-8");
//        return decryptedValue;
//
//    }
    public byte[] getBytes(InputStream is) throws IOException {
        ByteArrayOutputStream byteBuff = new ByteArrayOutputStream();

        int buffSize = 1024;
        byte[] buff = new byte[buffSize];

        int len = 0;
        while ((len = is.read(buff)) != -1) {
            byteBuff.write(buff, 0, len);
        }

        return byteBuff.toByteArray();
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

    @SuppressLint("CheckResult")
    private void uploadData(Uri fileUri) {
progressBar.setVisibility(View.VISIBLE);

        File file = new File(convertMediaUriToPath(fileUri));
        RequestBody requestUserEmailId = RequestBody.create(MediaType.parse("multipart/form-data"), PreferenceManagerss.getStringValue(Preferences.USER_EMAIL));
        MultipartBody.Part requesestImage = null;

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        requesestImage = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        ApiClient.getApiClient().uploadImage(PreferenceManagerss.getStringValue(Preferences.TOKEN_TYPE) + " " + PreferenceManagerss.getStringValue(Preferences.ACCESS_TOKEN), requesestImage, requestUserEmailId).enqueue(new Callback<CreateLoginUserResponse>() {
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


                    File file = new File(selectedImage.getPath());
                    uploadData(selectedImage);

                    ivuser.setImageURI(selectedImage);
                }
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}