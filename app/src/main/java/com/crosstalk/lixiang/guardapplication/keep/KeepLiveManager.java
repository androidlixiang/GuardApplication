package com.crosstalk.lixiang.guardapplication.keep;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.ref.WeakReference;

/**
 * Created by lixiang on 2019/2/21
 */
public class KeepLiveManager {
    private static volatile KeepLiveManager keepLiveManager;
    private KeepReceiver keepReceiver;
    public WeakReference<KeepActivity> keepActivityWeakReference;

    public WeakReference<KeepActivity> getKeepActivityWeakReference() {
        return keepActivityWeakReference;
    }

    public void setKeepActivityWeakReference(KeepActivity activity) {
        keepActivityWeakReference = new WeakReference<>(activity);

    }


    public static KeepLiveManager getInstance() {

        if (keepLiveManager == null) {
            synchronized (KeepLiveManager.class) {
                if (keepLiveManager == null) {
                    keepLiveManager = new KeepLiveManager();
                }
            }
        }
        return keepLiveManager;
    }

    public void register(Context context) {
        keepReceiver = new KeepReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        context.registerReceiver(keepReceiver, filter);

    }

    public void unRegister(Context context) {
        if (keepReceiver != null) {
            context.unregisterReceiver(keepReceiver);
        }
    }
}
