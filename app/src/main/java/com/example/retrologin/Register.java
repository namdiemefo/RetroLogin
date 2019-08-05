package com.example.retrologin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrologin.Model.Response;
import com.example.retrologin.Model.User;
import com.example.retrologin.Network.Interface;
import com.example.retrologin.Network.Util;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;

public class Register extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText password;
    private Button register;

   //private Interface anInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
    }

    private void initViews() {
        name = findViewById(R.id.name);
        email = findViewById(R.id.password);
        password = findViewById(R.id.pass);
        register = findViewById(R.id.reg);

        register.setOnClickListener(v -> register());
    }

    private void register() {

        String username = name.getText().toString();
        String useremail = email.getText().toString();
        String userpass = password.getText().toString();
        // use an intent service
        User user = new User(username, useremail, userpass);
        Util util = new Util();
        Call<Response> responseCall = util.getApi().register(user);

        responseCall.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(Register.this, Login.class);
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
