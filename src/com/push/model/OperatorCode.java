package com.push.model;

/**
 * 报文标志
 * @author DRAGON
 *
 */
public final class OperatorCode {
	
	/** 业务报文  **/
	public final static String BUSINESS="2002";
	
	/** 客户端ping心跳 **/
	public final static String PING="1001";
	
	/** 服务器端pong心跳 **/
	public final static String PONG="1111";
	
	/** 报文分割符 **/
	public final static short SIGN = (short)0xAABB;
	
	/** 报文解码错误 **/
	public final static short DECODE_ERROR = (short)0xEEEE;
	
	/** quit **/
	public final static String QUIT="quit";
	
	
}
