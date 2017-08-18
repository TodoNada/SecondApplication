package com.example.holynskyi.secondapplication.models;

import android.content.Context;

import com.example.holynskyi.secondapplication.db.CheckersDatabaseImplementation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by holynskyi on 17.08.17.
 */

public class Model {

    private List<Checker> list = new ArrayList<>();
    private Context context;

    CheckersDatabaseImplementation checkersDatabaseImplementation;

    public Model(Context context) {
        this.context = context;
        checkersDatabaseImplementation = new CheckersDatabaseImplementation(context);
        uploadListFromDb();
    }

    private void uploadListFromDb() {
        list = checkersDatabaseImplementation.getAllCheckersFromDb();
    }

    public boolean deleteItemFromList(int position) {
        Checker checker = list.get(position);
        if (checkersDatabaseImplementation.remove(checker)) {
            list.remove(position);
            return true;
        }
        return false;
    }

    public boolean addItemIntoList(int flag1, int flag2) {
        Checker checker = new Checker();
        checker.setField1(flag1);
        checker.setField2(flag2);
        if (checkersDatabaseImplementation.insert(checker)) {
            list.add(checker);
            return true;
        }
        return false;
    }

    public List<Checker> getList() {
        return list;
    }

}
