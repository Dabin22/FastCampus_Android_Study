package com.example.dabin.firebase_database01;

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

import static com.example.dabin.firebase_database01.R.id.tv_branch;

/**
 * Created by Dabin on 2016-11-01.
 */

public class MenuAdapter extends  RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    DetailActivity detailActivity;
    ArrayList<MENU> datas;
    int itemLayout;
    public MenuAdapter(DetailActivity detailActivity, ArrayList<MENU> datas,int itemLayout){
        this.detailActivity = detailActivity;
        this.datas = datas;
        this.itemLayout = itemLayout;

    }
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuAdapter.ViewHolder holder, int position) {
        MENU menu = datas.get(position);

        Glide.with(detailActivity)
                .load(menu.getMENU_IMAGE())
                .bitmapTransform(new CropCircleTransformation(detailActivity))
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

        holder.tv_name.setText(menu.getMENU_NAME());
        holder.tv_price.setText(menu.getMENU_PRICE()+"");

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_name;
        TextView tv_price;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.imageView);
            tv_name = (TextView)itemView.findViewById(R.id.tv_name);
            tv_price = (TextView)itemView.findViewById(tv_branch);
        }
    }
}
