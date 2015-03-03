package com.push.util;

import io.netty.channel.Channel;

import java.util.Date;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.push.callback.CallBackDelegate;
import com.push.client.Client;
import com.push.client.impl.TcpClient;
import com.push.cmd.config.ConfigFactory;
import com.push.cmd.config.ConfigManager;
import com.push.cmd.config.SocketConfig;
import com.push.cmd.manager.CommandManager;
import com.push.model.BindIdMessage;
import com.push.model.BussinessMessage;
import com.push.model.DataPackage;
import com.push.model.Head;
import com.push.model.HeartBeatMessage;
import com.push.model.body.BindIdBody;
import com.push.model.body.BussinessBody;
import com.push.model.body.HeartBeatBody;

public class ClientUtil {

	private final static String tag = "log";

	/** 单例 **/
	private static ClientUtil clientUtil;

	/** 消息回调 **/
	public CallBackDelegate delegate;
	
	/** 消息回调 **/
	public CallBackDelegate imDelegate;
	

	/** 全局channel **/
	public Channel channel;

	/** 全局client **/
	public Client client = new TcpClient();

	/** 全局上下文 **/
	private Context context;
	
	/** 是否被激活 **/
	private boolean isActive = false;
	
	
	public boolean isActive() {
		return this.isActive;
	}

	private ClientUtil() {
		super();
		if (null == client) {
			client = new TcpClient();
		}
	}

	public static ClientUtil getInstance() {
		if (null == clientUtil) {
			clientUtil = new ClientUtil();
		}
		return clientUtil;
	}

