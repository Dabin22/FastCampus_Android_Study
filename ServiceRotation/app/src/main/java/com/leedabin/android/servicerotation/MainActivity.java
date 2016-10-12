package com.leedabin.android.servicerotation;

import android.animation.ObjectAnimator;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/*

    3가지 방법으로 하였습니다.
    1.액티비티->서비스->브로드캐스트리시버->액티비티
    2. 액티비티->서비스->액티비티(인텐트로만)
    3. 액티비티->서비스->액티비티(pendingIntent사용)
 */
public class MainActivity extends AppCompatActivity {

    private static final int request = 1000;
    private static final String result = "result";

    Button btn_run;
    Button rotate_object;
    int degree = 0;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == request && resultCode == 100) {
            Bundle rotate_check = data.getExtras();
            int result = rotate_check.getInt("rotate");
            if (result != 0) {
                rotate(result);
            } else {
                Log.i("tag", "result = 0");
            }
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_run = (Button) findViewById(R.id.btn_run);
        rotate_object = (Button) findViewById(R.id.rotate_object);


        btn_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Rotation_Service.class);
                Log.i("tag","Button Click!");
//                PendingIntent pe = createPendingResult(request, new Intent(), 0);
//                intent.putExtra(result,pe);
                startService(intent);

            }
        });

        onResume_rotation();


    }

  //1,2번 방식은 새로운 레이아웃을 띄어서 메인 엑티비티화면이 정지되었다가 사용되는 방식으로 쓰인다.
    protected void onResume_rotation() {
        super.onResume();
        Log.i("tag","resume()!");
        try{
            Intent check = getIntent();
            Bundle rotate_check = check.getExtras();
            Boolean Request = false;
            Request = rotate_check.getBoolean("broadcast");
            int result = 0;
            result = rotate_check.getInt("rotate");

            if (Request) {
                if (result != 0) {
                    if(result >36)
                        rotate_object.setRotation(result-36);
                    rotate(result);
                } else {
                    Log.i("tag", "result = 0");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void rotate(int result) {

        degree = result;
        ObjectAnimator ani1 = ObjectAnimator.ofFloat(rotate_object, "rotation", degree);
        ani1.setStartDelay(500);
        ani1.start();
    }
}
