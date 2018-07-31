package com.example.priyankam.myapplication.contract;

import java.util.List;

/**
 * Defines the contract between the View {@link com.example.priyankam.myapplication.view.MainActivity }
 * and the Presenter{@link com.example.priyankam.myapplication.presenter.MainActivityPresenter}.
 */
public interface MainActivityContract {
    interface View {
        void initView();

        void showProgress();

        void hideProgress();

        void setItems(List<String> datas);

        void setViewData();
        void showMessage(String message);
    }

    interface Model {
        List getData();
    }

    interface Presenter {
    }


}

