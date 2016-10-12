package com.leedabin.android.broadcastreciever01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    final static String BROADCAST_ACTION = "com.leedabin.android.MESSAGE";

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(BROADCAST_ACTION))
        {
            //Intent recived = intent.getIntent();
            //  Acitity 화면을 띄워준다.
            Intent mapIntent = new Intent(context, MapsActivity.class);
            mapIntent.addFlags(mapIntent.FLAG_ACTIVITY_NEW_TASK | mapIntent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(mapIntent);

        }
    }
}
