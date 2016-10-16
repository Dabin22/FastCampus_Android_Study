package com.leedabin.android.broadcastsender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void sendBroadcast(View v){
        Intent intent = new Intent("com.leedabin.android.MESSAGE");
        //intent.putExtra("msg","hello guys~~~");
        endBroadcast(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
