package com.leedabin.android.servicerotation;

import android.app.IntentService;
import android.app.PendingIntent;
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
public class Rotation_Service extends IntentService {

    static final int request = 100;
    public Rotation_Service() {
        super("SubService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("tag","onHandleIntent!");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);

                int value = (i+1)*36;
                //boradcast로 보내서 처리할때
                Intent intent_broad = new Intent("dabin.ROTATION");

                intent_broad.putExtra("rotate",value);
                Log.i("tag","alive");

                sendBroadcast(intent_broad);
                Intent intent1 = new Intent(this,LoadingActivity.class);  //1,2번 방법사용시
                intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1); //1,2번 사용시


                //서비스에서 직접 보낼때
//                Intent rotate = new Intent(this,MainActivity.class);
//                rotate.addFlags(rotate.FLAG_ACTIVITY_SINGLE_TOP|rotate.FLAG_ACTIVITY_NEW_TASK);
//                rotate.putExtra("rotate",36);
//                startActivity(rotate);

                //pending intent 사용
//                Intent intent1 = new Intent(this,MainActivity.class);
//
//                intent1.putExtra("rotate",value);
//                PendingIntent pe = intent.getParcelableExtra("result");
//                pe.send(this,100,intent1);

            } catch (Exception e) {

            }

        }

    }


}
