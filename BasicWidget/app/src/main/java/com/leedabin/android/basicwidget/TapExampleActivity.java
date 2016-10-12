package com.leedabin.android.basicwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class TapExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_example);

        TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
        tabHost.setup();

        //Tab 1
        TabHost.TabSpec spec1 = tabHost.newTabSpec("Tab One");
        // 탭을 눌렀을 때 호출되는 뷰
        spec1.setContent(R.id.tab1);
        //탭의 이름 지정
        spec1.setIndicator("Tab 001");
        //탭을 추가
        tabHost.addTab(spec1);

        //Tab 2
        TabHost.TabSpec spec2 = tabHost.newTabSpec("Tab Two");
        // 탭을 눌렀을 때 호출되는 뷰
        spec2.setContent(R.id.tab2);
        //탭의 이름 지정
        spec2.setIndicator("Tab 002");
        //탭호스트에 탭을 추가
        tabHost.addTab(spec2);

        //Tab 1
        TabHost.TabSpec spec3 = tabHost.newTabSpec("Tab Three");
        // 탭을 눌렀을 때 호출되는 뷰
        spec3.setContent(R.id.tab3);
        //탭의 이름 지정
        spec3.setIndicator("Tab 003");
        //탭을 추가
        tabHost.addTab(spec3);
    }
}
