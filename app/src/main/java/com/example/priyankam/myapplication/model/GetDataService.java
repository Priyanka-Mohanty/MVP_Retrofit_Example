package com.example.priyankam.myapplication.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

  //  @GET("/photos")
   // @GET("5b5ecac02e00009b0a694666")
    @GET("5b5efb0a2e00009c0a69476a")
    Call<ResultArray> getAllData();
}
