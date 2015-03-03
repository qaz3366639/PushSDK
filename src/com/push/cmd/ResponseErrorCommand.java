package com.push.cmd;

import io.netty.channel.Channel;

import android.util.Log;

import com.push.exception.ExceptionCode;
import com.push.exception.MyException;
import com.push.model.DataPackage;
import com.push.model.OperatorCode;

/**
 * 错误命令 回复
 * 
 * @author DRAGON
 * @date 2015年1月23日
 */
public class ResponseErrorCommand extends CommandImpl {
	/** logger **/
	private static String tag = "log";

	@Override
	public <T> void execute(Channel channel, DataPackage message) {

		// 设置解码失败代码，其他按原来返回
		message.setCode(OperatorCode.DECODE_ERROR);
		super.send(channel, message);

		Log.e(tag, "解码错误...");
		throw new MyException(ExceptionCode.DECODE_FAIL);
	}

}
