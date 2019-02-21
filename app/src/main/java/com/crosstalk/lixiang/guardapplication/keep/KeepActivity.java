package com.crosstalk.lixiang.guardapplication.keep;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by lixiang on 2019/2/21
 */
public class KeepActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = 1;
        attributes.height = 1;
        window.setAttributes(attributes);
        KeepLiveManager.getInstance().setKeepActivityWeakReference(this);

//        setContentView(R.layout.activity_keep);

    }
}
