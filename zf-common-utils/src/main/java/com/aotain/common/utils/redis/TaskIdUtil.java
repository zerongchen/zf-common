package com.aotain.common.utils.redis;

import com.aotain.common.config.ContextUtil;
import com.aotain.common.config.redis.BaseRedisService;
import com.aotain.common.utils.constant.RedisKeyConstant;

/**
 * 工具类，获取全局唯一taskId的方法
 *
 * @author daiyh@aotain.com
 * @date 2018/01/30
 */
public class TaskIdUtil {

    private static BaseRedisService<String, String, Long> rediscluster =
            ContextUtil.getContext().getBean("baseRedisServiceImpl",BaseRedisService.class);

    private static TaskIdUtil instance = null;

    /**
     * 私有化构造方法
     */
    private TaskIdUtil(){

    }

    /**
     * 获取单例实例的唯一方法
     * @return
     */
    public static TaskIdUtil getInstance(){
        if (instance == null) {
            synchronized (TaskIdUtil.class){
                if (instance == null) {
                    instance = new TaskIdUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 获取全局唯一taskId
     * @return
     */
    public Long getTaskId(){
        return rediscluster.incr(RedisKeyConstant.TASK_ID_KEY);
    }
}
