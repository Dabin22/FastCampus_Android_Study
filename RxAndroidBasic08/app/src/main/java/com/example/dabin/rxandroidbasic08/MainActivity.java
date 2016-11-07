package com.example.dabin.rxandroidbasic08;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;

import rx.Observable;

public class MainActivity extends AppCompatActivity {

    Button btn_signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_signIn = (Button) findViewById(R.id.btn_signIn);

        Observable<TextViewTextChangeEvent> idObs = RxTextView.textChangeEvents((EditText) findViewById(R.id.et_id));
        Observable<TextViewTextChangeEvent> pwObs = RxTextView.textChangeEvents((EditText) findViewById(R.id.et_pwd));

        Observable.combineLatest(idObs, pwObs,
                (item1, item2) -> {
                    boolean idCheck = item1.text().length() >= 5;
                    boolean pwdCheck = item2.text().length() >= 8;

                    return idCheck && pwdCheck;
                }
        )
                .subscribe(
                        checkFlag -> btn_signIn.setEnabled(checkFlag)
                );
    }
}
