package com.push.client.impl;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

import android.util.Log;

import com.push.client.Client;
import com.push.client.cmd.ReceiveBindIdCommand;
import com.push.client.cmd.ReceiveForseQuitCommand;
import com.push.client.cmd.ReceiveHeartBeatCommand;
import com.push.client.cmd.ReceiveQuitCommand;
import com.push.client.cmd.ReveiveBussinessCommand;
import com.push.client.cmd.ReveiveIMCommand;
import com.push.client.cmd.SendBindIdCommand;
import com.push.client.cmd.SendBindMutilIdCommand;
import com.push.client.cmd.SendBussinessCommand;
import com.push.client.cmd.SendHeartBeatCommand;
import com.push.client.cmd.SendIMCommand;
import com.push.client.cmd.SendQuitCommand;
import com.push.client.handler.TcpClientHandler;
import com.push.cmd.Command;
import com.push.cmd.CommandCode;
import com.push.cmd.config.ConfigFactory;
import com.push.cmd.config.ConfigManager;
import com.push.cmd.config.SocketConfig;
import com.push.cmd.manager.CommandManager;
import com.push.cmd.manager.CommandPool;
import com.push.exception.ExceptionCode;
import com.push.exception.MyException;
import com.push.model.DataPackage;
import com.push.util.FdMode;

/**
 * TCP长连接
 * @author DRAGON
 * @date 2015年1月23日
 */
public class TcpClient implements Client {
	/** 日志 **/
	private static String tag = "log";

	/** 配置文件 **/
	private SocketConfig config;

	/** 启动器 **/
	private Bootstrap bootstrap = getBootstrap();
	private Channel channel = null;
	private EventLoopGroup group = null;

	public TcpClient() {
		super();
	}

	/**
	 * 初始化Bootstrap
	 *
	 * @return
	 */
	public Bootstrap getBootstrap() {
		group = new NioEventLoopGroup();
		Bootstrap b = new Bootstrap();
		b.group(group).channel(NioSocketChannel.class);
		b.handler(new ChannelInitializer<Channel>() {
			@Override
			protected void initChannel(Channel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();
				pipeline.addLast("frameDecoder",
						new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 2,
								4, -6, 0));

//				pipeline.addLast("idleStateHandler", new IdleStateHandler(config.getReadIdle(),
//						config.getWriteIdle(), 0, TimeUnit.SECONDS));// 心跳定时回调

				pipeline.addLast("clientHandler", new TcpClientHandler());// 业务处理

			}
		});
		b.option(ChannelOption.SO_KEEPALIVE, true);
		return b;
	}

	/**
	 * 获取通道
	 *
	 * @param host
	 * @param port
	 * @return
	 */
	public Channel getChannel(String host, int port) {
		try {
			return bootstrap.connect(host, port).sync().channel();
		} catch (Exception e) {
			Log.i(tag,"连接失败:" + host + "：" + port);
			Log.i(tag,e.getMessage());
			return null;
		}
	}
	/**
	 * 获取通道
	 *
	 * @param host
	 * @param port
	 * @return
	 * @throws InterruptedException
	 */
	public Channel getChannel() throws InterruptedException {
		return bootstrap.connect(this.config.getHost(), this.config.getPort()).sync().channel();
	}

	/**
	 * 发送信息
	 *
	 * @param msg
	 * @throws Exception
	 */
	public void sendMsg(Object msg) {
		System.out.println("channel:" + channel);
		if (null == channel) {
			channel = getChannel(config.getHost(), config.getPort());
		}

		try {
			channel.writeAndFlush(msg).sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭channel
	 */
	public void closeChannel() {
		group.shutdownGracefully();
	}

	@Override
	public void sendMsg(CommandManager manager, DataPackage message) {
		if (null == channel) {
			channel = getChannel(config.getHost(), config.getPort());
		}
		try {
			manager.runCommand(channel, message);
		} catch (Exception e) {
			Log.e(tag,"发送失败...");
			throw new MyException(ExceptionCode.SEND_FAIL);
		}
	}

	@Override
	public void init(String host,int port,String uid,String clientUid) {
		ConfigManager manager = new ConfigManager();
		manager.setConnectTime(60*1000);

		SocketConfig dataConfig = new SocketConfig();
		dataConfig.setHost(host);
		dataConfig.setMaxConnection(8);
		dataConfig.setMaxWorkConnection(10);
		dataConfig.setPort(port);
		dataConfig.setReadIdle(60);
		dataConfig.setTimeout(60*1000);
		dataConfig.setWriteIdle(15);

		manager.setDataConfig(dataConfig);
		manager.setEnv(null);
		manager.setHttpHost(null);
		manager.setHttpPort(0);
		manager.setOpenHttp(0);

		CommandPool<Command> pool = new CommandPool<Command>();
		pool.addCommand(CommandCode.SENDHEARTBEAT, new SendHeartBeatCommand());
		pool.addCommand(CommandCode.RECEIVEHEARTBEAT, new ReceiveHeartBeatCommand());
		pool.addCommand(CommandCode.SENDBUSSINESS, new SendBussinessCommand());
		pool.addCommand(CommandCode.RECEIVEBUSSINESS, new ReveiveBussinessCommand());
		pool.addCommand(CommandCode.SENDBIND, new SendBindIdCommand());
		pool.addCommand(CommandCode.SENDMUTILBIND, new SendBindMutilIdCommand());
		pool.addCommand(CommandCode.RECEIVEBIND, new ReceiveBindIdCommand());
		pool.addCommand(CommandCode.SENDIM, new SendIMCommand());
		pool.addCommand(CommandCode.RECEIVEIM, new ReveiveIMCommand());
		pool.addCommand(CommandCode.SENDQUIT, new SendQuitCommand());
		pool.addCommand(CommandCode.RECEIVEFORCEQUIT, new ReceiveForseQuitCommand());
		pool.addCommand(CommandCode.RECEIVEQUIT, new ReceiveQuitCommand());

		manager.setPool(pool);
		manager.setPrivateKeyPwd(null);
		manager.setProducer(null);
		manager.setPrvateKeyPath(null);
		manager.setRedisIdField(null);
		manager.setRedisMsgField(null);
		manager.setRedisSpringProxy(null);
		manager.setFdMode(FdMode.MORE.toString());
		manager.setSystemId(uid);
		manager.setClientUid(clientUid);


		ConfigFactory factory = ConfigFactory.getInstance();
		factory.setConfigManager(manager);

		Log.i(tag, "=================================="+factory.toString());

		this.config = dataConfig;
	}

}
