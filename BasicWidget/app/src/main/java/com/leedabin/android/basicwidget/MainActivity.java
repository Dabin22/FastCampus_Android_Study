package com.leedabin.android.basicwidget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    //라디오 그룹
    RadioGroup rg;
    //결과값이 출력되는 텍스트뷰
    TextView result_tv;
    //check box
    CheckBox dog;
    CheckBox pig;
    CheckBox chicken;
    //switch
    Switch switch_01;
    //toggle button
    ToggleButton tgbtn;

    //progressbar
    ProgressBar pgbar;
    Switch switch_progress;

    //Seekbar
    SeekBar seekBar;
    TextView seekBar_tv;

    //RatingBar
    RatingBar ratingBar;
    TextView rate_tv;

    Button btn_edit;
    Button btn_timer;
    Button btn_spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result_tv = (TextView) findViewById(R.id.result_tv);

        rg = (RadioGroup) findViewById(R.id.radioGroup);

        dog = (CheckBox) findViewById(R.id.dog_check);
        pig = (CheckBox) findViewById(R.id.pig_check);
        chicken = (CheckBox) findViewById(R.id.chicken_check);

        switch_01 = (Switch)findViewById(R.id.switch1);

        tgbtn = (ToggleButton)findViewById(R.id.toggleButton);

        pgbar = (ProgressBar)findViewById(R.id.progressBar);
        switch_progress = (Switch)findViewById(R.id.switch_progress);

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar_tv = (TextView)findViewById(R.id.seekbar_tv);

        ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        rate_tv = (TextView)findViewById(R.id.rate_tv);

        btn_edit = (Button)findViewById(R.id.btn_edit);

        btn_timer = (Button)findViewById(R.id.btn_timer);

        btn_spinner = (Button)findViewById(R.id.btn_spinner);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                //현재 체크된 라디오버튼 아이디를 가져온다
                //int ckecked = rg.getCheckedRadioButtonId();

                switch (i) {
                    case R.id.apple_btn:
                        result_tv.setText("Apple이 선택됨");
                        break;
                    case R.id.orange_btn:
                        result_tv.setText("Orange가 선택됨");
                        break;
                    case R.id.banana_btn:
                        result_tv.setText("Banana가 선택됨");
                        break;

                }
            }
        });

        dog.setOnCheckedChangeListener(checkedChangeListener);
        pig.setOnCheckedChangeListener(checkedChangeListener);
        chicken.setOnCheckedChangeListener(checkedChangeListener);

        switch_01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked())
                {
                    compoundButton.setText("스위치가 On 되었습니다.");
                }else{
                    compoundButton.setText("스위치가 Off 되었습니다.");
                }
            }
        });

        tgbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b)
                    {
                        Intent intent = new Intent(MainActivity.this,TapExampleActivity.class);
                        startActivity(intent);
                    }
            }
        });

        switch_progress.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked())
                {
                    pgbar.setVisibility(View.VISIBLE);
                }else
                {
                    pgbar.setVisibility(View.INVISIBLE);
                }
            }
        });



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBar_tv.setText(i+"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this,seekBar.getProgress()+"위치에서 터치가 시작됨",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this,seekBar.getProgress()+"위치에서 터치가 중단됨",Toast.LENGTH_SHORT).show();
            }
        });


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rate_tv.setText(v+" /5");
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TextActivity.class);
                startActivity(intent);
            }
        });

        btn_timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DateActivity.class);
                startActivity(intent);
            }
        });

        btn_spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SpinnerActivity.class);
                startActivity(intent);
            }
        });
    }


    CompoundButton.OnCheckedChangeListener checkedChangeListener
            = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            StringBuilder sb = new StringBuilder();

            if (dog.isChecked()) {
                sb.append("Dog");
            }

            if (pig.isChecked()) {
                sb.append("Pig");
            }

            if (chicken.isChecked()) {
                sb.append("Chicken");
            }

            result_tv.setText(sb.toString());
        }
    };
}
