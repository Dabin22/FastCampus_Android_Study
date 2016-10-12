package com.leedabin.android.fragmentbasic_pager;


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
public class FragmentGallery extends Fragment {

    int position;
    ArrayList<FragmentData> datas;
    TextView detail_tv;
    Boolean used = false;

    public FragmentGallery() {


        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_gallery, container, false);
        MainActivity mainActivity = (MainActivity) getActivity();
        position = mainActivity.position;
        datas = mainActivity.datas;
        detail_tv = (TextView) view.findViewById(R.id.textView2);
        used = true;
        detail_tv.setText(datas.get(position).content);
        return view;
    }

    public void setChangedPosition(int position) {
        this.position = position;
        Log.i("tag", position+"");
        detail_tv.setText(datas.get(position).content);
    }

}
