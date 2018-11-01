package com.aotain.common.config.model;

import java.io.Serializable;

public class EuAttributeInfo implements Serializable {

	private static final long serialVersionUID = -1789430396367636541L;

	private String euip;
	private int probeType;
	private String areaId;
	private String softwareProvider;
	

	public String getEuip() {
		return euip;
	}
	public void setEuip(String euip) {
		this.euip = euip;
	}
	public int getProbeType() {
		return probeType;
	}
	public String getAreaId() {
		return areaId;
	}
	public String getSoftwareProvider() {
		return softwareProvider;
	}
	public void setProbeType(int probeType) {
		this.probeType = probeType;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public void setSoftwareProvider(String softwareProvider) {
		this.softwareProvider = softwareProvider;
	}

	@Override
	public String toString() {
		return "EuAttributeInfo{" +
				"euip='" + euip + '\'' +
				", probeType=" + probeType +
				", areaId='" + areaId + '\'' +
				", softwareProvider='" + softwareProvider + '\'' +
				'}';
	}
}
