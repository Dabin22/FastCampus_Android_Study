package com.leedabin.android.fragmentbasic_pager;

import java.util.ArrayList;

/**
 * Created by Dabin on 2016-10-06.
 */
public class MakeDatas {
    MakeDatas(ArrayList<FragmentData> datas)
    {

        for(int i=0; i<50; i++)
        {
            FragmentData data = new FragmentData();
            data.title = (i+1)+"번째 제목";
            data.content = (i+1) + "상세내용";
            datas.add(data);
        }
    }
}
