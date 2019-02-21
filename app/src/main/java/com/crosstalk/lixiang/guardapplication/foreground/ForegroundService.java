package com.crosstalk.lixiang.guardapplication.foreground;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by lixiang on 2019/2/21
 */
public class ForegroundService extends Service {
    public static final int FOREGROUNDID = 1;

    public class LocalBinder extends Binder {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return new LocalBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT < 18) {
            startForeground(FOREGROUNDID, new Notification());
        } else if (Build.VERSION.SDK_INT < 26) {
            startForeground(FOREGROUNDID, new Notification());
            startService(new Intent(this, InnerService.class));
        } else {
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (manager != null) {
                NotificationChannel channel = new NotificationChannel("Channel", "Channel", NotificationManager.IMPORTANCE_MIN);
                manager.createNotificationChannel(channel);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "Channel");
                startForeground(FOREGROUNDID, builder.build());
            }
        }

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    for (int i = 0; i < 1111111111; i++) {
                        sleep(1000);
                        Log.i("ForegroundService", i + "");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();


        return START_STICKY;
    }


    public static class InnerService extends Service {


        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(FOREGROUNDID, new Notification());
            stopForeground(true);
            stopSelf();

            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        for (int i = 0; i < 1111111111; i++) {
                            sleep(1000);
                            Log.i("InnerService", i + "");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }.start();

            return START_STICKY;
        }
    }
}
