package com.push.core;

/**
 * User: WuRuiqiang(263454190@qq.com)
 * Date: 2015-03-03
 * Time: 09:49
 * Description：
 */
public class YnPushConfig {
    public static final String ACTION_PULL_SERVICE = "com.yn.push.service.pull_service";

    public static final String ACTION_PULL_ALARM = "com.yn.push.receiver.pull";

    public static final String ACTION_PULL_DATA = "com.yn.push.pull.pull_data";

    public static final String ACTION_SCREEN_ON = "com.yn.push.receiver.screen_on";

    public static final String ACTION_SCREEN_OFF = "com.yn.push.receiver.screen_off";

    public static final String ACTION_NETWORK_OTHER = "com.yn.push.receiver.network_other";

    public static final String ACTION_NETWORK_WIFI = "com.yn.push.receiver.network_wifi";

    public static final String NOTIFICATION_JUMP = "jump";

    public static final int HEART_BEAT_TIME = 2000;//心跳间隔，默认3分钟1次，wifi连接状态下1分钟1次
}
