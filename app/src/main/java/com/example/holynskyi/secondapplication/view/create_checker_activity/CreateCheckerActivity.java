package com.example.holynskyi.secondapplication.view.create_checker_activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.example.holynskyi.secondapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by holynskyi on 17.08.17.
 */

public class CreateCheckerActivity extends AppCompatActivity implements IContractCreateChecker.View {

    AddCheckerActivityPresenter addCheckerActivityPresenter;

    @BindView(R.id.ivBackFromCheckerAddActivity)
    ImageView imageView;

    @BindView(R.id.checkBoxField1Create)
    CheckBox checkBox1;

    @BindView(R.id.checkBoxField2Create)
    CheckBox checkBox2;

    @BindView(R.id.buttonCreateChecker)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_checker);
        ButterKnife.bind(this);
        initButtonListener();
        addCheckerActivityPresenter = new AddCheckerActivityPresenter();
    }


    @Override
    protected void onResume() {
        super.onResume();
        addCheckerActivityPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addCheckerActivityPresenter.detachView();
    }


    private void initButtonListener() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCheckerActivityPresenter.onButtonAddCheckerClicked(checkBox1.isChecked(), checkBox2.isChecked());
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    //methods implemented from MVP


    public Context getContext() {
        return this;
    }


    @Override
    public void setAddingResult(boolean result) {
        Intent intent = new Intent();
        setResult(result ? Activity.RESULT_OK : Activity.RESULT_CANCELED, intent);
        finish();
    }
}