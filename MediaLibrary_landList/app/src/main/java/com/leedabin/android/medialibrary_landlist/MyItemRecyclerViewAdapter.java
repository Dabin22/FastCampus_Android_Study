package com.leedabin.android.medialibrary_landlist;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<MusicData> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(List<MusicData> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.title_tv.setText(mValues.get(position).title);
        holder.singer_tv.setText(mValues.get(position).singer);
        holder.album_iv.setImageBitmap(mValues.get(position).album_id);

        holder.item_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onListFragmentInteraction(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView title_tv;
        public final TextView singer_tv;
        public final ImageView album_iv;
        public final LinearLayout item_layout;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            title_tv = (TextView)view.findViewById(R.id.music_title);
            singer_tv = (TextView)view.findViewById(R.id.singer);
            album_iv = (ImageView)view.findViewById(R.id.image);
            item_layout = (LinearLayout)view.findViewById(R.id.item_layout);


        }

    }
}
