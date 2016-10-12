package com.leedabin.android.sqlitebasic_bbs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;


public class EditFragment extends Fragment {
    private static final int BBS_INSERT = -1;

    // 메인액티비티와 통신하는 리스너
    OnFragmentInteractionListener listener;

    Button cancel;
    Button save;

    int bbsno = -1;

    EditText title;
    EditText name;
    EditText contents;

    public EditFragment() {
        // Required empty public constructor
    }

    // Database 에서 bbsno 에 해당하는 레코드를 가져와서 화면에 뿌려준다
    public void setData(int bbsno) {
        BbsData data = DataUtil.select(getContext(), bbsno);

        title.setText(data.title);
        name.setText(data.name);
        contents.setText(data.contents);
        this.bbsno = bbsno;
    }

    // 캔슬하거나 저장후에 호출하여 텍스트필드값을 초기화해준다
    public void resetData() {
        title.setText("");
        name.setText("");
        contents.setText("");
        this.bbsno = -1;
    }

    public static EditFragment newInstance(String param1, String param2) {
        EditFragment fragment = new EditFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        cancel = (Button) view.findViewById(R.id.cancle_btn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.action(MainActivity.ACTION_CANCEL);
                resetData();
            }
        });
        save = (Button) view.findViewById(R.id.save_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BbsData data = new BbsData();
                data.no = bbsno;
                data.title = title.getText().toString();
                data.name = name.getText().toString();
                data.contents = contents.getText().toString();
                if (bbsno == BBS_INSERT) {
                    DataUtil.insert(getContext(), data);
                } else {
                    DataUtil.update(getContext(), data);
                }
                resetData();
                listener.action(MainActivity.ACTION_GOLIST_WITH_REFRESH);
            }
        });
        name = (EditText) view.findViewById(R.id.name_et);
        title = (EditText) view.findViewById(R.id.title_et);
        contents = (EditText) view.findViewById(R.id.content_et);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 메인 액티비티가 OnFragmentListener를 구현했는지 확인
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else { // 구현하지 않았으면 MainActivity와 통신할 방법이 없으므로 앱을 죽인다
            throw new RuntimeException();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
