package com.aotain.common.utils.model.smmsupload;

public class UserIpInfos {

	private long ipId;
	private long houseId;
	private long ipType;
	private String unitname;
	private String internetStartIp;
	private String internetEndIp;
	private String netStartIp;
	private String netEndIp;
	
	public UserIpInfos() {
		super();
	}
	
	public UserIpInfos(String internetStartIp, String internetEndIp) {
		super();
		this.internetStartIp = internetStartIp;
		this.internetEndIp = internetEndIp;
	}

	public UserIpInfos(long ipId, long houseId, long ipType, String unitname, String internetStartIp, String internetEndIp) {
		super();
		this.ipId = ipId;
		this.houseId = houseId;
		this.ipType = ipType;
		this.unitname = unitname;
		this.internetStartIp = internetStartIp;
		this.internetEndIp = internetEndIp;
	}
	
	public UserIpInfos(long ipId, long houseId, String unitname, String internetStartIp, String internetEndIp, String netStartIp, String netEndIp) {
		super();
		this.ipId = ipId;
		this.houseId = houseId;
		this.unitname = unitname;
		this.internetStartIp = internetStartIp;
		this.internetEndIp = internetEndIp;
		this.netStartIp = netStartIp;
		this.netEndIp = netEndIp;
	}

	public long getIpId() {
		return ipId;
	}

	public void setIpId(long ipId) {
		this.ipId = ipId;
	}

	public long getHouseId() {
		return houseId;
	}

	public void setHouseId(long houseId) {
		this.houseId = houseId;
	}
	
	public long getIpType() {
		return ipType;
	}

	public void setIpType(long ipType) {
		this.ipType = ipType;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getInternetStartIp() {
		return internetStartIp;
	}

	public void setInternetStartIp(String internetStartIp) {
		this.internetStartIp = internetStartIp;
	}

	public String getInternetEndIp() {
		return internetEndIp;
	}

	public void setInternetEndIp(String internetEndIp) {
		this.internetEndIp = internetEndIp;
	}

	public String getNetStartIp() {
		return netStartIp;
	}

	public void setNetStartIp(String netStartIp) {
		this.netStartIp = netStartIp;
	}

	public String getNetEndIp() {
		return netEndIp;
	}

	public void setNetEndIp(String netEndIp) {
		this.netEndIp = netEndIp;
	}
	
}