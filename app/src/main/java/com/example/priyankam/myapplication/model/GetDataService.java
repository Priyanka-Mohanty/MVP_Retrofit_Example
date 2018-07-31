package com.example.priyankam.myapplication.model;


import retrofit2.Call;
import retrofit2.http.GET;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService {

    //  @GET("/photos")
    // @GET("5b5ecac02e00009b0a694666")
   /* @GET("5b5efb0a2e00009c0a69476a")
    Call<ResultArray> getAllData();*/

    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter
    @GET("5b5efb0a2e00009c0a69476a")
    //Observable<ResultArray> getAllData();
    Call<ResultArray> getAllData();
}
