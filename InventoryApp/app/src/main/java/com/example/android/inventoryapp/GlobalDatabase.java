package com.example.android.inventoryapp;

import android.app.Application;

/**
 * Created by Alexa on 8/14/2016.
 */
public class GlobalDatabase extends Application{
    private DatabaseHelper data;
    public DatabaseHelper getData() {return data;}
    public void setData(DatabaseHelper data) {this.data = data;}
}
