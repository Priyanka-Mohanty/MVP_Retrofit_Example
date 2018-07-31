package com.example.priyankam.myapplication.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.priyankam.myapplication.R;
import com.example.priyankam.myapplication.adapter.MainAdapter;
import com.example.priyankam.myapplication.contract.MainActivityContract;
import com.example.priyankam.myapplication.model.GetDataService;
import com.example.priyankam.myapplication.model.ResultArray;
import com.example.priyankam.myapplication.model.ResultObject;
import com.example.priyankam.myapplication.network.RetrofitClientInstance;
import com.example.priyankam.myapplication.presenter.MainActivityPresenter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    public MainActivityContract.Presenter mPresenter;
    MainAdapter adapter;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=MainActivity.this;
        fetchData();
        mPresenter = new MainActivityPresenter(this);
    }

    @Override
    public void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.listview);
        progressBar = findViewById(R.id.progress);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List items) {
        adapter = new MainAdapter(context,items);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
       // recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

    }

    private void fetchData() {
        try {
            /*Create handle for the RetrofitInstance interface*/
            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<ResultArray> call = service.getAllData();

            call.enqueue(new Callback<ResultArray>() {

                @Override
                public void onResponse(Call<ResultArray> call, Response<ResultArray> response) {
                    // progressDoalog.dismiss();
                    generateDataList(response.body());
                    List<ResultObject> movies = response.body().getResult();
                    Log.d("TAG", "Number of movies received: " + movies.size());
                    System.out.println("response = " + response.body().toString());
                }

                @Override
                public void onFailure(Call<ResultArray> call, Throwable t) {
                    try {
                        //   progressDoalog.dismiss();
                        System.out.println("error = " + t.toString());
                        Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(ResultArray resultArray) {


        List<ResultObject> resultObjects =resultArray.getResult();
        Log.d("TAG", "Number of movies received: " + resultObjects.size());
        List<String> list= new ArrayList<String>();
        for(int i= 0;i<resultObjects.size();i++){
            Log.d("TAG", "Name: " + resultObjects.get(i).getSiteName());
            list.add(String.valueOf(resultObjects.get(i).getSiteName()));
        }
        setItems(resultObjects);
    }

    @Override
    public void setViewData() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
