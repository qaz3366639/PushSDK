package com.push.exception;

import android.util.Log;

/**
 * 我的全局消息异常
 * 
 * @author DRAGON
 * @date 2015年1月23日
 */
@SuppressWarnings("serial")
public class MyException extends RuntimeException {

	/** logger **/
	private static String tag = "log";

	/**
	 * 根据代码处理
	 * 
	 * @param code
	 */
	public MyException(ExceptionCode code) {
		// done 根据代码处理
		Log.e(tag, "异常代码:" + code.toString());
	}

	/**
	 * 
	 */
	public MyException() {
	}

	/**
	 * @param message
	 */
	public MyException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public MyException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public MyException(String message, Throwable cause) {
		super(message, cause);
	}

}
