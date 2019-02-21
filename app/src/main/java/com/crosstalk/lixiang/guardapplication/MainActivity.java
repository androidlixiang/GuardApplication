package com.crosstalk.lixiang.guardapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.JobIntentService;
import android.support.v7.app.AppCompatActivity;

import com.crosstalk.lixiang.guardapplication.guardService.LocalService;
import com.crosstalk.lixiang.guardapplication.keep.KeepLiveManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KeepLiveManager.getInstance().register(this);
        startService(new Intent(this,LocalService.class));
        startService(new Intent(this,JobIntentService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeepLiveManager.getInstance().unRegister(this);

    }
}
