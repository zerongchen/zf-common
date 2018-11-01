package com.aotain.common.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2017/11/14
 */
@Repository
public class BaseRedisDaoImpl<K, H, V> implements BaseRedisDao<K, H, V> {

    @Autowired
    @Qualifier("redisTemplate")
    /**
     * redistemplate是全局唯一的， 子类不要出现对redistemplate的成员变量的设置(比如keyserializer, )
     *
     * @return
     */
    private RedisTemplate<K, V> redisTemplate;

    @Override
    public void saveValue(K key, V v) {
    	redisTemplate.opsForValue().set(key, v);
    }

    public boolean hasKey(K key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Boolean expire( K key, long timeout, TimeUnit unit ) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 当需要更改serializer,可以直接通过connection.set等方法实现
     *
     * @param callback
     * @return
     */
    protected <T> T execute(RedisCallback<T> callback) {
        return redisTemplate.execute(callback);
    }

    /**
     * 获取stringserializer
     */
    protected RedisSerializer<String> getStringSerializer() {
        return redisTemplate.getStringSerializer();
    }

    /**
     * 获取JdkSerializationRedisSerializer
     */
    @SuppressWarnings("unchecked")
    protected <T> RedisSerializer<T> getDefaultSerializer() {
        return (RedisSerializer<T>) redisTemplate.getDefaultSerializer();
    }

    /**
     * 获取stringserializer
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    protected RedisSerializer<String> getKeySerializer() {
        return (RedisSerializer<String>) redisTemplate.getKeySerializer();
    }

    /**
     * 获取jackson2jsonredisserializer
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    protected RedisSerializer<V> getValueSerializer() {
        return (RedisSerializer<V>) redisTemplate.getValueSerializer();
    }

    /**
     * 获取jackson2jsonredisserializer
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    protected RedisSerializer<K> getHashKeySerializer() {
        return (RedisSerializer<K>) redisTemplate.getHashKeySerializer();
    }

    /**
     * 获取jackson2jsonredisserializer
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    protected RedisSerializer<V> getHashValueSerializer() {
        return (RedisSerializer<V>) redisTemplate.getHashValueSerializer();
    }

    @Override
    public HashOperations<K, H, V> getOpsForHash() {
        return redisTemplate.opsForHash();
    }

    @Override
    public ValueOperations<K, V> getOpsForValue() {
        return redisTemplate.opsForValue();
    }

    /**
     * 获取Set的Operations
     *
     * @return
     */
    @Override
    public SetOperations<K, V> getOpsForSet() {
        return redisTemplate.opsForSet();

    }

    /**
     * 获取ZSet的Operations
     *
     * @return
     */
    @Override
    public ZSetOperations<K, V> getOpsForZSet() {
        return redisTemplate.opsForZSet();
    }

    /**
     * 获取List的Operations
     *
     * @return
     */
    @Override
    public ListOperations<K, V> getOpsForList() {
        return redisTemplate.opsForList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public V getHash(K key, H hashKey) {
        return (V) redisTemplate.opsForHash().get(key, hashKey);
    }

    @Override
    public Map<H, V> getHash(K key) {
        return getOpsForHash().entries(key);
    }

    @Override
    public V getList(K key) {
        return redisTemplate.opsForList().index(key, 0);
    }

    @Override
    public V getSet(K key) {
        return redisTemplate.opsForSet().pop(key);
    }

    @Override
    public V getZSet(K key) {
        return null;
    }

    @Override
    public void saveHash(K key, H hashKey, V hashValue) throws DataAccessException {
        redisTemplate.opsForHash().put(key, hashKey, hashValue);
    }

    @Override
    public void saveHashes(K key, Map<? extends H, ? extends V> m) throws DataAccessException {
        redisTemplate.opsForHash().putAll(key, m);
    }

    @Override
    public void updateHash(K key, H hashKey, H oldHashKey, V hashValue) throws DataAccessException {
        if (hasHashKey(key, oldHashKey)) {
            redisTemplate.opsForHash().delete(key, oldHashKey);
            redisTemplate.opsForHash().put(key, hashKey, hashValue);
        }
    }

    @Override
    public void saveList(K key, V listValue) throws DataAccessException {
    	redisTemplate.opsForList().leftPush(key, listValue);
    }

    @Override
    public void delete(K key) throws DataAccessException {
        redisTemplate.delete(key);
    }

    @Override
    public void deleteHash(K key, H hashKey) {
        getOpsForHash().delete(key, hashKey);
    }

    @Override
    public void deleteHashes(K key, H[] hashKeys) {
    	getOpsForHash().delete(key, hashKeys);
    }

    @Override
    public V getValue(final K key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean hasHashKey(K key, H hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey) != null;
    }

    public RedisTemplate<K, V> getRedisTemplate() {
        return redisTemplate;
    }

    @Override
    public long incr(K key) {
        return redisTemplate.boundValueOps(key).increment(1);
    }

    @Override
    public long hincrByHash(K key, H hashKey, Long value) {
        return redisTemplate.opsForHash().increment(key,hashKey,value);
    }

    @Override
    public void sendMessage(String channel, Serializable message) {
        redisTemplate.convertAndSend(channel,message);
    }

    @Override
    public boolean addZSet(K key,V value,double score) {
        return redisTemplate.opsForZSet().add(key,value,score);
    }

    @Override
    public long removeZSet(K key,V value){
        return redisTemplate.opsForZSet().remove(key,value);
    }

    @Override
    public long rightPush(K key,V value) {
        return redisTemplate.opsForList().rightPush(key,value);
    }

    @Override
    public V leftPop(K key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public V rightPop(K key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public long listSize (K key) {
        return redisTemplate.opsForList().size(key);
    }
}
