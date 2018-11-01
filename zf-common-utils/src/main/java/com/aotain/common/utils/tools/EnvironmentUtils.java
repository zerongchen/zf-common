package com.aotain.common.utils.tools;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统环境工具
 * 
 * @author liuz@aotian.com
 * @date 2017年12月4日 下午12:24:41
 */
public class EnvironmentUtils {
//	/** 环境变量-CU日志存放目录定义 */
//	private static final String CU_HOME = "CU_HOME";
//	/** Log4j配置文件所在目录、或者路径 **/
//	public static final String LOG4J_PATH = "log4j.path";
//	/** 用户目录 */
//	public static final String WORK_PATH = "work.path";
//
////	private static final String DEFAULT_LOG4J_NAME = "log4j.properties";
//
//	static {
//		// 设置后可让log4j配置中可通过${CU_LOG_HOME}访问环境变量值
//		String env =  System.getenv(CU_HOME);
//		if(env != null){
//			System.setProperty(CU_HOME,env);
//		}
//	}
//
//	public static void init(Map<String, String> config) {
//		if (config == null) {
//			return;
//		}
//		// 1. 配置Log4j环境
//		String value = config.get(LOG4J_PATH);
//		if (!StringUtils.isBlank(value)) {
//			File file = new File(value);
//			if (file.exists()) {
//				if (file.isDirectory()) {
//					String path = file.getAbsolutePath();
//					if (!path.endsWith("/") && path.endsWith("\\")) {
//						System.out.println("System.out log4j configure is : "+path + DEFAULT_LOG4J_NAME);
//						PropertyConfigurator.configure(path + DEFAULT_LOG4J_NAME);
//					} else {
//						System.out.println("System.out log4j configure is : "+path+File.separator  + DEFAULT_LOG4J_NAME);
//						PropertyConfigurator.configure(path + File.separator + DEFAULT_LOG4J_NAME);
//					}
//				} else {
//					System.out.println("System.out log4j configure is : "+file.getAbsolutePath());
//					PropertyConfigurator.configure(file.getAbsolutePath());
//				}
//			}else{
//				System.out.println("System.out log4j configure is default configure file");
//			}
//		}
//
//		// 2. 配置用户目录
//		value = config.get(WORK_PATH);
//		if (!StringUtils.isBlank(value)) {
//			System.setProperty(WORK_PATH,value);
//			System.out.println("System.out work.path is : "+new File(value).getAbsolutePath());
//		}
//	}
//
//	public static class ConfigBuilder {
//		private Map<String, String> config;
//
//		public ConfigBuilder append(String key, String value) {
//			if(config == null){
//				config = new HashMap<String, String>();
//			}
//			config.put(key, value);
//			return this;
//		}
//
//		public Map<String,String> build(){
//			return config;
//		}
//	}
}
