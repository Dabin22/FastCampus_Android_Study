package com.leedabin.android.sqlitebasic_bbs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ListFragment extends Fragment {

    private ListView listView;
    private Button btnWrite;
    private Button btnSearch;
    private EditText searchEt;
    private String searching;
    int totalCount;

    public  static  int listCount = 15;

    // 메인액티비티와 통신하는 리스너
    OnFragmentInteractionListener listener;

    CustomAdapter adapter;

    // 목록에서 사용할 데이터셋 정의
    ArrayList<BbsData> datas = new ArrayList<>();

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance(String param1, String param2) {
        ListFragment fragment = new ListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        // Write 버튼
        btnWrite = (Button) view.findViewById(R.id.button);
        btnSearch = (Button)view.findViewById(R.id.search_btn);
        searchEt = (EditText)view.findViewById(R.id.search_et);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searching = searchEt.getText().toString();
                if(searching.equals(""))
                    setList(listCount);
                else {
                    serachedItem();
                }
            }
        });
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.action(MainActivity.ACTION_WRITE);
                searchEt.setText("");
            }
        });

        listView = (ListView) view.findViewById(R.id.listView);
        adapter = new CustomAdapter(inflater);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 현재 리스트에 있는 클릭한 데이터를 가져오고
                BbsData data = datas.get(position);
                // 해당 데이터의 bbs no를 리스너를 통해 Edit Fragment로 넘겨준다
                int bbsno = data.no;
                listener.actionEdit(bbsno);
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.i("tag","firstV = " + firstVisibleItem+" visibleItemCount = " + visibleItemCount +" totalItemCount = " + totalItemCount);
                                           //현재 리스트상에 보여지는 최상단 아이템의 index      현재 화면내에 조금이라도 보이는 갯수            현재 리스트에 있는 아이템의 총 개수
                if(totalItemCount == firstVisibleItem+visibleItemCount){
                    //현재 리스트에 있는 데이터개숫가 실제 databse에 있는 데이터 개수보다 작을 때만 리스트를 갱신
                    if(totalItemCount < totalCount) {
                        listCount+=15;
                        setList(listCount);
                    }
                }


            }
        });
        return view;
    }


    public void serachedItem(){
        datas = DataUtil.searchDatas(getContext(), searching);
        totalCount = datas.size();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 메인 액티비티가 OnFragmentListener를 구현했는지 확인
        if(context instanceof OnFragmentInteractionListener){
            listener = (OnFragmentInteractionListener) context;
        }else{ // 구현하지 않았으면 MainActivity와 통신할 방법이 없으므로 앱을 죽인다
            throw  new RuntimeException();
        }

        setList(listCount);
    }

    public void setList(int count){
        datas = DataUtil.selectAll(getContext(),count);
        totalCount = DataUtil.selectCount(getContext());
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }

    class CustomAdapter extends BaseAdapter {
        LayoutInflater inflater;
        public CustomAdapter(LayoutInflater inflater){ this.inflater = inflater; }
        @Override
        public int getCount() {
            return datas.size();
        }
        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) convertView = inflater.inflate(R.layout.fragment_list_item,null);
            TextView no = (TextView) convertView.findViewById(R.id.number_tv);
            TextView title = (TextView) convertView.findViewById(R.id.title_list_tv);
            no.setText(datas.get(position).no+"");
            title.setText(datas.get(position).title);
            return convertView;
        }
    }

}
