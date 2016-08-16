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
        db.execSQL(DatabaseContract.Inventory.CREATE_TABLE);

    }

    //returns item id
    public int insert(String name, int quantity, double price){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.Inventory.COLUMN_NAME_NAME, name);
        contentValues.put(DatabaseContract.Inventory.COLUMN_NAME_QUANTITY, quantity);
        contentValues.put(DatabaseContract.Inventory.COLUMN_NAME_PRICE, price);
        long result = db.insert(DatabaseContract.Inventory.TABLE_NAME, null, contentValues);
        return (int) result;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.Inventory.TABLE_NAME);
        onCreate(getWritableDatabase());
    }

    public Cursor getAllData(){
        Cursor res = db.query(DatabaseContract.Inventory.TABLE_NAME, null, null, null, null, null, null);
        return res;
    }

    public boolean updateData(int id, String name, int quantity, double price){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.Inventory.COLUMN_NAME_ID, id);
        contentValues.put(DatabaseContract.Inventory.COLUMN_NAME_NAME, name);
        contentValues.put(DatabaseContract.Inventory.COLUMN_NAME_QUANTITY, quantity);
        contentValues.put(DatabaseContract.Inventory.COLUMN_NAME_PRICE, price);
        db.update(DatabaseContract.Inventory.TABLE_NAME, contentValues, DatabaseContract.Inventory.COLUMN_NAME_ID + " = ?", new String[]{Integer.toString(id)});
        return true;
    }

    public void deleteDatabase(){
        context.deleteDatabase(DatabaseContract.DB_NAME);
    }

    public void deleteItemsFromDatabase(String name){
        db.delete(DatabaseContract.Inventory.TABLE_NAME, DatabaseContract.Inventory.COLUMN_NAME_NAME + " = '"+ name+"'", null);
    }
}
