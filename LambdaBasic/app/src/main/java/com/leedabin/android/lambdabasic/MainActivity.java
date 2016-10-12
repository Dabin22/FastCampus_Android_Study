package com.leedabin.android.lambdabasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"람다식으로 만들 예정",Toast.LENGTH_SHORT).show();
            }
        });
        btn.setOnClickListener((view)->Toast.makeText(MainActivity.this,"람다식표현입니다.",Toast.LENGTH_SHORT).show());
    }
}
