package com.example.fvck6.motoruas;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.support.constraint.Constraints.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService
{
    private static final String TAG = "FCM_GUE";
    String atas,bawah;
    public void onNewToken(String s)
    {
        super.onNewToken(s);
        Log.e("NEW_TOKEN",s);
    }

    public void onMessageReceived(RemoteMessage remoteMessage)
    {
        super.onMessageReceived(remoteMessage);

        Log.d(TAG, "Pengirim: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0)
        {
            Log.d(TAG,"Pesennya bang: " + remoteMessage.getData().get("body"));
            atas = remoteMessage.getData().get("title");
            bawah = remoteMessage.getData().get("body");
        }

//      MyNotificationManager.getInstance(this).displayNotification(remoteMessage.getData().get("body"),remoteMessage.getData().get("title"));
        MyNotificationManager.getInstance(this).tampilpesan(atas,bawah);

    }
}
