package com.leedabin.android.servicebasic01;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class SubService extends IntentService {

    public static final String TAG = "SubSerivce";

    public SubService() {
        super("SubService");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "=========== on HandleIntent ============");

        for (int i = 0; i < 20; i++) {
            try {
                Log.i(TAG, "========Service Sub=======" + i);
                Thread.sleep(500);
            } catch (Exception e) {

            }

        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "=========== on onCreate ============");
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        Log.i(TAG, "=========== on Destroy ============");
    }
}
