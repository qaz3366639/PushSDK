package com.push.model.body;

import java.io.Serializable;

/**
 * body绑定
 * @author DRAGON
 * @date 2015年1月26日
 */
@SuppressWarnings("serial")
public class BindIdBody implements Serializable{

	/** 用户id **/
	private String uid;

	/** 客户端id **/
	private String clientId;

	/** 保留字段 **/
	private String reserve;
	
	public BindIdBody() {
		super();
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

	public BindIdBody(String uid, String clientId, String reserve) {
		super();
		this.uid = uid;
		this.clientId = clientId;
		this.reserve = reserve;
	}

	@Override
	public String toString() {
		return "BindIdBody [uid=" + uid + ", clientId=" + clientId
				+ ", reserve=" + reserve + "]";
	}



}