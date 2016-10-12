package com.leedabin.android.materialdesign_propertyanimation01;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
    /*
            ObjectAnimator 사용법
            1. 애니메이터를 정의한다.
            ObjectAnimator ani = ObjectAnimator.ofFloat(대상의 객체,개체의 속성명, 속성값);

            2. 정의된 애니메이터를 실행한다.
            ani.start();
    */

public class MainActivity extends AppCompatActivity {

    ImageButton player;
    int x = 0;
    int y = 0;
    int gx = 0;
    int gy = 0;
    int px = 0;
    int py = 0;

    float scale_x =1f;
    float scale_y =1f;
    int r = 0;
    RelativeLayout ground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player = (ImageButton) findViewById(R.id.player);
        ground = (RelativeLayout) findViewById(R.id.ground);
    }

    private void setGruondSize() {
        gx = ground.getWidth();
        gy = ground.getHeight();
        px = player.getWidth();
        py = player.getHeight();
        Log.i("MainActivity on Resume", "px=" + px + ", py=" + py);
        //          태그                              로그
    }

    public void up(View v) {
        setGruondSize();
        y = y - 50;

        if ((-(gy / 2) + (py / 2)) <= y) {
            ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationY", y);
            ani.setDuration(1000);
            ani.start();
        } else {
            y = y + 50;
        }
    }

    public void down(View v) {
        setGruondSize();
        y = y + 50;
        if ((gy / 2 - py / 2) >= y) {
            ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationY", y);
            ani.setDuration(1000);
            ani.start();
        } else {
            y -= 50;
        }
    }

    public void left(View v) {
        setGruondSize();
        x = x - 50;
        if ((px / 2 - (gx / 2)) <= x) {
            ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationX", x);
            ani.setDuration(1000);
            ani.start();
        } else {
            x += 50;
        }
    }

    public void right(View v) {
        setGruondSize();
        x = x + 50;
        if ((gx / 2 - px / 2) >= x) {
            ObjectAnimator ani = ObjectAnimator.ofFloat(player, "translationX", x);
            ani.setDuration(1000);
            ani.start();
        } else {
            x -= 50;
        }
    }

    public void showMessage(View v) {
        Toast.makeText(this, "I am Here!!! ", Toast.LENGTH_SHORT).show();
    }

    public void rotate(View v) {
        r = r + 90;
        ObjectAnimator ani = ObjectAnimator.ofFloat(player, "rotation", r);
        ani.setDuration(1000);
        ani.start();
    }

    public void smaller(View v) {
        scale_x /=2;
        scale_y /=2;
        ObjectAnimator ani1 = ObjectAnimator.ofFloat(player, "scaleX", scale_x);
        ObjectAnimator ani2 = ObjectAnimator.ofFloat(player, "scaleY", scale_y);

        //여러개의 애니메이션 동시에 사용하기
        // 1, ANimatorSet을 초기화한다.
        AnimatorSet aniSet = new AnimatorSet();
        // 2. PlayTogether에 애니메이션을 담아준다.
        aniSet.playTogether(ani1,ani2);
        aniSet.setDuration(1000);
        // 3. 애니메이터 셋을 실행한다.
        aniSet.start();
    }

    public void bigger(View v) {
        scale_x *=2;
        scale_y *=2;
        ObjectAnimator ani1 = ObjectAnimator.ofFloat(player, "scaleX", scale_x);
        ObjectAnimator ani2 = ObjectAnimator.ofFloat(player, "scaleY", scale_y);

        AnimatorSet aniSet = new AnimatorSet();
        aniSet.playTogether(ani1,ani2);
        aniSet.setDuration(1000);
        aniSet.start();

    }
}
