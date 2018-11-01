package com.aotain.common.utils.model.report;

import com.aotain.common.utils.model.msg.BaseVo;

public class PolicyGroupVo extends BaseVo{
	
	private Long groupid;
	private Integer operationtype; //1=新增，3=删除，4=重发
	public Long getGroupid() {
		return groupid;
	}
	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}
	public Integer getOperationtype() {
		return operationtype;
	}
	public void setOperationtype(Integer operationtype) {
		this.operationtype = operationtype;
	}
	
}
