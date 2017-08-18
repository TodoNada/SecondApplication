package com.example.holynskyi.secondapplication.view.create_checker_activity;

import android.content.Context;


/**
 * Created by holynskyi on 18.08.17.
 */

public interface IContractCreateChecker {

    interface View {

        void setAddingResult(boolean result);

        Context getContext();
    }

    interface Presenter {
        void attachView(IContractCreateChecker.View view);

        void detachView();

        void onButtonAddCheckerClicked(boolean flag1, boolean flag2);

    }
}
