package com.leedabin.android.acitivitybasic02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlEngine;


public class SubActivity extends AppCompatActivity {

    EditText sub_edit;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        sub_edit = (EditText) findViewById(R.id.Sub_editText);
        String output = bundle.getString("key1");
        //String output = eval(bundle.getString("key1"));
        sub_edit.setText(output);

        btn = (Button) findViewById(R.id.btn_finish);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //에딧 텍스트에서 값을 가져온다.
                String result = sub_edit.getText().toString();
                //인텐트를 생성하고 돌려줄 값을 세팅한다.
                Intent intent = new Intent();
                intent.putExtra("return1", result);
                intent.putExtra("return2", "결과값2");
                //3. setResult에 결과코드와 데이터를 넘겨준다.
                setResult(1, intent);
                //현재 액티비티 종료
                finish();
            }
        });


    }




}
