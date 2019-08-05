package com.example.retrologin.Network;

import java.io.IOException;
import java.net.Authenticator;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class Auth implements Interceptor {
    private String authToken;

    public Auth(String token) {
        this.authToken = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request.Builder builder = original.newBuilder()
                .header("Authorization", authToken);

        Request request = builder.build();
        return chain.proceed(request);
    }
}
