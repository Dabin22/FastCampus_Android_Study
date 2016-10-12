package com.leedabin.android.basiclist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BasicList2Activity extends AppCompatActivity {

    ArrayList<Map<String,String>> datas;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_list2);
        seDatas();

        listView = (ListView)findViewById(R.id.listView2);
                                                    //1.context 2.dat 3.customlayout 4.datas에 들어가는 맵의 key값 4.커스텀레이아웃의 view아이디들
        SimpleAdapter adapter = new SimpleAdapter(this,datas,R.layout.activity_basic_list2_item,new String[]{"number","char","smallChar"},new int[]{R.id.text1,R.id.text2,R.id.text3});
        listView.setAdapter(adapter);
    }

    private  void seDatas(){
        datas = new ArrayList<>();
        for(int i=0; i<26; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("number",(i+1)+".");
            map.put("char",(char)('A'+i)+"");
            map.put("smallChar", (char)('a'+i)+"");
            datas.add(map);
        }

    }


}
