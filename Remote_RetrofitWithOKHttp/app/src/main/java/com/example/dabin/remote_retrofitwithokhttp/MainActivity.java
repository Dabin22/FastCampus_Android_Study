package com.example.dabin.remote_retrofitwithokhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callHttp();
    }

    public void callHttp() {
        String key = "634f617053776f77313038524b766c44";

        String serviceName = "JobFairInfo";
        int begin = 1;
        int end = 5;

        Call<RemoteData> remoteData = RestAdapter.getInstance().getData(key, serviceName, begin, end);
        remoteData.enqueue(new Callback<RemoteData>() {
            @Override
            public void onResponse(Call<RemoteData> call, Response<RemoteData> response) {

                if(response.isSuccessful()){
                    RemoteData data = response.body();
                    RemoteData.JobFairInfo.Row[] rows = data.getJobFairInfo().getRow();
                    for(RemoteData.JobFairInfo.Row row : rows)
                    {
                        Log.i("tag",row.getJOBFAIR_NAME());
                    }
                }else{
                    Log.e("ERROR",response.message());
                }
            }

            @Override
            public void onFailure(Call<RemoteData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
