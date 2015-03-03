package com.push.callback;


import com.push.model.BindIdMessage;
import com.push.model.BussinessMessage;
import com.push.model.HeartBeatMessage;


/**
 * 消息回调
 * @author DRAGON
 * @date  2015-2-5
 */
public interface CallBackDelegate {
	
	/**
	 * 绑定ID
	 * @param message
	 */
	public void bindIdCallBack(BindIdMessage message);
	
	/**
	 * 心跳
	 * @param message
	 */
	public void heartBeatCallBack(HeartBeatMessage message);
	
	/**
	 * 正常退出
	 * @param message
	 */
	public void normalQuitCallBack(HeartBeatMessage message);
	
	/**
	 * 强制退出
	 * @param message
	 */
	public void forceQuitCallBack(HeartBeatMessage message);
	
	/**
	 * 业务消息推送
	 * @param message
	 */
	public void bussinessCallBack(BussinessMessage message);
	
	
	/**
	 * IM消息回调
	 * @param message
	 */
	public void IMCallBack(BussinessMessage message);
	
	
	
}
