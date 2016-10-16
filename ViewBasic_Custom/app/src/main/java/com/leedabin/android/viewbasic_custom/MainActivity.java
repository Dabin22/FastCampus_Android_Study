package com.leedabin.android.viewbasic_custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new CustomView(this));
    }
}

class CustomView extends View {
    Paint paint = new Paint();
    public CustomView(Context context) {
        super(context);
        paint.setColor(Color.MAGENTA);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);


        //사각형 그리기  left top right down
        //canvas.drawRect(0,0,300,300,paint);
        if(x >=0) //x좌표 //y좌표 //지름
            canvas.drawCircle(x,y,rad,paint); //원그리기
    }

    int x = -1;
    int y = -1;
    int rad = 100;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                x = (int)event.getX();
                y = (int)event.getY();

                invalidate();
                break;
        }
        return true;
    }
}
