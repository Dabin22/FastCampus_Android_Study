package com.example.dabin.firebase_database01;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by Dabin on 2016-11-01.
 */

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {
    int itemLayout;
    ArrayList<Branch> datas;
    MainActivity mainActivity;
    public StoreAdapter(ArrayList<Branch> datas, int itemLayout,MainActivity mainActivity){
        this.datas = datas;
        this.itemLayout = itemLayout;
        this.mainActivity = mainActivity;

    }
    @Override
    public StoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StoreAdapter.ViewHolder holder, final int position) {
        Branch branch = datas.get(position);

        Glide.with(mainActivity)
                .load(branch.getLOGO())
                .bitmapTransform(new CropCircleTransformation(mainActivity))
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {

                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {

                        return false;
                    }
                })
                .into(holder.imageView);

        holder.tv_name.setText(branch.getNAME());
        holder.tv_branch.setText(branch.getBRANCH());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainActivity,DetailActivity.class);
                intent.putExtra("position",position);
                mainActivity.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_name;
        TextView tv_branch;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            tv_name = (TextView)itemView.findViewById(R.id.tv_name);
            tv_branch = (TextView)itemView.findViewById(R.id.tv_branch);

        }
    }
}
