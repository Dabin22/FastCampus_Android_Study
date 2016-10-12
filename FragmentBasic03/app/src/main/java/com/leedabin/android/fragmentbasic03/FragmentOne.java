package com.leedabin.android.fragmentbasic03;


import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.ColorUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.leedabin.android.fragmentbasic03.R.id.decor_content_parent;
import static com.leedabin.android.fragmentbasic03.R.id.listView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {


    public FragmentOne() {
        // Required empty public constructor
    }
   ArrayList<FragmentData> datas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_one, container, false);
        MainActivity mainActivity = (MainActivity)getActivity();
        datas = mainActivity.datas;
        CustomAdapter customAdapter = new CustomAdapter(mainActivity,datas);

        ListView listView = (ListView)view.findViewById(R.id.listView);
        listView.setAdapter(customAdapter);

        return view;

    }




}
