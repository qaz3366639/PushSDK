package com.push.client.cmd;

import io.netty.channel.Channel;
import android.util.Log;

import com.push.cmd.CommandImpl;
import com.push.model.DataPackage;

/**
 * <p>
 * <p>处理业务消息
 * <p>
 * @author DRAGON
 * @date 2014年12月29日
 */
public class SendBussinessCommand extends CommandImpl{

	/** logger **/
	private static String tag = "log";

	@Override
	public <T> void execute(Channel channel, DataPackage message) {
		//Log.i(tag,"客户端发送业务消息...");
		send(channel, message);
	}
	


	

}
