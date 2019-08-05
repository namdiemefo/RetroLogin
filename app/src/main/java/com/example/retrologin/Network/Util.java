package com.example.retrologin.Network;

import android.util.Base64;


import com.example.retrologin.Model.LoginUser;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Util {
    public static final String BASE_URL = "http://10.0.2.2:4000/";
   // public static final String TOKEN = "token";
    // public static final String EMAIL = "email";
    private static Interface anInterface;



    public Util() {
       //String email = loginUser.getEmail();
       //String password = loginUser.getPassword();
        //String credentials = email + ":" + password;
       // final String basic = "Basic" + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request originalrequest = chain.request();
                    Request newRequest = originalrequest.newBuilder()
                            .method(originalrequest.method(), originalrequest.body())
                            .build();

                    return chain.proceed(newRequest);
                })
                .addInterceptor(loggingInterceptor)
                .build();

         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl(BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create())
                 .client(okHttpClient)
                 .build();

         anInterface = retrofit.create(Interface.class);
    }

    public Interface getApi() {
        return anInterface;
    }

    //private static Retrofit.Builder builder = new Retrofit.Builder()
       //     .baseUrl(BASE_URL)
         //   .addConverterFactory(GsonConverterFactory.create());

   //private  static Retrofit retrofit = builder.build();

//   Interface anInterface = retrofit.create(Interface.class);

  // public static Interface getApi(Class<Interface> interfaceClass) {
    //         return retrofit.create(interfaceClass);
      //   }


        // public static Interface getApi(Class<Interface> interfaceClass, String email, String password) {
          //   if (email != null && password != null) {
            //     String credentials = email + ":" + password;
              //   final String basic = "Basic" + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                // OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

                // httpClient.addInterceptor(chain -> {
                  //   Request original = chain.request();
                    // Request.Builder builder = original.newBuilder()
                      //       .addHeader("Authorization", basic)
                        //     .addHeader("Accept", "application/json")
                          //   .method(original.method(), original.body());

                    // Request newRequest = builder.build();
                    // return chain.proceed(newRequest);
               //  });


          //   }
            // return retrofit.create(interfaceClass);
        // }

}
