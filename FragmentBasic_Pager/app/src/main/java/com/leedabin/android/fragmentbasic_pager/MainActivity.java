package com.leedabin.android.fragmentbasic_pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Fragment01 fragment01;
    FragmentGallery fragmentG;
    ArrayList<FragmentData> datas;
    int position =0;
    ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (ViewPager)findViewById(R.id.pager);
        datas = new ArrayList<>();
        MakeDatas makeDatas = new MakeDatas(datas);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);


    }
    public ViewPager getPage()
    {
        fragmentG.setChangedPosition(position);
        return pager;
    }

    private  class PagerAdapter extends FragmentStatePagerAdapter{

        //프래그먼트 아답터를 생성하기 위해서는 프래그먼트 매니저를 생성자에 넘겨주고
        //부모 아답터가 초기화 하여야 한다.
        public PagerAdapter(FragmentManager manager)
        {
            super(manager);
            fragment01 = new Fragment01();
            fragmentG = new FragmentGallery();

        }
        //선택한 프로그먼트
        @Override
        public Fragment getItem(int position) {
            Fragment result;
            switch(position)
            {
                case 0:
                    result = fragment01;
                    break;
                case 1:
                    result = fragmentG;
                    break;
                default:
                    result = null;
            }
            return result;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
