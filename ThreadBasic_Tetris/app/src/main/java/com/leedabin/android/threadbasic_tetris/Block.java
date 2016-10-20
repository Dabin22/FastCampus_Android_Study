package com.leedabin.android.threadbasic_tetris;

import android.os.Handler;
import android.util.Log;

import java.util.Random;

/**
 * Created by Dabin on 2016-10-17.
 */
public class Block extends Thread {

    int x = 5;
    int y = 0;
    int blockType[][][];
    int orientation = 0;
    int interval = 0;
    int block[][];
    static final int ORIENTATION_LIMIT = 4;
    boolean alive = true;
    Handler handler;
    private int width = 4;
    private int height = 4;

    public Block(int interval, int[][][] blockType, Handler handler) {
        this.interval = interval;
        this.blockType = blockType;
        block = blockType[orientation];
        this.handler = handler;
    }

    public int[][] getBlock() {
        return block;
    }

    //변환
    public void rotate() {
        orientation++;
        orientation %= ORIENTATION_LIMIT;
        block = blockType[orientation];

    }

    // 회전 거꾸로
    public void rotateBack() {
        orientation--;
        block = blockType[orientation];
    }




    //생성되고 화면에 세팅되면
    public void run() {
        while (alive) {
            try {
                Thread.sleep(interval);
                if (alive) {
                    y++;
                    if (!collisionCheck()) {
                        handler.sendEmptyMessage(MainStage.REFRESH);
                    } else {
                        y--;
                        setBlockIntoStage();
                    }
                }else {
                    Log.i("tag","이러면 죽은 건데 alive = false");
                }
            } catch (Exception e) {

            }
        }
        try{
            interrupt();
        }catch (Exception e){ e.printStackTrace(); }
    }

    public void setBlockIntoStage() {
        alive = false;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                // 현재 블럭의 셀의 값을 가져온다
                int cBlockValue = block[j][i];
                if (cBlockValue > 0) { // 현재 블럭 셀의 값이 0보다 클경우만 스테이지에 담는다
                    // 스테이지 셀에 블럭셀의 값을 담아준다
                    MainStage.map[y + j][x + i] = cBlockValue;
                }
            }
        }

        // 블럭을 스테이지에 입력한 후에 해당 블럭범위에 있는 스테이지 가로줄이 꽉찼으면 지워준다
        for (int i = y; i < y + 4; i++) {
            // 전체 스테이지 height값보다 작을때만
            if (i < 20) {
                // 스테이지 맵에서 한줄식 꺼낸다
                int row[] = MainStage.map[i];
                int zeroCount = 0;
                // 각 로우의 셀에 0이 있는지 검사
                for (int j = 0; j < row.length; j++) {
                    if (row[j] == 0) {
                        zeroCount++;
                    }
                }
                // 각로우의 셀에 0이 한개도 없으면
                if (zeroCount == 0) {
                    // 해당줄을 지운다
                    for (int k = 1; k < row.length - 1; k++) {
                        MainStage.map[i][k] = 0;
                    }
                }
            }
        }

        MainStage.currentBlockGroup = null;
        handler.sendEmptyMessage(MainStage.NEWBLOCK);
    }

    public boolean collisionCheck() {
        boolean result = false;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int cBlockValue = block[j][i];
                if (cBlockValue > 0) {
                    int sum = cBlockValue + MainStage.map[y + j][x + i];
                    if (sum > cBlockValue) {
                        return true;
                    }
                }
            }
        }
        return result;
    }


}
