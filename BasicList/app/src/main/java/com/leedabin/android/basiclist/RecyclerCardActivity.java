package com.leedabin.android.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

public class RecyclerCardActivity extends AppCompatActivity {
    public static ArrayList<RecyclerData> datas = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_card);

        datas = new ArrayList<>();
        for(int i=1; i<=100; i++)
        {
            RecyclerData data = new RecyclerData();
            data.title = ("thriller "+ i);
            if(i%3 == 0){
                data.image =R.mipmap.ic_launcher;
            }else
                data.image = R.mipmap.michale;
            data.name="michael";

            datas.add(data);
        }
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerCardView);
        RecyclerCardAdapter rcAdapter = new RecyclerCardAdapter(datas,R.layout.activity_recycler_card_item,this);
        recyclerView.setAdapter(rcAdapter);
        RecyclerView.LayoutManager manager1 = new LinearLayoutManager(this);
        RecyclerView.LayoutManager manager2 = new GridLayoutManager(this,2);
        RecyclerView.LayoutManager manager3 = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager3);


    }
}
