package com.leedabin.android.threadbasic_asynctask;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar progress;
    TextView percent;
    Button btn_download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progress = (ProgressBar) findViewById(R.id.progressBar);
        percent = (TextView) findViewById(R.id.textView);
        btn_download = (Button) findViewById(R.id.btn_download);

        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //AsyncTask 동작함....
                new DownloadTask().execute(100);

            }
        });
    }

    class DownloadTask extends AsyncTask<Integer, Integer, String> {
        //각 generic의 의미
        //1. doInBackground 의 parameter type;
        //2. onProgressUpdata 의 parameter type;
        //3. onPostExecute의 parameter type;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected String doInBackground(Integer... parameters) {
            int max = parameters[0];
            try {
                for (int i = 0; i <= max; i++) {
                    publishProgress(i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "finish";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progress.setProgress(values[0]);
            percent.setText(values[0] +" %");
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            Log.i("result", str);
        }
    }
}
