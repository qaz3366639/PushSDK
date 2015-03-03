package com.push.model;

import java.io.Serializable;

/**
 * 报文头
 * @author DRAGON
 * @date 2015年1月23日
 */
@SuppressWarnings("serial")
public class Head implements Serializable {

	/** 具体操作  **/
	private String action;//表示打开应用(应用UI之外)/在应用内跳转页面 jump/none:表示跳转到浏览器
	
	/** 版本号 **/
	private String version;// 默认1.0

	/** 转换数据类型 **/
	private int dataType;// 0:json 1:xml

	/** 处理级别 **/
	private int level;// 0：一般 1：中等 2：紧急

	/** 签名信息 **/
	private String signedMsg;// 默认为不签名:nosignature

	/** 客户机设备类型 **/
	private String deviceType;// 客户机使用设备类型 (服务器默认为null);mi2s,iphone5s,android,server

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getSignedMsg() {
		return signedMsg;
	}

	public void setSignedMsg(String signedMsg) {
		this.signedMsg = signedMsg;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Head(String action, String version, int dataType, int level,
			String signedMsg, String deviceType) {
		super();
		this.action = action;
		this.version = version;
		this.dataType = dataType;
		this.level = level;
		this.signedMsg = signedMsg;
		this.deviceType = deviceType;
	}

	public Head() {
		super();
	}

	@Override
	public String toString() {
		return "Head [action=" + action + ", version=" + version
				+ ", dataType=" + dataType + ", level=" + level
				+ ", signedMsg=" + signedMsg + ", deviceType=" + deviceType
				+ "]";
	}
	
}
