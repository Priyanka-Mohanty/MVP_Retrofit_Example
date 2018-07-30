package com.example.priyankam.myapplication.presenter;


import com.example.priyankam.myapplication.contract.MainActivityContract;
import com.example.priyankam.myapplication.model.MainActivityModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private MainActivityContract.View mView;
    private MainActivityContract.Model mModel;

    public MainActivityPresenter(MainActivityContract.View view) {
        mView = view;
        initPresenter();
    }

    private void initPresenter() {
        List datas = new ArrayList<>();
        mModel = new MainActivityModel();
        mView.initView();
        mView.setItems(datas);
        mView.hideProgress();
        mView.setViewData();

    }


}
