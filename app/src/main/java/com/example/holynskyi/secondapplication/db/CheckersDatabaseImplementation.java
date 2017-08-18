package com.example.holynskyi.secondapplication.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.holynskyi.secondapplication.models.Checker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by holynskyi on 17.08.17.
 */

public class CheckersDatabaseImplementation {

    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private static String table = DatabaseStructure.tables.checkers;


    public CheckersDatabaseImplementation(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        reopen();
    }


    private void reopen() {
        db = dbHelper.getWritableDatabase();
    }

    private void close() {
        if (db == null) return;
        try {
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean insert(Checker checker) {
        reopen();
        if (db != null) {
            try {
                String insertSql = "INSERT INTO " + table + " (" +
                        DatabaseStructure.columns.checker.field1 + ", " +
                        DatabaseStructure.columns.checker.field2 + " " +
                        ") VALUES (?,?)";

                int n = 1;
                SQLiteStatement ss = db.compileStatement(insertSql);
                ss.bindLong(n++, checker.getField1());
                ss.bindLong(n++, checker.getField2());

                long dbId = ss.executeInsert();
                ss.close();

                checker.setId(dbId);
                close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public boolean remove(Checker checker) {
        reopen();
        if(db != null && checker.getId() > 0)
        {
            try {
                String removeSql = "DELETE FROM " + table + " WHERE " + DatabaseStructure.columns.checker.id + "= ?";
                int n = 1;
                SQLiteStatement ss = db.compileStatement(removeSql);
                ss.bindLong(n++, checker.getId());
                ss.execute();
                ss.close();
                close();
                return true;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }


    public boolean update(Checker checker) {
        reopen();
        if(db != null)
        {
            try
            {

                String updateSql = "UPDATE " + table + " SET " +
                        DatabaseStructure.columns.checker.id + " = ?, " +
                        DatabaseStructure.columns.checker.field1 + " = ?, " +
                        DatabaseStructure.columns.checker.field2 + " = ?" +
                        " WHERE " + DatabaseStructure.columns.checker.id + " = ?";

                int n = 1;
                SQLiteStatement ss = db.compileStatement(updateSql);

                ss.bindLong(n++, checker.getId());
                ss.bindLong(n++, checker.getField1());
                ss.bindLong(n++, checker.getField2());

                ss.bindLong(n++, checker.getId());

                ss.execute();
                ss.close();

                close();
                return true;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }

        return false;

    }


    public List<Checker> getAllCheckersFromDb() {
        reopen();
        try
        {
            if(db != null)
            {
                List<Checker> list = new ArrayList<>();

                Cursor loader = db.query(table, null, null, null, null, null, null);

                if(!loader.moveToFirst())
                    return list; // no rows in the db table, do something?

                while(!loader.isAfterLast())
                {
                    Checker checker = new Checker();
                    checker.setId(loader.getLong(loader.getColumnIndex(DatabaseStructure.columns.checker.id)));
                    checker.setField1(loader.getInt(loader.getColumnIndex(DatabaseStructure.columns.checker.field1)));
                    checker.setField2(loader.getInt(loader.getColumnIndex(DatabaseStructure.columns.checker.field2)));

                    list.add(checker);

                    loader.moveToNext();
                }

                loader.close();
                close();
                return list;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }


}
