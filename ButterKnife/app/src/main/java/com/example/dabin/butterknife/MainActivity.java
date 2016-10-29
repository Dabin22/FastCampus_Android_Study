package com.example.dabin.butterknife;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tvResult)
    TextView tvResult;

    @BindView(R.id.btnClick)
    Button btnClick;

    @BindView(R.id.btnString)
    Button btnString;

    @BindView(R.id.btnImage)
    Button btnImage;
    @BindString(R.string.butterknife)
    String value;
    @BindView(R.id.imageView)
    ImageView iv;
    @BindDrawable(R.mipmap.ic_launcher)
    Drawable drawable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnClick)
    public void onClickBind(){
        tvResult.setText("클릭");
    }

    @OnClick(R.id.btnString)
    public void setBtnString(){
        tvResult.setText(value);
    }

    @OnClick(R.id.btnImage)
    public void setBtnImage(){
        iv.setImageDrawable(drawable);
    }
}
