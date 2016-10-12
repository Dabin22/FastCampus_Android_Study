package com.leedabin.android.basiclist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button listView1;
    Button listView2;
    Button listCustom;
    Button gridView;
    Button gridCustom;
    Button expandableList;
    Button recycler;
    Button recyclerA;
    Button recycler_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView1 = (Button)findViewById(R.id.button);
        listView2 = (Button)findViewById(R.id.button2);
        listCustom = (Button)findViewById(R.id.button3);
        gridView = (Button)findViewById(R.id.button4);
        gridCustom = (Button)findViewById(R.id.button5);
        expandableList = (Button)findViewById(R.id.button6);
        recycler = (Button)findViewById(R.id.button7);
        recyclerA = (Button)findViewById(R.id.button8);
        recycler_card = (Button)findViewById(R.id.button9);


        listView1.setOnClickListener(this);
        listView2.setOnClickListener(this);
        listCustom.setOnClickListener(this);
        gridView.setOnClickListener(this);
        gridCustom.setOnClickListener(this);
        expandableList.setOnClickListener(this);
        recycler.setOnClickListener(this);
        recyclerA.setOnClickListener(this);
        recycler_card.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()){
            case R.id.button:
                intent = new Intent(this,BasicList1Activity.class);
                break;
            case R.id.button2:
                intent = new Intent(this,BasicList2Activity.class);
                break;
            case R.id.button3:
                intent = new Intent(this,CustomListActivity.class);
                break;
            case R.id.button4:
                intent = new Intent(this,BasicGridActivity.class);
                break;
            case R.id.button5:
                intent = new Intent(this,CustomGridActivity.class);
                break;
            case R.id.button6:
                intent = new Intent(this,ExpandableActivity.class);
                break;
            case R.id.button7:
                intent = new Intent(this,RecyclerActivity.class);
                break;
            case R.id.button8:
                intent = new Intent(this,RecyclerAnimationActivity.class);
                break;
            case R.id.button9:
                intent = new Intent(this,RecyclerCardActivity.class);
                break;
        }
        startActivity(intent);
    }
}
