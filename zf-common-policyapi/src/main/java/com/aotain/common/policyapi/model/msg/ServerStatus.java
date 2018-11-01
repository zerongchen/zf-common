package com.aotain.common.policyapi.model.msg;

/**
 * 
* @ClassName: ServerStatus 
* @Description: 服务器状态(这里用一句话描述这个类的作用) 
* @author DongBoye
* @date 2018年1月15日 下午5:08:46 
*
 */
public class ServerStatus {
	private Integer status; // 0-正常,1-异常
	private Long lastUpdateTime; // 反馈此IP最后心跳时间

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Override
	public String toString() {
		return "ServerStatus [status=" + status + ", lastUpdateTime=" + lastUpdateTime + "]";
	}

}
