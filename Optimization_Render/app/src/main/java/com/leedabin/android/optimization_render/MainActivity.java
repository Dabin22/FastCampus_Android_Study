package com.leedabin.android.optimization_render;

import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Debug.startMethodTracing("traceResult01");

        Thread  another01 = new Thread(){
            @Override
            public void run(){
              for(int i=100; i>0; i--)
                  Log.i("another01 tag", "print ===="+i);
            }
        };
        Thread  another02 = new Thread(){
            @Override
            public void run(){
                for(int i=100; i>0; i--)
                    Log.i("another02 tag", "print ===="+i);
            }
        };

        another01.start();
        another02.start();
        print100000();


    }

    public void print100000(){
        for(int i=0; i<1000; i++)
        {
            Log.i("main tag","print ===="+i);
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Debug.stopMethodTracing();
    }
}
