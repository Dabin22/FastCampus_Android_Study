package com.leedabin.android.activitybasic01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

public class Activity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
    }


    //액티비티 3 호출
    public void openActvity3(View v)
    {
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }
}
