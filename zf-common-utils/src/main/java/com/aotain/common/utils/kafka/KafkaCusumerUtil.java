package com.aotain.common.utils.kafka;

import com.aotain.common.config.LocalConfig;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KafkaCusumerUtil {
	
	/**
     * log4j日志
     */
    public Logger logger = LoggerFactory.getLogger(KafkaCusumerUtil.class);
	
	// 设置消费者根目录
	private String zkconsumers = "/consumers";

	// 操作zookeeper工具curator 
	private CuratorFramework client;
	
	public KafkaCusumerUtil(){
		this.zkconsumers = LocalConfig.getInstance().getHashValueByHashKey("kafka.path") + "/consumers";
		RetryPolicy retryPolicy = new RetryNTimes(3, 1000);
		this.client = CuratorFrameworkFactory.newClient(
				LocalConfig.getInstance().getHashValueByHashKey("zookeeper.connect"), 
				10000, 
				10000,
				retryPolicy);
	}
	
	/**
	 * 修改制定消费者组下 topic下分区offset
	 * @param groupId 消费者组
	 * @param topic 主题
	 * @param partition 分区
	 * @param offsetvalue offset值
	 */
	public void UpdateOffsetValue(String groupId,String topic,String partition, String offsetvalue) {

		String partitionpath = zkconsumers + "/" + groupId + "/offsets/" + topic + "/" + partition;
		try {
			client.start();
			byte[] newoffset = client.getData().forPath(partitionpath);
			logger.info(partitionpath + ":oldoffset=" + new String(newoffset, "UTF-8"));
			client.setData().forPath(partitionpath, offsetvalue.getBytes());
			newoffset = client.getData().forPath(partitionpath);
			logger.info(partitionpath + ":newoffset=" + new String(newoffset, "UTF-8"));
		} catch (Exception e) {
			logger.error("Failed to update offset.partitionpath = " + partitionpath,e);
		} finally {
			client.close();
		}
	}

	public Map<String,Long> getCusumerPartitionOffsets(String groupId,String topic) {
		
		Map<String,Long> map = new HashMap<String,Long>();
		try {
			client.start();
			String topicPath = zkconsumers + "/" + groupId + "/offsets/" + topic;
			List<String> listpartition = client.getChildren().forPath(topicPath);
			for (String partition : listpartition) {
				String partitionpath = zkconsumers + "/" + groupId + "/offsets/" + topic + "/" + partition;
				byte[] bytOffset = client.getData().forPath(partitionpath);
				long offset = -1;
				String sOffset = new String(bytOffset, "UTF-8");
				if(sOffset != null && sOffset.length()>0) 
					offset = Long.parseLong(sOffset);
				map.put(partition, offset);
			}
			
			return map;
		} catch (Exception e) {
			logger.error("Failed to get partitions offset.groupId = " + groupId + ",topic=" + topic,e);
		}
		finally{
			client.close();
		}
		
		return null;

	}

}
