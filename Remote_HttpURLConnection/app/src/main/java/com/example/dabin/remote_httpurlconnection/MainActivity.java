package com.example.dabin.remote_httpurlconnection;

import android.app.ProgressDialog;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        Button btn_call = (Button)findViewById(R.id.button);

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNaver();
            }
        });
    }

    private void getNaver(){
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
                    result = Reomote.getData("http://openapi.seoul.go.kr:8088/634f617053776f77313038524b766c44/json/CardBusStatisticsService/1/5/201306/");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                StringBuilder sb= new StringBuilder();
                try {
                    //전체 문자열을 JSON 오브젝트로 변환
                    JSONObject json = new JSONObject(s);
                    //특정 키를 가진 단일값을 꺼낸다.
                    JSONObject topObject = json.getJSONObject("CardBusStatisticsService");
                    //특정 키를 가진 배열 형태의 값을 꺼낸다.
                    JSONArray arrays = topObject.getJSONArray("row");
                    int length = arrays.length();

                    for(int i=0; i<length; i++){
                        //배열을 반복문을 돌면서 배열의 인덱스 값을 가져온다.
                        JSONObject jsonObject = (JSONObject) arrays.get(i);
                        //각 열의 컬럼의 키이름에 해당하는 값을 꺼낸다.
                        sb.append(jsonObject.get("BUS_STA_NM")+"\n");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                textView.setText(sb);
                Log.i("tag",s);

                progress.dismiss();


            }
        }.execute();


    }
}
