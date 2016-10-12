package com.leedabin.android.medialibrary;

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
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<RecyclerData> datas;
    //권한 체크 후 결과 값이 내가 원하는 결과 값인지 확인
    private final int REQUEST_CODE = 100;

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

    public void initData() {
        datas = getMusicInfo();
        RecyclerView rcView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerCardAdapter rcAdapter = new RecyclerCardAdapter(datas, R.layout.item_cardview, this);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rcView.setLayoutManager(manager);
        rcView.setAdapter(rcAdapter);
    }

    RecyclerData data;

    public ArrayList<RecyclerData> getMusicInfo() {
        ArrayList<RecyclerData> datas = new ArrayList<>();

        String projections[] = {
                MediaStore.Audio.Media._ID,         //노래 아이디
                MediaStore.Audio.Media.ALBUM_ID,    //앨범 아이디
                MediaStore.Audio.Media.TITLE,       //제목
                MediaStore.Audio.Media.ARTIST       //가수
        };

        //getContentResolver 파라메터
        //1번째 주소 2번째 검색해올 컬럼명들 3번째 조건절 4번째 조건절에 부합하는 값, 5번째 정렬기준
        //아래 주석 참고
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projections, null, null, null);

        /*

            - uri : content ://스키마 형태로 정해져 있는 곳의 데이터를 가져온다.
            - projection : 가져올 컬럼 이름들의 배열, null을 입력하면 모든값을 가져온다.
            - selection : 조건절(where)에 해당하는 내용
            - selectionArgs : 조건절이 Preparedstatement 형태일 때 ?에 매핑하는 되는값의 배열
            - sort order : 정렬 조건
         */


        if (cursor != null) {
            while (cursor.moveToNext()) {

                try {
                    data = new RecyclerData();
                    //1. 가수 이름 컴럼의 인덱스 순서를 가져온다.
                    //2. 해당 index를 가진 컬럼의 실제값을 가져온다.
//
                    data.singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    data.title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                    data.music_id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
                    Long album_id_idx = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
                    Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");

                    Uri sAlbumArtUri = ContentUris.withAppendedId(sArtworkUri, album_id_idx);


                    ParcelFileDescriptor fd = this.getContentResolver().openFileDescriptor(sAlbumArtUri, "r");
                    Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fd.getFileDescriptor(), null, null);
                    data.album_id = Bitmap.createScaledBitmap(bitmap, 500, 500, true);

//                    }

                } catch (Exception e) {
                   data.album_id = null;
                }
                datas.add(data);
            }
            cursor.close();
        }
        return datas;
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissions() {
        //런타임 권한 체크(디스크 읽기권한)
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //요청할 권한들을 배열로 만든다 이때에는 한개라 한개만 입력
            String permissionArray[] = {Manifest.permission.READ_EXTERNAL_STORAGE};
            //런타임 권한 요청을 위한 팝업창 출력
            requestPermissions(permissionArray, REQUEST_CODE);
        } else {
            //권한이 있을시 데이터 세팅
            initData();
        }

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

}
