package com.leedabin.android.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;

public class BasicList1Activity extends AppCompatActivity {


    String[] datas = {"백향목","김동진", "김태원", "임재민", "김도형", " 석주영", "장홍석", "김해든"};


    //데이터를 담은 객체
    ArrayAdapter arrayAdapter;

    //데이터를 담은 어댑터를 받는 리스트 뷰
    ListView listView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_list1);
                                                    //1.컨텍스트    2.아이템 레이아웃  3.데이터
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);



        /*
            아이템 레이아웃 종류
            simple_list_item2 텍스트 뷰 2개로 구성
            simple_list_item_checked  끝에 체크박스가 포함됨
            simple_list item_single_choice 끝에 라디오 버튼 생성
            simple_list_item_nultiple_choice 끝에 체크박스가 생성성
        */

        listView1 = (ListView) findViewById(R.id.listView1);
        listView1.setAdapter(arrayAdapter);


    }
}
