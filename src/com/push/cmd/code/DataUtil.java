package com.push.cmd.code;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.util.Log;

import com.push.exception.ExceptionCode;
import com.push.exception.MyException;
import com.push.model.DataPackage;
import com.push.util.BytesUtility;

/**
 * 报文编解码
 * 
 * @author DRAGON
 * @date 2015年1月23日
 */
public class DataUtil {

	/** logger **/
	private static String tag = "log";

	/**
	 * 顺序写入流
	 * 
	 * @param buf
	 * @param message
	 * @return
	 */
	public static ByteBuf setBuf(ByteBuf buf, DataPackage message) {

		if (null == buf) {
			buf = Unpooled.buffer();
		}

		if (null == message) {
			throw new MyException(ExceptionCode.NULL_POINT);
		}

		buf.writeBytes(BytesUtility.short2Byte(message.getSign()));// 标识
		buf.writeBytes(BytesUtility.int2Byte(message.getLength()));// 长度
		buf.writeBytes(BytesUtility.short2Byte(message.getCode()));// 操作代码|后续动作
		buf.writeBytes(message.getMessage().getBytes());// 数据报文

		return buf;
	}

	/**
	 * 返回通信报文实体
	 * 
	 * @param buf
	 * @return
	 * @throws IOException
	 */
	public static DataPackage getMessage(ByteBuf buf) throws IOException {
		// 1.实例化message
		DataPackage message = new DataPackage();

		// 2.把ByteBuf缓冲区的流读取到byte[]数组
		byte[] bufByte = new byte[buf.readableBytes()];
		buf.readBytes(bufByte);

		Log.i(tag,"bufByte:" + bufByte.length);

		// 3.把byte[]数组放入输入流
		InputStream in = new ByteArrayInputStream(bufByte);

		int signLen = 2;
		byte[] signByte = new byte[signLen];

		int lengthLen = 4;
		byte[] lenghtByte = new byte[lengthLen];

		int codeLen = 2;
		byte[] codeByte = new byte[codeLen];

		// 4.先读取两字节标志
		in.read(signByte, 0, signLen);

		// 5.判断标志
		if ((short) 0xAABB != BytesUtility.byte2Short(signByte)) {
			Log.e(tag,"消息解码错误...");
			return null;
		}

		// 6.读取报文内容长度length
		in.read(lenghtByte, 0, lengthLen);

		// 7.读取操作码code
		in.read(codeByte, 0, codeLen);
		short code = BytesUtility.byte2Short(codeByte);

		// 8.计算出 报文体的长度
		int length = BytesUtility.byte2Int(lenghtByte);
		int messageLength = length - 2 - 4 - 2;

		// 9.读取报文体
		byte[] messageByte = new byte[messageLength];

		messageLength = in.read(messageByte, 0, messageLength);
		String msg = new String(messageByte, "utf-8");
		// 10.设置实体
		message.setLength(length);
		message.setCode(code);
		message.setMessage(msg);

		return message;
	}
}
