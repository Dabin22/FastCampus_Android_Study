package com.leedabin.android.customview_pushpush;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static int GROUNDLIMIT = 10;
    private int groundUnit = 0;
    private int unit = 0;

    private int player_x = 0;
    private int player_y = 0;

    int map[][] = {
            {0, 0, 1, 0, 0, 1, 0, 0, 1, 0},
            {0, 0, 1, 0, 0, 0, 0, 1, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 1, 0, 1, 1, 0, 1, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 1, 1, 0},
            {0, 0, 1, 1, 0, 1, 0, 1, 1, 0},
            {0, 0, 1, 1, 0, 1, 0, 1, 1, 0},
            {0, 0, 1, 0, 0, 1, 0, 1, 1, 0},
            {0, 0, 0, 0, 1, 0, 0, 1, 1, 0}
    };


    Button btnUp;
    Button btnLeft;
    Button btnRight;
    Button btnDown;
    CustomView cv;


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

        btnUp.setOnClickListener(this);
        btnLeft.setOnClickListener(this);
        btnRight.setOnClickListener(this);
        btnDown.setOnClickListener(this);

        Toast.makeText(this, "노란색이 도착지점입니다. 가세요", Toast.LENGTH_LONG).show();


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

        //장애물 체크
        if (direction.equals("y")) {
            int temp_y = player_y + nextValue;
            if (map[temp_y][player_x] == 1) {
                if (temp_y + nextValue < 0 || temp_y + nextValue >= GROUNDLIMIT || map[temp_y + nextValue][player_x] != 0)
                    return 0;
                else {
                    map[temp_y][player_x] = 0;
                    map[temp_y + nextValue][player_x] = 1;
                }
            }
        } else if (direction.equals("x")) {
            int temp_x = player_x + nextValue;
            if (map[player_y][temp_x] == 1) {
                if (temp_x + nextValue < 0 || temp_x + nextValue >= GROUNDLIMIT || map[player_y][temp_x + nextValue] != 0) {
                    return 0;
                } else {
                    map[player_y][temp_x] = 0;
                    map[player_y][temp_x + nextValue] = 1;
                }
            }
        }


        return nextValue;
    }


    class CustomView extends View {
        Paint paint = new Paint();

        public CustomView(Context context) {
            super(context);


        }

        @Override
        protected void onDraw(Canvas canvas) {
            //운동자 그리기
            paint.setColor(Color.MAGENTA);
            canvas.drawRect(0, 0, groundUnit, groundUnit, paint);

            if (player_x == 7 && player_y == 2) {
                map[2][6] = 1;
                map[2][8] = 1;
                Toast.makeText(getContext(), "트랩 발동!!", Toast.LENGTH_LONG).show();
            }
            if (player_y == 9 && player_x == 9) {
                Toast.makeText(getContext(), "성공!", Toast.LENGTH_LONG).show();
                for (int i = 0; i < GROUNDLIMIT; i++) {
                    for (int j = 0; j < GROUNDLIMIT; j++) {

                        map[i][j] = 1;

                    }

                }
            }

            // map에 세팅ㅇ된 장애물 그리기
            paint.setColor(Color.BLACK);
            for (int i = 0; i < GROUNDLIMIT; i++) {
                for (int j = 0; j < GROUNDLIMIT; j++) {
                    if (map[i][j] == 1) {

                        canvas.drawRect(j * unit, i * unit, j * unit + unit, i * unit + unit, paint);
                    }
                }

            }


            paint.setColor(Color.CYAN);
            //플레이어 그리기
            Log.i("tag", "x = " + player_x + ", y =" + player_y + " unit =" + unit);
            canvas.drawRect(player_x * unit, player_y * unit, player_x * unit + unit, player_y * unit + unit, paint);

            paint.setColor(Color.YELLOW);
            canvas.drawRect(9 * unit, 9 * unit, 9 * unit + unit, 9 * unit + unit, paint);
        }

    }
}


