package com.example.holynskyi.secondapplication.view.main_actvity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.holynskyi.secondapplication.R;
import com.example.holynskyi.secondapplication.models.Checker;
import com.example.holynskyi.secondapplication.view.create_checker_activity.CreateCheckerActivity;
import com.example.holynskyi.secondapplication.view.main_actvity.adapters.CheckersAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IContractMain.View, OnCheckerItemSelectedListener {

    public final int SECOND_ACTIVITY = 10;

    MainActivityPresenter mMainActivityPresenter;

    CheckersAdapter checkersAdapter;

    @BindView(R.id.ivAdd)
    ImageView imageView;

    @BindView(R.id.recyclerViewMainCheckers)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainActivityPresenter = new MainActivityPresenter();
        initAddButtonListener();
        initRecycleView();
        initAdapter();
    }


    @Override
    protected void onResume() {
        super.onResume();
        mMainActivityPresenter.attachView(this);
        mMainActivityPresenter.onStart();
    }

    private void initAddButtonListener() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateCheckerActivity.class);
                startActivityForResult(intent, SECOND_ACTIVITY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        if (requestCode == SECOND_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
                mMainActivityPresenter.onNewItemAdded();
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainActivityPresenter.detachView();
    }

    private void initRecycleView() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
    }

    private void initAdapter() {
        checkersAdapter = new CheckersAdapter();
        checkersAdapter.setOnItemSelectedListener(this);
    }

    // implementation of View in (MVP pattern)

    @Override
    public void createRecyclerView(List<Checker> checkerList) {
        checkersAdapter.setList(checkerList);
        recyclerView.setAdapter(checkersAdapter);
    }

    @Override
    public void updateRecyclerView() {
        if (checkersAdapter != null) {
            checkersAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void updateRecyclerViewItem(int position) {
        if (checkersAdapter != null) {
            checkersAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }


    //implementation of RecyclerView Listener

    @Override
    public void itemCheckerSelected(int position) {
        mMainActivityPresenter.onDeleteItemClicked(position);
    }
}
