package com.crosstalk.lixiang.guardapplication.guardService;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.crosstalk.lixiang.guardapplication.foreground.ForegroundService;

/**
 * Created by lixiang on 2019/2/21
 */
public class LocalService extends ForegroundService {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    for (int i = 0; i < 1111111111; i++) {
                        sleep(1000);
                        Log.i("LocalService", i + "");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();

        bindService(new Intent(this, RemoteSercice.class), connection, Context.BIND_IMPORTANT);
        return super.onStartCommand(intent, flags, startId);
    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            startService(new Intent(LocalService.this, RemoteSercice.class));
            bindService(new Intent(LocalService.this, RemoteSercice.class), connection, Context.BIND_IMPORTANT);
        }
    };
}
