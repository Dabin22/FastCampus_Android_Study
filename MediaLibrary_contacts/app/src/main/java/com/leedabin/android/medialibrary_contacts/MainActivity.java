package com.leedabin.android.medialibrary_contacts;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<PhoneData> datas;
    private final static int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //안드로이드 버전 마쉬멜로우 미만을 경우 데이터를 그냥 세팅
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            initData();
        } else {
            checkPermissions();  //이상일 경우 런타임 권한을 체크해야 한다.
        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissions() {
        //런타임 권한 체크(디스크 읽기권한)
        if (checkSelfPermission(Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            //요청할 권한들을 배열로 만든다 이때에는 한개라 한개만 입력
            String permissionArray[] = {Manifest.permission.READ_CONTACTS};
            //런타임 권한 요청을 위한 팝업창 출력
            requestPermissions(permissionArray, REQUEST_CODE);
        } else {
            //권한이 있을시 데이터 세팅
            initData();
        }
    }

    public void initData() {
        datas = getPhoneNumbers();
        RecyclerView list = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerAdapter rc_adpter = new RecyclerAdapter(datas, this, R.layout.item_cardview);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        list.setLayoutManager(manager);
        list.setAdapter(rc_adpter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE:
                //요청한 코드가 위의 팝업창에서 넘겨준 코드값가 같은면
                //팝업창에서 권한 수락시 데이터 세팅
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    initData();
                break;
        }
    }



    public ArrayList<PhoneData> getPhoneNumbers(){
        ArrayList<PhoneData> datas = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        Cursor c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " asc");
        if(c != null){
            while(c.moveToNext()){
                PhoneData data = new PhoneData();
                data.name = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                data.phone = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                if(!names.contains(data.name)){
                    names.add(data.name);
                    datas.add(data);
                }


            }
        }

        c.close();
        return datas;
    }
}
