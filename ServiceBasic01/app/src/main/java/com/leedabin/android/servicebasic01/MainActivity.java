package com.leedabin.android.servicebasic01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_start;
    Button btn_stop;
    Button btn_startSI;
    Button btn_stopSI;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_startSI = (Button)findViewById(R.id.btn_startSI);
//        서비스실행
//        Intent intent = new Intent(this, 서비스명.class);
//        startService(intent);
//        서비스 중단
//        Intent intent = new Intent(this, 서비스명.class);
//        stopService(intent);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainService.class);
                startService(intent);


            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainService.class);
                stopService(intent);
            }
        });

        //서비스 인텐트를 자동 종료되어서 스탑서비스가 필요없다.
        btn_startSI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubService.class);
                startService(intent);

            }
        });



    }


}
