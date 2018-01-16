package liwei.hackcode.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by zhenglw on 2018/1/14.
 */

public class FrontService extends Service {
    private static final String TAG = "FrontService";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate exec");

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Title here")
                .setContentText("content text here")
                .setWhen(System.currentTimeMillis())
//                .setSmallIcon()
//                .setLargeIcon()
                .setContentIntent(pi)
                .build();
        startForeground(1, notification);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
