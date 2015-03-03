package com.push.cmd;

import io.netty.channel.Channel;
import android.util.Log;

import com.push.exception.ExceptionCode;
import com.push.exception.MyException;
import com.push.model.DataPackage;

/**
 * 错误命令 回复
 * 
 * @author DRAGON
 * @date 2015年1月23日
 */
public class ErrorCommand extends CommandImpl {

	/** logger **/
	private static String tag = "log";

	@Override
	public <T> void execute(Channel channel, DataPackage message) {
		Log.e(tag, "命令为空...");
		throw new MyException(ExceptionCode.CMD_NULL);
	}

}
