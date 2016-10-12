package com.leedabin.android.fragmentbasic_pager;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment01 extends Fragment {

    ArrayList<FragmentData> datas;
    MainActivity mainActivity;
    public Fragment01() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment01, container, false);
        ListView list = (ListView)view.findViewById(R.id.listView);
        final MainActivity mainActivity = (MainActivity)getActivity();
        ArrayList<FragmentData> datas = mainActivity.datas;
        final CustomAdapter cAdapter = new CustomAdapter(datas,mainActivity);
        list.setAdapter(cAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                mainActivity.position = position;
                ViewPager pager = mainActivity.getPage();
                pager.setCurrentItem(1);
            }
        });



        return view;
    }

}
