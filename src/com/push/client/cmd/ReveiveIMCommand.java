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
 * 接收IM消息
 * <p>
 * 
 * @author DRAGON
 * @date 2014年12月29日
 */
public class ReveiveIMCommand extends CommandImpl {

	/** logger **/
	private static String tag = "log";

	/** 客户端工具类 **/
	private ClientUtil clientUtil;
	
	@Override
	public <T> void execute(Channel channel, DataPackage message) {
		//Log.d(tag, "接收IM消息:\n\n\n\n\n" + message.toString() + "\n\n\n\n\n");

		BussinessMessage bussinessMessage = JSON.parseObject(message.getMessage(), BussinessMessage.class); 
		
		clientUtil = ClientUtil.getInstance();
		CallBackDelegate delegate = clientUtil.getImDelegate();
		if (null != delegate ) {
			delegate.IMCallBack(bussinessMessage);
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
