package com.aotain.common.utils.model.report;

import com.aotain.common.utils.model.msg.BaseVo;

public class MonitorErrorDomain extends BaseVo{

	private String ip;
	private Long port;
	private String domain;
	private String servicecontent;
	private Integer illegaltype;
	private String firstfound;
	private String lastfound;
	private Long visitcount;
	private Integer protocol;
	private Integer block;
	private String oprationaccount;
	
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
	public Integer getIllegaltype() {
		return illegaltype;
	}
	public void setIllegaltype(Integer illegaltype) {
		this.illegaltype = illegaltype;
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
	public Integer getBlock() {
		return block;
	}
	public void setBlock(Integer block) {
		this.block = block;
	}
	public String getOprationaccount() {
		return oprationaccount;
	}
	public void setOprationaccount(String oprationaccount) {
		this.oprationaccount = oprationaccount;
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
	public String getServicecontent() {
		return servicecontent;
	}
	public void setServicecontent(String servicecontent) {
		this.servicecontent = servicecontent;
	}
	
	
	
	
}
