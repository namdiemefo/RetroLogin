package com.example.retrologin.Network;

import com.example.retrologin.Login;
import com.example.retrologin.Model.LoginUser;
import com.example.retrologin.Model.Response;
import com.example.retrologin.Model.User;
import com.google.gson.annotations.JsonAdapter;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import rx.Observable;

public interface Interface {
    @POST("login")
    Call<Response> login(
            @Header("Authorization") String authHeader,
            @Body LoginUser loginUser);

@POST("users")
Call<Response> register(@Body User user);

}
