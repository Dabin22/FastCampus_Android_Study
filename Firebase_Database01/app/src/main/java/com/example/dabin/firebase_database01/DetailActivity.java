package com.example.dabin.firebase_database01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class DetailActivity extends AppCompatActivity {
    Branch branch;
    TextView tv_detail_fee;
    TextView tv_detail_storeName;
    RecyclerView menu_list;
    TextView tv_detail_branch;
    ImageView imageVIew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        int index = intent.getExtras().getInt("position");
        branch = MainActivity.branches.get(index);
        tv_detail_storeName = (TextView)findViewById(R.id.tv_detail_storeName);
        tv_detail_branch = (TextView)findViewById(R.id.tv_detail_branch);
        tv_detail_fee = (TextView)findViewById(R.id.tv_detail_fee);
        imageVIew = (ImageView)findViewById(R.id.imageView2);
        tv_detail_fee.setText(branch.getDELIVERY_FEE()+"");
        tv_detail_storeName.setText(branch.getNAME());
        tv_detail_branch.setText(branch.getBRANCH());
        menu_list = (RecyclerView)findViewById(R.id.menu_list);
        MenuAdapter menuAdapter = new MenuAdapter(this,(ArrayList)branch.getMENU(),R.layout.main_list_item);
        menu_list.setAdapter(menuAdapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        menu_list.setLayoutManager(manager);

        Glide.with(this)
                .load(branch.getLOGO())
                .bitmapTransform(new CropCircleTransformation(this))
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
                .into(imageVIew);



    }
}
