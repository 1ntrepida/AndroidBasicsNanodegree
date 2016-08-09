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

    private static final String DB_NAME = "Contracts";
    private static final String DELETE_LOG_ENTRY_SQL = "DELETE FROM LogEntries WHERE _id = ?;";
    private static final String FIND_LOG_ENTRY_SQL = "SELECT _id, Longitude, Latitude FROM LogEntries WHERE _id = ?";
    private static final String FIND_ALL_ENTRIES_SQL = "SELECT * FROM LogEntries";
    private static final String[] NO_ARGS = {};
    private Context context;
    private final SQLiteDatabase db = getWritableDatabase();

    public Contract(Context context) {
        super(context, DB_NAME, null, 1);
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
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(getWritableDatabase());
    }

    public void deleteDatabase(){

    }

}
