package com.aotain.common.utils.model.report;

import com.aotain.common.utils.model.msg.BaseVo;

public class AllReportVo extends BaseVo{

	private Long houseid;
	private String houseidstr;
	private Integer date;//格式20171114
	private Integer type; //活跃资源监测上报时有效 0=域名，1=IP
	
	public Long getHouseid() {
		return houseid;
	}
	public void setHouseid(Long houseid) {
		this.houseid = houseid;
	}
	public String getHouseidstr() {
		return houseidstr;
	}
	public void setHouseidstr(String houseidstr) {
		this.houseidstr = houseidstr;
	}
	public Integer getDate() {
		return date;
	}
	public void setDate(Integer date) {
		this.date = date;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
}
