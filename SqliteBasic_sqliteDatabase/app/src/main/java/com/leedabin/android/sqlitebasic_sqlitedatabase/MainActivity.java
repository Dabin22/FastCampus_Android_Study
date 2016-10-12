package com.leedabin.android.sqlitebasic_sqlitedatabase;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    TextView textView_result;
    Button btn_open;
    Button btn_insert;
    Button btn_select;
    Button btn_update;
    Button btn_delete;
    String fileName;
    SQLiteDatabase db;
    String result;
    int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Log.i("tag","final index = " +index);
        btn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //데이터 베이스를 연결하는 APi 그리고 파라메터는 1. path, 2. cursor, 3 0:read 가능 1:쓰기 가능
                db = SQLiteDatabase.openDatabase(getFullpath(), null, 0);
                setIndex();
            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (db != null) {
                    //DB의 쿼리를 실행해준다. select를 제외한 쿼리에 사용 리턴이 필요 없기에
                    db.execSQL("insert into bbs(user_name,title) values(  '홍길동','글제목')");
                    //쿼리를 실행후 결과값을 커서로 리턴해 준다. 즉 select문에 사용 리턴이 필요하기에
                    //db.rawQuery("",null);
                    index++;
                }
            }
        });
        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (db != null) {
                    Cursor cursor = db.rawQuery("select * from bbs", null);
                    while (cursor.moveToNext()) {
                        int idIdx = cursor.getColumnIndex("no");
                        String id = cursor.getString(idIdx);
                        int nameIdx = cursor.getColumnIndex("user_name");
                        String name = cursor.getString(nameIdx);
                        int titleIdx = cursor.getColumnIndex("title");
                        String title = cursor.getString(titleIdx);
                        int dataIdx = cursor.getColumnIndex("ndate");
                        String date = cursor.getString(dataIdx);

                        boolean temp_id = textView_result.getText().toString().contains(id);
                        String temp = textView_result.getText().toString();
                        result = "id = " + id + " user_name  = " + name + " title = " + title + " data = " +date+ "\n";
                        if (!temp_id) {
                            if (temp.equals("Result:"))
                                textView_result.setText(result);
                            else
                                textView_result.setText(temp + result);
                        }
                    }

                }
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db!= null)
                {
                    if(index != 0)
                        db.execSQL("update bbs set user_name='장길산' where no ="+(index));
                }
            }
        });




    }
    public void setIndex(){
        if (db != null) {
            Cursor cursor = db.rawQuery("select * from bbs", null);

            while (cursor.moveToNext()) {
                int idIdx = cursor.getColumnIndex("no");
                index = cursor.getInt(idIdx);
                Log.i("tag" , index + "-<");
            }
        }
        index++;
    }

    public void init() {
        fileName = "sqllite.db";
        File file = new File(getFullpath());
        if(!file.exists())
            assetToDisk("sqllite.db");

        textView_result = (TextView) findViewById(R.id.textView_result);
        btn_open = (Button) findViewById(R.id.btn_open);
        btn_insert = (Button) findViewById(R.id.btn_insert);
        btn_select = (Button) findViewById(R.id.btn_select);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_update = (Button)findViewById(R.id.btn_update);

    }

    public String getFullpath() {
        String internelPath = getFilesDir().getAbsolutePath();
        Log.i("tag", "internelPath = " + internelPath);
        String targetFile = internelPath + File.separator + fileName;
        return targetFile;


    }

    public void assetToDisk(String fileName) {
        InputStream is = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            //외부에서 작성된 sqlite db파일 사용하기
            // 1. assets에 담아둔 파일을 internel 혹은 external 공간으로 복사한다.
            // 공간으로 복사하기 위해 읽어온다.
            AssetManager manager = getAssets();
            //assets 에 파일이 없므면 exception 이 발생하여 로직이 실행되지 않는다.
            is = manager.open(fileName);
            bis = new BufferedInputStream(is);
            //저장할 위치에 파일이 없을시 생성해둔다.
            String fullPath = getFullpath();

            File file = new File(fullPath);
            if (!file.exists()) {
                file.createNewFile();
            }

            //3 OutputStream 을 생성해서 파일내용을 쓴다.
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            //읽어올 데이터를 담아줄 변수
            int read = -1;
            //한번에 읽을 버퍼의 크기를 지정
            byte buffer[] = new byte[1024];
            //더이상 읽어올 데이터가 없을때까지 buffer 단위로 읽어서 쓴다.
            while ((read = bis.read(buffer, 0, 1024)) != -1) {
                bos.write(buffer, 0, read);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) bos.close();
                if (fos != null) fos.close();
                if (bis != null) bis.close();
                if (is != null) is.close();
            } catch (Exception e) {

            }


        }
    }
}
