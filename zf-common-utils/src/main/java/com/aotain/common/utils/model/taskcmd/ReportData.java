package com.aotain.common.utils.model.taskcmd;

import com.aotain.common.utils.model.msg.BaseVo;

import java.util.List;

public class ReportData extends BaseVo {

	private String idcid;
	
	private List<String> userids;
	
	private List<String>  houseids;

	public String getIdcid() {
		return idcid;
	}

	public void setIdcid(String idcid) {
		this.idcid = idcid;
	}

	public List<String> getUserids() {
		return userids;
	}

	public void setUserids(List<String> userids) {
		this.userids = userids;
	}

	public List<String> getHouseids() {
		return houseids;
	}

	public void setHouseids(List<String> houseids) {
		this.houseids = houseids;
	}
	
}
