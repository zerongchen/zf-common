package com.aotain.common.utils.model.smmsupload;

public class UserBandInfos {

	private long hhid;
	private long userId;
	private long houseId;
	private String distributeTime;
	private Long bandWidth;
	
	public UserBandInfos() {
	}

	public UserBandInfos(long hhid, long userId, long houseId, String distributeTime, Long bandWidth) {
		super();
		this.hhid = hhid;
		this.userId = userId;
		this.houseId = houseId;
		this.distributeTime = distributeTime;
		this.bandWidth = bandWidth;
	}

	public long getHhid() {
		return hhid;
	}

	public void setHhid(long hhid) {
		this.hhid = hhid;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getHouseId() {
		return houseId;
	}

	public void setHouseId(long houseId) {
		this.houseId = houseId;
	}

	public String getDistributeTime() {
		return distributeTime;
	}

	public void setDistributeTime(String distributeTime) {
		this.distributeTime = distributeTime;
	}

	public Long getBandWidth() {
		return bandWidth;
	}

	public void setBandWidth(Long bandWidth) {
		this.bandWidth = bandWidth;
	}
	
}
