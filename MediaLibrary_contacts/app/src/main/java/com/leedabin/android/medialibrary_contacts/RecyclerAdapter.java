package com.leedabin.android.medialibrary_contacts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dabin on 2016-10-05.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<PhoneData> datas;
    Context context;
    int layout;

    RecyclerAdapter(ArrayList<PhoneData> datas,Context context,int layout){
        this.datas =datas;
        this.context =context;
        this.layout = layout;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PhoneData data =datas.get(position);
        if(data != null) {
            if (data.name != null)
                holder.name_tv.setText(data.name);
            else
                holder.name_tv.setText("null");
            holder.num_tv.setText(data.phone);

            holder.itemView.setTag(data);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView num_tv;
        TextView name_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            num_tv = (TextView)itemView.findViewById(R.id.number);
            name_tv =(TextView) itemView.findViewById(R.id.name);
        }
    }
}
