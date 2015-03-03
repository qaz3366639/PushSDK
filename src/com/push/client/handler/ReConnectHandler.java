package com.push.client.handler;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.util.Log;

import com.push.cmd.config.ConfigFactory;
import com.push.cmd.config.ConfigManager;
import com.push.cmd.config.SocketConfig;
import com.push.util.ClientUtil;
import com.push.util.FdMode;
import com.push.util.NetCheck;

/**
 * 重连机制
 * 
 * @author DRAGON
 * @date 2014年12月16日
 */
public class ReConnectHandler {

	/** logger **/
	private static String tag = "log";

	private Timer timer;

	public ReConnectHandler() {
		super();
	}

	public void connect() {

		if (null == timer) {
			timer = new Timer();
		}

		timer.schedule(new TimerTask() {

			@Override
			public void run() {

				ClientUtil clientUtil = ClientUtil.getInstance();
				ConfigManager manager = ConfigFactory.getInstance()
						.getConfigManager();
				SocketConfig config = (SocketConfig) manager.getDataConfig();

				Context context = clientUtil.getContext();
				Log.d(tag, "\n\n\n context:" + context + "  \n\n\n");
				boolean isConnect = NetCheck.isConnect(context);

				if (isConnect) {

					clientUtil.setParam(config.getHost(), config.getPort(),
							manager.getSystemId(), manager.getClientUid());
					Log.d(tag, "\n\n\n尝试重新连接, 网络可用  \n\n\n");

					boolean binding;
					if (manager.getFdMode().equals(FdMode.MORE.toString())) {
						binding = clientUtil.bind(FdMode.MORE);
					} else {
						binding = clientUtil.bind(FdMode.SINGLE);
					}

					if (binding) {// 如果连接成功，则关闭定时器
						timer.purge();
						this.cancel();
					}
				} else {
					Log.d(tag, "\n\n\n尝试重新连接, 网络不可用  \n\n\n");
				}
			}
		}, 5 * 1000, 5 * 1000);

	}

}
