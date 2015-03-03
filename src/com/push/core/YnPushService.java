package com.push.core;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.RemoteException;

import com.push.callback.DefaultCallBack;
import com.push.model.BindIdMessage;
import com.push.model.BussinessMessage;
import com.push.model.HeartBeatMessage;
import com.push.util.ClientUtil;
import com.push.util.FdMode;

import java.lang.ref.WeakReference;

public class YnPushService extends Service {

    private static final String TAG = YnPushService.class.getSimpleName();

    final String IP = "172.16.110.179";

    private AlarmManager alarmManager;

    final int PORT = 20001;

    private final IBinder iBinder = new YnPushStub(this);

    @Override
    public void onCreate() {
        super.onCreate();
        startForeground(0, new Notification());
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }

    @Override
    public IBinder onBind(Intent intent) {
        ClientUtil.getInstance().setContext(this);
        ClientUtil.getInstance().setParam(IP, PORT, "mytest", "123456");
        ClientUtil.getInstance().setDelegate(new PushCallBack());
        ClientUtil.getInstance().bind(FdMode.MORE);
        return iBinder;
    }

    private void startRequestAlarm() {
        cancelRequestAlarm();
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1000,
                YnPushConfig.HEART_BEAT_TIME, getOperationIntent());
    }

    /**
     * 采用轮询方式实现消息推送<br>
     * 每次被调用都去执行一次{@link #}onReceive()方法
     *
     * @return
     */
    private PendingIntent getOperationIntent() {
        Intent intent = new Intent(this, YnPushReceiver.class);
        intent.setAction(YnPushConfig.ACTION_PULL_ALARM);
        return PendingIntent.getBroadcast(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void cancelRequestAlarm() {
        alarmManager.cancel(getOperationIntent());
    }

    private static class YnPushStub extends IYnPushService.Stub {

        WeakReference<YnPushService> service;

        private YnPushStub(YnPushService service) {
            this.service = new WeakReference<YnPushService>(service);
        }

        @Override
        public void startPull() throws RemoteException {
            service.get().startRequestAlarm();
        }

        @Override
        public void stopPull() throws RemoteException {
            service.get().cancelRequestAlarm();
        }
    }

    private final class PushCallBack extends DefaultCallBack {

        @Override
        public void bindIdCallBack(BindIdMessage message) {
            super.bindIdCallBack(message);
        }

        @Override
        public void heartBeatCallBack(HeartBeatMessage message) {
            super.heartBeatCallBack(message);
            System.out.print(TAG + ":-----------heartBeatCallBack------------");
        }

        @Override
        public void normalQuitCallBack(HeartBeatMessage message) {
            super.normalQuitCallBack(message);
        }

        @Override
        public void forceQuitCallBack(HeartBeatMessage message) {
            super.forceQuitCallBack(message);
        }

        @Override
        public void bussinessCallBack(BussinessMessage message) {
            super.bussinessCallBack(message);
            Intent intent = new Intent();
            intent.setAction(YnPushConfig.ACTION_PULL_DATA);
            intent.putExtra("msg", message);
            intent.putExtra("service_pkg_name", getPackageName());
            sendBroadcast(intent);
        }
    }
}
