package com.push.client.cmd;

import io.netty.channel.Channel;

import com.alibaba.fastjson.JSON;
import com.push.callback.CallBackDelegate;
import com.push.cmd.CommandImpl;
import com.push.model.DataPackage;
import com.push.model.HeartBeatMessage;
import com.push.util.ClientUtil;

/**
 * <p>
 * <p>处理客户端 心跳	
 * @author DRAGON
 * @date 2014年12月29日
 */
public class ReceiveHeartBeatCommand extends CommandImpl{

	/** logger **/
	private static String tag = "log";


	/** 客户端工具类 **/
	private ClientUtil clientUtil;
	
	@Override
	public <T> void execute(Channel channel, DataPackage message) {
		//Log.d(tag,"接收到服务器端的心跳消息："+message.toString());
		
		HeartBeatMessage heartBeatMessage = JSON.parseObject(message.getMessage(), HeartBeatMessage.class); 
		
		clientUtil = ClientUtil.getInstance();
		CallBackDelegate delegate = clientUtil.getDelegate();
		if (null != delegate ) {
			delegate.heartBeatCallBack(heartBeatMessage);
		}
		
	}


}
