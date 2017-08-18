package com.example.holynskyi.secondapplication.view.main_actvity;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.example.holynskyi.secondapplication.R;
import com.example.holynskyi.secondapplication.models.Model;

/**
 * Created by holynskyi on 17.08.17.
 */

public class MainActivityPresenter implements IContractMain.Presenter {

    IContractMain.View view;
    Context context;

    Model model;

    public MainActivityPresenter() {
    }

    @Override
    public void attachView(IContractMain.View view) {
        this.view = view;
        this.context = view.getContext();
        model = new Model(context);
    }

    @Override
    public void detachView() {
        this.view = null;
        this.model = null;
    }

    @Override
    public void onStart() {
        view.createRecyclerView(model.getList());
    }


    @Override
    public void onDeleteItemClicked(final int position) {

        AlertDialog ad = new AlertDialog.Builder(context).create();
        ad.setTitle(context.getString(R.string.delete_dialog_title));
        ad.setMessage(context.getString(R.string.delete_dialog_message));
        ad.setButton(AlertDialog.BUTTON_POSITIVE, context.getString(R.string.delete_dialog_button_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (model.deleteItemFromList(position)) {
                    view.updateRecyclerView();
                }
            }
        });
        ad.setButton(AlertDialog.BUTTON_NEGATIVE, context.getString(R.string.delete_dialog_button_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                view.updateRecyclerViewItem(position);
            }
        });
        ad.setCancelable(true);
        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                view.updateRecyclerViewItem(position);
            }
        });

        ad.show();
    }

    @Override
    public void onNewItemAdded() {
        view.updateRecyclerView();
    }

}
