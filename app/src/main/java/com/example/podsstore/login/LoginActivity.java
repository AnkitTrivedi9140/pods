package com.example.podsstore.login;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;


import com.example.podsstore.MainActivity;
import com.example.podsstore.R;


public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getName();
    private Button logInBtn;
    private EditText usernameEt, passwordEt, emaiEt;
    private TextView createtv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

//        if (UserPreference.getUserId() != null) {
//            startActivity(new Intent(this, MainActivity.class));
//            finish();
//        }
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        initViews();


        //   binding.url.setOnClickListener(onClickListener);
    }

    private TextWatcher usernameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            if (s.toString().isEmpty()) {
                usernameEt.setError(getString(R.string.error_msg_username));
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private TextWatcher passwordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            if (s.toString().isEmpty()) {
                passwordEt.setError(getString(R.string.error_msg_password));
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.logInBtn:
                    String username = usernameEt.getText().toString();
                    String password = passwordEt.getText().toString();
                    Intent login = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(login);
                    finish();

                    break;
                case R.id.createtv:
                    Intent createaccount = new Intent(LoginActivity.this, CreateAccountActivity.class);
                    startActivity(createaccount);
                    finish();
                    break;


            }

        }
    };

    private void initViews() {
        logInBtn = findViewById(R.id.logInBtn);
        usernameEt = findViewById(R.id.usernameEt);
        passwordEt = findViewById(R.id.passwordEt);
        emaiEt = findViewById(R.id.emailEt);
        createtv = findViewById(R.id.createtv);
        logInBtn.setOnClickListener(onClickListener);
        createtv.setOnClickListener(onClickListener);
        Typeface typeface = ResourcesCompat.getFont(getBaseContext(), R.font.acme);
        usernameEt.setTypeface(typeface);
        passwordEt.setTypeface(typeface);
        usernameEt.addTextChangedListener(usernameTextWatcher);
        passwordEt.addTextChangedListener(passwordTextWatcher);
    }

}
