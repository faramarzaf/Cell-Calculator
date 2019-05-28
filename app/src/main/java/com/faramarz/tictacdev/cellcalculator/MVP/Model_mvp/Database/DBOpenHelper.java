package com.faramarz.tictacdev.cellcalculator.MVP.Model_mvp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "history.db";
    private static final int DB_VERSION = 3;
    public static final String TABLE_HISTORY = "history";
    public static final String HISTORY_ID = "id";
    public static final String HISTORY_DOUBLING_TIME = "doublingtime";
    public static final String HISTORY_DESCRIPTION = "description";
    public static final String HISTORY_DATE = "date";
    public static final String HISTORY_TITLE = "title";

    private static String QUERY_CREATE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_HISTORY + "" +
                    " (" + HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " " + HISTORY_DESCRIPTION + " TEXT, " +
                    " " + HISTORY_TITLE + " TEXT, " +
                    " " + HISTORY_DOUBLING_TIME + " TEXT, " +
                    " " + HISTORY_DATE + " TEXT)";


    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(QUERY_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY + " ");
        onCreate(sqLiteDatabase);
    }

}
