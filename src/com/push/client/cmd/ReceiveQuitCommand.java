package com.push.client.cmd;

import io.netty.channel.Channel;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.push.callback.CallBackDelegate;
import com.push.cmd.CommandImpl;
import com.push.model.DataPackage;
import com.push.model.HeartBeatMessage;
import com.push.util.ClientUtil;

/**
 * <p>
 * <p>
 * 处理客户端退出命令
 * 
 * @author DRAGON
 * @date 2014年12月29日
 */
public class ReceiveQuitCommand extends CommandImpl {

	/** logger **/
	private static String tag = "log";

	/** 客户端工具类 **/
	private ClientUtil clientUtil;
	
	@Override
	public <T> void execute(Channel channel, DataPackage message) {
		//Log.d(tag, "接收到服务器通知断开连接，退出..." + message.toString());

		HeartBeatMessage quitMessage = JSON.parseObject(message.getMessage(), HeartBeatMessage.class); 
		
		clientUtil = ClientUtil.getInstance();
		CallBackDelegate delegate = clientUtil.getDelegate();
		if (null != delegate ) {
			delegate.normalQuitCallBack(quitMessage);
		}
	}

}
