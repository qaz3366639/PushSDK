package com.push.model.body;

import java.io.Serializable;
import java.util.Date;

/**
 * 推送/im 业务body
 * 
 * @author DRAGON
 * @date 2015年1月26日
 */
@SuppressWarnings("serial")
public class BussinessBody implements Serializable {

	/** 历史消息编号/IM消息编号 **/
	private Long id;

	/** 后续行为 **/
	private String action;// none jump

	/** 消息标题 **/
	private String title;

	/** 消息 **/
	private String msg;

	/** 图片url,可包含多个url,使用######分割 **/
	private String imagesUrls;

	/** 语音 url,可包含多个url,使用######分割 **/
	private String soundsUrls;

	/** 视频 url,可包含多个url,使用######分割 **/
	private String videosUrls;

	/** 经度 **/
	private Double longitude;

	/** 纬度 **/
	private Double altitude;

	/** 推送时间 /发送时间 **/
	private Date sendDate;

	/** 推送或者IM 发起端UID 服务器默认0000 **/
	private String sendUid;

	/** 接收端的uid **/
	private String receiveUid;

	/** 保留字段 **/
	private String reserve;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getImagesUrls() {
		return imagesUrls;
	}

	public void setImagesUrls(String imagesUrls) {
		this.imagesUrls = imagesUrls;
	}

	public String getSoundsUrls() {
		return soundsUrls;
	}

	public void setSoundsUrls(String soundsUrls) {
		this.soundsUrls = soundsUrls;
	}

	public String getVideosUrls() {
		return videosUrls;
	}

	public void setVideosUrls(String videosUrls) {
		this.videosUrls = videosUrls;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getSendUid() {
		return sendUid;
	}

	public void setSendUid(String sendUid) {
		this.sendUid = sendUid;
	}

	public String getReceiveUid() {
		return receiveUid;
	}

	public void setReceiveUid(String receiveUid) {
		this.receiveUid = receiveUid;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

	public BussinessBody(Long id, String action, String title, String msg,
			String imagesUrls, String soundsUrls, String videosUrls,
			Double longitude, Double altitude, Date sendDate, String sendUid,
			String receiveUid, String reserve) {
		super();
		this.id = id;
		this.action = action;
		this.title = title;
		this.msg = msg;
		this.imagesUrls = imagesUrls;
		this.soundsUrls = soundsUrls;
		this.videosUrls = videosUrls;
		this.longitude = longitude;
		this.altitude = altitude;
		this.sendDate = sendDate;
		this.sendUid = sendUid;
		this.receiveUid = receiveUid;
		this.reserve = reserve;
	}

	public BussinessBody() {
		super();
	}

	@Override
	public String toString() {
		return "BussinessBody [id=" + id + ", action=" + action + ", title="
				+ title + ", msg=" + msg + ", imagesUrls=" + imagesUrls
				+ ", soundsUrls=" + soundsUrls + ", videosUrls=" + videosUrls
				+ ", longitude=" + longitude + ", altitude=" + altitude
				+ ", sendDate=" + sendDate + ", sendUid=" + sendUid
				+ ", receiveUid=" + receiveUid + ", reserve=" + reserve + "]";
	}
}
