package com.leedabin.android.acitivitybasic02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    EditText et;
    Button btn_send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = (EditText)findViewById(R.id.main_EditText);

    }

    public void sendActivity(View v)
    {
        Intent intent = new Intent(this,SubActivity.class);
        String input = et.getText().toString();
        intent.putExtra("key1",input);
        intent.putExtra("key2","홍길동");
        intent.putExtra("key3",10000);
        startActivityForResult(intent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode)
        {
            case 1000 :
                if(resultCode == 1) {
                    Bundle bundle = data.getExtras();
                    String result = bundle.getString("return1");
                    et.setText(result);
                }
                break;
        }
    }
}
