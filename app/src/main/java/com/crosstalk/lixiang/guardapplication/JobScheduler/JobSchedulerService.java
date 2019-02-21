package com.crosstalk.lixiang.guardapplication.JobScheduler;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by lixiang on 2019/2/21
 */
@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobSchedulerService extends JobService {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(111, new ComponentName(this, JobSchedulerService.class));
        builder.setPeriodic(2000);
        jobScheduler.schedule(builder.build());
        return START_STICKY;
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
