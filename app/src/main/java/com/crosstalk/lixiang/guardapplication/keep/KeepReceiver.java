package com.crosstalk.lixiang.guardapplication.keep;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.lang.ref.WeakReference;

/**
 * Created by lixiang on 2019/2/21
 */
public class KeepReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            intent.setClass(context, KeepActivity.class);
            context.startActivity(intent);

        } else {

            WeakReference<KeepActivity> keepActivityWeakReference = KeepLiveManager.getInstance().getKeepActivityWeakReference();
            KeepActivity keepActivity = keepActivityWeakReference.get();
            if (keepActivity != null) {
                keepActivity.finish();
            }

        }

    }
}
