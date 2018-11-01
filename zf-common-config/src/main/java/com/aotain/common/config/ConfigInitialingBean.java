package com.aotain.common.config;

import com.aotain.common.config.constant.ConfigRedisConstant;
import com.aotain.common.config.dao.SystemConfigDictMapper;
import com.aotain.common.config.model.SystemConfigDict;
import com.aotain.common.config.redis.BaseRedisService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 将数据库中的配置信息保存到redis中
 * 
 * @author daiyh@aotain.com
 * @date 2017/11/14
 */
@Repository
public class ConfigInitialingBean {

	public static final Logger logger = LoggerFactory.getLogger(ConfigInitialingBean.class);

	@Autowired
	private SystemConfigDictMapper systemConfigDictMapper;

	@Autowired
	private BaseRedisService<String, String, String> baseRedisService;

	/**
	 * 初始化时将数据库配置信息写入redis缓存中
	 */
	@PostConstruct
	public void initConfig() {
		logger.info("Initialize the system configuration start....");
		try {
			initConfiguration();
		} catch (Exception e) {
			logger.info("Initialize the system configuration start exception ....",e);
		}
	}

	private void initConfiguration() {
		logger.info("Initialize the system_config_dict configuration start....");
		List<SystemConfigDict> systemConfigDictList = systemConfigDictMapper.selectConfig();
		if (systemConfigDictList.isEmpty()) {
			logger.info("There is no data in the table [zf_dict_system_config]!");
			return;
		}
		// 将查询的配置信息保存到redis中
		for (SystemConfigDict systemConfigDict : systemConfigDictList) {
			String key = null;
			String value = null;
			// 取出来的key 或者 value为空 则跳过此条数据
			if (StringUtils.isEmpty(systemConfigDict.getConfigKey())) {
				logger.warn("the config param cannot be init,because the key"+systemConfigDict.getConfigKey()+" is null");
				continue;
			}
			if (StringUtils.isEmpty(systemConfigDict.getConfigValue())){
				logger.warn("the config param cannot be init,because the value"+systemConfigDict.getConfigValue()+" is null");
				continue;
			}
			key = systemConfigDict.getConfigKey();
			value = systemConfigDict.getConfigValue();
			baseRedisService.putHash(ConfigRedisConstant.SYSTEM_CONFIG_DICT, key, value);
		}
		logger.info("Initialize the system_config_dict configuration end....");

	}

}
