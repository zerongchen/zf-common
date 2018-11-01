package com.aotain.common.utils.kafka;


import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.StringEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * kafka消费者
 * @author Administrator
 *
 */
public class KafkaCustomer {

    /**
     * log4j日志
     */
    public Logger logger = LoggerFactory.getLogger(KafkaCustomer.class);

    /**
     * 参数， map中需要的key: zookeeper.connect,group.id
     */
    private Map<String, Object> conf = new HashMap<String, Object>();

    /**
     * 消费连接
     */
    private final ConsumerConnector consumer;
    
    /**
     * 多线程执行
     */
    private ExecutorService executor;
    
    /**
     * 构造函数
     * @param conf
     */
    public KafkaCustomer(Map<String, Object> conf) {
        this.conf = conf;
        
        consumer = kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig());
    }
    
    /**
     * 消费队列数据
     * @param callback
     */
    public void customer(String topic, int threadnum, ICustomerCallback callback) {
        // TODO Auto-generated method stub
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, threadnum); // 描述读取哪个topic，需要几个线程读
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap); // 创建Streams
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic); // 每个线程对应于一个KafkaStream

        // now launch all the threads
        //
        executor = Executors.newFixedThreadPool(threadnum);

        // now create an object to consume the messages
        //
        int threadNumber = 0;
        for (final KafkaStream<byte[], byte[]> stream : streams) {
            // 启动consumer
            executor.submit(new KafkaCustomerThread(stream, threadNumber, callback)); 
            // thread
            threadNumber++ ;
        }
    }

    /**
     * kafka配置
     * @return 消费者配置
     */
    private ConsumerConfig createConsumerConfig() {
        Properties props = new Properties();        
//        props.put("zookeeper.session.timeout.ms", "400");
//        props.put("zookeeper.sync.time.ms", "200");
//        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "smallest");
        props.put("serializer.class", StringEncoder.class.getName());
        
        for(Entry<String, Object> entry : conf.entrySet()){
            
            props.put(entry.getKey(), entry.getValue());
        }

        return new ConsumerConfig(props);
    }

    /**
     * 获得活动的线程数
     * @return
     */
    public int getActiveThreadNum(){
        if(executor == null){
            return 0;
        }
        
        return ((ThreadPoolExecutor)executor).getActiveCount();
    }
    
    /**
     * 停止
     */
    public void shutdown() {
        if (consumer != null){
            consumer.shutdown();
        }
        if (executor != null){
            executor.shutdown();
        }
    }
}
