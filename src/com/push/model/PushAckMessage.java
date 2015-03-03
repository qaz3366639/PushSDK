package com.push.model;

import java.io.Serializable;

import com.push.model.body.PushAckBody;

/**
 * 报文----回应消息
 * 
 * @author DRAGON
 * @date 2015年1月23日
 */
@SuppressWarnings("serial")
public class PushAckMessage implements Serializable {

	/** 报文头 **/
	private Head head;

	/** 报文body **/
	private PushAckBody body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public PushAckBody getBody() {
		return body;
	}

	public void setBody(PushAckBody body) {
		this.body = body;
	}

	public PushAckMessage(Head head, PushAckBody body) {
		super();
		this.head = head;
		this.body = body;
	}

	public PushAckMessage() {
		super();
	}

	@Override
	public String toString() {
		return "Message [head=" + head + ", body=" + body + "]";
	}

}
