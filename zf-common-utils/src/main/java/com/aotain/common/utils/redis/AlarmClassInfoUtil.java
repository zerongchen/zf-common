package com.aotain.common.utils.redis;

import com.aotain.common.config.ContextUtil;
import com.aotain.common.config.redis.BaseRedisService;
import com.aotain.common.utils.constant.RedisKeyConstant;

public class AlarmClassInfoUtil {

	private static BaseRedisService<String, String, String> rediscluster =
            ContextUtil.getContext().getBean("baseRedisServiceImpl",BaseRedisService.class);

    private static AlarmClassInfoUtil instance = null;

    /**
     * 私有化构造方法
     */
    private AlarmClassInfoUtil(){

    }

    /**
     * 获取单例实例的唯一方法
     * @return
     */
    public static AlarmClassInfoUtil getInstance(){
        if (instance == null) {
            synchronized (AlarmClassInfoUtil.class){
                if (instance == null) {
                    instance = new AlarmClassInfoUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 根据alarmType(提示類型)获取它的版本号
     * @param alarmType
     * @return
     */
    public String getAlarmType(Integer alarmType){
        return rediscluster.getHash(RedisKeyConstant.GLOBAL_CLASSINFO_ALARM, String.valueOf(alarmType));
    }

    /**
     * 根據alarmType设置为提示状态
     * @param alarmType
     */
    public void setToAlarmByType(Integer alarmType){
        rediscluster.putHash(RedisKeyConstant.GLOBAL_CLASSINFO_ALARM,String.valueOf(alarmType),"1");
    }

    /**
     * 根據alarmType 取消提示状态
     * @param alarmType
     */
    public void cancleAlarmByType(Integer alarmType){
        rediscluster.putHash(RedisKeyConstant.GLOBAL_CLASSINFO_ALARM,String.valueOf(alarmType),"0");
    }
}
