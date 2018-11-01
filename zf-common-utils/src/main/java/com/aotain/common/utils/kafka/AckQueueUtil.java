package com.aotain.common.utils.kafka;

import com.aotain.common.config.LocalConfig;
import com.aotain.common.utils.model.msg.SmmsAckQueue;
import com.aotain.common.utils.tools.CommonConstant;

import java.util.Map;

/**
 * ack队列操作类
 * @author Administrator
 *
 */
public class AckQueueUtil {

	/**
	 * 添加单条消息到ack队列
	 */
	public static void sendMsgToKafkaAckQueue(SmmsAckQueue ack) {
	    Map<String, Object> conf = LocalConfig.getInstance().getKafkaProducerConf();
	    
		KafkaProducer producer=new KafkaProducer(conf);
        producer.producer(CommonConstant.KAFKA_QUEUE_NAME_ACK,ack.objectToJson());  
    }
	
	/**
	 * 直接添加一条字符串信息到ack队列
	 * @param message
	 */
	public static void sendMsgToKafkaAckQueue(String message) {
		Map<String, Object> conf = LocalConfig.getInstance().getKafkaProducerConf();
		KafkaProducer producer = new KafkaProducer(conf);
		producer.producer(CommonConstant.KAFKA_QUEUE_NAME_ACK, message);
	}
}
