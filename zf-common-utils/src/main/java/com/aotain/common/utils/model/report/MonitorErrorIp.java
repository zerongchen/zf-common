package com.aotain.common.utils.model.report;

import com.aotain.common.utils.model.msg.BaseVo;

public class MonitorErrorIp extends BaseVo{

	private String ip;
	private Long port;
	private String domain;
	private Integer servicetype;
	private String firstfound;
	private String lastfound;
	private Long visitcount;
	private Integer protocol;
	private Integer illegaltype;
	private Integer currentstate;
	private String user;
	private Integer regerror;
	private String regdomain;
	
	private Long reportHouseId;
	private String houseStr;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Long getPort() {
		return port;
	}
	public void setPort(Long port) {
		this.port = port;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public Integer getServicetype() {
		return servicetype;
	}
	public void setServicetype(Integer servicetype) {
		this.servicetype = servicetype;
	}
	public String getFirstfound() {
		return firstfound;
	}
	public void setFirstfound(String firstfound) {
		this.firstfound = firstfound;
	}
	public String getLastfound() {
		return lastfound;
	}
	public void setLastfound(String lastfound) {
		this.lastfound = lastfound;
	}
	public Long getVisitcount() {
		return visitcount;
	}
	public void setVisitcount(Long visitcount) {
		this.visitcount = visitcount;
	}
	public Integer getProtocol() {
		return protocol;
	}
	public void setProtocol(Integer protocol) {
		this.protocol = protocol;
	}
	public Integer getIllegaltype() {
		return illegaltype;
	}
	public void setIllegaltype(Integer illegaltype) {
		this.illegaltype = illegaltype;
	}
	public Integer getCurrentstate() {
		return currentstate;
	}
	public void setCurrentstate(Integer currentstate) {
		this.currentstate = currentstate;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Integer getRegerror() {
		return regerror;
	}
	public void setRegerror(Integer regerror) {
		this.regerror = regerror;
	}
	public String getRegdomain() {
		return regdomain;
	}
	public void setRegdomain(String regdomain) {
		this.regdomain = regdomain;
	}
	public String getHouseStr() {
		return houseStr;
	}
	public void setHouseStr(String houseStr) {
		this.houseStr = houseStr;
	}
	public Long getReportHouseId() {
		return reportHouseId;
	}
	public void setReportHouseId(Long reportHouseId) {
		this.reportHouseId = reportHouseId;
	}
	
	
	
}
