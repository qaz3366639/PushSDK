package com.push.cmd.config;


/**
 * socket初始化 通信 数据
 * @author DRAGON
 * @date 2015年1月23日
 */
public class SocketConfig {

	/** host地址 **/
	private String host;
	/** 端口 **/
	private int port;
	/** 最大连接数 **/
	private int maxConnection;
	/** 最大工作连接数 **/
	private int maxWorkConnection;
	/** 超时时间 **/
	private int timeout;
	/** 读空闲  **/
	private int readIdle;
	/** 写空闲  **/
	private int writeIdle;
	

	public int getReadIdle() {
		return readIdle;
	}

	public void setReadIdle(int readIdle) {
		this.readIdle = readIdle;
	}

	public int getWriteIdle() {
		return writeIdle;
	}

	public void setWriteIdle(int writeIdle) {
		this.writeIdle = writeIdle;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}


	public int getMaxConnection() {
		return maxConnection;
	}

	public void setMaxConnection(int maxConnection) {
		this.maxConnection = maxConnection;
	}

	public int getMaxWorkConnection() {
		return maxWorkConnection;
	}

	public void setMaxWorkConnection(int maxWorkConnection) {
		this.maxWorkConnection = maxWorkConnection;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	@Override
	public String toString() {
		return "SocketConfig [host=" + host + ", port=" + port
				+ ", maxConnection=" + maxConnection + ", maxWorkConnection="
				+ maxWorkConnection + ", timeout=" + timeout + ", readIdle="
				+ readIdle + ", writeIdle=" + writeIdle + "]";
	}

	public SocketConfig() {
		super();
		loadConfig();
	}

	

	public SocketConfig(String host, int port, int maxConnection,
			int maxWorkConnection, int timeout, int readIdle, int writeIdle) {
		super();
		this.host = host;
		this.port = port;
		this.maxConnection = maxConnection;
		this.maxWorkConnection = maxWorkConnection;
		this.timeout = timeout;
		this.readIdle = readIdle;
		this.writeIdle = writeIdle;
	}

	/**
	 * 默认静态加载
	 */
	private void loadConfig() {
		this.host = "0.0.0.0";
		this.port = 20001;
		this.maxConnection = Runtime.getRuntime().availableProcessors() * 2;
		this.maxWorkConnection = 1000;
		this.timeout = 60;
		this.readIdle = 5000;
		this.writeIdle = 5000;
	}

}
