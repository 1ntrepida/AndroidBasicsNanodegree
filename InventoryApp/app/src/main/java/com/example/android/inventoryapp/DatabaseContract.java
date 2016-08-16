package com.example.android.inventoryapp;

import android.provider.BaseColumns;

/**
 * Created by Alexa on 8/13/2016.
 */
public final class DatabaseContract {

    public static final String DB_NAME = "inventory.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String DOUBLE_TYPE = " FLOAT";
    private static final String COMMA_SEP = ",";


    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private DatabaseContract() {
    }

    public static abstract class Inventory implements BaseColumns {
        public static final String TABLE_NAME = "contracts_table";
        public static final String COLUMN_NAME_ID = "entryid";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_QUANTITY = "quantity";
        public static final String COLUMN_NAME_PRICE = "price";


        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                COLUMN_NAME_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_QUANTITY + INT_TYPE + COMMA_SEP +
                COLUMN_NAME_PRICE + DOUBLE_TYPE + " )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}