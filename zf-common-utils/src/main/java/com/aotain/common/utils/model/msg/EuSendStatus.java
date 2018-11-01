package com.aotain.common.utils.model.msg;

import lombok.Getter;
import lombok.Setter;

/**
 * EU设备下发状态实体
 * 
 * @author liuz@aotian.com
 * @date 2017年12月22日 下午2:55:33
 */
@Getter
@Setter
public class EuSendStatus {

	/** 0x00(0)：代表DPI设备,0x01(1)：代表EU设备  */
	private Integer probeType;

	private Integer messageType;

	private Integer messageNo;

	/** EU IP */
	private String ip;

	/** 下发策略的policy IP地址 */
	private String policyIp;

	/** 0-正在发送、1-发送成功、2-发送失败 */
	private Integer status;

}
