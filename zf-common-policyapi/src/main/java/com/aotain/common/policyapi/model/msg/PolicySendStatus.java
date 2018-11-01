package com.aotain.common.policyapi.model.msg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
* @ClassName: PolicySendStatus 
* @Description: DPI/EU设备下发状态实体(这里用一句话描述这个类的作用) 
* @author DongBoye
* @date 2018年1月15日 下午5:07:55 
*
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolicySendStatus {
	private Integer probeType; // 0x00(0)：代表DPI设备,0x01(1)：代表EU设备
	private Integer messageType; 
	private Integer messageNo;
	private String ip; // EU IP
	private String policyIp; // 下发策略的policy IP地址
	private Integer status;// 0-正在发送、1-发送成功、2-发送失败

}
