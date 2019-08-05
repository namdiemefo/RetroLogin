package com.example.retrologin.Model;

import com.google.gson.annotations.SerializedName;

public class Response {

    @SerializedName("message")
    private String message;

    @SerializedName("token")
    private String token;

    public String getMessage() {

        return message;
    }

    public String getToken() {
        return token;
    }
}