	public Context getContext() {
		return this.context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	/***
	 * 设置参数
	 */
	public void setParam(String host, int port, String uid, String clientUid) {
		client.init(host, port, uid, clientUid);
		
		if (isActive) {
			if (null != channel) {
				stop();
			}
			isActive = false;
		}
	}

	/***
	 * 绑定
	 */
	public boolean bind(FdMode mode) {
		try {
			channel = client.getChannel();
			System.out.println("channel:" + channel);

		} catch (Exception e) {
			Toast.makeText(getContext(), "连接服务器失败", Toast.LENGTH_LONG).show();

			ConfigFactory factory = ConfigFactory.getInstance();
			factory.getConfigManager().setFdMode(mode.toString());
			SocketConfig config = (SocketConfig) factory.getConfigManager()
					.getDataConfig();

			Log.i(tag,
					"###############################################################\n\n\n\n"
							+ "连接失败:"
							+ config.getHost()
							+ "："
							+ config.getPort()
							+ "\n\n\n\n###############################################################");

		}

		if (null == channel) {
			return false;
		}

		if (!channel.isActive()) {
			return false;
		}

		if (null == channel) {
			Log.e(tag, "\n\n\n\n 获取channel失败 \n\n\n\n");

			return false;
		}

		CommandManager manager = CommandManager.getInstance();
		try {
			if (mode == FdMode.MORE) {
				manager.runCommand(channel, getMutilBindPackage());
			} else {
				manager.runCommand(channel, getBindPackage());
			}
			Log.i(tag,
					"\n\n\n\n##################################连接绑定成功###################################\n\n\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

		isActive = true;
		
		return true;
	}

	/**
	 * 停止
	 */
	public void stop() {

		if (!isActive) {
			return;
		}
		
		if (!channel.isActive()) {
			return;
		}

		CommandManager manager = CommandManager.getInstance();
		try {
			manager.runCommand(channel, getQuitPackage());
			Log.i(tag,
					"\n\n\n\n##################################断开连接成功###################################\n\n\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送
	 * 
	 * @param sendMsg
	 * @param fid
	 * @param device
	 */
	public void send(String sendMsg, String fid, String device) {

		if (!channel.isActive()) {
			return;
		}

		CommandManager manager = CommandManager.getInstance();
		try {
			manager.runCommand(channel, getIMPackage(sendMsg, fid, device));
			Log.i(tag,
					"\n\n\n\n##################################发送成功###################################\n\n\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		isActive = true;
	}
	
	public CallBackDelegate getDelegate() {
		return delegate;
	}

	public void setDelegate(CallBackDelegate delegate) {
		this.delegate = delegate;
	}

	public CallBackDelegate getImDelegate() {
		return imDelegate;
	}

	public void setImDelegate(CallBackDelegate imDelegate) {
		this.imDelegate = imDelegate;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	/**
	 * 退出
	 * 
	 * @return
	 */
	public DataPackage getQuitPackage() {

		ConfigFactory factory = ConfigFactory.getInstance();
		ConfigManager manager = factory.getConfigManager();
		DataPackage dataPackage = new DataPackage();

		HeartBeatMessage msg = new HeartBeatMessage();
		Head head = new Head("none", "1.0", 0, 0, "", "client");
		msg.setHead(head);
		HeartBeatBody body = new HeartBeatBody(manager.getSystemId(),
				manager.getClientUid(), "", "quit...");
		msg.setBody(body);

		String json = null;
		json = JSON.toJSONString(msg);

		dataPackage.setMessage(json);
		dataPackage.setCode((short) 9009);// 退出
		dataPackage.setLength(2 + 4 + 2 + json.getBytes().length);

		return dataPackage;
	}

	/**
	 * 绑定ID
	 * 
	 * @return
	 */
	public DataPackage getBindPackage() {

		DataPackage dataPackage = new DataPackage();

		BindIdMessage msg = new BindIdMessage();
		Head head = new Head("none", "1.0", 0, 0, "", "client");
		msg.setHead(head);
		ConfigFactory factory = ConfigFactory.getInstance();
		ConfigManager manager = factory.getConfigManager();
		BindIdBody body = new BindIdBody(manager.getSystemId(),
				manager.getClientUid(), "");
		msg.setBody(body);

		String json = JSON.toJSONString(msg);

		dataPackage.setMessage(json);
		dataPackage.setCode((short) 3003);// ID绑定
		dataPackage.setLength(2 + 4 + 2 + json.getBytes().length);

		return dataPackage;
	}

	/**
	 * 绑定mutil ID
	 * 
	 * @return
	 */
	public DataPackage getMutilBindPackage() {

		DataPackage dataPackage = new DataPackage();

		BindIdMessage msg = new BindIdMessage();
		Head head = new Head("none", "1.0", 0, 0, "", "client");
		msg.setHead(head);
		ConfigFactory factory = ConfigFactory.getInstance();
		ConfigManager manager = factory.getConfigManager();
		BindIdBody body = new BindIdBody(manager.getSystemId(),
				manager.getClientUid(), "");
		msg.setBody(body);

		String json = JSON.toJSONString(msg);

		dataPackage.setMessage(json);
		dataPackage.setCode((short) 3030);// ID绑定
		dataPackage.setLength(2 + 4 + 2 + json.getBytes().length);

		return dataPackage;
	}

	/**
	 * IM
	 * 
	 * @return
	 */
	public DataPackage getIMPackage(String sendMsg, String fid, String device) {

		ConfigFactory factory = ConfigFactory.getInstance();

		DataPackage dataPackage = new DataPackage();

		BussinessMessage msg = new BussinessMessage();
		Head head = new Head("jump", "1.0", 0, 0, "", device);
		msg.setHead(head);

		Date sendDate = new Date();

		BussinessBody body = new BussinessBody();
		body.setAction("jump");
		body.setAltitude(0.0);
		body.setId(sendDate.getTime());
		body.setImagesUrls("");
		body.setLongitude(0.0);
		body.setMsg(sendMsg);
		body.setReceiveUid(fid);
		body.setReserve("");
		body.setSendDate(sendDate);
		body.setSendUid(factory.getConfigManager().getSystemId());
		body.setSoundsUrls("");
		body.setTitle("IM消息");
		body.setVideosUrls("");

		msg.setBody(body);

		String json = JSON.toJSONString(msg);

		dataPackage.setMessage(json);
		dataPackage.setCode((short) 4004);// IM
		dataPackage.setLength(2 + 4 + 2 + json.getBytes().length);

		return dataPackage;
	}

	/**
	 * 实例化ping心跳
	 * 
	 * @return
	 */
	public DataPackage getHeartBeatPackage() {

		DataPackage dataPackage = new DataPackage();

		HeartBeatMessage msg = new HeartBeatMessage();
		Head head = new Head("0000", "1.0", 0, 0, "", "client");
		msg.setHead(head);

		ConfigFactory factory = ConfigFactory.getInstance();
		ConfigManager manager = factory.getConfigManager();
		HeartBeatBody body = new HeartBeatBody(manager.getSystemId(),
				manager.getClientUid(), "", "received heartbeat from client...");
		msg.setBody(body);
		String json = null;
		json = JSON.toJSONString(msg);

		dataPackage.setMessage(json);
		dataPackage.setCode((short) 1001);// 发送心跳
		dataPackage.setLength(2 + 4 + 2 + json.getBytes().length);

		return dataPackage;
	}

}
