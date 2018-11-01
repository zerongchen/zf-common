package com.aotain.common.utils.redis;

import com.aotain.common.config.ContextUtil;
import com.aotain.common.config.redis.BaseRedisService;
import com.aotain.common.utils.constant.RedisKeyConstant;

/**
 * 工具类，根据messageType获取messageNo的方法
 *
 * @author daiyh@aotain.com
 * @date 2018/01/30
 */
public class MessageNoUtil {

    private static BaseRedisService<String, String, Long> rediscluster =
            ContextUtil.getContext().getBean("baseRedisServiceImpl",BaseRedisService.class);

    private static MessageNoUtil instance = null;

    /**
     * 私有化构造方法
     */
    private MessageNoUtil(){

    }

    /**
     * 获取单例实例的唯一方法
     * @return
     */
    public static MessageNoUtil getInstance(){
        if (instance == null) {
            synchronized (MessageNoUtil.class){
                if (instance == null) {
                    instance = new MessageNoUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 根据messageType获取messageNo
     * @param messageType
     * @return
     */
    public Long getMessageNo(int messageType){
        return rediscluster.hincrByHash(RedisKeyConstant.MESSAGE_NO_KEY, String.valueOf(messageType), 1L);
    }
    
   /**
    * 
   * @Title: getLastMessageNo 
   * @Description: 获取上次的messageNo(这里用一句话描述这个方法的作用) 
   * @param @param messageType
   * @param @return    设定文件 
   * @return Long    返回类型 
   * @throws
    */
    public Long getLastMessageNo(int messageType){
        return rediscluster.hincrByHash(RedisKeyConstant.MESSAGE_NO_KEY, String.valueOf(messageType), 0L);
    }

}
