package com.aotain.common.utils.model.report;

import com.aotain.common.utils.model.msg.BaseVo;

import java.util.List;

public class MonitorDataReportVo extends BaseVo{

	private String houseidstr;
	private List<MonitorErrorIp> housemonitor;//基础数据异常监测
	private List<MonitorErrorDomain> illagelweb;//违法违规网站
	
	public String getHouseidstr() {
		return houseidstr;
	}
	public void setHouseidstr(String houseidstr) {
		this.houseidstr = houseidstr;
	}
	public List<MonitorErrorIp> getHousemonitor() {
		return housemonitor;
	}
	public void setHousemonitor(List<MonitorErrorIp> housemonitor) {
		this.housemonitor = housemonitor;
	}
	public List<MonitorErrorDomain> getIllagelweb() {
		return illagelweb;
	}
	public void setIllagelweb(List<MonitorErrorDomain> illagelweb) {
		this.illagelweb = illagelweb;
	}
	
	
}
