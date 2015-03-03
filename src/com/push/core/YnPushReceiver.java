package com.push.core;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.push.cmd.manager.CommandManager;
import com.push.model.BussinessMessage;
import com.push.util.ClientUtil;

public abstract class YnPushReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (YnPushConfig.ACTION_PULL_DATA.equals(action)) {
            onPullData(context, intent);
        }else if (YnPushConfig.ACTION_NETWORK_OTHER.equals(action)) {
            onNetworkOther(context, intent);
        }else if (YnPushConfig.ACTION_NETWORK_WIFI.equals(action)) {
            onNetworkWifi(context, intent);
        }else if (YnPushConfig.ACTION_PULL_ALARM.equals(action)) {
            onHeartBeat(context, intent);
        }else if (YnPushConfig.ACTION_SCREEN_OFF.equals(action)) {
            onScreenOff(context, intent);
        }else if (YnPushConfig.ACTION_SCREEN_ON.equals(action)) {
            onScreenOn(context, intent);
        }
    }

    /**
     * 接收到服务器端信息时回调
     * @param context
     * @param intent
     */
    void onPullData(Context context, Intent intent) {
        BussinessMessage message = (BussinessMessage) intent.getSerializableExtra("msg");
        if (message.getBody().getAction().equals(YnPushConfig.NOTIFICATION_JUMP)) {
            showNotification(context, intent.getStringExtra("service_pkg_name"),
                    message.getBody().getTitle(), message.getBody().getMsg());
        } else {
            onReceiveMessage(context, message.getBody().getMsg());
        }
    }

    void showNotification(Context context, String pkgName, String title, String msg) {
        Toast.makeText(context, "通知栏回调title：" + title + "----msg:" + msg, Toast.LENGTH_SHORT).show();
        // 第一步：获取NotificationManager
        NotificationManager nm = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        // 第二步：定义Notification
        Intent intent = new Intent();
        intent.setClassName(pkgName, context.getPackageName());
        //PendingIntent是待执行的Intent
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        Notification notification = new Notification.Builder(context)
                .setContentTitle(title)
                .setContentText(msg)
                .setSmallIcon(context.getApplicationInfo().icon).setContentIntent(pi)
                .getNotification();
        notification.flags = Notification.FLAG_NO_CLEAR;

        /////第三步：启动通知栏，第一个参数是一个通知的唯一标识
        nm.notify(0, notification);
    }

    /**
     * 心跳包回调
     * @param context
     * @param intent
     */
    void onHeartBeat(Context context, Intent intent) {
        Toast.makeText(context, "心跳回调", Toast.LENGTH_SHORT).show();
        try {
            CommandManager manager = CommandManager.getInstance();
            manager.runCommand(ClientUtil.getInstance().getChannel(),
                    ClientUtil.getInstance().getHeartBeatPackage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 网络连接状态改变为除wifi外的情况时调用
     * @param context
     * @param msg
     */
    public abstract void onReceiveMessage(Context context, String msg);
    /**
     * 网络连接状态改变为除wifi外的情况时调用
     * @param context
     * @param intent
     */
    public abstract void onNetworkOther(Context context, Intent intent);
    /**
     * 网络连接状态改变为wifi时调用
     * @param context
     * @param intent
     */
    public abstract void onNetworkWifi(Context context, Intent intent);
    /**
     * 屏幕唤醒时调用
     * @param context
     * @param intent
     */
    public abstract void onScreenOn(Context context, Intent intent);
    /**
     * 屏幕关闭时调用
     * @param context
     * @param intent
     */
    public abstract void onScreenOff(Context context, Intent intent);
}
