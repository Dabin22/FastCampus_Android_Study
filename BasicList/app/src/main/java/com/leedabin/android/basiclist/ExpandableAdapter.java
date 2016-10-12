package com.leedabin.android.basiclist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dabin on 2016-09-28.
 */
public class ExpandableAdapter extends BaseExpandableListAdapter {
    Context context;
    int groupLayout;
    int childLayout;
    ArrayList<ExpandData> datas;
    LayoutInflater inflater;

    public ExpandableAdapter(Context context, int groupLayout, int childLayout, ArrayList<ExpandData> datas){
        this.context = context;
        this.groupLayout = groupLayout;
        this.childLayout = childLayout;
        this.datas =datas;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getGroupCount() {
        return datas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return datas.get(groupPosition).guNmaes.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return datas.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPostion) {
        return datas.get(groupPosition).guNmaes.get(childPostion);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int chidPositon) {
        return chidPositon;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = inflater.inflate(groupLayout, parent, false);

        TextView tv = (TextView)convertView.findViewById(R.id.expand_parent_tv);
        tv.setText(datas.get(groupPosition).cityName);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = inflater.inflate(childLayout, parent, false);

        TextView tv = (TextView)convertView.findViewById(R.id.expand_child_tv);
        tv.setText(datas.get(groupPosition).guNmaes.get(childPosition));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
