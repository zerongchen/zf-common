package com.aotain.common.config.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2017/11/14
 */
public interface BaseRedisDao<K, H, V> {
	
    V getValue(K key);

    V getHash(K key, H hashKey);

    V getList(K key);

    V getSet(K key);

    V getZSet(K key);

    Map<H, V> getHash(K key);

    Boolean expire(K key, long timeout, TimeUnit unit);

    void saveValue(K key, V value) throws DataAccessException;

    void saveHash(K key, H hashKey, V hashValue) throws DataAccessException;

    void saveHashes(K key, Map<? extends H, ? extends V> m) throws DataAccessException;

    void saveList(K key, V listValue) throws DataAccessException;

    void delete(K key) throws DataAccessException;

    void deleteHashes(K key, H[] hashKeys);

    void deleteHash(K key, H hashKey);

    /**
     * 更新hash，机制：删掉原先的key:value重填
     *
     * @param key
     * @param oldHashKey
     * @param hashKey
     * @param hashValue
     * @throws DataAccessException
     */
    void updateHash(K key, H oldHashKey, H hashKey, V hashValue) throws DataAccessException;

    boolean hasHashKey(K key, H hashKey);

    /**
     * 获取HashOperations
     *
     * @return
     */
    HashOperations<K, H, V> getOpsForHash();

    /**
     * 获取String的Operations
     *
     * @return
     */
    ValueOperations<K, V> getOpsForValue();

    /**
     * 获取Set的Operations
     *
     * @return
     */
    SetOperations<K, V> getOpsForSet();

    /**
     * 获取ZSet的Operations
     *
     * @return
     */
    ZSetOperations<K, V> getOpsForZSet();

    /**
     * 获取List的Operations
     *
     * @return
     */
    ListOperations<K, V> getOpsForList();

    /**
     * string类型值加1
     */
    long incr(K key);

    /**
     * 将hash中指定字段值加/减一个常亮
     * @param key
     * @param hashKey
     * @param value
     * @return
     */
    long hincrByHash(K key, H hashKey, Long value);


    /**
     * 使用redis发布消息
     * @param channel
     * @param message
     */
    void sendMessage(String channel, Serializable message);

    /**
     * 往ZSet中添加元素
     * @param key
     * @param value
     * @param score
     * @return
     */
    boolean addZSet(K key, V value, double score);

    /**
     * 删除ZSet中的元素
     * @param key
     * @param value
     * @return
     */
    long removeZSet(K key, V value);

    /**
     * 往链表尾部(右边)中增加一个元素
     * @param key
     * @param value
     * @return
     */
    long rightPush(K key, V value);

    /**
     * 从链表左边取出一个元素
     * @param key
     * @return
     */
    V leftPop(K key);

    /**
     * 从链表右边取出一个元素
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
