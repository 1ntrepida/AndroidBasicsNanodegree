package com.example.android.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alexa on 8/9/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "habits.db";
    private static final String TABLE_NAME = "contracts_table";
    private static final String COLUMN_NAME_ID = "entryid";
    private static final String COLUMN_NAME_TITLE = "title";
    private static final String COLUMN_NAME_COUNT = "count";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String INTEGER_KEY_TYPE = " INTEGER PRIMARY KEY";
    private static final String COMMA_SEP = ",";
    private Context context;
    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME +" (entryid INTEGER PRIMARY KEY, title TEXT, count INTEGER)");

    }

    public boolean insert(String title, int count){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_TITLE, title);
        contentValues.put(COLUMN_NAME_COUNT, count);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(getWritableDatabase());
    }

    public Cursor getAllData(){
        Cursor res = db.rawQuery("select * from "+TABLE_NAME, null);
        return res;
    }

    public boolean updateData(int id, String title, int count){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_ID, id);
        contentValues.put(COLUMN_NAME_TITLE, title);
        contentValues.put(COLUMN_NAME_COUNT, count);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{Integer.toString(id)});
        return true;
    }

    public void deleteDatabase(int id){
        db.delete(TABLE_NAME, "ID = ?", new String[]{Integer.toString(id)});
    }

}
