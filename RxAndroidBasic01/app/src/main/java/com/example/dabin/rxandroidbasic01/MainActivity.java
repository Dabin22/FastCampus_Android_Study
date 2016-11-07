package com.example.dabin.rxandroidbasic01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //1. Observer 생성
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello RxAdnorid");
                subscriber.onNext("Hello RxAdnorid!");
                subscriber.onNext("Hello RxAdnorid!!");
                subscriber.onCompleted();
            }
        });

        // 2. Observable 을 통해 데이터를 가져온다.
        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e("tag", "completed");

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("tag", "next value = " + s);
            }
        });

        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                ((TextView) findViewById(R.id.textview)).setText(s);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() {

            }
        });

    }
}
