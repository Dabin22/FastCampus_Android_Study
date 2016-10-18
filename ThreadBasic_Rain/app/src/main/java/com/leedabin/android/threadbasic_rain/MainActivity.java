package com.leedabin.android.threadbasic_rain;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity{

    public static Boolean running = true;
    public static int deviceWidth = 0;
    public static int deviceHeight = 0;
    CustomView cv;
    Button btn_start;
    Button btn_stop;
    FrameLayout frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        //화면 가로폭
        deviceWidth = metrics.widthPixels;
        //화면 세로폭
        deviceHeight = metrics.heightPixels;
        frame = (FrameLayout) findViewById(R.id.frame);
        cv = new CustomView(this);
        frame.addView(cv);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("tag","onClick Start");
                running = true;
                MakeDrop makeDrop = new MakeDrop(cv);
                new Thread(makeDrop).start();
            }
        });
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                running = false;
            }
        });


    }


    class CustomView extends View {
        List<RainDrop> rain_drops = Collections.synchronizedList(new ArrayList<RainDrop>());
        Paint paint = new Paint();

        public CustomView(Context context) {
            super(context);
            paint.setColor(Color.BLUE);
        }

        public void add(RainDrop rd) {
            rain_drops.add(rd);
        }

        public  void remove(RainDrop rd){
            rain_drops.remove(rd);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            synchronized (rain_drops) {
                for (RainDrop rainDrop : rain_drops) {
                    canvas.drawCircle(rainDrop.x, rainDrop.y, rainDrop.size, paint);
                }
            }
        }
    }

    //빗방울 한개의 단위 클래스
    class RainDrop implements Runnable {
        //자신의 좌표값
        int x;
        int y;
        int size;
        int speed;
        int size_limit; // 최대 큭리
        int speed_limit; // 최대 속도

        CustomView cv;

        public RainDrop(CustomView cv) {
            Random random = new Random();
            this.cv = cv;

            x = random.nextInt(deviceWidth);
            y = 0;
            size_limit = deviceWidth / 20;
            speed_limit = deviceHeight / 100;
            size = random.nextInt(size_limit) + 1; // 빗방울의 최대크기가 화면 가로사이즈에 1/20보다 작게 만든다.
            speed = random.nextInt(speed_limit)+1; // 빗방울의 한번 이동하는 양이 최대 세로 사이즈의 1/100만큼 이동한다.
            cv.add(this);
        }

        @Override
        public void run() {
            while (y <= deviceHeight) { // 화면밖으로 벗어나면 종료된다. (Thread 종료)
                try {
                    if (running) {
                        y += speed;
                    }
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cv.remove(this);
        }
    }

    //화면을 그려주는 Thread
    class CallDraw implements Runnable {
        CustomView customView;
        int interval;

        public CallDraw(CustomView customView, int interval) {
            this.customView = customView;
            this.interval = interval;
        }

        @Override
        public void run() {
            //interval에 입력된 값만큼 쉰 후에 화면을 반복해서 그려준다.
            while (running) {
                try {
                    Log.i("tag", "call draw!");
                    customView.postInvalidate();
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //빗방울을 만들고 화면을 그려주는 Thread를 동작시켜주는 Sub Thread
    class MakeDrop extends Thread {
        CustomView cv;

        MakeDrop(CustomView cv) {
            this.cv = cv;
        }


        public void run() {
            //빗방울 Thread 생성 후 동작
            CallDraw cd = new CallDraw(cv, 10);
            new Thread(cd).start();
            while (running) {
                RainDrop rainDrop = new RainDrop(cv);
                new Thread(rainDrop).start();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
