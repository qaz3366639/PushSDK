package com.push.client.cmd;

import io.netty.channel.Channel;
import android.util.Log;

import com.push.cmd.CommandImpl;
import com.push.model.DataPackage;

/**
 * <p>
 * <p>处理客户端退出命令
 * @author DRAGON
 * @date 2014年12月29日
 */
public class SendQuitCommand extends CommandImpl{


	/** logger **/
	private static String tag = "log";


	@Override
	public <T> void execute(Channel channel, DataPackage message) {
		//Log.i(tag,"客户端退出...");
		channel = send(channel, message).channel();
		closeChannel(channel);
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
