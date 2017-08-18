package com.example.holynskyi.secondapplication.models;

/**
 * Created by holynskyi on 17.08.17.
 */

public class Checker {
    private long id;
    private int field1;
    private int field2;

    public Checker() {
    }

    public Checker(long id, int field1, int field2) {
        this.id = id;
        this.field1 = field1;
        this.field2 = field2;
    }

    public int getField1() {
        return field1;
    }

    public void setField1(int field1) {
        this.field1 = field1;
    }

    public int getField2() {
        return field2;
    }

    public void setField2(int field2) {
        this.field2 = field2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
