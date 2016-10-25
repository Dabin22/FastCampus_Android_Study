package com.example.dabin.remote_httpurlconnection_cookie;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    SharedPreferences sp;
    SharedPreferences.Editor editor;
    EditText et_id;
    EditText et_pwd;
    Button btn_signIn;
    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getApplicationContext().getSharedPreferences("cookie", 0);
        editor = sp.edit();
        setContentView(R.layout.activity_main);
        et_id = (EditText) findViewById(R.id.et_id);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_signIn = (Button) findViewById(R.id.btn_signIn);
        tv_result = (TextView) findViewById(R.id.tv_result);
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        tv_result.setText("cookie : " + sp.getString("USERID", "") + "; " + sp.getString("USERPW", ""));

    }

    private void signIn() {
        Map userinfo = new HashMap();
        userinfo.put("user_id", et_id.getText().toString());
        userinfo.put("user_pw", et_pwd.getText().toString());
        new AsyncTask<Map, Void, String>() {
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
            protected String doInBackground(Map... params) {
                String result = "";
                String uri = "http://192.168.0.166:8080/setCookies.jsp";
                try {
                    result = Reomote.postData(uri, params[0], "POST");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String jsonString) {


                List<HttpCookie> cookies = Reomote.cookieManager.getCookieStore().getCookies();

                StringBuffer cookieString = new StringBuffer();
                for (HttpCookie cookie : cookies) {
                    if (!cookie.getName().equals("JSESSIONID"))
                        cookieString.append(cookie.getName() + " = " + cookie.getValue() + "\n");
                    editor.putString(cookie.getName(), cookie.getValue());
                }
                editor.commit();
                tv_result.setText("cookie : " + cookieString.toString());

                progress.dismiss();


            }
        }.execute(userinfo);
    }
}
