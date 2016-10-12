package com.leedabin.android.basicwidget;

import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;

public class DateActivity extends AppCompatActivity {


    Chronometer timer;

    long temp1 =0;
    long temp2 =0;


    Button btn_start;
    Button btn_stop;
    Button btn_pause;
    //현재 일시정지 버튼이 눌렸는지 체크
    boolean pauseFlag = false;
    boolean stopFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        timer = (Chronometer) findViewById(R.id.chrono);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_pause = (Button) findViewById(R.id.btn_pause);

        btn_start.setOnClickListener(onClickListener);
        btn_stop.setOnClickListener(onClickListener);
        btn_pause.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.btn_start:
                    timer.setBase(SystemClock.elapsedRealtime());
                    temp1 = SystemClock.elapsedRealtime();
                    timer.start();

                    stopFlag = false;
                    pauseFlag = false;
                    break;
                case R.id.btn_stop:
                    if (!pauseFlag) {
                        timer.stop();
                        stopFlag = true;
                    }
                    break;
                case R.id.btn_pause:
                    if (!stopFlag) {
                        if (pauseFlag) {

                            
                            long gap = SystemClock.elapsedRealtime() - temp2;
                            timer.setBase(temp1+ gap);
                            Log.i("tag","temp2 = "+ temp2+" gap = " + gap);
                            timer.start();

                            pauseFlag = false;
                        } else {
                            pauseFlag = true;
                            timer.stop();
                            temp2 = SystemClock.elapsedRealtime();

                        }
                    }
                    break;
            }
        }
    };
}
