package com.example.dabin.remote_httpurlconnection;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Dabin on 2016-10-21.
 */

public class Reomote {

    private static final String TAG = "REMOTE";

    public static String getData(String weburl) throws IOException {
        StringBuffer result = new StringBuffer();

        URL url = new URL(weburl);
        //아래 한줄로 webUrl 주소에 해당하는 서버와 연결이 된 상태가 된다.
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //REST API =GET(조회), POST(입력), (DELETE, PUT(수정) -> 이두분이 지원하지 않는 서버가 있음)
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String dataLine = null;
            while ((dataLine = br.readLine()) != null) {
                result.append(dataLine);
            }
        } else {
            Log.i(TAG, "HTTP ERROR CODE = " + responseCode);
        }


        return result.toString();
    }
}
