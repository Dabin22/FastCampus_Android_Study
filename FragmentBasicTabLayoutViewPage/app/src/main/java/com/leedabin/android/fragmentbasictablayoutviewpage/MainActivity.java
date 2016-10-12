package com.leedabin.android.fragmentbasictablayoutviewpage;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

/*
    1. gradle에 design library 추가
    2. main.xml에 TabLayout, View page 추가
 */
public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    static final  int FRAGMENT_COUNT =4;
    FragmentHome fragmentHome;
    FragmentMap fragmentMap;
    FragmentETC fragmentETC;
    //FragmentSettings fragmentSettings;
    BlankFragment fragmentSettings;
    ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tab = (TabLayout)findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("Home"));
        tab.addTab(tab.newTab().setText("Map"));
        tab.addTab(tab.newTab().setText("Etc"));
        tab.addTab(tab.newTab().setText("Settings"));



        fragmentHome = FragmentHome.newInstance("sss","Sss");
        fragmentMap = new FragmentMap();
        fragmentETC = new FragmentETC();
        fragmentSettings = BlankFragment.newInstance("sss","Sss");

        pager = (ViewPager)findViewById(R.id.pager);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        //pager가 변경시에 탭을 변경시켜주는 리스너
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        //tab에서 변경시에 pager를 변경시주는 리스너
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));

    }

    @Override
    public void onFragmentInteraction(Fragment fragment) {
        if(fragment == fragmentSettings )
            Toast.makeText(this,"Blank",Toast.LENGTH_SHORT).show();
        else if(fragment == fragmentHome)
            Toast.makeText(this,"home",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"not select",Toast.LENGTH_SHORT).show();
    }

    class PagerAdapter extends FragmentStatePagerAdapter{


       public  PagerAdapter(FragmentManager fm){
           super(fm);

       }
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch(position)
            {
                case 0:
                    fragment = fragmentHome;
                    break;
                case 1:
                    fragment = fragmentMap;
                    break;
                case 2:
                    fragment = fragmentETC;
                    break;
                case 3:
                    fragment = fragmentSettings;
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return FRAGMENT_COUNT;
        }
    }


}
