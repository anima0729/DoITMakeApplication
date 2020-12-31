package com.cookandroid.bottom_setting;

import android.provider.BaseColumns;

public final class Another_DataBase_Notification {

    public class Another_CreateDB implements BaseColumns {

        public static final String ID = "id";
        public static final String TITLE = "title";
        public static final String WRITER = "writer";
        public static final String DATE = "date";
        public static final String CONTENT = "content";
        public static final String _TABLENAME0 = "Notification";

        public static final String _CREATE0 = "create table if not exists " + _TABLENAME0 + "("
                + ID + " integer primary key autoincrement, "
                + TITLE + " text not null ,"
                + WRITER + " text not null ,"
                + DATE + " text not null ,"
                + CONTENT + " text not null );";
    }


}
