package com.leedabin.android.materialdesign_propertyanimation_spreadcube;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    Button btn;
    ImageView iv1;
    ImageView iv2;
    ImageView iv3;
    ImageView iv4;
    ImageView iv5;
    ImageView iv6;
    ImageView iv7;
    ImageView iv8;
    ImageView iv9;
    RelativeLayout space;
    float x = 0;
    float y = 0;
    boolean spreaded = false;
    boolean check = false;
    AnimatorSet aniset;

    Collection<Animator> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1 = (ImageView) findViewById(R.id.blueBright);
        iv2 = (ImageView) findViewById(R.id.redLight);
        iv3 = (ImageView) findViewById(R.id.orange);
        iv4 = (ImageView) findViewById(R.id.purple);
        iv5 = (ImageView) findViewById(R.id.red);
        iv6 = (ImageView) findViewById(R.id.greenDark);
        iv7 = (ImageView) findViewById(R.id.greenLight);
        iv8 = (ImageView) findViewById(R.id.black);
        iv9 = (ImageView) findViewById(R.id.gray);
        btn = (Button) findViewById(R.id.button);
        space = (RelativeLayout) findViewById(R.id.space);
        list = new ArrayList<>();
        aniset = new AnimatorSet();

    }

    int degree;

    public void rotate(View v) {

        degree += 360;
        ObjectAnimator ani1 = ObjectAnimator.ofFloat(space, "rotation", degree);
        ani1.setDuration(4000);
        ani1.start();

        spread(space);
        combine(true);


    }

    public void setSize() {
        x = iv1.getWidth();
        y = iv1.getHeight();
        Log.i("tag", "x = " + x + " y= " + y);
    }

    public void spread(View v) {

        Log.i("tag", "v.getid() = " + v.getId() + "space.getId() = " + space.getId());

        if (v.getId() == space.getId()) {
            setSize();
            move(iv1, x, -y, check);
            move(iv2, 0, -y, check);
            move(iv3, -x, -y, check);
            move(iv4, -x, 0, check);
            move(iv6, x, 0, check);
            move(iv7, -x, y, check);
            move(iv8, 0, y, check);
            move(iv9, x, y, check);

        } else {
            check = false;
            if (!spreaded) {
                setSize();
                move(iv1, x, -y, check);
                move(iv2, 0, -y, check);
                move(iv3, -x, -y, check);
                move(iv4, -x, 0, check);
                move(iv6, x, 0, check);
                move(iv7, -x, y, check);
                move(iv8, 0, y, check);
                move(iv9, x, y, check);
                spreaded = true;
                btn.setText("Combine");
            } else {
                combine(check);
                spreaded = false;
                btn.setText("Spread");
            }
        }


    }

    public void combine(boolean check) {
        setSize();
        move(iv1, 0, 0, check);
        move(iv2, 0, 0, check);
        move(iv3, 0, 0, check);
        move(iv4, 0, 0, check);
        move(iv6, 0, 0, check);
        move(iv7, 0, 0, check);
        move(iv8, 0, 0, check);
        move(iv9, 0, 0, check);

    }

    public void move(ImageView iv, float x, float y, boolean check) {
        ObjectAnimator oaY = ObjectAnimator.ofFloat(iv, "translationY", y);
        ObjectAnimator oaX = ObjectAnimator.ofFloat(iv, "translationX", x);

        //같은 대상에 두개 이상의 에니매이션을 사용할때
        //에니메이션 셋을 만들어서 그안에 넣고
        //한꺼번에 실행시킬수 있다.
        AnimatorSet aniset = new AnimatorSet();
        aniset.playTogether(oaX, oaY);
        if (x == 0 && y == 0 && check)
            aniset.setStartDelay(1500); //시작을 딜레이만큼 늦게 한다.
        aniset.setDuration(1500);
        aniset.start();

    }


}
