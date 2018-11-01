package com.aotain.common.utils.model.msg;

import com.aotain.common.utils.tools.Tools;

/**
 * Kafka消息队列-ACK队列-实体类
 * @author zouyong
 * @date 2017-11-09
 */
public class SmmsAckQueue extends BaseVo{

	public static final String KAFKA_TOPIC_SMMSACKQUEUE = "smmsackqueue";
	private Long taskid;
	private Long toptaskid;
	private String ackxml;
	private Long createtime;
	private String createip = Tools.getHostAddressAndIp();
	
	public String getCreateip() {
		return createip;
	}
	public void setCreateip(String createip) {
		this.createip = createip;
	}
	public Long getTaskid() {
		return taskid;
	}
	public void setTaskid(Long taskid) {
		this.taskid = taskid;
	}
	public Long getToptaskid() {
        return toptaskid;
    }
    public void setToptaskid(Long toptaskid) {
        this.toptaskid = toptaskid;
    }
	public String getAckxml() {
		return ackxml;
	}
	public void setAckxml(String ackxml) {
		this.ackxml = ackxml;
	}
	public Long getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }
}
