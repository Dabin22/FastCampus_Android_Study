package com.example.dabin.remote_rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> datas;
    Button btn;
    ListView listView;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datas = new ArrayList<>();
        btn = (Button)findViewById(R.id.btnGet);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
        listView = (ListView)findViewById(R.id.listview);
    }

    private void getData(){
        new AsyncTask<Void,Void,String>(){
            ProgressDialog progress;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progress = new ProgressDialog(MainActivity.this);
                progress.setTitle("다운로드");
                progress.setMessage("downloading...");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setCancelable(false);
                progress.show();
            }

            @Override
            protected String doInBackground(Void... params) {
                String result = "";
                try {
                    result = Reomote.getData("http://192.168.0.166:8080/sub/request.jsp");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String jsonString) {
                StringBuilder sb= new StringBuilder();
                try {
                    //전체 문자열을 JSON 오브젝트로 변환
                    JSONObject json = new JSONObject(jsonString);

                    JSONArray arrays = json.getJSONArray("root");
                    int length = arrays.length();
                    Log.i("tag",arrays+"");
                    for(int i=0; i<length; i++){
                        //배열을 반복문을 돌면서 배열의 인덱스 값을 가져온다.
                        JSONObject jsonObject = (JSONObject) arrays.get(i);
                        Log.i("tag",jsonObject+"");
                        //각 열의 컬럼의 키이름에 해당하는 값을 꺼낸다.

                        datas.add(jsonObject.get("key")+"");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                progress.dismiss();
                if(datas != null) {
                    Log.i("tag","datas = "+datas.size()+"");
                    customAdapter = new CustomAdapter(getApplicationContext(), datas);
                    listView.setAdapter(customAdapter);
                }else
                {
                    Log.i("tag", "datas null");
                }

            }
        }.execute();


    }



    class CustomAdapter extends BaseAdapter{
        ArrayList<String> datas;
        Context context;
        LayoutInflater inflater;
        CustomAdapter(Context context,ArrayList<String> datas){
            this.context = context;
            this.datas = datas;
            inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
        }
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
            if(convertView == null)
            {
                convertView = inflater.inflate(R.layout.item_list, null);
            }
            TextView tv1 = (TextView)convertView.findViewById(R.id.textView);
            TextView tv2  = (TextView)convertView.findViewById(R.id.textView2);
            tv1.setText((position+1)+"");
            tv2.setText(datas.get(position));
            return convertView;
        }
    }


}
