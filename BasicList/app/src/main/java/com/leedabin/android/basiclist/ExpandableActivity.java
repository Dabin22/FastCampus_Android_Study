package com.leedabin.android.basiclist;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class ExpandableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable);

        ExpandableListView listView = (ExpandableListView)findViewById(R.id.expandableListView);

        //아뎁터에 넘겨줄 데이터 정의
        ArrayList<ExpandData> datas = new ArrayList<>();
        ExpandData data = new ExpandData();
        data.cityName = "서울";
        data.guNmaes.add("강동");
        data.guNmaes.add("강서");
        data.guNmaes.add("강남");
        data.guNmaes.add("강북");
        data.guNmaes.add("마포");
        data.guNmaes.add("서초");
        data.guNmaes.add("동작");
        datas.add(data);

        data = new ExpandData();
        data.cityName = "광주";
        data.guNmaes.add("광산");
        data.guNmaes.add("중구");
        data.guNmaes.add("남구");
        data.guNmaes.add("서구");
        datas.add(data);

        data = new ExpandData();
        data.cityName = "부산";
        data.guNmaes.add("동래");
        data.guNmaes.add("수영");
        data.guNmaes.add("해운대");
        data.guNmaes.add("영도");
        data.guNmaes.add("중구");
        datas.add(data);

        ExpandableAdapter epAdapter = new ExpandableAdapter(this,R.layout.activity_expand_parent_item,R.layout.activity_expand_child_item,datas);


        //실제 화면 가로 크기 가져오기(pixel)
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2)
            listView.setIndicatorBounds(width-getPixelFromDisplay(50),width-getPixelFromDisplay(10));
        else
            listView.setIndicatorBoundsRelative(width-getPixelFromDisplay(50),width-getPixelFromDisplay(10));

        listView.setAdapter(epAdapter);
        // dp를 pixel로 변환할때
        int convertedPixel = (int)TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,200,getResources().getDisplayMetrics()
                                            //↑Dp 값 입력
        );

    }

    public int getPixelFromDisplay(float pixels){
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (pixels * scale + 0.5f);
    }

    public int pxToDp(Context context,int px){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px/(metrics.xdpi / metrics.DENSITY_DEFAULT));
        return dp;
    }
    public int dpToPx(Context context, int dp){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp *(metrics.xdpi / metrics.DENSITY_DEFAULT));
        return px;
    }
}
