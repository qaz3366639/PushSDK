package com.push.core;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * User: WuRuiqiang(263454190@qq.com)
 * Date: 2015-03-03
 * Time: 10:39
 * Description：
 */
public class YnPushManager {

    static Class<?> receiverClazz;

    private static boolean isRunning = false;

    private IYnPushService iService;

    private Context cxt;

    private final ProxyReceiver proxyReceiver = new ProxyReceiver();

    private static YnPushManager instance;

    private YnPushManager() {
    }

    public static YnPushManager getInstance() {
        if (instance == null) {
            instance = new YnPushManager();
        }
        return instance;
    }

    public void startWork(Context context, Class<? extends YnPushReceiver> clazz) {
        if (!isRunning) {
            this.cxt = context;
            receiverClazz = clazz;
            isRunning = true;
            startPullService();
            startReceiver();
        }
    }

    private void stopWork() {
        if (isRunning) {
            isRunning = false;
            stopPullService();
            stopReceiver();
        }
    }

    /**
     * 启动去拉数据的服务
     */
    private void startPullService() {
        Intent intent = new Intent(YnPushConfig.ACTION_PULL_SERVICE);
        cxt.bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    /**
     * 结束去拉数据的服务
     */
    private void stopPullService() {
        cxt.unbindService(conn);
    }

    /**
     * 监听系统的一些广播
     */
    private void startReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        cxt.registerReceiver(proxyReceiver, intentFilter);
    }

    private void stopReceiver() {
        cxt.unregisterReceiver(proxyReceiver);
    }

    private final ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iService = IYnPushService.Stub.asInterface(iBinder);// 调用外部进程中service的转换方法
            try {
                iService.startPull();
            } catch (RemoteException e) {
                throw new RuntimeException(
                        "aidl error, not found open interface");
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
