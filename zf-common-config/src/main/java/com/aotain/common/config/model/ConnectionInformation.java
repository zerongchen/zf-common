package com.aotain.common.config.model;

import java.io.Serializable;

public class ConnectionInformation implements Serializable {
	private static final long serialVersionUID = -6569525014734319264L;

	private Integer clusterId;
	private String clusterName;
	private String idcName;
	private String userName;
	private String password;
	private String hiveDriver;
	private String hiveUrl;
	private String impalaDriver;
	private String impalaUrl;
	private String redisInfo;
	private String namenodeInfo;
	private String azkabanUrl;

	public ConnectionInformation() {
	}

	public Integer getClusterId() {
		return clusterId;
	}

	public void setClusterId(Integer clusterId) {
		this.clusterId = clusterId;
	}

	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getIdcName() {
		return idcName;
	}

	public void setIdcName(String idcName) {
		this.idcName = idcName;
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
