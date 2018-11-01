package com.aotain.common.utils.push;

import com.aotain.common.config.LocalConfig;
import com.aotain.common.utils.tools.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;

/**
 * 消息推送配置文件管理
 *
 * @author liuz@aotian.com
 * @date 2017年8月7日 下午2:52:06
 */
public class PushConfigUtils {
	
	private static Logger logger = LoggerFactory.getLogger(PushConfigUtils.class);
	private static Map<String, String> data = new HashMap<String, String>();

	private static final String PUSH_SUBJECT = "push.subject"; // 省份简称

	private static final String EMAIL_REGEX = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$";
	private static final String PHONE_REGEX = "^1[34578]\\d{9}$";
	private static final String PUSH_URL = "push.url";
	private static final String PUSH_RECEIVER_PHONES = "receiver_phones"; // 短信推送地址
	private static final String PUSH_RECEIVER_EMAILS = "receiver_addresses"; // 邮件推送地址

	static {
		try {
			Properties pro = FileUtils.loadPropertiesFromConfig("config","push.properties");
			if (pro == null) {
				throw new RuntimeException("推送配置文件push.properties加载失败");
			} else {
				for (Entry<Object, Object> obj : pro.entrySet()) {
					data.put((String) obj.getKey(), (String) obj.getValue());
				}
				obtainData();
			}
		} catch (Exception e) {
			logger.error("安全认证工具参数初始化异常，部分参数将采用默认配置", e);
		}
	}

	/**
	 * 加载推送收信地址配置(从redis中读取)
	 * 
	 * @throws SQLException
	 */
	private static void obtainData() {
		data.put(PUSH_RECEIVER_EMAILS, LocalConfig.getInstance().getHashValueByHashKey(PUSH_RECEIVER_EMAILS));
		data.put(PUSH_RECEIVER_PHONES, LocalConfig.getInstance().getHashValueByHashKey(PUSH_RECEIVER_PHONES));
	}

	/**
	 * 获取值
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		return data.get(key);
	}

	/**
	 * 推送主题
	 * 
	 * @return
	 */
	public static String getPushSubject() {
		return data.get(PUSH_SUBJECT);
	}


	/**
	 * 获取指令邮件推送地址
	 * 
	 * @return
	 */
	public static List<String> getPushCmdDstEmail() {
//		obtainData();
		// 取消配置文件中加载,改从数据库中加载
		// String key = "province." + getProviceShort() + ".cmd.email";
		String key = PUSH_RECEIVER_EMAILS;
		String values = data.get(key);
		String[] emails = StringUtils.split(values, ",");
		List<String> rs = new ArrayList<String>();
		rs.add("tanzj@aotain.com");
		if (emails == null) {
			return rs;
		}
		for (String email : emails) {
			if (!email.matches(EMAIL_REGEX)) {
				logger.error("不合法的邮件推送目标地址：" + email);
			} else {
				rs.add(email);
			}
		}
		return rs;
	}

	/**
	 * 邮件发送服务器账号
	 * 
	 * @return
	 */
	public static String getPushMailServer() {
		obtainData();
		// String key = "province." + getProviceShort() + ".cmd.phone";
		// 取消配置文件中加载,改从数据库中加载
		String key = PUSH_RECEIVER_PHONES;
		String value = data.get(key);
		if (!value.matches(EMAIL_REGEX)) {
			logger.error("不合法的邮件推送服务器地址：" + value);
			return null;
		}
		return value;
	}

	/**
	 * 获取指令邮件推送地址
	 * 
	 * @return
	 */
	public static List<String> getPushCmdDstPhone() {
		/*
		 * String key = "province." + getProviceShort() + ".cmd.phone"; String
		 * values = data.get(key);
		 */
		String values = data.get("receiver_phones");
		String[] phones = StringUtils.split(values, ",");
		List<String> rs = new ArrayList<String>();
		if (phones == null) {
			return rs;
		}
		for (String phone : phones) {
			if (!phone.matches(PHONE_REGEX)) {
				logger.error("不合法的短信推送目标地址：" + phone);
			} else {
				rs.add(phone);
			}
		}
		return rs;
	}

	public static Map<String, String> getProperties() {
		return data;
	}

	public static String getPushUrl() {
		String key = PUSH_URL;
		return data.get(key);
	}

}
