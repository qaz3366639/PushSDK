package com.push.client.cmd;

import io.netty.channel.Channel;
import android.util.Log;

import com.push.cmd.CommandImpl;
import com.push.model.DataPackage;

/**
 * <p>
 * <p>
 * 处理客户端 心跳
 * 
 * @author DRAGON
 * @date 2014年12月29日
 */
public class SendHeartBeatCommand extends CommandImpl {


	/** logger **/
	private static String tag = "log";

	@Override
	public <T> void execute(Channel channel, DataPackage message) {
		//Log.i(tag,"ping心跳...");
		send(channel, message);
	}
	


}
