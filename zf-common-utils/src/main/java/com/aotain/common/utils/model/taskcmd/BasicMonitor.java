package com.aotain.common.utils.model.taskcmd;

import com.aotain.common.utils.model.msg.BaseVo;

import java.io.Serializable;

public class BasicMonitor extends BaseVo implements Serializable {

	private static final long serialVersionUID = -7792554745250068495L;
	
	private long houseid;
	
	private String houseidstr;
	
	private int date;

	public long getHouseid() {
		return houseid;
	}

	public void setHouseid(long houseid) {
		this.houseid = houseid;
	}

	public String getHouseidstr() {
		return houseidstr;
	}

	public void setHouseidstr(String houseidstr) {
		this.houseidstr = houseidstr;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}
	
	

}
