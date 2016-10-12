package com.leedabin.android.basicwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerActivity extends AppCompatActivity {

    String[] datas = {"월","화","수","목","금","토","일"};
    Spinner sp;
    TextView sp_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        sp = (Spinner)findViewById(R.id.spinner);
        sp_tv = (TextView)findViewById(R.id.spinner_tv);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_dropdown_item,datas);
        /*
            첫번째 파라미터 : 컨텍스트
            두번째 파라미터 : 줄당 레이아웃
            세번째 파라미터 : 데이터 배열
         */
        // 스피너에 값이 세팅된 어답터를 넣어준다.
        sp.setAdapter(arrayAdapter);
        //스피너에 리스너를 등록한다.

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sp_tv.setText(datas[i]+"요일");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sp_tv.setText("unselected");
            }
        });

    }
}
