package com.leedabin.android.basiclist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dabin on 2016-09-28.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    ArrayList<RecyclerData> datas;
    int itemLayout;

    public RecyclerAdapter(ArrayList<RecyclerData> datas,int itemLayout)
    {
        this.datas =datas;
        this.itemLayout = itemLayout;
    }
    //view 를 만들어서 홀더에 저장하는 역할
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent, false);
        return new ViewHolder(view);
    }

    //일반 Adapter의 getView를 대처하는 함수
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerData data = datas.get(position);
        holder.iv.setBackgroundResource(data.image);
        holder.textTitle.setText(data.title);
        holder.textName.setText(data.name);

        //태그값을 준다 ->아이디부여의 의미
        holder.itemView.setTag(data);

    }


    @Override
    public int getItemCount() {
        return datas.size();
    }


    //데이터를 재사용해주는 객체
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView textTitle;
        TextView textName;
        public ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView)itemView.findViewById(R.id.imageView);
            textTitle = (TextView)itemView.findViewById(R.id.textTitle);
            textName = (TextView)itemView.findViewById(R.id.textName);

        }
    }
}
