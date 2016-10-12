package com.leedabin.android.servicerotation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class RotateReceiver extends BroadcastReceiver {
    private static final String BROADCAST_ACTION = "dabin.ROTATION";
    public RotateReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(BROADCAST_ACTION)) {

            Bundle recived = intent.getExtras();
            int value = recived.getInt("rotate");
            Log.i("tag","value = "+value);
            Intent intent_r = new Intent(context, MainActivity.class);
            intent_r.putExtra("rotate", value);
            intent_r.putExtra("broadcast",true);
            intent_r.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP |intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent_r);
        }
    }

}
