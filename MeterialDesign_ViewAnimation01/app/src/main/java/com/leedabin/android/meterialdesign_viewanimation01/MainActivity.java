package com.leedabin.android.meterialdesign_viewanimation01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button)findViewById(R.id.btnAlpha);
        btn2 = (Button)findViewById(R.id.btnRotate);
        btn3 = (Button)findViewById(R.id.btnScale);
        btn4 = (Button)findViewById(R.id.btnTranslate);

    }
    public void onClickAlpha(View view) {
        // 1. 미리 정의된 애니메이션 xml을 로드한다.
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.alpha);
        // 2.애니메이션을 뷰에 적용하고 실행한다.
        btn1.startAnimation(animation);
    }

    public void onClickRotate(View view) {
        // 1. 미리 정의된 애니메이션 xml을 로드한다.
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate);
        // 2.애니메이션을 뷰에 적용하고 실행한다.
        btn2.startAnimation(animation);
    }

    public void onClickScale(View view) {
        // 1. 미리 정의된 애니메이션 xml을 로드한다.
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale);
        // 2.애니메이션을 뷰에 적용하고 실행한다.
        btn3.startAnimation(animation);
    }

    public void onClickTranslate(View view) {
        // 1. 미리 정의된 애니메이션 xml을 로드한다.
        Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.translate);
        // 2.애니메이션을 뷰에 적용하고 실행한다.
        btn4.startAnimation(animation);
    }
}
