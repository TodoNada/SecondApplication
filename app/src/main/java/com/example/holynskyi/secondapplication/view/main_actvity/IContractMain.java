package com.example.holynskyi.secondapplication.view.main_actvity;

import android.content.Context;

import com.example.holynskyi.secondapplication.models.Checker;

import java.util.List;

/**
 * Created by holynskyi on 17.08.17.
 */

public interface IContractMain {

    interface View {
        void createRecyclerView(List<Checker> checkerList);

        void updateRecyclerView();

        void updateRecyclerViewItem(int position);

        Context getContext();
    }

    interface Presenter {
        void attachView(IContractMain.View view);

        void detachView();

        void onStart();

        void onDeleteItemClicked(int position);

        void onNewItemAdded();

    }

}
