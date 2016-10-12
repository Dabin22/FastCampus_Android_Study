package com.leedabin.android.fragmentbasic03;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwo extends Fragment {
    TextView textView;
    MainActivity mainActivity;
    ArrayList<FragmentData> datas;
    int postion;

    public FragmentTwo() {
        // Required empty public constructor

    }

    @Override
    public void onStart() {
        super.onStart();


    }

    @Override
    public void onPause() {
        super.onPause();
        FragmentData data =datas.get(postion);
        textView.setText(data.content);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_fragment_two, container, false);
       mainActivity  = (MainActivity)getActivity();
        datas = mainActivity.datas;
        this.postion =mainActivity.position;
        textView = (TextView)view.findViewById(R.id.detail_tv);
        FragmentData data =datas.get(postion);
        textView.setText(data.content);
        return view;
    }

}
