package com.leedabin.android.servicebasic01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MainService extends Service {
    private static final String TAG="MainService";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"=========== on Create ============");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"=========== on StartCommand ============");

        for(int i=0; i<20; i++)
        {
            try {
                Log.i(TAG, "========Service Main=======" + i);
                Thread.sleep(500);
            }catch (Exception e){

            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG,"=========== on Destroy ============");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
