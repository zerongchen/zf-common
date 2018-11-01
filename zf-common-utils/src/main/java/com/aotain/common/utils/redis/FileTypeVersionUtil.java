package com.aotain.common.utils.redis;

import com.aotain.common.config.ContextUtil;
import com.aotain.common.config.redis.BaseRedisService;
import com.aotain.common.utils.constant.RedisKeyConstant;

public class FileTypeVersionUtil {
	
	private static BaseRedisService<String, String, Long> rediscluster =
            ContextUtil.getContext().getBean("baseRedisServiceImpl",BaseRedisService.class);

    private static FileTypeVersionUtil instance = null;

    /**
     * 私有化构造方法
     */
    private FileTypeVersionUtil(){

    }

    /**
     * 获取单例实例的唯一方法
     * @return
     */
    public static FileTypeVersionUtil getInstance(){
        if (instance == null) {
            synchronized (FileTypeVersionUtil.class){
                if (instance == null) {
                    instance = new FileTypeVersionUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 根据fileType获取它的版本号
     * @param messageType
     * @return
     */
    public Long getFileType(Integer fileType){
    	String key =  String.format(RedisKeyConstant.CLASS_FILE_TYPE,fileType);
        return rediscluster.hincrByHash(key, String.valueOf(fileType), 1L);
    }
}
