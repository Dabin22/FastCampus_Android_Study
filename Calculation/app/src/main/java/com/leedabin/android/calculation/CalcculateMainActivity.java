package com.leedabin.android.calculation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlEngine;

public class CalcculateMainActivity extends AppCompatActivity {

    Button btn_ac;
    Button btn_divide;
    Button btn_multi;
    Button btn_cancle;
    Button btn_remain;
    Button btn_minus;
    Button btn_plus;
    Button btn_result;
    Button btn_dot;
    Button btn_bracket;
    Button btn_num0;
    Button btn_num1;
    Button btn_num2;
    Button btn_num3;
    Button btn_num4;
    Button btn_num5;
    Button btn_num6;
    Button btn_num7;
    Button btn_num8;
    Button btn_num9;

    float x = 0;
    float y = 0;
    TextView tv;
    RelativeLayout layout;
    Boolean bracket_used = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcculate_main);
        btn_ac = (Button) findViewById(R.id.button);
        btn_divide = (Button) findViewById(R.id.button2);
        btn_multi = (Button) findViewById(R.id.button3);
        btn_cancle = (Button) findViewById(R.id.button4);
        btn_remain = (Button) findViewById(R.id.button8);
        btn_minus = (Button) findViewById(R.id.button12);
        btn_plus = (Button) findViewById(R.id.button16);
        btn_result = (Button) findViewById(R.id.button20);
        btn_dot = (Button) findViewById(R.id.button18);
        btn_bracket = (Button) findViewById(R.id.button19);
        btn_num0 = (Button) findViewById(R.id.button17);
        btn_num1 = (Button) findViewById(R.id.button13);
        btn_num2 = (Button) findViewById(R.id.button14);
        btn_num3 = (Button) findViewById(R.id.button15);
        btn_num4 = (Button) findViewById(R.id.button9);
        btn_num5 = (Button) findViewById(R.id.button10);
        btn_num6 = (Button) findViewById(R.id.button11);
        btn_num7 = (Button) findViewById(R.id.button5);
        btn_num8 = (Button) findViewById(R.id.button6);
        btn_num9 = (Button) findViewById(R.id.button7);


        tv = (TextView) findViewById(R.id.textView);
        layout = (RelativeLayout) findViewById(R.id.layout);
    }

    public void setSize(Button b) {
        int[] location = new int[2];
        b.getLocationOnScreen(location);
        x = layout.getWidth() - location[0];
        y = -location[1];
        //Log.i("tag","tv = "+location[0] + " b.getHeight = " + location[1]);
    }


    public void pushAc(View v) {
        setSize(btn_ac);
        move(btn_ac, x, y);
        input("AC");


    }

    public void pushDivide(View v) {
        setSize(btn_divide);
        move(btn_divide, x, y);
        input("/");
    }

    public void pushMulti(View v) {
        setSize(btn_multi);
        move(btn_multi, x, y);
        input("*");

    }

    public void btn_cancle(View v) {
        setSize(btn_cancle);
        move(btn_cancle, x, y);
        input("cancle");
    }

    public void btn_remain(View v) {
        setSize(btn_remain);
        move(btn_remain, x, y);
        input("%");
    }

    public void btn_minus(View v) {
        setSize(btn_minus);
        move(btn_minus, x, y);
        input("-");
    }

    public void btn_plus(View v) {
        setSize(btn_plus);
        move(btn_plus, x, y);
        input("+");
    }

    public void btn_result(View v) {
        setSize(btn_result);
        move(btn_result, x, y);
        input("=");
    }

    public void btn_dot(View v) {
        setSize(btn_dot);
        move(btn_dot, x, y);
        input(".");
    }

    public void btn_bracket(View v) {
        setSize(btn_bracket);
        move(btn_bracket, x, y);
        if (!bracket_used) {
            input("(");
            bracket_used = true;
        } else {
            input(")");
            bracket_used = false;
        }

    }

    public void btn_num0(View v) {
        setSize(btn_num0);
        move(btn_num0, x, y);
        input("0");
    }

    public void btn_num1(View v) {
        setSize(btn_num1);
        move(btn_num1, x, y);
        input("1");
    }

    public void btn_num2(View v) {
        setSize(btn_num2);
        move(btn_num2, x, y);
        input("2");
    }

    public void btn_num3(View v) {
        setSize(btn_num3);
        move(btn_num3, x, y);
        input("3");
    }

    public void btn_num4(View v) {
        setSize(btn_num4);
        move(btn_num4, x, y);
        input("4");
    }

    public void btn_num5(View v) {
        setSize(btn_num5);
        move(btn_num5, x, y);
        input("5");
    }

    public void btn_num6(View v) {
        setSize(btn_num6);
        move(btn_num6, x, y);
        input("6");
    }

    public void btn_num7(View v) {
        setSize(btn_num7);
        move(btn_num7, x, y);
        input("7");
    }

    public void btn_num8(View v) {
        setSize(btn_num8);
        move(btn_num8, x, y);
        input("8");
    }

    public void btn_num9(View v) {
        setSize(btn_num9);
        move(btn_num9, x, y);
        input("9");
    }

    public void input(String str) {
        StringBuffer input_value = new StringBuffer();
        input_value.append(tv.getText().toString());
        if (str.equals("AC")) {
            tv.setText("0");
            bracket_used = false;
        } else {
            if (str.equals("cancle")) {
                int last_index = input_value.length() - 1;
                char ch = input_value.charAt(last_index);
                if (last_index == 0) {
                    tv.setText("0");
                } else {
                    input_value.deleteCharAt(last_index);
                    tv.setText(input_value);
                }

                if (ch == '(') {
                    bracket_used = false;
                } else if (ch == ')') {
                    bracket_used = true;
                }

            } else if (str.equals("=")) {
                String result = eval(tv.getText().toString());
                tv.setText(result);
            } else {
                if (tv.getText().toString().equals("0")|| tv.getText().toString().equals("error")) {
                    tv.setText(str);
                } else {
                    input_value.append(str);
                    tv.setText(input_value);
                }
            }

        }
    }

    int degere;

    public void move(Button b, float x, float y) {
        degere += 360;
        ObjectAnimator ani1 = ObjectAnimator.ofFloat(b, "translationX", x);
        ObjectAnimator ani2 = ObjectAnimator.ofFloat(b, "translationY", y);
        ObjectAnimator ani3 = ObjectAnimator.ofFloat(b, "rotation", degere);
        ObjectAnimator ani4 = ObjectAnimator.ofFloat(b, "alpha", 0);
        ObjectAnimator ani5 = ObjectAnimator.ofFloat(b, "translationX", 0);
        ObjectAnimator ani6 = ObjectAnimator.ofFloat(b, "translationY", 0);
        ObjectAnimator ani7 = ObjectAnimator.ofFloat(b, "alpha", 1);
        ani7.setStartDelay(200);

        AnimatorSet aniset1 = new AnimatorSet();
        AnimatorSet aniset2 = new AnimatorSet();


        aniset1.playTogether(ani1, ani2, ani3, ani4);
        aniset1.setDuration(1000);
        aniset1.start();
        aniset2.playTogether(ani5, ani6, ani7);
        aniset2.setStartDelay(1400);
        aniset2.start();
    }

    public String eval(String str) {
        String result = "";
        try {
            // 1. jexl 엔진 생성
            JexlEngine jexl = new JexlEngine();
            // 2. 계산식이 담긴 문자열을 jexl 에 담아준다
            Expression e = jexl.createExpression(str);
            // 3. evaluate 함수를 통해 연산을 하고 "" 를 붙혀서 문자열로 변경한다
            result = e.evaluate(null) + "";
        } catch (Exception e) {
            result ="error";
            e.printStackTrace();
        }
        return result;
    }


}
