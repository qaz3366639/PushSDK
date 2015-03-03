package com.push.model;

import java.io.Serializable;

import com.push.model.body.BussinessBody;

/**
 * 报文----业务消息
 * 
 * @author DRAGON
 * @date 2015年1月23日
 */
@SuppressWarnings("serial")
public class BussinessMessage implements Serializable {

	/** 报文头 **/
	private Head head;

	/** 报文body **/
	private BussinessBody body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public BussinessBody getBody() {
		return body;
	}

	public void setBody(BussinessBody body) {
		this.body = body;
	}

	public BussinessMessage(Head head, BussinessBody body) {
		super();
		this.head = head;
		this.body = body;
	}

	public BussinessMessage() {
		super();
	}

	@Override
	public String toString() {
		return "Message [head=" + head + ", body=" + body + "]";
	}

}
