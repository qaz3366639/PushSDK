package com.push.client.cmd;

import io.netty.channel.Channel;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.push.callback.CallBackDelegate;
import com.push.cmd.CommandImpl;
import com.push.model.BussinessMessage;
import com.push.model.DataPackage;
import com.push.model.Head;
import com.push.model.PushAckMessage;
import com.push.model.body.PushAckBody;
import com.push.util.ClientUtil;

/**
 * <p>
 * <p>
 * 处理业务消息
 * <p>
 * 
 * @author DRAGON
 * @date 2014年12月29日
 */
public class ReveiveBussinessCommand extends CommandImpl {

	/** logger **/
	private static String tag = "bussiness";
	

	/** 客户端工具类 **/
	private ClientUtil clientUtil;
	
	@Override
	public <T> void execute(Channel channel, DataPackage message) {
		//Log.d(tag, "接收到服务器端推送的业务消息:\n\n\n\n\n" + message.toString()+ "\n\n\n\n\n");
		BussinessMessage bussinessMessage = JSON.parseObject(message.getMessage(), BussinessMessage.class); 
		
		String sendUid = bussinessMessage.getBody().getSendUid();
		
		clientUtil = ClientUtil.getInstance();
		CallBackDelegate delegate = clientUtil.getDelegate();
		CallBackDelegate imDelegate = clientUtil.getImDelegate();
		if (null != delegate ) {
			if (sendUid.equals("0000")) {//服务器端推送消息
				Log.w(tag, sendUid+"推送回调");
				delegate.bussinessCallBack(bussinessMessage);
			}else {
				imDelegate.IMCallBack(bussinessMessage);
				Log.w(tag, sendUid+"IM推送回调");
			}
		}
		
		//回应服务器
		ackServer(channel, message);
	}
	
	/**
	 * 回应服务器
	 * @param channel
	 * @param message
	 */
	private void ackServer(Channel channel, DataPackage message){
		
		BussinessMessage bMsg=JSON.parseObject(message.getMessage(), BussinessMessage.class);
		
		String uid = bMsg.getBody().getReceiveUid();
		Long msgId = bMsg.getBody().getId();
		String reserve = "接收成功...请服务器去除消息队列消息";
		
		Head head = new Head("none", "1.0", 0, 0, "", android.os.Build.MODEL);
		PushAckBody body = new PushAckBody(uid, msgId, reserve);
		PushAckMessage pushAckMessage = new PushAckMessage(head, body);
		
		String json = JSON.toJSONString(pushAckMessage);
		
		message.setCode((short)2020);//ack
		message.setMessage(json);
		message.setLength(2 + 4 + 2 + json.getBytes().length);
		
		//发送回应消息
		send(channel, message);
		
	}
	
}
