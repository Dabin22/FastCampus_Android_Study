package com.leedabin.android.activitybasic01;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Acivity를 호출하는 함수
    // - 버튼에서 호출하기 위해 파라미터로 View를 설정해야만 한다.
    public void openActivity(View v)
    {
        //Activity 를 호출하는 로직
        //1. 인텐트를 생성 -> 건네줄 인자 2개 (컨택스트(this), 호출할 Activity class 명)
        Intent intent = new Intent(this,Activity2.class);

        //2.생성된 인텐트를 시스템에 넘겨서 실행한다.
        startActivity(intent);
    }

    // 전화걸기
    public void openCall(View v)
    {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:114"));
        startActivity(intent);
    }
    // 문자보내기
    public void openSMS(View v)
    {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:114"));
        startActivity(intent);
    }
    // 네이버
    public void openNaver(View v)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http:www.naver.com"));
        startActivity(intent);
    }

    public void openMe(View v)//자기 자신의 엑티비티 호출
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
