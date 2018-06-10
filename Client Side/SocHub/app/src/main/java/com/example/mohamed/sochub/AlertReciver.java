package com.example.mohamed.sochub;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public  class AlertReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Alarm start seconds",Toast.LENGTH_LONG).show();
        Log.i(TAG, "onReceive: i am in ");

    }
    public void  CreateNot(Context context,String msg,String msgTxt,String Alert){

    }
}
