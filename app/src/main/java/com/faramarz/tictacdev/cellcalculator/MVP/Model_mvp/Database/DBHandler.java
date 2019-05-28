package com.faramarz.tictacdev.cellcalculator.MVP.Model_mvp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.faramarz.tictacdev.cellcalculator.MVP.Model_mvp.HistoryModel;

import java.util.ArrayList;
import java.util.List;

public class DBHandler {

    private SQLiteOpenHelper sqLiteOpenHelper;
    private SQLiteDatabase database;
    String[] historyColumns = {DBOpenHelper.HISTORY_ID, DBOpenHelper.HISTORY_DESCRIPTION, DBOpenHelper.HISTORY_TITLE, DBOpenHelper.HISTORY_DATE, DBOpenHelper.HISTORY_DOUBLING_TIME};


    public DBHandler(Context context) {
        sqLiteOpenHelper = new DBOpenHelper(context);
        // database.close();
    }

    public void addHistory(HistoryModel historyModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBOpenHelper.HISTORY_DESCRIPTION, historyModel.getDescription());
        contentValues.put(DBOpenHelper.HISTORY_DATE, historyModel.getDate());
        contentValues.put(DBOpenHelper.HISTORY_TITLE, historyModel.getTitle());
        contentValues.put(DBOpenHelper.HISTORY_DOUBLING_TIME, historyModel.getDoublingtime());
        database.insert(DBOpenHelper.TABLE_HISTORY, null, contentValues);
    }


    public void deleteItem(int id) {
        database.delete(DBOpenHelper.TABLE_HISTORY, DBOpenHelper.HISTORY_ID + " = " + id, null);
    }


    public List<com.faramarz.tictacdev.cellcalculator.MVP.Model_mvp.HistoryModel> getAllDiary() {
        List<com.faramarz.tictacdev.cellcalculator.MVP.Model_mvp.HistoryModel> historyModels = new ArrayList<>();
//        diaryColumns[0] + " DESC"
        Cursor cursor = database.query(DBOpenHelper.TABLE_HISTORY, historyColumns, null, null, null, null, historyColumns[0] + " DESC");
        if (cursor.moveToFirst()) {
            do {
                com.faramarz.tictacdev.cellcalculator.MVP.Model_mvp.HistoryModel historyModel = new com.faramarz.tictacdev.cellcalculator.MVP.Model_mvp.HistoryModel();
                historyModel.setId(cursor.getInt(cursor.getColumnIndex(DBOpenHelper.HISTORY_ID)));
                historyModel.setDescription(cursor.getString(cursor.getColumnIndex(DBOpenHelper.HISTORY_DESCRIPTION)));
                historyModel.setDate(cursor.getString(cursor.getColumnIndex(DBOpenHelper.HISTORY_DATE)));
                historyModel.setTitle(cursor.getString(cursor.getColumnIndex(DBOpenHelper.HISTORY_TITLE)));
                historyModel.setDoublingtime(cursor.getString(cursor.getColumnIndex(DBOpenHelper.HISTORY_DOUBLING_TIME)));
                historyModels.add(historyModel);
            } while (cursor.moveToNext());
        }
        database.close();
        return historyModels;
    }

    public void open() {
        database = sqLiteOpenHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }


    public void deleteAll() {
        database = sqLiteOpenHelper.getWritableDatabase();
        database.delete(DBOpenHelper.TABLE_HISTORY, null, null);
        database.execSQL("delete from " + DBOpenHelper.TABLE_HISTORY);
        database.close();
    }

}
