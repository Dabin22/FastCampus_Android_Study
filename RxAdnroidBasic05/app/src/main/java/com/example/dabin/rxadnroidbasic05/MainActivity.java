package com.example.dabin.rxadnroidbasic05;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Integer dataset[] = {1, 2, 3, 1, 4, 5, 3, 6, 7, 8, 7, 5, 9};
    Button btn_filter, btn_forEach, btn_first, btn_distinct, btn_take, btn_groupby, btn_last;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);

        btn_filter = (Button) findViewById(R.id.btn_filter);
        btn_forEach = (Button) findViewById(R.id.btn_forEach);
        btn_first = (Button) findViewById(R.id.btn_first);
        btn_distinct = (Button) findViewById(R.id.btn_distinct);
        btn_take = (Button) findViewById(R.id.btn_take);
        btn_last = (Button) findViewById(R.id.btn_last);
        btn_groupby = (Button) findViewById(R.id.btn_groupby);
        btn_filter.setOnClickListener(this);
        btn_forEach.setOnClickListener(this);
        btn_first.setOnClickListener(this);
        btn_distinct.setOnClickListener(this);
        btn_take.setOnClickListener(this);
        btn_groupby.setOnClickListener(this);
        btn_last.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_filter:
                textView.setText("filter 결과 :");
                filter();
                break;
            case R.id.btn_forEach:
                textView.setText("forEach 결과 :");
                forEach();
                break;
            case R.id.btn_first:
                textView.setText("first 결과 :");
                first();
                break;
            case R.id.btn_distinct:
                textView.setText("distinct 결과 :");
                distinct();
                break;
            case R.id.btn_take:
                textView.setText("take 결과 :");
                take(3);
                break;
            case R.id.btn_last:
                textView.setText("last 결과 :");
                last();
                break;
            case R.id.btn_groupby:
                textView.setText("groupBy 결과 :");
                groupBy();
                break;
        }
    }

    private void groupBy() {
        Observable.from(dataset)
                .groupBy(item -> item % 2 == 0)
                .subscribe(
                        result -> result.toList().subscribe(
                                item ->
                                {
                                    String set = textView.getText().toString();
                                    textView.setText(set + " " + item + " "+ result.getKey());
                                }
                        )

                );

    }

    private void last() {
        Observable.from(dataset)
                .last()
                .subscribe(
                        result ->
                        {
                            String set = textView.getText().toString();
                            textView.setText(set + " " + result);
                        }
                );
    }

    //갯수만큼만 출력
    private void take(int vaule) {
        Observable.from(dataset)
                .take(vaule)
                .subscribe(
                        result ->
                        {
                            String set = textView.getText().toString();
                            textView.setText(set + " " + result);
                        }
                );
    }

    //중복값 제거
    private void distinct() {
        Observable.from(dataset)
                .distinct()
                .subscribe(
                        result -> {
                            String set = textView.getText().toString();
                            textView.setText(set + " " + result);
                        }
                );
    }

    private void first() {
        Observable.from(dataset)
                .first()
                .subscribe(
                        result ->
                        {
                            String set = textView.getText().toString();
                            textView.setText(set + " " + result);
                        }
                );
    }

    private void forEach() {
        Observable.from(dataset)
                .forEach(
                        result -> {
                            String set = textView.getText().toString();
                            textView.setText(set + " " + result);
                        }
                );
    }

    private void filter() {
        Observable.from(dataset)
                .filter(item -> item % 2 == 0)
                .subscribe(
                        result -> {
                            String set = textView.getText().toString();
                            textView.setText(set + " " + result);
                        }
                );
    }
}
