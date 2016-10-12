package com.leedabin.android.sqlitebasic_bbs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dabin on 2016-10-10.
 */
public class DBListAdapter extends BaseAdapter {
    private ArrayList<BbsData> datas = null;
    private Context context;
    private LayoutInflater inflater;

    public DBListAdapter(ArrayList<BbsData> datas,Context context){
        this.datas = datas;
        this.context = context;

        inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
            return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            //뷰를 인플레이터에 넣어서 인스턴스화 파라메터1은 커스텀 레이아웃 2,널로 설정
            view = inflater.inflate(R.layout.fragment_list_item, null);

        }
        TextView no = (TextView)view.findViewById(R.id.number_tv);
        TextView title = (TextView)view.findViewById(R.id.title_list_tv);

        no.setText(datas.get(i).no+"");
        title.setText(datas.get(i).title);


        return view;
    }
}
