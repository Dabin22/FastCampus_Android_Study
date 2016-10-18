package com.leedabin.android.threadbasic_tetris;

import android.os.Handler;

import java.util.Random;

/**
 * Created by Dabin on 2016-10-17.
 */
public class Block extends Thread {

    int x =5;
    int y =0;
    int blockType[][][];
    int orientation =0;
    int interval = 0;
    int block[][];
    static final int ORIENTATION_LIMIT =4;
    boolean alive = true;
    Handler handler;
    public void draw(){
        handler.sendEmptyMessage(Stage.REFRESH);
    }

    public Block(int interval, int[][][] blockType, Handler handler){
        this.interval = interval;
        this.blockType = blockType;
        block = blockType[orientation];
        this.handler = handler;
    }

    //변환
    public void rotate(){
        orientation++;
        orientation %= ORIENTATION_LIMIT;

    }

    //이동
    public void move(int x, int y){
        this.x = x;
        this.y = y;
    }


    //생성되고 화면에 세팅되면
    public void run(){
        while(alive){
            try{
                y++;
                Thread.sleep(interval);
            }catch (Exception e){

            }
        }
    }



}
