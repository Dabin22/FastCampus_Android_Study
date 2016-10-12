package com.leedabin.android.basiclist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomListActivity extends AppCompatActivity {

    ListView listView;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list);

        listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> datas = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            datas.add(i + "");
        }

        customAdapter = new CustomAdapter(this, datas);
        listView.setAdapter(customAdapter);
    }
}

class CustomAdapter extends BaseAdapter {
    Context context;    //context
    ArrayList datas;    //data array
    LayoutInflater inflater; //xml file을 instance화 해줘서 메모리에 올려준다.

    public CustomAdapter(Context context, ArrayList datas) {
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {



        if (convertView == null) {
            //뷰를 인플레이터에 넣어서 인스턴스화 파라메터1은 커스텀 레이아웃 2,널로 설정
            convertView = inflater.inflate(R.layout.activity_custom_list_item, null);
            Log.i("GETVIEW", "-------position-------"+position);
            Log.i("VIEWGROUP", ">>>>>>>>>parent = " + viewGroup);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.custom_item1);
        tv.setText(datas.get(position).toString());
        return convertView;
    }
}
