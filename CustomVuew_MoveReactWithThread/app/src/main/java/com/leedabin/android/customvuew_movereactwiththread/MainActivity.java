package com.leedabin.android.customvuew_movereactwiththread;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout ground;
    Button btnAnimate;
    CustomView cv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ground = (FrameLayout)findViewById(R.id.ground);
        cv = new CustomView(this);
        ground.addView(cv);

        btnAnimate =(Button)findViewById(R.id.button);
        btnAnimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomThread thread = new CustomThread(cv);
                thread.start();
            }
        });


    }
}

class CustomView extends View {
    Paint paint = new Paint();
    private final static int WIDTH = 100;
    private final static int HEIGHT =100;

    private int x =0;
    private int y =0;




    public CustomView(Context context) {
        super(context);

        paint.setColor(Color.MAGENTA);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawRect(x,y,x+WIDTH,y+HEIGHT,paint);
    }

    public void MoveRect(int offset){
        x +=offset;
        y +=offset;
    }



}
class CustomThread extends Thread{
    CustomView cv;
    private static final int OFFSET =2;

    public CustomThread(CustomView cv){

        this.cv = cv;
    }
    @Override
    public void run() {
        int limit =0;
        while(limit<1000)
        {
            cv.MoveRect(OFFSET);
            cv.postInvalidate();
            //cv에 그려지는 사각형의 좌표값을 조작한다.
            limit++;
            try {
                Thread.sleep(50);
            }catch (Exception e){

            }

        }
    }
}
