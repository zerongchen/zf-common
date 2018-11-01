package com.aotain.common.utils.redis;

import com.aotain.common.config.ContextUtil;
import com.aotain.common.config.redis.BaseRedisService;
import com.aotain.common.utils.model.msg.RedisPolicyAck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolicyAckUtil {

    /**
     * 策略下发的ACK队列
     */
    private static String REDIS_POLICY_ACKQUEUE_KEY = "StrategyACKQueue_%d_%d_%d";
    
    /**
     * redis实例
     */
    @SuppressWarnings("unchecked")
    private static BaseRedisService<String, String, String> rediscluster = ContextUtil.getContext().getBean("baseRedisServiceImpl",BaseRedisService.class);
        
    /**
     * 单例
     */
    private static PolicyAckUtil instance = null;
    
    /**
     * 获得单例
     */
    public synchronized static PolicyAckUtil getInstance(){
        if(instance == null){
            instance = new PolicyAckUtil();
        }
        
        return instance;
    }
    
    /**
     * 设置policy ack
     * @param messageType
     * @param messageNo
     * @param eu_ip
     * @param status
     */
    public void setPolicyAck(int probeType, int messageType, long messageNo, List<String> eu_ip, int status){
        
        String key = String.format(REDIS_POLICY_ACKQUEUE_KEY, probeType, messageType, messageNo);
        
        Map<String, String> ackMap = new HashMap<String, String>();
        
        for(String ip : eu_ip){
            RedisPolicyAck ack = getEuAck(key, ip);
            ack.setStatus(status);            
            
            ackMap.put(ip, ack.objectToJson());                       
        }
        
        rediscluster.putAllHash(key, ackMap);
    }
    
    /**
     * 获得某个EUIP的状态
     * @param key
     * @param ip
     * @return
     */
    private RedisPolicyAck getEuAck(String key, String ip){
        
        String ackstatus = rediscluster.getHash(key, ip);
        
        if(ackstatus == null || ackstatus.length() == 0){
            RedisPolicyAck ack = new RedisPolicyAck();
            ack.setStatus(0);
            ack.setPolicyIp("");
            
            return ack;
        }else{
            return RedisPolicyAck.parseFrom(ackstatus, RedisPolicyAck.class);
        }
    }
        
}
