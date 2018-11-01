package com.aotain.common.utils.model.msg;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.utils.tools.Tools;
import lombok.Getter;
import lombok.Setter;

/**
 * Redis任务状态 实体类 包括Job任务（管局指令、主动上报指令、导出任务）、管局ACK、文件上报、EU策略、Azkaban任务
 * 
 * @author zouyong
 * @date 2017-11-09
 */
@Getter
@Setter
public class RedisTaskStatus extends BaseVo {

	private Long taskId;
	private Long topTaskId;
	private Integer taskType;
	@JSONField(jsonDirect = true)
	private String content;
	/**  1-开始 2-重试 3-失败 4-成功 5-超时 */
	private Integer status;
	private Integer maxTimes;
	private Integer times = 1;
	private Long expireTime;
	private Integer interval;
	private Long nextTime;
	private Long createTime;
	private String createIp = Tools.getHostAddressAndIp();



	public static void main(String[] args) {
		RedisTaskStatus rs = new RedisTaskStatus();
		rs.setContent("1212");
		rs.setTaskType(1);
		System.out.println(rs.objectToJson());
	}
}
