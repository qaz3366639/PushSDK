package com.push.util;

import java.nio.ByteBuffer;

/**
 *  基础类型 <=>byte[]
 * @author DRAGON
 * 日期 2014年12月23日
 */
public class BytesUtility {
	
	/**
	 * 将short转成byte[2]
	 * @param a
	 * @return
	 */
	public static byte[] short2Byte(short a){
		byte[] b = new byte[2];
		
		b[0] = (byte) (a >> 8);
		b[1] = (byte) (a);
		
		return b;
	}
	
	/**
	 * 将short转成byte[2]
	 * @param a
	 * @param b
	 * @param offset b中的偏移量
	 */
	public static void short2Byte(short a, byte[] b, int offset){
		b[offset] = (byte) (a >> 8);
		b[offset+1] = (byte) (a);
	}
	
	/**
	 * 将byte[2]转换成short
	 * @param b
	 * @return
	 */
	public static short byte2Short(byte[] b){
		return (short) (((b[0] & 0xff) << 8) | (b[1] & 0xff));
	}
	
	/**
	 * 将byte[2]转换成short
	 * @param b
	 * @param offset
	 * @return 
	 */
	public static short byte2Short(byte[] b, int offset){
		return (short) (((b[offset] & 0xff) << 8) | (b[offset+1] & 0xff));
	}

	/**
	 * long转byte[8]
	 * 
	 * @param a
	 * @param b
	 * @param offset
	 *            b的偏移量
	 */
	public static void long2Byte(long a, byte[] b, int offset) {		
		b[offset + 0] = (byte) (a >> 56);
		b[offset + 1] = (byte) (a >> 48);
		b[offset + 2] = (byte) (a >> 40);
		b[offset + 3] = (byte) (a >> 32);

		b[offset + 4] = (byte) (a >> 24);
		b[offset + 5] = (byte) (a >> 16);
		b[offset + 6] = (byte) (a >> 8);
		b[offset + 7] = (byte) (a);
	}

	/**
	 * byte[8]转long
	 * 
	 * @param b
	 * @param offset
	 *            b的偏移量
	 * @return
	 */
	public static long byte2Long(byte[] b, int offset) {
		 return ((((long) b[offset + 0] & 0xff) << 56)
		 | (((long) b[offset + 1] & 0xff) << 48)
		 | (((long) b[offset + 2] & 0xff) << 40)
		 | (((long) b[offset + 3] & 0xff) << 32)
		 
		 | (((long) b[offset + 4] & 0xff) << 24)
		 | (((long) b[offset + 5] & 0xff) << 16)
		 | (((long) b[offset + 6] & 0xff) << 8)
		 | (((long) b[offset + 7] & 0xff) << 0));
	}

	/**
	 * byte[8]转long
	 * 
	 * @param b
	 * @return
	 */
	public static long byte2Long(byte[] b) {
		 return
		 ((b[0]&0xff)<<56)|
		 ((b[1]&0xff)<<48)|
		 ((b[2]&0xff)<<40)|
		 ((b[3]&0xff)<<32)|
		
		 ((b[4]&0xff)<<24)|
		 ((b[5]&0xff)<<16)|
		 ((b[6]&0xff)<<8)|
		 (b[7]&0xff);
	}

	/**
	 * long转byte[8]
	 * 
	 * @param a
	 * @return
	 */
	public static byte[] long2Byte(long a) {
		byte[] b = new byte[4 * 2];

		b[0] = (byte) (a >> 56);
		b[1] = (byte) (a >> 48);
		b[2] = (byte) (a >> 40);
		b[3] = (byte) (a >> 32);
		
		b[4] = (byte) (a >> 24);
		b[5] = (byte) (a >> 16);
		b[6] = (byte) (a >> 8);
		b[7] = (byte) (a >> 0);

		return b;
	}

	/**
	 * byte数组转int
	 * 
	 * @param b
	 * @return
	 */
	public static int byte2Int(byte[] b) {
		return ((b[0] & 0xff) << 24) | ((b[1] & 0xff) << 16)
				| ((b[2] & 0xff) << 8) | (b[3] & 0xff);
	}

	/**
	 * byte数组转int
	 * 
	 * @param b
	 * @param offset
	 * @return
	 */
	public static int byte2Int(byte[] b, int offset) {
		return ((b[offset++] & 0xff) << 24) | ((b[offset++] & 0xff) << 16)
				| ((b[offset++] & 0xff) << 8) | (b[offset++] & 0xff);
	}

	/**
	 * int转byte数组
	 * 
	 * @param a
	 * @return
	 */
	public static byte[] int2Byte(int a) {
		byte[] b = new byte[4];
		b[0] = (byte) (a >> 24);
		b[1] = (byte) (a >> 16);
		b[2] = (byte) (a >> 8);
		b[3] = (byte) (a);

		return b;
	}

	/**
	 * int转byte数组
	 * 
	 * @param a
	 * @param b
	 * @param offset
	 * @return
	 */
	public static void int2Byte(int a, byte[] b, int offset) {		
		b[offset++] = (byte) (a >> 24);
		b[offset++] = (byte) (a >> 16);
		b[offset++] = (byte) (a >> 8);
		b[offset++] = (byte) (a);
	}
	
	/**
	 * 开辟并赋值
	 * @param byteLength
	 * @param intValue
	 * @return
	 */
	public static byte[] intToByteArray(int byteLength, int intValue) {

		return ByteBuffer.allocate(byteLength).putInt(intValue).array();

	}

	/**
	 * 合并
	 * @param array1
	 * @param array2
	 * @return
	 */
	public static byte[] combineByteArray(byte[] array1, byte[] array2) {

		byte[] combined = new byte[array1.length + array2.length];

		System.arraycopy(array1, 0, combined, 0, array1.length);

		System.arraycopy(array2, 0, combined, array1.length, array2.length);

		return combined;

	}
	
	/**
	 * 字符串截取
	 * @param source		-----原字符串
	 * @param startStr		-----开始字符串
	 * @param endStr		-----结束字符串
	 * @return				-----返回截取code
	 * @throws Exception
	 */
	public static String splitStr(String source,String startStr,String endStr) throws Exception{
		
		if (startStr.length()+endStr.length()>source.length()) {
			throw new Exception("截取字符不合法");
		}
		
		String[] tmpArr = source.split(startStr);
		String[] tmpArr1 = tmpArr[1].split(endStr);
		String code = tmpArr1[0];	
		
		return code.trim();
	}
	
	
	
	
	
	
	
}
