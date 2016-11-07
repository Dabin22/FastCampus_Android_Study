package com.example.dabin.rxandroidbasic02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    Button btn_just;
    Button btn_from;
    Button btn_defer;
    ListView listView;
    ArrayList<String> datas;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        btn_just = (Button) findViewById(R.id.btn_just);
        btn_from = (Button) findViewById(R.id.btn_from);
        btn_defer = (Button) findViewById(R.id.btn_defer);
        listView = (ListView) findViewById(R.id.listview);
        btn_just.setOnClickListener(this);
        btn_from.setOnClickListener(this);
        btn_defer.setOnClickListener(this);
        datas = new ArrayList<>();
        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);
        listView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_just:
                doJust();
                break;
            case R.id.btn_from:
                doFrom();
                break;
            case R.id.btn_defer:
                doDefer();
                break;
        }
    }
    public void doJust(){
        Observable<String> observable = Observable.just("dog");

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                textView.setText("Result :"+s);
            }
        });
    }

    // 컬렉션 형태의 자바 객체으로 부터 옵져버블을 생성한다.
    public void doFrom(){
        Observable<String> observable = Observable.from(new String[]{"dog","bird","chicken","horse","turtle","rabbit","tiger"});

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                datas.add(s);

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() {
                adapter.notifyDataSetChanged();
            }
        });


    }
    //지연처리 함수를 제공하고
    // 호출할때마다 옵져버블 객체를 매번 생성한다.
    public void doDefer(){
        Observable<String> observable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                return Observable.just("bird");
            }
        });

        observable.delaySubscription(3, TimeUnit.SECONDS)
                .subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                textView.setText(s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        }, new Action0() {
            @Override
            public void call() {

            }
        });
    }
}
