package com.leedabin.android.notepad;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leedabin.android.notepad.com.leedabin.android.notepad.domain.NotepadData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Dabin on 2016-09-30.
 */
public class Recycler_adapter extends RecyclerView.Adapter<Recycler_adapter.ViewHolder> {

    ArrayList<NotepadData> datas;
    int itemLayout;
    MainActivity mainActivity;
    ViewHolder selectViewholder;

    public Recycler_adapter(ArrayList<NotepadData> datas, int itemLayout, MainActivity mainActivity) {
        this.datas = datas;
        this.itemLayout = itemLayout;
        this.mainActivity = mainActivity;
    }
    public void setDatas(ArrayList<NotepadData>datas)
    {
        this.datas= datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(view);
    }
    NotepadData data;
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        data = datas.get(position);

        holder.textTitle.setText(data.title_string);
        holder.textNo.setText(position+1+"");
        Date date = new Date(data.nDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String convertDate =  dateFormat.format(date) ;
        holder.textDate.setText(convertDate+"");


        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.keyBoardOff();
                mainActivity.showItem(datas.get(position).content_string,datas.get(position).no);
            }
        });

        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                holder.delete_btn.setVisibility(view.VISIBLE);
                mainActivity.deleteMode();
                selectViewholder = holder;
                return true;
            }
        });

        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectViewholder = holder;
                mainActivity.delete_item(data.no);
                mainActivity.returnMode();

            }
        });


        //태그값을 준다 ->아이디부여의 의미
        holder.itemView.setTag(data);


    }


    @Override
    public int getItemCount() {
        return datas.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textNo;
        TextView textDate;
        RelativeLayout layout;
        ImageButton delete_btn;


        public ViewHolder(View itemView) {
            super(itemView);
            textTitle = (TextView) itemView.findViewById(R.id.cardTitle);
            textNo = (TextView) itemView.findViewById(R.id.cardNo);
            textDate = (TextView) itemView.findViewById(R.id.cardDate);
            layout = (RelativeLayout) itemView.findViewById(R.id.item_layout);
            delete_btn = (ImageButton) itemView.findViewById(R.id.delete_btn);
        }
    }

    public ViewHolder selectedViewHolder() {
        return selectViewholder;
    }

}