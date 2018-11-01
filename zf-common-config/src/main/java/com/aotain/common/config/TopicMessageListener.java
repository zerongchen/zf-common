package com.aotain.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2017/11/15
 */
public class TopicMessageListener implements MessageListener {

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void onMessage(Message message, byte[] pattern) {
        RedisSerializer serializer  = redisTemplate.getValueSerializer();
        String result = (String)serializer.deserialize(message.getBody());
        System.out.println(result+"========");
//        String channel = new String(message.getChannel());
//        String body = new String(message.getBody());
//        System.out.println(serializer+channel+"========"+body);
    }

}
