package com.aotain.common.utils.kafka;

import com.aotain.common.config.LocalConfig;
import com.aotain.common.utils.model.msg.NoticeQueueMessage;
import com.aotain.common.utils.tools.CommonConstant;

import java.util.Map;

/**
 * 通知消息队列工具类
 * 
 * @author liuz@aotian.com
 * @date 2017年11月30日 下午4:37:20
 */
public class NoticeQueueUtils {
	
	/**
	 * 添加单条消息对象到通知消息队列
	 */
	public static void sendMessage(NoticeQueueMessage message) {
		Map<String, Object> conf = LocalConfig.getInstance().getKafkaProducerConf();
		KafkaProducer producer = new KafkaProducer(conf);
		producer.producer(CommonConstant.KAFKA_QUEUE_NAME_NOTICE, message.objectToJson());
	}
	
	/**
	 * 添加单条消息json string到通知消息队列
	 */
	public static void sendMessage(String messageJsonStr) {
		Map<String, Object> conf = LocalConfig.getInstance().getKafkaProducerConf();
		KafkaProducer producer = new KafkaProducer(conf);
		producer.producer(CommonConstant.KAFKA_QUEUE_NAME_NOTICE, messageJsonStr);
	}
	
}
