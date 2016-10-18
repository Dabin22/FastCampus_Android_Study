package com.leedabin.android.threadbasic_tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;

/**
 * Created by Dabin on 2016-10-17.
 */
public class MainStage extends View {

    Paint paint[] = new Paint[7];


    public MainStage(Context context) {
        super(context);

        for(int i=0; i<paint.length; i++)
        {
            paint[i] = new Paint();
        }

        paint[0].setColor(Color.GRAY);
        paint[1].setColor(ContextCompat.getColor(context, R.color.block1));
        paint[2].setColor(ContextCompat.getColor(context, R.color.block2));
        paint[3].setColor(ContextCompat.getColor(context, R.color.block3));
        paint[4].setColor(ContextCompat.getColor(context, R.color.block4));
        paint[5].setColor(ContextCompat.getColor(context, R.color.block4));
        paint[6].setColor(ContextCompat.getColor(context, R.color.block6));
        paint[7].setColor(ContextCompat.getColor(context, R.color.block7));
        paint[9].setColor(ContextCompat.getColor(context,R.color.border));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
