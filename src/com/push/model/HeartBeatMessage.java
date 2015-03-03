package com.push.model;

import java.io.Serializable;

import com.push.model.body.HeartBeatBody;

/**
 * 报文----心跳
 * @author DRAGON
 * @date 2015年1月23日
 */
@SuppressWarnings("serial")
public class HeartBeatMessage implements Serializable{
	
	/** 报文头 **/
	private Head head;
	
	/** 报文body **/
	private HeartBeatBody body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public HeartBeatBody getBody() {
		return body;
	}

	public void setBody(HeartBeatBody body) {
		this.body = body;
	}

	public HeartBeatMessage(Head head, HeartBeatBody body) {
		super();
		this.head = head;
		this.body = body;
	}

	public HeartBeatMessage() {
		super();
	}

	@Override
	public String toString() {
		return "Message [head=" + head + ", body=" + body + "]";
	}
	

}
