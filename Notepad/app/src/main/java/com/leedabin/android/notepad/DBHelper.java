package com.leedabin.android.notepad;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.leedabin.android.notepad.com.leedabin.android.notepad.domain.NotepadData;

import java.security.Timestamp;
import java.util.ArrayList;

/**
 * Created by Dabin on 2016-10-13.
 */
public class DBHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "memo.sqlite";
    private final static int DB_VERSION =1;

    // C-insert R-select1개 R-select전체 U-update D- delete 쿼리를 만든얼 준다.
    public void dbInsert(NotepadData data){
        //db를 연결
        SQLiteDatabase db = getWritableDatabase();

        //쿼리 생성
        String query = " insert into memo(content, ndate, title)"
                + " values('" + data.content_string+"', "+getTimestamp()+", '"+ data.title_string+"')";
        db.execSQL(query);
        //사용후 db를 닫는다.
        db.close();

    }
    public NotepadData dbSelectOne(int no) { // select는 table의 key를 기준으로 값을 받는다.
        NotepadData data = new NotepadData(); // 리턴타입에 맞게 객체를 생성해주고
        //TODO 처리후
        return data; //위에서 정의된 리턴변수를 넘겨준다.
    }
    public ArrayList<NotepadData> dbSelectAll(){
        ArrayList<NotepadData> datas = new ArrayList<>();
        //1. db를 open 읽기모드로
        SQLiteDatabase db = getReadableDatabase();
        //2. select 쿼리 작성
        String query = "select * from memo";

        //3. 쿼리를 실행해서 cusor에 담고
        Cursor cursor = db.rawQuery(query,null);
        //4. 커서에 담기 데이터를 while문을 돌면서 꺼내고
        while(cursor.moveToNext()) {
            NotepadData data = new NotepadData();
            //5.1 데이터 단위로 cursor에서 꺼내와서 담아준다
            int idx = cursor.getColumnIndex("no");
            data.no = cursor.getInt(idx);
            idx = cursor.getColumnIndex("title");
            data.title_string = cursor.getString(idx);
            idx = cursor.getColumnIndex("content");
            data.content_string = cursor.getString(idx);
            idx = cursor.getColumnIndex("ndate");
            data.nDate = cursor.getLong(idx);

            Log.i("tag","no = " + data.no);
            //5.2 data.add(메모 데이터)
            datas.add(data);
        }
        cursor.close();
        db.close();
        return datas;
    }

    public void dbUpdate(NotepadData data){
        String query = "update memo set title = '"+data.title_string+"', " +
                "content = '" + data.content_string+"' where no = "+data.no;
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();

    }
    public void dbDelete(int no){
        SQLiteDatabase db = getWritableDatabase();
        String query = "delete from memo where no = "+no;
        db.execSQL(query);

    }


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String scheme_01 = "create table memo(no integer primary key autoincrement not null " +
                ", content text not null" +
                ", title text not null" +
                ", ndate integer not null)";

        sqLiteDatabase.execSQL(scheme_01);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {




  }


    public long getTimestamp(){
        return System.currentTimeMillis();
    }
}
