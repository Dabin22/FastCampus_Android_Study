package com.leedabin.android.notepad;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
/*
    메모 어플로 엑티비티에 버튼 하나로 구성되었다.
    1. 메인 화면 -> 메모 목록을 볼수 있는 화면. (아직 파일 입출력을 배우지 않아 불러오는
                                                카드뷰가 없다.)
    2. 입력모드 -> 플로팅 버튼인 플러스를 통해 메로를 입력하는 창으로변한다.
                    (거기서 입력한 글자수의 일부분이 목록에서 제목으로 보이게 된다.)
    3. 읽기 모드-> 목록이 보이는 화면에서 한 카드뷰를 클릭하면 상세하게 볼수있는 화면으로 간다.
    4. 수정모드 -> 읽기모드에서 화면을 한번 더 누르면 수정을 할 수 있는 창으로 간다.
    5. 삭제 모드 -> 메인 화면에서 카드뷰를 길게 클릭시 카드뷰에 엑스 버튼이 활성화 클릭시 삭제
 */

public class MainActivity extends AppCompatActivity {

    //리사이클러 어뎁터와 뷰와 그 안에 넣을 데이타 리스트 변수
    private RecyclerView recyclerView;
    private Recycler_adapter rc_adapter;
    private ArrayList<NotepadData> datas;
    private NotepadData data;

    //추가와 수정 등에 쓰이는 플러스 버튼과 입력창
    private FloatingActionButton fab;
    private EditText editText;
    // 입력 창이 수정될때 쓰이는 지 추가 될때 쓰이는 지 구분자
    private Boolean modified = false;

    //자세힐 볼때 쓰이는 텍스트뷰
    private TextView read_tv;

    //메인 화면,입력,읽기 창을 담당하는 레이아웃
    private RelativeLayout content;
    private LinearLayout input;
    private LinearLayout read;

    //삭제모드에서 원래 모드로 돌아갈때 쓰이는 돌아가기 버튼
    private FloatingActionButton back_fab;
    //현재 선택된 메모의 위치
    private int selected_position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        read = (LinearLayout) findViewById(R.id.read_linear);
        content = (RelativeLayout) findViewById(R.id.contentLayout);
        input = (LinearLayout) findViewById(R.id.inputLayout);
        editText = (EditText) findViewById(R.id.editText);
        read_tv = (TextView) findViewById(R.id.read_tv);

        datas = new ArrayList<>();
//        data = new NotepadData();
//        data.title_string = "";
//        data.content_string = "";
//        datas.add(data);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rc_adapter = new Recycler_adapter(datas, R.layout.item_recycler_main, this);
        recyclerView.setAdapter(rc_adapter);
        recyclerView.setLayoutManager(manager);

        //읽기모든에서 클릭시 수정모드로 가게 설정
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText(read_tv.getText());
                modifiedMode();
            }
        });

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //메인모드에서 버튼 클릭시 입력모드로 간다.
                //아닐시 에딧텍스트에 내용을 data에 저장후 datas에 넣고 어뎁터에 알린다.
                if (content.getVisibility() == View.VISIBLE) {
                    inputMode();
                    keyBoardOff();
                } else {
                    //글자 입력시에 작동
                    if(editText.getText().length() >0){
                        //수정모드에서누른 버튼인지 입력모드(추가)에서 누른건지 구별
                        //기존의 카드뷰의 포지션번호를 이용해서 수정
                        if(modified){
                            setModified(selected_position);
                        }else{
                            //단순 추가
                            memo_input();
                        }
                        keyBoardOff();
                        //처리시 기본화면으로 돌아간다.
                       list_viewMode();
                    }else{
                        Toast.makeText(MainActivity.this,"내용을 입력해 주세요",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
        back_fab = (FloatingActionButton)findViewById(R.id.back_fab);
        back_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //카드뷰를 오래 클릭하여 삭제 모드가 되었으나 삭제를 원치 않을 시 이
                //되돌아가기 버튼을 통해 원래 메인화면으로 돌아간다.
                returnMode();
            }
        });

    }


    @Override
    public void onBackPressed() {
        //액정밖에 있는 뒤로가기 버튼을 눌렀을 때 액티비티가 하나여서
        //종료되는것을 방지하고 원하는 xml화면이 Visible 하게 해준다.
        if (content.getVisibility() == View.GONE) {
            list_viewMode();
            if(back_fab.getVisibility() == View.VISIBLE)
                returnMode();
            editText.setText("");
            keyBoardOff();
        } else
            super.onBackPressed();
    }

    //메모를 추가할때 쓰이는 메소드
    public void setAdd(NotepadData data) {
        datas.add(data);
        rc_adapter.notifyDataSetChanged();
        editText.setText("");
    }

    //메모를 자세히 볼때 쓰이는 메소드
    public void showItem(String content_fild) {
        readMode();
        read_tv.setText(content_fild);
    }

    //화면에서 사용자 키보드가 안보이게 하는 메소드
    public void keyBoardOff() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    //입력된 내용을 notepadData에 담는 함수
    public void memo_input(){
        NotepadData data = new NotepadData();
        if (editText.getText().length() < 13) {
            data.title_string = editText.getText().subSequence(0, editText.getText().length()).toString();
        } else {
            data.title_string = editText.getText().subSequence(0, 13).toString()+"....";
        }
        data.content_string = editText.getText().toString();
        setAdd(data);

    }

    //변경 모드에서 입력된 내용을 그 위치의 데이터 내용에 덮어 씌우는 메소드
    public void setModified(int index) {

        if (editText.getText().length() < 13) {
            datas.get(index).title_string = editText.getText().subSequence(0, editText.getText().length()).toString();
        } else {
            datas.get(index).title_string = editText.getText().subSequence(0, 13).toString()+"....";
        }
        datas.get(index).content_string = editText.getText().toString();

        rc_adapter.notifyDataSetChanged();
        modified =false;
    }

    //현재 내가 선택한 위치를 얻는 메소드
    public void selectPosition(int selectde_position)
    {
        this.selected_position = selectde_position;
    }

    //데이터 삭제
    public void delete_item(){
        datas.remove(selected_position);
        rc_adapter.notifyDataSetChanged();
    }

    //메인화면 활성화
    public void list_viewMode(){
        content.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);
        input.setVisibility(View.GONE);
        read.setVisibility(View.GONE);
    }
    //입력화면 활성화
    public void inputMode(){
        content.setVisibility(View.GONE);
        input.setVisibility(View.VISIBLE);
    }

    //메모 자세히 보는 화면 활성화
    public void readMode(){
        content.setVisibility(View.GONE);
        read.setVisibility(View.VISIBLE);
        fab.setVisibility(View.GONE);
    }
    //메모 변경화면 활성화
    public void modifiedMode(){
        read.setVisibility(View.GONE);
        content.setVisibility(View.GONE);
        input.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);
        modified = true;
    }
    //삭제화면 활성화
    public void deleteMode(){
        fab.setVisibility(View.GONE);
        back_fab.setVisibility(View.VISIBLE);
    }
    //삭제 화면 활성화후 다시 원래 화면으로 돌아가는 메소드
    public void returnMode(){
        Recycler_adapter.ViewHolder vh = rc_adapter.selectedViewHolder();
        back_fab.setVisibility(View.GONE);
        fab.setVisibility(View.VISIBLE);
        vh.delete_btn.setVisibility(View.GONE);
    }
}
