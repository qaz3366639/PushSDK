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
 * <p>处理客户端强制退出命令 9090
 * @author DRAGON
 * @date 2014年12月29日
 */
public class ReceiveForseQuitCommand extends CommandImpl{


	/** logger **/
	private static String tag = "log";

	/** 客户端工具类 **/
	private ClientUtil clientUtil;
	
	@Override
	public <T> void execute(Channel channel, DataPackage message) {
		//Log.d(tag,"\n\n\n\n接收到服务器通知断开连接，退出..."+message.toString()+"\n\n\n\n");
		closeChannel(channel);
		
		HeartBeatMessage forceQuitMessage = JSON.parseObject(message.getMessage(), HeartBeatMessage.class); 
		
		clientUtil = ClientUtil.getInstance();
		CallBackDelegate delegate = clientUtil.getDelegate();
		if (null != delegate ) {
			delegate.forceQuitCallBack(forceQuitMessage);
		}
	}
	
	
	/**
	 * 关闭channel
	 */
	public void closeChannel(Channel channel) {
		try {
			channel.closeFuture().sync();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	

}
