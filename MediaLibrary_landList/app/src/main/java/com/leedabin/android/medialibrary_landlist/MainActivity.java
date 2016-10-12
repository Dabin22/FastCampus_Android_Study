package com.leedabin.android.medialibrary_landlist;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnListFragmentInteractionListener {

    ArrayList<MusicData> datas;
    // 결과값을 넘겨받을 때 비교하는 요청 코드
    private final static int REQUEST_CODE = 100;
    ItemFragment itemFragment;
    FragmentDetail fragmentDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // 안드로이드 버전이 마시멜로우 미만일 경우 데이터를 그냥 세팅
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            initData();
        else
            checkPermissions(); // 마시멜로우 이상일 경우는 런타임 권한을 체크해야 한다
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissions() {
        // 런타임 권한 체크 (디스크읽기권한)
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ){
            // 요청할 권한 배열생성
            String permissionArray[] = { Manifest.permission.READ_EXTERNAL_STORAGE };
            // 런타임 권한요청을 위한 팝업창 출력
            requestPermissions( permissionArray , REQUEST_CODE );
        }else{
            // 런타임 권한이 이미 있으면 데이터를 세팅한다
            initData();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case REQUEST_CODE: // 요청코드가 위의 팝업창에 넘겨준 코드와 같으면
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) { // 권한을 체크하고
                    // 권한이 있으면 데이터를 생성한다
                    initData();
                }
                break;
        }
    }

    public void initData(){
        setContentView(R.layout.activity_main);
        itemFragment = ItemFragment.newInstance();
        fragmentDetail = FragmentDetail.newInstance();


    }



    @Override
    public void onListFragmentInteraction() {
    }
}
