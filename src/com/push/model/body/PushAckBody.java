package com.push.model.body;

/**
 * 消息推送成功
 * @author DRAGON
 * @date 2015年2月10日
 */
public class PushAckBody {

	/** 用户uid  **/
	private String uid;
	
	/** 消息Id **/
	private Long msgId;
	
	/** 保留字段  **/
	private String reserve;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

	public PushAckBody(String uid, Long msgId, String reserve) {
		super();
		this.uid = uid;
		this.msgId = msgId;
		this.reserve = reserve;
	}

	public PushAckBody() {
		super();
	}

	@Override
	public String toString() {
		return "PushAckBody [uid=" + uid + ", msgId=" + msgId
				+ ", reserve=" + reserve + "]";
	}
	
}
