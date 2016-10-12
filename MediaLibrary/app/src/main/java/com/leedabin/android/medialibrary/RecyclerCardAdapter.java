package com.leedabin.android.medialibrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dabin on 2016-10-04.
 */
public class RecyclerCardAdapter extends RecyclerView.Adapter<RecyclerCardAdapter.ViewHolder> {
    ArrayList<RecyclerData> datas;
    int itemLayout;
    Context context;

    RecyclerCardAdapter(ArrayList<RecyclerData> datas, int itemLayout, Context context) {
        this.datas = datas;
        this.itemLayout = itemLayout;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(view);
    }




    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerData data = datas.get(position);

        Log.i("Tag","datas.album_id = "+data.album_id +", datas.music_id = " +data.music_id);
        holder.title.setText(data.title);
        holder.singer.setText(data.singer);
        if(data.album_id != null)
            holder.image.setImageBitmap(data.album_id);
        else
            holder.image.setImageResource(R.mipmap.ic_launcher);

        holder.itemView.setTag(data);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView singer;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.music_title);
            singer = (TextView) itemView.findViewById(R.id.singer);
            image = (ImageView) itemView.findViewById(R.id.image);

        }
    }


}
