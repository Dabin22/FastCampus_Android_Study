package com.leedabin.android.threadbasic_tetris;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    FrameLayout tetris_ground;
    FrameLayout tetris_privew;
    private static final int M_ABS_X =5;
    private static final int M_ABS_Y =0;
    private static final int P_ABS_X =5;
    private static final int P_ABS_Y =0;
    int x_main = 0;
    int y_main = 0;
    int stageLevel = 1;

    int ground_width_pixel;
    int ground_height_pixel;
    final static int  MWIDTH_MAX_COUNT = 16;


    int block_pixel_unit =0;


    int currentBlock[][];

    Stage stage = new Stage();
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case Stage.REFRESH:
                    //화면 갱신을 Stage에 요청한다.
                    break;
            }
        }
    };

    MainStage mainStage;
    PreviewStage previewStage;
    Button btn_up, btn_down, btn_left, btn_right, btn_start, btn_pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tetris_ground = (FrameLayout) findViewById(R.id.tetrisGround);
        tetris_privew = (FrameLayout) findViewById(R.id.preview);
        btn_up = (Button) findViewById(R.id.btn_up);
        btn_down = (Button) findViewById(R.id.btn_up);
        btn_left = (Button) findViewById(R.id.btn_up);
        btn_right = (Button) findViewById(R.id.btn_up);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_pause = (Button) findViewById(R.id.btn_pause);


        DisplayMetrics metrics = getResources().getDisplayMetrics();
        ground_width_pixel = metrics.widthPixels-(int)tetris_privew.getX();

        // stageMap 배열에 Stage 객체에 정의된 배열값을 세팅한다.
        setStage(1);
        //뷰 객체를 생성한다.

    }

    public void setStage(int stageLevel) {
      stage.setLevel(stageLevel);

        //최초의 세팅되는 블럭의 위치
        x_main = M_ABS_X;
        y_main = M_ABS_Y;
    }


}
