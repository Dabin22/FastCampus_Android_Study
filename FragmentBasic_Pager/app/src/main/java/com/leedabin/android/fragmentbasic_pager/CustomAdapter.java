package com.leedabin.android.fragmentbasic_pager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dabin on 2016-10-06.
 */
public class CustomAdapter extends BaseAdapter {
    ArrayList<FragmentData> datas;
    Context context;
    LayoutInflater inflater;

    public CustomAdapter(ArrayList<FragmentData>datas,Context context){
        this.datas = datas;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_layout, null);

        }
        TextView index_tv = (TextView)convertView.findViewById(R.id.index_tv);
        TextView title_tv = (TextView)convertView.findViewById(R.id.title_tv);

        index_tv.setText((position+1)+"");
        title_tv.setText(datas.get(position).title);

        return convertView;
    }
}
