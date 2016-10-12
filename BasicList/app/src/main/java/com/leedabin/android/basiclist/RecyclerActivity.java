package com.leedabin.android.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        ArrayList<RecyclerData> datas = new ArrayList<>();
        for(int i=0; i<100; i++)
        {
            RecyclerData data = new RecyclerData();
            data.title = ("thriller "+ i);
            data.name="michael";
            data.image = R.mipmap.michale;
            datas.add(data);
        }
        RecyclerView listView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerAdapter rAdapter = new RecyclerAdapter(datas,R.layout.activity_recycler_item);
        listView.setAdapter(rAdapter);
        listView.setLayoutManager(new LinearLayoutManager(this));
    }
}
