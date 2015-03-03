package com.push.client.cmd;

import io.netty.channel.Channel;
import android.util.Log;

import com.push.cmd.CommandImpl;
import com.push.model.DataPackage;

/**
 * <p>
 * <p>
 * 发送IM
 * <p>
 * 
 * @author DRAGON
 * @date 2014年12月29日
 */
public class SendIMCommand extends CommandImpl {

	/** logger **/
	private static String tag = "log";

	@Override
	public <T> void execute(Channel channel, DataPackage message) {
		//Log.i(tag, "\n\n\n\n客户端发送IM消息...\n" + message + "\n\n\n\n");
		send(channel, message);
	}

}
