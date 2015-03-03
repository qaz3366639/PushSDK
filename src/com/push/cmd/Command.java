package com.push.cmd;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import com.push.model.DataPackage;

/**
 * 命令
 * @author DRAGON
 * @date 2014年12月26日
 */
public interface Command {

	/**
	 * 命令执行
	 * @param <T>
	 * @param ctx		---channel 上下文
	 * @param message	---报文
	 */
	public <T> void execute(Channel channel,DataPackage message);
	
	/**
	 * 发送
	 * @param ctx
	 * @param message
	 * @return
	 */
	public ChannelFuture send(Channel channel,DataPackage message);
	
}
