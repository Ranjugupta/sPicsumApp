package com.ranju.taskapp.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.GsonBuildConfig;
import com.ranju.taskapp.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllApiClass {
    public static final String BASE_URL = "https://picsum.photos/";

    private static com.ranju.taskapp.api.AllApiClass mInstance;
    public static Retrofit retrofit;

    private AllApiClass() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okhttp = new OkHttpClient.Builder();
        okhttp.addInterceptor(loggingInterceptor);
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static synchronized com.ranju.taskapp.api.AllApiClass getInstance() {
        if (mInstance == null) {
            mInstance = new com.ranju.taskapp.api.AllApiClass();
        }
        return mInstance;
    }
    public Holders getApi() {
        return retrofit.create(Holders.class);
    }

}