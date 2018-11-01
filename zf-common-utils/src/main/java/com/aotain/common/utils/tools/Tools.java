package com.aotain.common.utils.tools;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 * 
 * @author Administrator
 * 
 */
public class Tools {

	private static Logger logger = LoggerFactory.getLogger(Tools.class);

	/**
	 * 获得当前服务器的IP地址
	 * 
	 * @return
	 */
	public static String getHostAddress() {
		String ip = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress();
		} catch (Exception e) {
			logger.error("get host address error!", e);
		}
		return ip;
	}
	
	/**
	 * 获取服务器名+IP
	 * @return
	 */
	public static String getHostAddressAndIp() {
		String ip = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			ip = addr.toString();
		} catch (Exception e) {
			logger.error("get host address error!", e);
		}
		return ip;
	}

	/**
	 * 获得当前服务器的主机名
	 * 
	 * @return
	 */
	public static String getHostName() {
		String hostname = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			hostname = addr.getHostName();
		} catch (Exception e) {
			logger.error("get host name error!", e);
		}
		return hostname;
	}

	/**
	 * 获得配置文件路径
	 * 
	 * @return
	 */
	public static String getSystemWorkPath(Class<?> cls) {
		String path = System.getProperty("work.dir");
		if (path == null || path.length() == 0) {
			path = cls.getProtectionDomain().getCodeSource()
					.getLocation().getPath();// Thread.currentThread().getContextClassLoader().getResource("./").getPath();
			// path = path + "../";
			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				path = path.replaceFirst("/", "");
			}
			System.setProperty("work.dir", path);
		}

		return path;
	}

	/**
	 * 判断字符串是否为空或NULL
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (s == null || s.length() <= 0)
			return true;
		else
			return false;
	}

	public static String format2Two(String num) {
		if (num.length() < 2) {
			return "0" + num;
		}
		return num;
	}

	/**
	 * 构造一个批次ID
	 * 
	 * @return
	 */
	public static String getBatchId() {
		String mBatchId = "";
		Calendar cal = Calendar.getInstance();
		mBatchId = "" + cal.get(Calendar.YEAR);
		mBatchId += format2Two(cal.get(Calendar.MONTH) + 1 + "");
		mBatchId += format2Two(cal.get(Calendar.DAY_OF_MONTH) + "");
		mBatchId += format2Two(cal.get(Calendar.HOUR_OF_DAY) + "");
		mBatchId += format2Two(cal.get(Calendar.MINUTE) + "");
		mBatchId += format2Two(cal.get(Calendar.SECOND) + "");
		mBatchId += format2Two(cal.get(Calendar.MILLISECOND) + "");
		mBatchId += getUUIDPrefix();
		return mBatchId;
	}

	public static String getUUIDPrefix() {
		String result = "";
		// 550E8400-E29B-11D4-A716-446655440000
		UUID uuid = UUID.randomUUID();
		String temp = uuid.toString();
		if (temp.contains("-")) {
			result = temp.substring(0, 8);
		}
		return result;
	}

	public static byte[] joinBytes(byte[] srcByte1, byte[] srcByte2) {
		int byte1Length = srcByte1.length;
		int byte2Length = srcByte2.length;
		byte[] retByte = new byte[byte1Length + byte2Length];
		System.arraycopy(srcByte1, 0, retByte, 0, byte1Length);
		System.arraycopy(srcByte2, 0, retByte, byte1Length, byte2Length);
		return retByte;
	}

	public static String getDateStr(long timestamp) {
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date(timestamp);
		return dateFormater.format(date);
	}

	public static String getDatetimeStr(long timestamp) {
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(timestamp);
		return dateFormater.format(date);
	}
	
	public static List<BigInteger> str2List(String mStr, String separator) {
		List<BigInteger> list = new ArrayList<BigInteger>();
		if (mStr != null) {
			String[] strs = mStr.split(separator);
			for (int i = 0; i < strs.length; i++) {
				list.add(new BigInteger(strs[i]));
			}
		}
		return list;
	}
	public static boolean isNumeric(String str) {
		if(str == null) return false;
		Pattern pattern = Pattern.compile("-?[0-9]+");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	

}
