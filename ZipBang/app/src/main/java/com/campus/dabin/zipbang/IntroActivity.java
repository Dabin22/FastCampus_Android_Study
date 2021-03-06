package com.campus.dabin.zipbang;


import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;



public class IntroActivity extends AppCompatActivity {

    private  static final int REQUEST_CODE = 99;
    private static final long SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            loadMain();
        }else{
            checkPermissions();
        }


        //1. 유효성체크 - 권한을 획득하기 전
        // checkSelfPermission()
        //2. 권한에 대한 부가적인 설명이 필요할 경우 호출
        //shouldShowRequestPermissionRationale()
        //3. 권한을 획득하기 위해 호출
        //requestPermissions();
        //0. 완료후 최종 결과값을 처리하는 callback 함수가 자동으로 호출된다
        //onRequestPermissionsResult();

    }



    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissions() {
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                 || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            // 쓰기권한이 없으면 로직 처리
            // 중간에 권한 내용에 대한 알림을 처리하는 함수
            // shouldShowRequestPermissionRationale()


            String permissionArray[] = { android.Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};
            requestPermissions( permissionArray , REQUEST_CODE );


        }else{
            // GPS 권한이 있으면 메인액티비티 호출
            loadMain();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode) {
            case REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED
                        && grantResults[3] == PackageManager.PERMISSION_GRANTED) {
                    loadMain();
                }else{
                    Toast.makeText(IntroActivity.this,"You need Permissions",Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }


    private void loadMain(){
        // 여기서 메인액티비티 호출
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //메인 엑티비티를 실행하고 로딩화면을 죽인다.
                Intent intent = new Intent(IntroActivity.this,MainActivity.class);
                IntroActivity.this.startActivity(intent);
                //애니매이션 제공
                IntroActivity.this.overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                IntroActivity.this.finish();
            }
        },SPLASH_DISPLAY_LENGTH);


    }
}


