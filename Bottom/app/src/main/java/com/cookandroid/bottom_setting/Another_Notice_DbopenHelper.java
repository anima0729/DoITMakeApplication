package com.cookandroid.bottom_setting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Another_Notice_DbopenHelper {

    private static final String DATABASE_NAME = "Notice(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatebaseHelper mDBHelper;
    private Context mCtx;

    private class DatebaseHelper extends SQLiteOpenHelper {

        public DatebaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(Another_DataBase_Notification.Another_CreateDB._CREATE0);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + Another_DataBase_Notification.Another_CreateDB._TABLENAME0);
            onCreate(db);
        }
    }

    public Another_Notice_DbopenHelper(Context context){
        this.mCtx = context;
    }

    public Another_Notice_DbopenHelper open() throws SQLException {
        mDBHelper = new DatebaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        mDBHelper.onCreate(mDB);
    }

    public void close(){
        mDB.close();
    }

    public long insertColumn(String id, String title, String writer , String date, String content){
        ContentValues values = new ContentValues();
        values.put(Another_DataBase_Notification.Another_CreateDB.ID, id);
        values.put(Another_DataBase_Notification.Another_CreateDB.TITLE, title);
        values.put(Another_DataBase_Notification.Another_CreateDB.WRITER, writer);
        values.put(Another_DataBase_Notification.Another_CreateDB.DATE, date);
        values.put(Another_DataBase_Notification.Another_CreateDB.CONTENT, content);
        return mDB.insert(Another_DataBase_Notification.Another_CreateDB._TABLENAME0, null, values);
    }

    public Cursor selectColumns(){
        return mDB.query(Another_DataBase_Notification.Another_CreateDB._TABLENAME0, null, null, null, null, null, null);
    }


}