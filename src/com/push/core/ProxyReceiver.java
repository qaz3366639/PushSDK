package com.push.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ProxyReceiver extends BroadcastReceiver {
    public ProxyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Intent proxyIntent = new Intent();
        if (Intent.ACTION_SCREEN_OFF.equals(action)) {
            proxyIntent.setAction(YnPushConfig.ACTION_SCREEN_OFF);//屏幕关闭时

        }else if (Intent.ACTION_SCREEN_ON.equals(action)) {
            proxyIntent.setAction(YnPushConfig.ACTION_SCREEN_ON);//屏幕唤醒时

        }else if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
            if (isNetworkStateLinkWifi(context)) {
                proxyIntent.setAction(YnPushConfig.ACTION_NETWORK_WIFI);//wifi网络状态
            } else {
                proxyIntent.setAction(YnPushConfig.ACTION_NETWORK_OTHER);//其他网络状态
            }
        }
        proxyIntent.setClass(context, YnPushManager.receiverClazz);
        context.sendBroadcast(proxyIntent);
    }

    private boolean isNetworkStateLinkWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

}
