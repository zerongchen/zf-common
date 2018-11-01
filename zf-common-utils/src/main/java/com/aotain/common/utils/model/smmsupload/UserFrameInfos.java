package com.aotain.common.utils.model.smmsupload;

public class UserFrameInfos {

	private long id;
	private long houseId;
	private Long frameId;
	private String unitname;
	
	public UserFrameInfos() {
	}
	
	public UserFrameInfos(long id, long houseId, Long frameId, String unitname) {
		super();
		this.id = id;
		this.houseId = houseId;
		this.frameId = frameId;
		this.unitname = unitname;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getHouseId() {
		return houseId;
	}

	public void setHouseId(long houseId) {
		this.houseId = houseId;
	}

	public Long getFrameId() {
		return frameId;
	}

	public void setFrameId(Long frameId) {
		this.frameId = frameId;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

}
