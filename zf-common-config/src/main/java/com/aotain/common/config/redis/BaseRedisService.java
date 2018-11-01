package com.aotain.common.config.redis;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis操作封转类
 * @author Jason.CW.Cheung
 * @date 2017年11月17日 上午9:26:05
 * @version 1.0
 * @param <K>	Redis的key值
 * @param <H>	Redis的hash结构的field
 * @param <V>	Redis的value值
 */
public interface BaseRedisService<K, H, V> {

    V getHash(K key, H hashKey);
    
    Map<H, V> getHashs(K key);

    V getHashValueByHashKey(K key, H hashKey);

    void putHash(K key, H hashKey, V value);

    void putAllHash(K key, Map<? extends H, ? extends V> m);

    Boolean expire(K key, long timeout, TimeUnit unit);
    
    void removeHash(K key, H hashKey);

    void removeHashes(K key, H[] hashKeys);

    V getString(K key);

    void putString(K key, V value);

    void remove(K key);
    
    long incr(K key);
    
    long hincrByHash(K key, H field, Long increment);

    void sendMessage(String channel, Serializable message);

    boolean addZSet(K key, V value, double score);

    long removeZSet(K key, V value);

    /**
     * 往链表尾部(右边)中增加一个元素
     * @param key
     * @param value
     * @return
     */
    long rightPush(K key, V value);

    /**
     * 从链表头部(左边)取出一个元素
     * @param key
     * @return
     */
    V leftPop(K key);

    /**
     * 从链表尾部(右边)取出一个元素
     * @param key
     * @return
     */
    V rightPop(K key);

    /**
     * 统计当前list长度
     * @param key
     * @return
     */
    long listSize(K key);
    
}
