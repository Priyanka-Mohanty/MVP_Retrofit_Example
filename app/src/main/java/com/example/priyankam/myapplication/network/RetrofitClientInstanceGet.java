package com.example.priyankam.myapplication.network;

import android.content.Context;

import com.example.priyankam.myapplication.model.GetDataService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Url;

public class RetrofitClientInstanceGet {
    private static Retrofit retrofit;
    //private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    //private static final String BASE_URL = "http://www.mocky.io/v2/5b5ebe412e00009b0a69461b";
    private static final String BASE_URL = "http://www.mocky.io/v2/";

    private static int REQUEST_TIMEOUT = 60;
    /**
     * Get Retro Client
     *
     * @return JSON Object
     */
    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    static OkHttpClient okHttpClient;

    public static Retrofit getRetrofitInstance(String url) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory.create();
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .addCallAdapterFactory(rxAdapter)
                    .build();
        }
        return retrofit;
    }
    public static GetDataService getApiService(String url) {
        try {
            return getRetrofitInstance(url).create(GetDataService.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
