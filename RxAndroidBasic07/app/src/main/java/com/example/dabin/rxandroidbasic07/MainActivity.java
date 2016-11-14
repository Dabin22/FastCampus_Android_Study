package com.example.dabin.rxandroidbasic07;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.Random;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Rx view Binding
        RxView.clicks(findViewById(R.id.btn_bind))
                .map(event -> new Random().nextInt())
                .subscribe(rand -> ((TextView)findViewById(R.id.textView)).setText("value " +rand));

        //merge
       Observable<String> leftObs = RxView.clicks(findViewById(R.id.btn_left))
                .map(event -> "left");
       Observable<String> rightObs =RxView.clicks(findViewById(R.id.btn_right))
                .map(event -> "right");

        Observable.merge(leftObs,rightObs)
                    .subscribe(
                            text -> Toast.makeText(this, text, Toast.LENGTH_SHORT ).show()
                    );

        //text Change event
        RxTextView.textChangeEvents((TextView)findViewById(R.id.et_word))
                .subscribe(
                        word -> Log.i("SEARCH","word = " +word.text().toString())
                );
    }
}
