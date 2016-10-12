package com.leedabin.android.layoutbasic01;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class LayoutCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_layout_code);



        //직접 레이아웃 만들기

        //1 레이아웃을 생성한다.
        RelativeLayout layout = new RelativeLayout(this);

        //2 내부에 들어가는 위젯들을 생성한다.
        Button button = new Button(this);

        //2,1 위젯의 속성을 정의 할 수 없다.
        button.setText("직접 만든 버튼");
        button.setBackgroundColor(Color.BLUE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LayoutCode.this, "버튼 클릭", Toast.LENGTH_SHORT).show();
            }
        });

        //3 레이아웃을 설정한다.
        RelativeLayout.LayoutParams buttonParam = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);


        //3.1 위젯의 레이아웃을 설정
        buttonParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParam.addRule(RelativeLayout.CENTER_IN_PARENT);
        

        //4 레이아웃에 3번에 생성한 위젯들을 더해 준다.
        layout.addView(button, buttonParam);

        //9 최종적으로 액티비티에 최상위 레이아웃 개체를 세팅한다.
        setContentView(layout);


    }
}
