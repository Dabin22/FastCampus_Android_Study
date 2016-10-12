package com.leedabin.android.materialdesign_elevation;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


/*
    //API level 21 이상에서 Material design 설정
   1. res/values/style.xml 의 theme를 android:Theme.Material로 변경
   2. androidMainfest.xml의 aplication의 theme속성을 변경된 Thme로 지정
   3. 상속을 Activity로 변경

   //API level 21 미만에서 설정 안됨.
   1. 상속 받는 Activity를 원래대로 AppCompatActivity로 변경
   2. style의 Apptheme를 Theme.AppCompat으로 지정
 */


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
