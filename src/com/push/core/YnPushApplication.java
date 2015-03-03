package com.push.core;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import java.util.List;

/**
 * User: WuRuiqiang(263454190@qq.com)
 * Date: 2015-03-02
 * Time: 15:30
 * Description：
 */
public class YnPushApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (!isPushServiceRunning()) {
            startService(new Intent(this, YnPushService.class));
        }
    }

    private boolean isPushServiceRunning() {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager)
                this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList
                = activityManager.getRunningServices(30);
        if (!(serviceList.size()>0)) {
            return false;
        }
        for (int i=0; i<serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(YnPushService.class.getName())) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }
}
