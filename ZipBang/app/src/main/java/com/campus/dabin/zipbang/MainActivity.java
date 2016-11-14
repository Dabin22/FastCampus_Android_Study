package com.campus.dabin.zipbang;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {
    FragmentHome fragmentHome;
    FragmentMap fragmentMap;
    FragmentPost fragmentPost;



    //FragmentSettings fragmentSettings;
    static final  int FRAGMENT_COUNT =3;
    ViewPager pager;

    FirebaseDatabase firebase;
    DatabaseReference rootRef;
    DatabaseReference roomsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebase = FirebaseDatabase.getInstance();
        rootRef = firebase.getReference();
        roomsRef = firebase.getReference("rooms");

        TabLayout tab = (TabLayout) findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("Home"));
        tab.addTab(tab.newTab().setText("지도에서 찾기"));
        tab.addTab(tab.newTab().setText("방 등록"));



        fragmentHome = FragmentHome.newInstance("sss", "Sss");
        fragmentMap = new FragmentMap();
        fragmentPost = new FragmentPost();

        pager = (ViewPager) findViewById(R.id.pager);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        //pager가 변경시에 탭을 변경시켜주는 리스너
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        //tab에서 변경시에 pager를 변경시주는 리스너
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));


    }

    @Override
    public void selectedLocal(LatLng latLng, String localname) {
        fragmentMap.onMapSelected(latLng,localname);
    }


    class PagerAdapter extends FragmentStatePagerAdapter {


        public PagerAdapter(FragmentManager fm) {
            super(fm);

        }



        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = fragmentHome;
                    break;
                case 1:
                    fragment = fragmentMap;
                    break;
                case 2:
                    fragment = fragmentPost;
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



