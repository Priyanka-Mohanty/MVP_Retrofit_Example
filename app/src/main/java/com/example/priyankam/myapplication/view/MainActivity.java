package com.example.priyankam.myapplication.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    public MainActivityContract.Presenter mPresenter;
    String[] values;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        values = new String[]{"Android List View",
                "Adapter",
                "Adapter",
                "Adapter",
                "Adapter",
                "Adapter",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };
        items = Arrays.asList(values);
        adapter = new MainAdapter(items);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
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
                    // generateDataList(response.body());
                    System.out.println("response = " + response.body().toString());
                }

                @Override
                public void onFailure(Call<ResultArray> call, Throwable t) {
                    try {
                        //   progressDoalog.dismiss();
                        System.out.println("error = "+t.toString());
                        Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(ResultObject photoList) {
       // recyclerView = findViewById(R.id.customRecyclerView);
       // adapter = new CustomAdapter(this,photoList);
       /* RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);*/
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
