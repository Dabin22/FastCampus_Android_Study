package com.leedabin.android.threadbasic_tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

/**
 * Created by Dabin on 2016-10-17.
 */
public class PreviewStage extends View {

    public static final int NEXTBLOCK = 3;
    Paint paint[] = new Paint[10];
    int notFirst = 0;

    public static boolean running = true;
    int map[][];
    Stage stage;
    Handler mainHandler;
    int unit = 0;
    final int stageWidth = 6;
    final int stageHeight = 6;
    Block currentBlockGroup = null;
    private PreviewStage previewStage;

    public Block newBlock() {
        if (running) {
            currentBlockGroup = BlockFactory.newBlcok(mainHandler);
        }
        return currentBlockGroup;
    }



    public PreviewStage(Context context, Handler handler, int block_pixel_unit) {
        super(context);
        for (int i = 0; i < paint.length; i++) {
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
        paint[9].setColor(ContextCompat.getColor(context, R.color.border));
        stage = new Stage();
        map = stage.PREVIEWMAP;
        mainHandler = handler;
        unit = block_pixel_unit;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 스테이지를 그린다
        for (int i = 0; i < stageWidth; i++) {
            for (int j = 0; j < stageHeight; j++) {
                canvas.drawRect(
                        (i * unit)
                        , (j * unit)
                        , (i * unit + unit)
                        , (j * unit + unit)
                        , paint[map[j][i]]
                );


            }
        }
        if (currentBlockGroup != null && notFirst>0) {
            int cBlock[][] = currentBlockGroup.getBlock();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (cBlock[j][i] > 0) {
                        canvas.drawRect(
                                i * unit - 1
                                , j * unit - 1
                                , i * unit + unit - 1
                                , j * unit + unit - 1
                                , paint[cBlock[j][i]]
                        );
                    }
                }
            }
        }


    }
}
