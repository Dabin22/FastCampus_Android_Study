package com.leedabin.android.viewbasic_customviewwithpath;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.health.PackageHealthStats;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    CustomView cv; 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cv = new CustomView(this);
        FrameLayout ground =(FrameLayout)findViewById(R.id.ground);
        ground.addView(cv);
        Button btn_clear = (Button)findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cv.reset();
            }
        });
    }
}
class CustomView extends View {
    Paint paint = new Paint();
    Path path = new Path();
    public CustomView(Context context) {
        super(context);
        paint.setColor(Color.MAGENTA);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawPath(path,paint);
    }

    public void reset(){
        path = new Path();
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(x,y); //시작점 표시 *그림은 그려지지않는다.
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x,y); //이동 경로상에 라인을 그려준다.
                break;
        }
        invalidate();
        return true;
    }
}