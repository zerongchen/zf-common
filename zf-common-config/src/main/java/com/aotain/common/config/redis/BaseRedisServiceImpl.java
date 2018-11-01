package com.aotain.common.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Jason.CW.Cheung
 * @date 2017年11月17日 上午9:27:16
 * @version 1.0
 */
@Service
public class BaseRedisServiceImpl<K, H, V> implements BaseRedisService<K, H, V> {

    @Autowired
    private BaseRedisDaoImpl<K, H, V> redisDao;

    @Override
    public V getHash(K key, H hashKey) {
        return redisDao.getHash(key, hashKey);
    }

    @Override
    public V getHashValueByHashKey(K key, H hashKey) {
    	return redisDao.getHash(key, hashKey);
        //return redisDao.getHash("idc_jdcm_jkcs_config", hashKey);
    }

    @Override
    public void putHash(K key, H hashKey, V value) {
        redisDao.getOpsForHash().put(key, hashKey, value);
    }


    @Override
	public void putAllHash(K key, Map<? extends H, ? extends V> m) {
    	redisDao.getOpsForHash().putAll(key, m);
	}

    @Override
    public Boolean expire( K key, long timeout, TimeUnit unit ) {
        return redisDao.expire(key,timeout,unit);
    }

    @Override
    public void removeHash(K key, H hashKey) {
        redisDao.getOpsForHash().delete(key, hashKey);
    }

    @Override
    public void removeHashes(K key, H[] hashKeys) {
        redisDao.getOpsForHash().delete(key, hashKeys);
    }

    @Override
    public V getString(K key){
        return redisDao.getValue(key);
    }

    @Override
    public void putString(K key, V value) {
        redisDao.getOpsForValue().set(key, value);
    }

    @Override
    public void remove(K key) {
        redisDao.delete(key);
    }

    @Override
    public long incr(K key) {
        return redisDao.incr(key);
    }

    @Override
    public long hincrByHash(K key, H field, Long increment) {
        return redisDao.hincrByHash(key,field,increment);
    }

    @Override
    public void sendMessage(String channel, Serializable message){
        redisDao.sendMessage(channel,message);
    }

	@Override
	public Map<H, V> getHashs(K key) {
		return redisDao.getHash(key);
	}

	@Override
    public boolean addZSet(K key,V value,double score) {
        return redisDao.addZSet(key,value,score);
    }

    @Override
    public long removeZSet(K key,V value){
        return redisDao.removeZSet(key,value);
    }

    @Override
    public long rightPush(K key,V value) {
        return redisDao.rightPush(key,value);
    }

    @Override
    public V leftPop(K key) {
        return redisDao.leftPop(key);
    }

    @Override
    public V rightPop(K key) {
        return redisDao.rightPop(key);
    }

    @Override
    public long listSize (K key) {
        return redisDao.listSize(key);
    }
}
