package com.leedabin.android.fragmentbasic03;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dabin on 2016-10-05.
 */
class CustomAdapter extends BaseAdapter {
    MainActivity context;    //context
    ArrayList<FragmentData> datas;    //data array
    LayoutInflater inflater; //xml file을 instance화 해줘서 메모리에 올려준다.

    public CustomAdapter(MainActivity context, ArrayList datas) {
        this.context = context;
        this.datas = datas;

        // 시스템에서 xml을 개체화 시켜주는 인플레이터를 가져온다.
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //자식 뷰들의 갯수를 리턴
    @Override
    public int getCount() {
        return datas.size();
    }

    //선택한 순서의 자식 뷰를 리턴 , 1부터 시작
    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    //선택한 순서의 자식 뷰의 아이디 값을 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }


    //
    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {



        if (convertView == null) {
            //뷰를 인플레이터에 넣어서 인스턴스화 파라메터1은 커스텀 레이아웃 2,널로 설정
            convertView = inflater.inflate(R.layout.array_item, null);
            Log.i("GETVIEW", "-------position-------"+position);
            Log.i("VIEWGROUP", ">>>>>>>>>parent = " + viewGroup);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.textView);
        FragmentData data = datas.get(position);
        tv.setText(data.title);
        LinearLayout itemLayout = (LinearLayout)convertView.findViewById(R.id.itemLayout);

        itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = context;
                mainActivity.goDetail(position);
            }
        });
        return convertView;
    }
}