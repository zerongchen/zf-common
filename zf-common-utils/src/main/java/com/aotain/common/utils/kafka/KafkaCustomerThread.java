package com.aotain.common.utils.kafka;


import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * kafka消费者线程
 * @author Administrator
 *
 */
public class KafkaCustomerThread implements Runnable {

    /**
     * log4j日志
     */
    private Logger logger = LoggerFactory.getLogger(KafkaCustomerThread.class);

    /**
     * 流
     */
    private KafkaStream<byte[], byte[]> stream;

    /**
     * 线程号
     */
    private int threadNumber;

    /**
     * 回调
     */
    private ICustomerCallback callback;

    public KafkaCustomerThread(KafkaStream<byte[], byte[]> stream, int threadNumber,
                               ICustomerCallback callback) {
        this.stream = stream;
        this.threadNumber = threadNumber;
        this.callback = callback;
    }

    /**
     * 运行
     */
    public void run() {
        // TODO Auto-generated method stub
        logger.info("start consume kafka data, threadNumber:" + threadNumber);

        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        MessageAndMetadata<byte[], byte[]> ct = null;
        while (it.hasNext()) {
            try{
                ct = it.next();
                callback.callback(threadNumber, ct.partition(), ct.offset(), new String(ct.message(), "UTF-8"));
            }
            catch(Exception e){
                logger.error("consume kafka message error!", e);
            }
        }

        logger.info("end consume kafka data, threadNumber:" + threadNumber);
    }
}
