package com.aotain.common.config.model;

import java.io.Serializable;

public class IdcHouses implements Serializable {

	private static final long serialVersionUID = -1789430396367636541L;

	// 经营者许可证号
	private String IdcId;
	// 机房ID
	private Long HouseId;
	// 机房编码
	private String HouseIdStr;
	// 集群ID
	private Integer ClusterId;
	// 机房标识，1-专线、2-非专线
	private Integer Identity;
	// 是否报备，1-未报备、2-已报备
	private Integer isReport;

	public String getIdcId() {
		return IdcId;
	}

	public void setIdcId(String idcId) {
		IdcId = idcId;
	}

	public Long getHouseId() {
		return HouseId;
	}

	public void setHouseId(Long houseId) {
		HouseId = houseId;
	}

	public String getHouseIdStr() {
		return HouseIdStr;
	}

	public void setHouseIdStr(String houseIdStr) {
		HouseIdStr = houseIdStr;
	}

	public Integer getClusterId() {
		return ClusterId;
	}

	public void setClusterId(Integer clusterId) {
		ClusterId = clusterId;
	}

	public Integer getIdentity() {
		return Identity;
	}

	public void setIdentity(Integer identity) {
		Identity = identity;
	}

	public Integer getIsReport() {
		return isReport;
	}

	public void setIsReport(Integer isReport) {
		this.isReport = isReport;
	}

}