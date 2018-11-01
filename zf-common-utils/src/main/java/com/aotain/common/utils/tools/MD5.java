package com.aotain.common.utils.tools;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	public static String Encrypt(String source) { 
		try { 
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(source.getBytes()); 
			byte b[] = md.digest(); 
			
			return new BASE64Encoder().encodeBuffer(b).replace("\r\n", "").replace("\n", ""); 
		} catch (NoSuchAlgorithmException e) { 
			e.printStackTrace(); 
		} 
		return "";
	}
	
	public static String Encrypt2(byte[] source) { 
		return getBase64(getMd5(source));
	}
	
	public static String getBase64(String source) { 
		try { 
			return new String(org.apache.commons.codec.binary.Base64.encodeBase64(source.getBytes())); 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		return "";
	}
	
	public static String getMd5(byte[] source) { 
		String s = null;
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
				'e', 'f' };
		try {
			MessageDigest md = MessageDigest
					.getInstance("MD5");
			md.update(source);
			byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
			// 用字节表示就是 16 个字节
			char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
			// 所以表示成 16 进制需要 32 个字符
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
				// 转换成 16 进制字符的转换
				byte byte0 = tmp[i]; // 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
				// >>> 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符串

		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;

	}
	
	public static String Encrypt(byte[] source) { 
		try { 
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(source); 
			byte b[] = md.digest(); 
			return new BASE64Encoder().encodeBuffer(b).replace("\r\n", "").replace("\n", ""); 
		} catch (NoSuchAlgorithmException e) { 
			e.printStackTrace(); 
		} 
		return "";
	}
	
	public static String EncryptByGBK(String source) { 
		try { 
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(changeCharset(source,"GBK").getBytes()); 
			byte b[] = md.digest(); 
			
			return new BASE64Encoder().encodeBuffer(b).replace("\r\n", "").replace("\n", ""); 
		} 
		catch (NoSuchAlgorithmException e) { 
			e.printStackTrace(); 
		} 
		catch(UnsupportedEncodingException ex){
			ex.printStackTrace(); 
		}
		return "";
	}
	
	public static String changeCharset(String str, String newCharset)
	   throws UnsupportedEncodingException {
	  if (str != null) {
	   byte[] bs = str.getBytes();
	   return new String(bs, newCharset);
	  }
	  return null;
	 }
}
