package com.push.exception;

/**
 * 全局异常
 * @author DRAGON
 * @date 2015年1月23日
 */
public enum ExceptionCode {
	
	/** 发送失败 **/
	SEND_FAIL,
	
	/** 解码失败 **/
	DECODE_FAIL,
	
	/** 编码失败 **/
	ENCODE_FAIL,
	
	/**命令不存在 **/
	CMD_NULL,
	
	/** 空指针  **/
	NULL_POINT,
	
	/** rabbitMQ异常 **/
	RBMQ_FAIK,
	
	/** 默认**/
	DEFAULT,
	
	/** 连接失败 **/
	CONNECT_FAILE
	
}
