package com.example.dabin.rxandroidbasic03;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    final static String TAG = "DEFER ASYNC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG,"Thread name = "+Thread.currentThread().getName()+" in main");
        doDeferAsync();
    }

    public void doDeferAsync(){
        Observable<String> observable = Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                Log.e(TAG,"Thread name = "+Thread.currentThread().getName()+" in Func0");
                return Observable.just("Here i am!!");
            }
        });

        observable.subscribeOn(Schedulers.computation()) // computation thread 에서 defer의 Func가 실행되고
                                                        // subscribeOn 에서 지정한 thread에서 defer의 func에 넘겨준 함수가 실행된다.
                                                        // 즉 발행자 Thread를 지정한다.
                .observeOn(Schedulers.newThread())      // 구독이 새로운 thread에서 subscriber로 이벤트가 전달되고
                                                        //Subscriber가 실행되는 Thread를 지정한다.
                .subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e(TAG,"Thread name = "+Thread.currentThread().getName()+" in subscribe.onComplete");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e(TAG,"Thread name = "+Thread.currentThread().getName()+" in subscribe.onNext with value =" +s);
            }
        });

        observable.subscribeOn(Schedulers.computation()) // computation thread 에서 defer의 Func가 실행되고
                // subscribeOn 에서 지정한 thread에서 defer의 func에 넘겨준 함수가 실행된다.
                // 즉 발행자 Thread를 지정한다.
                .observeOn(Schedulers.newThread())      // 구독이 새로운 thread에서 subscriber로 이벤트가 전달되고
                //Subscriber가 실행되는 Thread를 지정한다.
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e(TAG,"Thread name = "+Thread.currentThread().getName()+" in subscribe2.onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG,"Thread name = "+Thread.currentThread().getName()+" in subscribe2.onNext with value =" +s);
                    }
                });
    }
}
