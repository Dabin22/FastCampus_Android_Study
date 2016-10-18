package com.leedabin.android.threadbasic_handler;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_result;
    Button btn_start;
    Button btn_call;
    Button btn_start_handler;
    Handler subHandler;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    tv_result.setText("input sub thread :" + msg.arg1);
                    break;

            }
        }
    };
    SubThread thread;
    LooperThread handlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_result = (TextView) findViewById(R.id.tv_result);
        btn_start = (Button) findViewById(R.id.btn_start);
        thread = new SubThread(handler);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                thread.start();
            }
        });

        btn_call = (Button) findViewById(R.id.btn_call);

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thread.printLog();
            }
        });
        handlerThread = new LooperThread(this,"HandleThread");
        handlerThread.start();



        btn_start_handler = (Button)findViewById(R.id.btn_start_handler);
        btn_start_handler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlerThread.looper_handler.sendEmptyMessage(LooperThread.SHOW_PROGRESS);
                run();
                handlerThread.looper_handler.sendEmptyMessage(LooperThread.HIDE_PROGRESS);
            }
        });
    }

    public void run(){
        try{
            int sum = 10;
            for(int i=0; i<20; i++)
            {
                sum += i;
                Thread.sleep(100);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    class SubThread extends Thread {
        Handler handler_m;
 //       Handler handler_sub;


        public SubThread(Handler handler) {
            this.handler_m = handler;

        }

        public void run() {
//            Looper.prepare();
//            Log.i("SubThread", "after prepare");
//            handler_sub = new Handler() {
//                @Override
//                public void handleMessage(Message msg) {
//                    super.handleMessage(msg);
//                }
//            };
//            Log.i("SubThread","before loop");
//            Looper.loop();
//            Log.i("SubThread", "after loop");
            int sum = 0;
            for (int i = 0; i < 5000; i++) {
                sum += i;
            }
            Message msg = new Message();
            msg.what = 1;
            msg.arg1 = sum;
            handler_m.sendMessage(msg);

        }

        public void printLog() {
            Log.i("SubThread", "called from the main Thread");
        }
    }


    class LooperThread extends HandlerThread{
        Handler looper_handler;
        Context context;
        ProgressDialog progress;
        public static final int SHOW_PROGRESS =1;
        public static final int HIDE_PROGRESS = 2;
        public LooperThread(Context context, String name) {
            super(name);
            this.context =context;


        }

        private void showProgressBar(){
            progress.show();
            Log.i("looperHandler","진행상태 보여주기");

        }
        private void hideProgressBar(){
            progress.dismiss();
            Log.i("looperHandler","진행상태 종료");
            quit();
        }

        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            progress = new ProgressDialog(context);
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setTitle("Progress Bar Title");
            progress.setMessage("Message");
            progress.setCancelable(false);


            looper_handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what){
                        case SHOW_PROGRESS:
                            showProgressBar();
                            break;
                        case HIDE_PROGRESS:
                            hideProgressBar();
                            break;

                    }
                }
            };
        }


    }
}
