package com.example.dabin.rxandroidbasic04;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import rx.Observable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textView;
    Button btn_lambda;
    Button btn_map;
    Button btn_flatMap;
    Button btn_zip;
    ListView listView;

    ArrayList<String> datas;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        btn_lambda = (Button) findViewById(R.id.btn_lambda);
        btn_map = (Button) findViewById(R.id.btn_map);
        btn_flatMap = (Button) findViewById(R.id.btn_flatMap);
        btn_zip = (Button)findViewById(R.id.btn_zip);
        listView = (ListView) findViewById(R.id.listview);
        datas = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);
        listView.setAdapter(adapter);
        btn_zip.setOnClickListener(this);
        btn_flatMap.setOnClickListener(this);
        btn_map.setOnClickListener(this);
        btn_lambda.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_lambda:
                    doLambda();
                    break;
                case R.id.btn_map:
                    doMap();
                    break;
                case R.id.btn_flatMap:
                    doFlatMap();
                    break;
                case R.id.btn_zip:
                    doZip();
                    break;
            }
    }

    private void doZip() {
        Observable.zip(Observable.just("Dabin Lee"),Observable.just("image.jpg"),(item1,item2) -> "Name : " + item1 + ", Profile image : " + item2)
                .subscribe(
                        zipped -> Log.w("TAG", "onNext item=" + zipped)
                );
    }

    private void doFlatMap() {
        Observable<String> observable = Observable.from(new String[]{"dog","bird","chicken","horse","turtle","rabbit","tiger"});

        observable.flatMap(item -> Observable.from(new String[] {"name"+item,item.getBytes()+""}))
                .subscribe(
                        item -> datas.add(item)
                        ,err -> err.printStackTrace()
                        ,() -> adapter.notifyDataSetChanged()
                );
    }

    private void doMap() {
        Observable<String> observable = Observable.from(new String[]{"dog","bird","chicken","horse","turtle","rabbit","tiger"});

        observable.map(item -> "[ " + item + " ]")
                .subscribe(
                item -> datas.add(item)
                ,err -> err.printStackTrace()
                ,() -> adapter.notifyDataSetChanged()
        );

    }

    private void doLambda() {
        Observable<String> observable = Observable.just("i am Lambda!");
        //람다 표현식을 사용
        observable.subscribe(item->textView.setText(item),err->err.printStackTrace(),()-> Log.e("tag","Complete"));
    }
}
