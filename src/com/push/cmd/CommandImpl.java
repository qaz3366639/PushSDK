package com.push.cmd;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import android.util.Log;

import com.push.cmd.code.DataUtil;
import com.push.exception.ExceptionCode;
import com.push.exception.MyException;
import com.push.model.DataPackage;


/**
 * 实现类
 * @author DRAGON
 * @date 2015年1月23日
 */
public abstract class CommandImpl implements Command{
	
	/** logger **/
	private static String tag = "log";
	
	@Override
	public ChannelFuture send(Channel channel, DataPackage message) {
		ByteBuf buf = Unpooled.buffer();
		buf = DataUtil.setBuf(buf, message);
		
		try {
			return channel.writeAndFlush(buf).sync();
		} catch (InterruptedException e) {
			Log.e(tag,"发送消息失败:" + e.getMessage());
			throw new MyException(ExceptionCode.SEND_FAIL);
		}
	}
}
