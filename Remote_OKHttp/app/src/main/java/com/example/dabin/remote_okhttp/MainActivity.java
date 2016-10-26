package com.example.dabin.remote_okhttp;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/*
    1. gradle에 Okhttp 추가
    2. manifests에 internet 권한 추가
    
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        String key = "634f617053776f77313038524b766c44";

        String serviceName = "JobFairInfo";
        int begin = 1;
        int end = 5;



        String url = "http://openapi.seoul.go.kr:8088/" + key + "/json/" + serviceName + "/" + begin + "/" + end+"/";
        callHttp(url);
    }
    public void callHttp(String url){
        new AsyncTask<String,Void,String>(){
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                Log.e("Result",result);
            }

            @Override
            protected String doInBackground(String... params) {
                String result ="";
                try {
                    result = getData(params[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }
        }.execute(url);


    }
    public String getData(String url) throws IOException{
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                                        .url(url)
                                        .build();
        Response response = client.newCall(request).execute();
        return response.body().string();

    }
}
