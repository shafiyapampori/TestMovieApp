package com.example.trialxcinema.DashboardActivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

// class for configuring Retrofit.
//http://image.tmdb.org/t/p/w780/

public class ApiClient {
    private static Retrofit getRetrofit() {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .addInterceptor(httpLoggingInterceptor)
                .build();


        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl("http://image.tmdb.org/t/p/w780/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public static ApiInterface getInterface(){
        ApiInterface apiInterface;
        apiInterface = getRetrofit().create(ApiInterface.class);
        return apiInterface;
    }
}
