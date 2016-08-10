package com.example.android.habittracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Alexa on 8/9/2016.
 */
public class Contract extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "contracts";
    private static final String COLUMN_NAME_ID = "entryid";
    private static final String COLUMN_NAME_TITLE = "title";
    private static final String COLUMN_NAME_COUNT = "count";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String INTEGER_KEY_TYPE = " INTEGER PRIMARY KEY";
    private static final String COMMA_SEP = ",";
    private Context context;
    private final SQLiteDatabase db = getWritableDatabase();

    public Contract(Context context) {
        super(context, TABLE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String s;
        try {
            Toast.makeText(context, "1", Toast.LENGTH_LONG).show();
        } catch (Throwable t) {
            Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
        }
        Log.e("DB", "DB Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(getWritableDatabase());
    }

    public void deleteDatabase(){

    }

}
