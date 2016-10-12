package com.leedabin.android.activitybasic01;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Created by Dabin on 2016-09-20.
 */

// 수동으로 Activity만들기

    //1. 액티비티 클래스 상속
    //2. oncreate 메소드를 오버라이딩
    //3. onCreate 메소드 안에서 레이아웃.xml을 셋해줘야한다.
public class Activity3 extends Activity {
    private static final String TAG = "Activity3";

    @Override //1. 액티비티 생성시에 호출되는 함수
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Log.i(TAG,"called onCreate");


    }

    @Override //2. 화면에 나타나기 바로전에 호출되는 함수
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"called onStart");
    }

    @Override//3. 이때 사용자가 화면에 입력할 수 있게 된다.
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"called onResume");
    }

    //액티비티가 동작하고 있는 중

    @Override //4. 화면에서 사라졌을 때 < 액티비티가 나를 일부만 가리고 있을 때
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"called onPause");
    }
    @Override //5. 화면에서 사라졌을 때
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"called onStop");
    }
    @Override //6 앱이 종료시
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"called onDestroy");
    }
    @Override //5.1 stop되었던 액티비티가 화면에 다시 나타날때 pause 상태일시 다시 화면에서 쓰일때 resume로 간다.
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"called onRestart");
    }

    //액티비티 4 호출
    public void openActivity4(View v)
    {
        Intent intent = new Intent(this,Activity4.class);
        startActivity(intent);
    }

    //액티비티 5 호출
    public void openActivity5(View v)
    {
        Intent intent = new Intent(this,Activity5.class);
        startActivity(intent);
    }

}
