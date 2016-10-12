package com.leedabin.android.basiclist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    ImageView iv;
    TextView title_view;
    TextView name_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();
        int position = bundle.getInt("position");
        RecyclerData data = RecyclerAnimationActivity.datas.get(position);
        int image = data.image;
        String title = data.title;
        String name = data.name;

        iv = (ImageView)findViewById(R.id.imageView2);
        title_view = (TextView)findViewById(R.id.titleDetail);
        name_view = (TextView)findViewById(R.id.nameDetail);

        iv.setImageResource(image);
        title_view.setText(title);
        name_view.setText(name);

    }
}
