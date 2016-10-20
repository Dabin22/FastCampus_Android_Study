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
public class MainStage extends View {
    public static final int REFRESH = 0;
    public static final int NEWBLOCK = 1;


    Paint paint[] = new Paint[10];
    public static int interval = 1000;
    MainStage mainStage;
    static int map[][];
    Stage stage;
    Handler mainHandler;
    int unit = 0;
    final int stageWidth = 14;
    final int stageHeight = 21;
    final int stageTop = 1;
    final int stageLeft = 1;


    int width = 4;
    int height = 4;
    int count = 0;
    //현재 스테이지에서 움직이고 있는 블럭
    static Block currentBlockGroup = null;

    public void setFirstBlock(Block newBlockGroup) {
        //생성자에서 호출된다.
            Log.i("tag", count + "번째 블럭");
            currentBlockGroup = newBlockGroup;
            currentBlockGroup.start();
            mainHandler.sendEmptyMessage(PreviewStage.NEXTBLOCK);
            count++;
    }

    public void setNextBlock(Block newBlockGroup)
    {
        if(PreviewStage.running) {
            currentBlockGroup = newBlockGroup;
            currentBlockGroup.start();
        }
    }



    public MainStage(Context context, Handler handler, int block_pixel_unit) {
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
        stage.setLevel(1);
        setStageMap(stage.currentMap());
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
                        (stageLeft + i) * unit
                        , (stageTop + j) * unit
                        , (stageLeft + i) * unit + unit
                        , (stageTop + j) * unit + unit
                        , paint[map[j][i]]
                );
            }
        }
        //현재 회전방향이 결정된 블럭을 가져와 그린다.
        if (currentBlockGroup != null) {
            int cBlock[][] = currentBlockGroup.getBlock();

            //blcok을 그린다.
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    if (cBlock[j][i] > 0) {
                        canvas.drawRect(
                                (stageLeft + i + currentBlockGroup.x) * unit - 1
                                , (stageTop + j + currentBlockGroup.y) * unit - 1
                                , (stageLeft + i + currentBlockGroup.x) * unit + unit - 1
                                , (stageTop + j + currentBlockGroup.y) * unit + unit - 1
                                , paint[cBlock[j][i]]
                        );
                    }
                }
            }
        }

    }

    public void leftBlock() {
        currentBlockGroup.x--;
        if (!currentBlockGroup.collisionCheck()) {
            invalidate();
        } else {
            currentBlockGroup.x++;
        }
    }

    public void rightBlock() {
        currentBlockGroup.x++;
        if (!currentBlockGroup.collisionCheck()) {
            invalidate();
        } else {
            currentBlockGroup.x--;
        }
    }

    public void downBlock() {

        Log.i("tag", "down!");
        currentBlockGroup.y++;
        if (!currentBlockGroup.collisionCheck()) {
            invalidate();
        } else {
            currentBlockGroup.y--;
            currentBlockGroup.setBlockIntoStage();
        }
    }


    public void rotateBlock() {
        currentBlockGroup.rotate();
        if (!currentBlockGroup.collisionCheck()) {
            invalidate();
            Log.i("tag", "변환 불가.....");
        } else {
            currentBlockGroup.rotateBack();

        }
    }

    public void setStageMap(int[][] stageMap) {
        map = stageMap.clone();
    }
}
