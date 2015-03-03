package com.push.model.body;

import java.io.Serializable;

/**
 * body绑定
 * @author DRAGON
 * @date 2015年1月26日
 */
@SuppressWarnings("serial")
public class HeartBeatBody implements Serializable{

	/** 用户id **/
	private String uid;

	/** 客户端id **/
	private String clientId;

	/** 保留字段 **/
	private String reserve;
	
	/** 心跳消息  **/
	private	String heartMsg;

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

	public String getHeartMsg() {
		return heartMsg;
	}

	public void setHeartMsg(String heartMsg) {
		this.heartMsg = heartMsg;
	}

	public HeartBeatBody(String uid, String clientId, String reserve,
			String heartMsg) {
		super();
		this.uid = uid;
		this.clientId = clientId;
		this.reserve = reserve;
		this.heartMsg = heartMsg;
	}

	public HeartBeatBody() {
		super();
	}

	@Override
	public String toString() {
		return "HeartBeatBody [uid=" + uid + ", clientId=" + clientId
				+ ", reserve=" + reserve + ", heartMsg=" + heartMsg + "]";
	}

	

}
