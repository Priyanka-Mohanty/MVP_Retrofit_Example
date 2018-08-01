package com.example.priyankam.myapplication.model;


import org.json.JSONObject;

import java.util.Map;

import okhttp3.Callback;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface GetDataService {

    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter
    //@GET("5b5efb0a2e00009c0a69476a")
    @GET("5b61a89a300000f10d6a4285")
    Call<ResultArray> getAllData();


    @POST("techMPost.php")
    Call<ResultStatusPost> savePost(@Body String body);
}
