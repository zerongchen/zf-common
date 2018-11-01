package com.aotain.common.utils.monitorstatistics;

import com.aotain.common.utils.tools.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 定时任务线程监控
 * 
 * @author liuz@aotian.com
 * @date 2017年12月26日 下午2:57:30
 */
@Component
public class QuartzThreadMonitorInterceptor {
	private Logger logger = LoggerFactory.getLogger(QuartzThreadMonitorInterceptor.class);
	private List<ThreadTaskConfig> cfgList = null;

	public QuartzThreadMonitorInterceptor() {
		cfgList = loadThreadTasks();
	}

	/**
	 * 统计线程异常情况
	 */
	public int countErrorThreads() {
		// 监控每一个
		int errorCount = 0;
		for (ThreadTaskConfig cfg : cfgList) {
			long lastExecTime = cfg.getLastExecTime();
			long interval = cfg.getInterval() * 1000;
			if (lastExecTime == -1) {
				lastExecTime = System.currentTimeMillis();
				cfg.setLastExecTime(System.currentTimeMillis());
			}
			long relInterval = System.currentTimeMillis() - lastExecTime;
			if (relInterval > interval) {
				errorCount++;
				logger.error("quartz thread task  execute timeout :" + cfg.getKey());
			}
		}
		return errorCount;
	}

	private List<ThreadTaskConfig> loadThreadTasks() {
		List<ThreadTaskConfig> list = new ArrayList<ThreadTaskConfig>();
		Properties p = FileUtils.loadPropertiesFromConfig("config", "tmonitor");
		if (p == null) {
			return list;
		}

		String tsStr = p.getProperty("tmonitor.threads");
		if (StringUtils.isBlank(tsStr)) {
			return list;
		}
		String[] ts = StringUtils.split((String) tsStr, ",");
		if (ts == null || ts.length == 0) {
			return list;
		}

		for (String t : ts) {
			String key = p.getProperty("tmonitor.thread." + t + "." + "key");
			if (StringUtils.isBlank(key)) {
				continue;
			}
			String type = p.getProperty("tmonitor.thread." + t + "." + "type");
			if (StringUtils.isBlank(type)) {
				logger.warn("skip empty type - quartz thread configure information fail :" + key);
				continue;
			}
			String method = p.getProperty("tmonitor.thread." + t + "." + "method");
			if (StringUtils.isBlank(method)) {
				logger.warn("skip empty method - quartz thread configure information fail :" + key);
				continue;
			}
			String intervalStr = p.getProperty("tmonitor.thread." + t + "." + "interval");
			if (StringUtils.isBlank(intervalStr)) {
				logger.warn("skip empty interval - quartz thread configure information fail :" + key);
				continue;
			}
			try {
				ThreadTaskConfig cfg = new ThreadTaskConfig();
				cfg.setKey(key);
				cfg.setMethod(method);
				cfg.setType(Class.forName(type));
				cfg.setInterval(Long.parseLong(intervalStr));
				list.add(cfg);
				logger.info("register quartz thread monitor : " + cfg.toString());
			} catch (Exception e) {
				logger.error("load quartz thread configure information fail :" + key, e);
				continue;
			}
		}
		return list;
	}

	/**
	 * 拦截器方法
	 * 
	 * @param joinPoint
	 */
	public void doBefore(JoinPoint joinPoint) {
		Class<?> type = joinPoint.getSignature().getDeclaringType();
		String method = joinPoint.getSignature().getName();
		// 查找注册信息，得到线程KEY
		ThreadTaskConfig cfg = getConfig(type, method);
		if (cfg == null) {
			return;
		}
		String key = cfg.getKey();

		// 更新换成
		logger.info("do before for quartz thread task , key=" + key);
		cfg.setLastExecTime(System.currentTimeMillis()); // 设置最近执行时间
	}

	private ThreadTaskConfig getConfig(Class<?> type, String method) {
		for (ThreadTaskConfig cfg : cfgList) {
			if (cfg.getType().isAssignableFrom(type) && cfg.getMethod().equals(method)) {
				return cfg;
			}
		}
		return null;
	}

	/**
	 * 线程任务配置信息
	 * 
	 * @author liuz@aotian.com
	 * @date 2017年12月26日 下午3:05:05
	 */
	public static class ThreadTaskConfig {
		private String key;
		private Class<?> type;
		private String method;
		private long interval; // 执行间隔时间(单位秒)
		private long lastExecTime = -1; // 上次执行时间（单位毫秒）,-1表示还未执行过一次

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public Class<?> getType() {
			return type;
		}

		public void setType(Class<?> type) {
			this.type = type;
		}

		public String getMethod() {
			return method;
		}

		public void setMethod(String method) {
			this.method = method;
		}

		public long getInterval() {
			return interval;
		}

		public void setInterval(long interval) {
			this.interval = interval;
		}

		public long getLastExecTime() {
			return lastExecTime;
		}

		public void setLastExecTime(long lastExecTime) {
			this.lastExecTime = lastExecTime;
		}

		@Override
		public String toString() {
			return "ThreadTaskConfig [key=" + key + ", type=" + type + ", method=" + method + ", interval=" + interval
					+ ", lastExecTime=" + lastExecTime + "]";
		}

	}
}
