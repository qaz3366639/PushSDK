package com.push.model;

/**
 * 报文	格式：
 * 		|--------|--------|--------|--------|--------|--------|--------|--------|--------...------|
 *		|  	 sign   	  |	 			length				  | 	 code    	| 	message		  |
 *		|--------|--------|--------|--------|--------|--------|--------|--------|--------...------|
 * @author DRAGON
 * @date 2014年12月22日
 */
public class DataPackage {

	/** 标志 如:AA88 **/
	private short sign = OperatorCode.SIGN;

	/** 报文长度 **/
	private int length;

	/** body类型|命令类型  **/ //例子：0x0010|0x0000,0x0002|0xff00, 	其中高8位	ff表示响应       00表示请求
	private short code;

	/** 报文 **/
	private String message;

	public short getSign() {
		return sign;
	}

	public void setSign(short sign) {
		this.sign = sign;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public short getCode() {
		return code;
	}

	public void setCode(short code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DataPackage(short sign, int length, short code, String message) {
		super();
		this.sign = sign;
		this.length = length;
		this.code = code;
		this.message = message;
	}

	public DataPackage() {
		super();
	}

	@Override
	public String toString() {
		return "DataPackage [sign=" + sign + ", length=" + length + ", code="
				+ code + ", message=" + message + "]";
	}
}
