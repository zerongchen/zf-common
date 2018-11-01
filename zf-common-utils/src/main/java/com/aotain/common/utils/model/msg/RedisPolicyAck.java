package com.aotain.common.utils.model.msg;

import lombok.Getter;
import lombok.Setter;

/**
 * 策略下发EU ip ACK状态
 * 
 * @author Administrator
 *
 */
@Getter
@Setter
public class RedisPolicyAck extends BaseVo {
	private static final long serialVersionUID = -1323227362611286469L;

	/**
	 * 0-初始化、1-成功
	 */
	private int status;

	/**
	 *  下发策略的policy IP地址
	 */
	private String policyIp;

}
