package com.aotain.common.policyapi.model.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
* @ClassName: RedisPolicyAck 
* @Description: 策略下发EU ip ACK状态(这里用一句话描述这个类的作用) 
* @author DongBoye
* @date 2018年1月15日 下午5:05:08 
*
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RedisPolicyAck extends BaseVO {
	private static final long serialVersionUID = -1323227362611286469L;

	/**
	 * 0-初始化、1-成功、2-失败
	 */

	private int status;

	// 下发策略的policy IP地址
	private String policyIp;

	/**
	 * 重发次数，默认值0（此字段已废弃）
	 */
	private int retransmissionTimes;

}
