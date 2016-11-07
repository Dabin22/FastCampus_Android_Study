package com.example.dabin.rxandroidbasic06;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView subject;
    Button btn_publish, btn_behavior, btn_replay, btn_async, btn_async_complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_publish = (Button) findViewById(R.id.btn_publish);
        btn_behavior = (Button) findViewById(R.id.btn_behavior);
        btn_replay = (Button) findViewById(R.id.btn_replay);
        btn_async = (Button) findViewById(R.id.btn_async);
        btn_async_complete = (Button) findViewById(R.id.btn_asyncComplete);
        btn_publish.setOnClickListener(this);
        btn_behavior.setOnClickListener(this);
        btn_replay.setOnClickListener(this);
        btn_async.setOnClickListener(this);
        btn_async_complete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_publish:
                publishSubject();
                break;
            case R.id.btn_behavior:
                behaviorSubject();
                break;
            case R.id.btn_replay:
                replaySubject();
                break;
            case R.id.btn_async:
                asyncSubject();
                break;
            case R.id.btn_asyncComplete:
                asyncCompleteSubject();
                break;
        }

    }
    //구독한 시점부터 발행된 아이템을 받는다.
    private void publishSubject() {
        PublishSubject<String> subject = PublishSubject.create();
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        subject.subscribe(item-> Log.e("Publish","item = "+item));
        subject.onNext("D");
        subject.onNext("E");

    }

    // 가장 최근에 관찰된 아이템부터 구독한다.
    private void behaviorSubject() {
        BehaviorSubject<String> subject = BehaviorSubject.create();
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        subject.subscribe(item-> Log.e("Behavior","item = "+item));
        subject.onNext("D");
        subject.onNext("E");
    }

    //전체를 보여준다.
    private void replaySubject() {
        ReplaySubject<String> subject = ReplaySubject.create();
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        subject.subscribe(item-> Log.e("Replay","item = "+item));
        subject.onNext("D");
        subject.onNext("E");
    }


    private void asyncSubject() {
        AsyncSubject<String> subject = AsyncSubject.create();
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        subject.subscribe(item-> Log.e("Async Don't Complete","item = "+item));
        subject.onNext("D");
        subject.onNext("E");

    }

    private void asyncCompleteSubject() {
        AsyncSubject<String> subject = AsyncSubject.create();
        subject.onNext("A");
        subject.onNext("B");
        subject.onNext("C");
        subject.subscribe(item-> Log.e("Async Do Complete ","item = "+item));
        subject.onNext("D");
        subject.onNext("E");
        subject.onCompleted();
    }
}
