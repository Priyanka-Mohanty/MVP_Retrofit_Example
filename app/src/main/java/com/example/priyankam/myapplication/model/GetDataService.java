package com.example.priyankam.myapplication.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

  //  @GET("/photos")
   // @GET("5b5ecac02e00009b0a694666")
    @GET("5b5ebe412e00009b0a69461b")
    Call<ResultArray> getAllData();
}
