package com.push.model;

import java.io.Serializable;

import com.push.model.body.BindIdBody;

/**
 * 报文---绑定ID
 * @author DRAGON
 * @date 2015年1月23日
 */
@SuppressWarnings("serial")
public class BindIdMessage implements Serializable{
	
	/** 报文头 **/
	private Head head;
	
	/** 报文body **/
	private BindIdBody body;

	public Head getHead() {
		return head;
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public BindIdBody getBody() {
		return body;
	}

	public void setBody(BindIdBody body) {
		this.body = body;
	}

	public BindIdMessage(Head head, BindIdBody body) {
		super();
		this.head = head;
		this.body = body;
	}

	public BindIdMessage() {
		super();
	}

	@Override
	public String toString() {
		return "Message [head=" + head + ", body=" + body + "]";
	}
	

}
