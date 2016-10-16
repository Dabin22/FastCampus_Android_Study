package com.leedabin.android.customview_rectai;

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
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static int GROUNDLIMIT = 10;
    private int groundUnit = 0;
    private int unit = 0;
    private int enemy_x = 0;
    private int enemy_y = 0;
    boolean started = false;
    private int player_x = 5;
    private int player_y = 5;


    Button btnUp;
    Button btnLeft;
    Button btnRight;
    Button btnDown;
    Button btn_start;
    CustomView cv;
    Enemy enemy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics dp = getResources().getDisplayMetrics();
        groundUnit = dp.widthPixels;
        unit = groundUnit / GROUNDLIMIT;

        FrameLayout ground = (FrameLayout) findViewById(R.id.ground);
        cv = new CustomView(this);
        ground.addView(cv);
        btnUp = (Button) findViewById(R.id.btnUp);
        btnLeft = (Button) findViewById(R.id.btnLeft);
        btnRight = (Button) findViewById(R.id.btnRight);
        btnDown = (Button) findViewById(R.id.btnDown);
        btn_start = (Button) findViewById(R.id.btn_start);

        btnUp.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnDown.setOnClickListener(this);
        btn_start.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnUp:
                player_y += checkCollision(-1, "y");
                break;
            case R.id.btnDown:
                player_y += checkCollision(1, "y");
                break;
            case R.id.btnLeft:
                player_x += checkCollision(-1, "x");
                break;
            case R.id.btnRight:
                player_x += checkCollision(1, "x");
                break;
            case R.id.btn_start:
                new Enemy(cv).start();
                break;
        }
        cv.invalidate();
    }

    private int checkCollision(int nextValue, String direction) {
        //외각선 체크
        if (direction.equals("x")) {
            if ((player_x + nextValue) < 0 || (player_x + nextValue >= GROUNDLIMIT))
                return 0;


        } else if (direction.equals("y")) {
            if ((player_y + nextValue < 0) || (player_y + nextValue >= GROUNDLIMIT))
                return 0;

        }


        return nextValue;
    }


    class Enemy extends Thread {
        CustomView cv;

        int size = unit / 2;
        int x = 0;
        int y = 0;


        public Enemy(CustomView cv) {
            this.cv = cv;
            cv.add(this);
        }

        @Override
        public void run() {
            int distanceX;
            int distanceY;
            try {

                while (true) {
                    distanceX = player_x * unit - x;
                    distanceY = player_y * unit - y;
                    Thread.sleep(20);
                    if (distanceX > 0) {
                        x = x + 1;
                    } else if (distanceX < 0) {
                        x = x - 1;
                    }
                    if (distanceY > 0) {
                        y = y + 1;
                    } else if (distanceY < 0) {
                        y = y - 1;
                    }

                    Log.i("finished", "x = " + x + " y = " + y);
                    Log.i("result" , "player x = " + player_x +" player y = " + player_y);
                    cv.postInvalidate();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }

    class CustomView extends View {
        Paint paint = new Paint();
        // 생성된 적군을 담아줄 변수
        ArrayList<Enemy> enemies = new ArrayList<>();

        public void add(Enemy enemy) {
            enemies.add(enemy);
        }

        public CustomView(Context context) {
            super(context);


        }

        @Override
        protected void onDraw(Canvas canvas) {
            //운동자 그리기
            paint.setColor(Color.MAGENTA);
            canvas.drawRect(0, 0, groundUnit, groundUnit, paint);


            paint.setColor(Color.CYAN);
            //플레이어 그리기
            canvas.drawRect(player_x * unit, player_y * unit, player_x * unit + unit, player_y * unit + unit, paint);


            for (Enemy enemy1 : enemies) {
                paint.setColor(Color.WHITE);
                canvas.drawCircle(enemy1.x+enemy1.size, enemy1.y+enemy1.size, enemy1.size, paint);
            }
        }

    }
}
