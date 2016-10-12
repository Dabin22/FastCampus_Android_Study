package com.leedabin.android.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAnimationActivity extends AppCompatActivity {
    public static ArrayList<RecyclerData> datas = null;
    //1. 리사이클러뷰를 현재 액티비의 메인레이아웃에 만든다.
    //2. 리사이클러뷰에 들어갈 아이템레이아웃을 만든다.
    //3.어뎁터를 만든다. !!(들어가 데이터가 지정되어 있다.
    //4. 리사이클러뷰에 아답터를 세팅한다.
    //5 리사이클러뷰에 레이아웃매니저를 지정한다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_animation);

        datas = new ArrayList<>();
        for(int i=0; i<100; i++)
        {
            RecyclerData data = new RecyclerData();
            data.title = ("thriller "+ i);
            data.name="michael";
            data.image = R.mipmap.michale;
            datas.add(data);
        }
        RecyclerView listView = (RecyclerView)findViewById(R.id.recyclerAView);
        RecyclerAnimationAdapter raAdapter = new RecyclerAnimationAdapter(datas,R.layout.activity_recycler_animation_item,this);
        listView.setAdapter(raAdapter);
        listView.setLayoutManager(new LinearLayoutManager(this));
    }
}
