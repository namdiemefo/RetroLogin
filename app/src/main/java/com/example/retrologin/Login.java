package com.example.retrologin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrologin.Model.LoginUser;
import com.example.retrologin.Model.Response;
import com.example.retrologin.Network.Interface;
import com.example.retrologin.Network.Util;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;

public class Login extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;
    private Button mLogin;
    private Button mRegister;
    private TextView mForgotPassword;

    private Interface anInterface;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
    }

    private void initViews() {
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mLogin = findViewById(R.id.login);
        mRegister = findViewById(R.id.button_register);
        mForgotPassword = findViewById(R.id.forgot_password);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {

        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        final String base = email + ":" + password;
        String authHeader = "Basic" + Base64.encodeToString(base.getBytes(), Base64.NO_WRAP);

        LoginUser loginUser = new LoginUser(email, password);
        Util util = new Util();
        Call<Response> responseCall = util.getApi().login(authHeader, loginUser);

        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    ToastMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                if (t instanceof IOException) {
                    call.cancel();

                    ToastMessage("wahala truly dey");
                }
            }
        });
    }

    private void ToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
