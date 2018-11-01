package com.aotain.common.config.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

public class ClusterHouses implements Serializable {

	private static final long serialVersionUID = -1789430396367636541L;
	
	@JSONField(name="clusterId")
	private Integer clusterId;
	
	private Long hosueId;
	
	@JSONField(name="userName")
	private String userName;
	
	@JSONField(name="password")
	private String password;
	
	@JSONField(name="hiveDriver")
	private String hiveDriver;
	
	@JSONField(name="hiveUrl")
	private String hiveUrl;
	
	@JSONField(name="impalaDriver")
	private String impalaDriver;
	
	@JSONField(name="impalaUrl")
	private String impalaUrl;
	
	@JSONField(name="redisInfo")
	private String redisInfo;
	
	@JSONField(name="namenodeInfo")
	private String namenodeInfo;
	
	@JSONField(name="azkabanUrl")
	private String azkabanUrl;
	
	public Integer getClusterId() {
		return clusterId;
	}

	public void setClusterId(Integer clusterId) {
		this.clusterId = clusterId;
	}

	public Long getHosueId() {
		return hosueId;
	}

	public void setHosueId(Long hosueId) {
		this.hosueId = hosueId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHiveDriver() {
		return hiveDriver;
	}

	public void setHiveDriver(String hiveDriver) {
		this.hiveDriver = hiveDriver;
	}

	public String getHiveUrl() {
		return hiveUrl;
	}

	public void setHiveUrl(String hiveUrl) {
		this.hiveUrl = hiveUrl;
	}

	public String getImpalaDriver() {
		return impalaDriver;
	}

	public void setImpalaDriver(String impalaDriver) {
		this.impalaDriver = impalaDriver;
	}

	public String getImpalaUrl() {
		return impalaUrl;
	}

	public void setImpalaUrl(String impalaUrl) {
		this.impalaUrl = impalaUrl;
	}

	public String getRedisInfo() {
		return redisInfo;
	}

	public void setRedisInfo(String redisInfo) {
		this.redisInfo = redisInfo;
	}


	public String getNamenodeInfo() {
		return namenodeInfo;
	}

	public void setNamenodeInfo(String namenodeInfo) {
		this.namenodeInfo = namenodeInfo;
	}

	public String getAzkabanUrl() {
		return azkabanUrl;
	}

	public void setAzkabanUrl(String azkabanUrl) {
		this.azkabanUrl = azkabanUrl;
	}
	
}