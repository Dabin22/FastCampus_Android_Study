package com.leedabin.android.threadbasic_tetris;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FrameLayout tetris_ground;
    FrameLayout tetris_privew;

    public static final int SCORE_UP = 10;

    int ground_width_pixel;
    int ground_height_pixel;

    int preview_width_pixel;
    int preview_height_pixel;
    final static int MWIDTH_MAX_COUNT = 16;
    final static int PWIDTH_MAX_COUNT = 6;


    public int score =0;

    Block blockGroup = null;
    int mBlock_pixel_unit = 0;
    int pBlock_pixel_unit = 0;

    TextView tv_score;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MainStage.REFRESH:
                    //화면 갱신을 Stage에 요청한다.
                    mainStage.invalidate();
                    break;
                case MainStage.NEWBLOCK:
                    mainStage.setNextBlock(blockGroup);
                    mainStage.invalidate();
                    blockGroup = previewStage.newBlock();
                    previewStage.invalidate();
                    break;
                case PreviewStage.NEXTBLOCK:
                    blockGroup = previewStage.newBlock();
                    previewStage.invalidate();
                    break;
                case MainActivity.SCORE_UP:
                    score +=100;
                    tv_score.setText(score+"");
                    break;
                case MainStage.PREVIEW_CLEAR:
                    previewStage.clear = true;
                    previewStage.invalidate();
                    break;
            }
        }
    };


    MainStage mainStage;
    PreviewStage previewStage;
    Button btn_up, btn_down, btn_left, btn_right, btn_start, btn_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tetris_ground = (FrameLayout) findViewById(R.id.tetrisGround);
        tetris_privew = (FrameLayout) findViewById(R.id.preview);
        btn_up = (Button) findViewById(R.id.btn_up);
        btn_down = (Button) findViewById(R.id.btn_down);
        btn_left = (Button) findViewById(R.id.btn_left);
        btn_right = (Button) findViewById(R.id.btn_right);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_end = (Button) findViewById(R.id.btn_end);
        btn_up.setOnClickListener(this);
        btn_down.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        btn_start.setOnClickListener(this);
        btn_end.setOnClickListener(this);

        tv_score = (TextView)findViewById(R.id.score_tv);
        tv_score.setText(score+"");

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

        if (hasFocus) {
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            ground_width_pixel = metrics.widthPixels - (int) tetris_privew.getWidth();
            ground_height_pixel = metrics.heightPixels;
            preview_width_pixel = tetris_privew.getWidth();
            preview_height_pixel = tetris_privew.getHeight();


            mBlock_pixel_unit = ground_width_pixel / MWIDTH_MAX_COUNT;
            pBlock_pixel_unit = preview_width_pixel / PWIDTH_MAX_COUNT;

            previewStage = new PreviewStage(this,handler,pBlock_pixel_unit);
            mainStage = new MainStage(this,handler,mBlock_pixel_unit);
            tetris_ground.addView(mainStage);
            tetris_privew.addView(previewStage);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_up:
                if (blockGroup != null)
                    mainStage.rotateBlock();
                break;
            case R.id.btn_down:
                if (blockGroup != null)
                    mainStage.downBlock();
                break;
            case R.id.btn_left:
                if (blockGroup != null)
                    mainStage.leftBlock();
                break;
            case R.id.btn_right:
                if (blockGroup != null)
                    mainStage.rightBlock();
                break;
            case R.id.btn_start:
                if(!mainStage.running) {
                    previewStage.clear = false;
                    blockGroup = previewStage.newBlock();
                    previewStage.notFirst++;
                    mainStage.running = true;
                    mainStage.setFirstBlock(blockGroup);
                    mainStage.invalidate();
                }
                break;
            case R.id.btn_end:
                Log.i("tag","btn end click");
                setGame();
                break;
        }
    }

    public void setGame(){
        mainStage.currentBlockDied();
        mainStage.running = false;
        mainStage.invalidate();
        score = 0;
        tv_score.setText("");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("tag","onRestart Mode");
        if(blockGroup != null) {
            blockGroup.pause = false;
            mainStage.invalidate();
            previewStage.invalidate();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("tag","OnStop mode");
        if(blockGroup != null) {
            blockGroup.pause = true;
            mainStage.invalidate();
            previewStage.invalidate();
        }
    }

    @Override
    protected void onDestroy() {
        Log.i("tag","onDestroy mode");
        setGame();
        super.onDestroy();
    }

}
