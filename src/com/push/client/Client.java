package com.push.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;

import com.push.cmd.manager.CommandManager;
import com.push.model.DataPackage;

/**
 * TCP 客户端
 * 
 * @author DRAGON
 * @date 2015年1月23日
 */
public interface Client {

	/**
	 * 初始化
	 */
	public void init(String host, int port, String uid, String clientUid);

	/**
	 * 获取启动对象
	 * 
	 * @return
	 */
	public Bootstrap getBootstrap();

	/**
	 * 发送消息
	 * 
	 * @return
	 */
	public void sendMsg(Object msg);

	/**
	 * 发送消息
	 * 
	 * @param manager
	 * @param msg
	 */
	public void sendMsg(CommandManager manager, DataPackage message);

	/**
	 * 获取通道
	 * 
	 * @param host
	 * @param port
	 * @return
	 */
	public Channel getChannel(String host, int port);

	/**
	 * 获取通道
	 * 
	 * @param host
	 * @param port
	 * @return
	 * @throws InterruptedException
	 */
	public Channel getChannel() throws InterruptedException;

	/**
	 * 关闭通道
	 */
	public void closeChannel();
}
