package com.aotain.common.utils.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {
	
	/**
     * 写日志
     */
    private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	private static final String PATH = "/job/leader";
	private static final String NAME = "client#2";
	private static final String CONNECT_STR = "server-1:2181";//LocalConfig.getInstance().getHashValueByHashKey("zookeeper.connect");//"192.168.5.98:2181";

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
		CuratorFramework client = CuratorFrameworkFactory.builder()
				.connectString(CONNECT_STR)
				.retryPolicy(retryPolicy)
				.sessionTimeoutMs(6000)
				.connectionTimeoutMs(3000)
				.namespace("CU")
				.build();
		client.start();
		
		final LeaderLatchClient curatorClient = new LeaderLatchClient(client, PATH, NAME);
		curatorClient.start();
		
		// 退出程序执行
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {                
                // 停止
            	try {
					curatorClient.close();
				} catch (IOException e) {
					logger.info("curatorClient close exception." ,e);
				}
                logger.info("curatorClient is closed!");
            }
        });
        
        //业务跑起来
        BizThread bizThread = new BizThread(curatorClient);
        bizThread.start();
       /* BizThread bizThread = null;
        while(true){
        	if(curatorClient.isLeader()){
        		System.out.println("##########I am a leader.##########");
        		if(bizThread == null){
        			bizThread = new BizThread(curatorClient);
        			bizThread.start();
        		}
        	}
        	else{
        		System.out.println("##########I am not a leader.######");
        		if(bizThread != null){
	        		bizThread.stopThread();
	        		bizThread = null;
        		}
        		
        	}
        	try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
	
}
