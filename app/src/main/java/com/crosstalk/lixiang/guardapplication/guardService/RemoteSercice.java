package com.crosstalk.lixiang.guardapplication.guardService;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.crosstalk.lixiang.guardapplication.foreground.ForegroundService;

/**
 * Created by lixiang on 2019/2/21
 */
public class RemoteSercice extends ForegroundService {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        bindService(new Intent(this, LocalService.class), connection, Context.BIND_IMPORTANT);
        return super.onStartCommand(intent, flags, startId);
    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            startService(new Intent(RemoteSercice.this, LocalService.class));
            bindService(new Intent(RemoteSercice.this, LocalService.class), connection, Context.BIND_IMPORTANT);


        }
    };
}
