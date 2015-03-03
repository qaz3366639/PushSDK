package com.push.cmd;


public final class CommandCode {
	
	/** 发送心跳 **/
	public final static short SENDHEARTBEAT = (short)1001;
	/** 接收心跳 **/
	public final static short RECEIVEHEARTBEAT = (short)1111;
	
	/** 发送业务消息 **/
	public final static short SENDBUSSINESS = (short)2002;
	/** 接收业务消息 **/
	public final static short RECEIVEBUSSINESS = (short)2222;
	
	/** 发送绑定 **/
	public final static short SENDBIND = (short)3003;
	/** 发送多绑定  **/
	public final static short SENDMUTILBIND = (short)3030;
	/** 接收绑定  **/
	public final static short RECEIVEBIND = (short)3333;
	
	/** 发送IM **/
	public final static short SENDIM = (short)4004;
	/** 接收IM **/
	public final static short RECEIVEIM = (short)4444;
	
	/** 发送退出  **/
	public final static short SENDQUIT = (short)9009;
	/** 接收强迫退出 **/
	public final static short RECEIVEFORCEQUIT = (short)9090;
	/** 接收退出 **/
	public final static short RECEIVEQUIT = (short)9999;
	
}
