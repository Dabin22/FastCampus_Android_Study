package com.leedabin.android.basiclist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Dabin on 2016-09-29.
 */
public class RecyclerCardAdapter extends RecyclerView.Adapter<RecyclerCardAdapter.ViewHolder>{

    ArrayList<RecyclerData> datas;
    int itemLayout;
    Context context;

    public RecyclerCardAdapter(ArrayList<RecyclerData> datas, int itemLayout, Context context)
    {
        this.datas =datas;
        this.itemLayout = itemLayout;
        this.context =context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        RecyclerData data = datas.get(position);
        holder.iv.setImageResource(data.image);
        holder.textTitle.setText(data.title);


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context,"이미지 클릭",Toast.LENGTH_SHORT).show();
            }
        });


        //태그값을 준다 ->아이디부여의 의미
        holder.itemView.setTag(data);
        setAnimation(holder.cardView,position);

    }

    int lastPosition =-1;
    public void setAnimation(View v,int position){
        if(position > lastPosition) {
            Animation ani = new AnimationUtils().loadAnimation(context, android.R.anim.slide_in_left);

            ani.setDuration(3000);
            v.startAnimation(ani);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView textTitle;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView)itemView.findViewById(R.id.card_image);
            textTitle = (TextView)itemView.findViewById(R.id.cardTitle);
            cardView = (CardView)itemView.findViewById(R.id.cardItem);
        }
    }
}
