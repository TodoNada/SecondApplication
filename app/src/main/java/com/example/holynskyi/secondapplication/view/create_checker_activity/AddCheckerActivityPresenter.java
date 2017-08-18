package com.example.holynskyi.secondapplication.view.create_checker_activity;

import android.content.Context;

import com.example.holynskyi.secondapplication.models.Model;

/**
 * Created by holynskyi on 18.08.17.
 */

public class AddCheckerActivityPresenter implements IContractCreateChecker.Presenter {

    private IContractCreateChecker.View view;
    private Context context;

    private Model model;

    @Override
    public void attachView(IContractCreateChecker.View view) {
        this.view = view;
        context = view.getContext();
        model = new Model(context);
    }

    @Override
    public void detachView() {
        this.view = null;
        this.model = null;
    }

    @Override
    public void onButtonAddCheckerClicked(boolean flag1, boolean flag2) {
        boolean added = model.addItemIntoList((flag1 ? 1 : 0), (flag2 ? 1 : 0));
        view.setAddingResult(added);
    }
}
