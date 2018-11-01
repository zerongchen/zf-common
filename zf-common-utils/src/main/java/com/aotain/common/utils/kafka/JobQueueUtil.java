package com.aotain.common.utils.kafka;

import com.aotain.common.config.LocalConfig;
import com.aotain.common.utils.model.msg.JobQueue;
import com.aotain.common.utils.tools.CommonConstant;

import java.util.Map;

public class JobQueueUtil {

	/**
	 * 添加单条消息到job队列
	 */
	public static void sendMsgToKafkaJobQueue(JobQueue job) {

		Map<String, Object> conf = LocalConfig.getInstance().getKafkaProducerConf();

		KafkaProducer producer = new KafkaProducer(conf);
		producer.producer(CommonConstant.KAFKA_QUEUE_NAME_JOB, job.objectToJson());
	}

	/**
	 * 直接添加一条字符串信息到Job队列
	 * @param message
	 */
	public static void senedMsgToKafkaJobQueue(String message) {
		Map<String, Object> conf = LocalConfig.getInstance().getKafkaProducerConf();
		KafkaProducer producer = new KafkaProducer(conf);
		producer.producer(CommonConstant.KAFKA_QUEUE_NAME_JOB, message);
	}

}
