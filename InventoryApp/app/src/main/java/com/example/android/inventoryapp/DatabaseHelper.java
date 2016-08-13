package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alexa on 8/13/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    private Context context;
    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DatabaseContract.DB_NAME, null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.Table.CREATE_TABLE);

    }

    public boolean insert(String name, int quantity, double price){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.Table.COLUMN_NAME_NAME, name);
        contentValues.put(DatabaseContract.Table.COLUMN_NAME_QUANTITY, quantity);
        contentValues.put(DatabaseContract.Table.COLUMN_NAME_PRICE, price);
        long result = db.insert(DatabaseContract.Table.TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.Table.TABLE_NAME);
        onCreate(getWritableDatabase());
    }

    public Cursor getAllData(){
        Cursor res = db.query(DatabaseContract.Table.TABLE_NAME, null, null, null, null, null, null);
        return res;
    }

    public boolean updateData(int id, String name, int quantity, double price){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.Table.COLUMN_NAME_ID, id);
        contentValues.put(DatabaseContract.Table.COLUMN_NAME_NAME, name);
        contentValues.put(DatabaseContract.Table.COLUMN_NAME_QUANTITY, quantity);
        contentValues.put(DatabaseContract.Table.COLUMN_NAME_PRICE, price);
        db.update(DatabaseContract.Table.TABLE_NAME, contentValues, "ID = ?", new String[]{Integer.toString(id)});
        return true;
    }

    public void deleteDatabase(){
        context.deleteDatabase(DatabaseContract.DB_NAME);
    }

    public void deleteItemsFromDatabase(int id){
        db.delete(DatabaseContract.Table.TABLE_NAME, "ID = ?", new String[]{Integer.toString(id)});
    }



}
