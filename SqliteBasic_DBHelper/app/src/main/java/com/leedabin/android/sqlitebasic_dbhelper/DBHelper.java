package com.leedabin.android.sqlitebasic_dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

/**
 * Created by Dabin on 2016-10-12.
 */
public class DBHelper extends SQLiteOpenHelper {
    String name;
    SQLiteDatabase.CursorFactory factory;
    public static final String DB_NAME ="sqlite.db";
    public static final int DB_VERSION =2;
    private static DBHelper dbHelper;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

      //assets에 있는 파일을 복사해서 디시크로 옮긴다.

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVesion) {
       //변경사항이 있으면 디스크에 있는 db에 덮어쓰기 한다.
    }

    public static SQLiteDatabase openDatabase(Context context){
        if(dbHelper == null)
            dbHelper = new DBHelper(context);
        return dbHelper.getWritableDatabase();
    }

    private DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
}
