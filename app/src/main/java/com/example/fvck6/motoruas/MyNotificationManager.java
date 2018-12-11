package com.example.fvck6.motoruas;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

public class MyNotificationManager
{
    private Context mCtx;
    private static MyNotificationManager mInstance;

    private MyNotificationManager(Context context)
    {
        mCtx = context;
    }

    public static synchronized MyNotificationManager getInstance(Context context)
    {
        if (mInstance == null)
        {
            mInstance = new MyNotificationManager(context);
        }
        return mInstance;
    }

    public void displayNotification(String title, String body)
    {
        String CHANNEL_ID = "my_channel_01";

        Intent resultIntent = new Intent(mCtx,MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(mCtx,0,resultIntent,PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mCtx, CHANNEL_ID).setSmallIcon(R.drawable.ic_stat_name).setContentTitle(title).setContentText(body).setAutoCancel(true).setSound(defaultSoundUri).setContentIntent(pendingIntent);

        NotificationManager mNotifyMgr = (NotificationManager) mCtx.getSystemService(Context.NOTIFICATION_SERVICE);
        if (mNotifyMgr != null)
        {
            mNotifyMgr.notify(1,mBuilder.build());
        }
    }

    public void tampilpesan(String title,String body)
    {
        String CHANNEL_ID = "my_channel_01";
        Intent resultIntent = new Intent(mCtx,MainActivity.class);
        resultIntent.putExtra("atas",title);
        resultIntent.putExtra("bawah",body);
        PendingIntent pendingIntent = PendingIntent.getActivity(mCtx,0,resultIntent,PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mCtx, CHANNEL_ID).setSmallIcon(R.drawable.ic_stat_name).setContentTitle(title).setContentText(body).setAutoCancel(true).setSound(defaultSoundUri).setContentIntent(pendingIntent);

        NotificationManager mNotifyMgr = (NotificationManager) mCtx.getSystemService(Context.NOTIFICATION_SERVICE);
        if (mNotifyMgr != null)
        {
            mNotifyMgr.notify(1,mBuilder.build());
        }
    }
}
