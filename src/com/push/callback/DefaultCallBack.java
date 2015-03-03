package com.push.callback;

import com.push.model.BindIdMessage;
import com.push.model.BussinessMessage;
import com.push.model.HeartBeatMessage;

/**
 * 回调抽象类
 * @author DRAGON
 * @date  2015-2-10
 */
public abstract class DefaultCallBack implements CallBackDelegate{

	@Override
	public void bindIdCallBack(BindIdMessage message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void heartBeatCallBack(HeartBeatMessage message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void normalQuitCallBack(HeartBeatMessage message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forceQuitCallBack(HeartBeatMessage message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bussinessCallBack(BussinessMessage message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void IMCallBack(BussinessMessage message) {
		// TODO Auto-generated method stub
		
	}
}
