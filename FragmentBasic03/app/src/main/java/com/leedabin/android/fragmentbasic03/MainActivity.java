package com.leedabin.android.fragmentbasic03;

import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ConfigurationHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FragmentOne one;
    FragmentTwo two;
    ArrayList<FragmentData> datas;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            FragmentData data = new FragmentData();
            data.title = i + "번째 제목";
            data.content = i + "번째 상세 내용";
            datas.add(data);

        }




    }

    public void setOne() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            transaction.replace(R.id.fragment2, one);
            two.textView.setText(datas.get(position).content);
        }
        else
            transaction.replace(R.id.fragment, one);
        transaction.commit();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        //가로모드로 변경되었으면
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            //세로모드로 변경되었으면
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {

        }
    }

    public void goDetail(int position) {
        this.position = position;
        Log.i("tag", "go two");

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        //프래그먼트 스택에 들어가 있는 개수 체크(커밋 갯수 체크)
        //manager.getBackStackEntryCount()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            transaction.replace(R.id.fragment2, two);

        } else {
            transaction.replace(R.id.fragment, two);
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

}
