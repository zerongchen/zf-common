package com.aotain.common.utils.kafka;

/**
 * 消费者接口，所有元子需要消费队列，则需要实现该接口
 * @author Administrator
 *
 */
public interface ICustomerCallback {

    /**
     * 回调函数
     * @param threadnum 线程ID
     * @param message 消息
     */
    public void callback(int threadnum, int partition, long offset, String message);
}
