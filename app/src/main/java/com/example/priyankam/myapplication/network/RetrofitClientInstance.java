package com.example.priyankam.myapplication.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static Retrofit retrofit;
    //private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    //private static final String BASE_URL = "http://www.mocky.io/v2/5b5ebe412e00009b0a69461b";
    private static final String BASE_URL = "http://www.mocky.io/v2/";
    /**
     * Get Retro Client
     *
     * @return JSON Object
     */
    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    public static Retrofit getRetrofitInstance() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}
