package com.example.holynskyi.secondapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by holynskyi on 09.08.17.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "checkers.db";

    public DBHelper(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create checkers table
        db.execSQL("CREATE TABLE "+ DatabaseStructure.tables.checkers+" (" +
                DatabaseStructure.columns.checker.id+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseStructure.columns.checker.field1+" INTEGER, " +
                DatabaseStructure.columns.checker.field2+" INTEGER " +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
