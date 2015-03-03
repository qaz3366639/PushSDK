package com.push.client.cmd;

import io.netty.channel.Channel;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.push.callback.CallBackDelegate;
import com.push.cmd.CommandImpl;
import com.push.model.BindIdMessage;
import com.push.model.DataPackage;
import com.push.util.ClientUtil;

/**
 * <p>
 * <p>
 * 处理客户端绑定
 * 
 * @author DRAGON
 * @date 2014年12月29日
 */
public class ReceiveBindIdCommand extends CommandImpl {

	/** logger **/
	private static String tag = "log";


	/** 客户端工具类 **/
	private ClientUtil clientUtil;
	
	@Override
	public <T> void execute(Channel channel, DataPackage message) {
		//Log.d(tag, "接收到服务器通知Id绑定...\n\n\n\n\n" + message.toString()+ "\n\n\n\n\n");
		
		BindIdMessage bindMessage = JSON.parseObject(message.getMessage(), BindIdMessage.class);
		
		clientUtil = ClientUtil.getInstance();
		CallBackDelegate delegate = clientUtil.getDelegate();
		if (null != delegate ) {
			delegate.bindIdCallBack(bindMessage);
		}
	}

}
