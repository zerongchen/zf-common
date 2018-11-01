package com.aotain.common.utils.model.report;

import com.aotain.common.utils.model.msg.BaseVo;

public class IllegalWebPolicyMessage extends BaseVo{

	//0-新增；1-删除
	private int operationtype;
	//1-域名  2-IP
	private int type;
	//域名或 IP 地址
	private String contents;
	private Long filterId;
	private Long blackId; 
	public int getOperationtype() {
		return operationtype;
	}
	public void setOperationtype(int operationtype) {
		this.operationtype = operationtype;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Long getFilterId() {
		return filterId;
	}
	public void setFilterId(Long filterId) {
		this.filterId = filterId;
	}
	public Long getBlackId() {
		return blackId;
	}
	public void setBlackId(Long blackId) {
		this.blackId = blackId;
	}
	
	
}
