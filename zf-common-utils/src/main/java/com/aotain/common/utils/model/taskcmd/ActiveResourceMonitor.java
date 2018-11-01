package com.aotain.common.utils.model.taskcmd;

import com.aotain.common.utils.model.msg.BaseVo;

import java.io.Serializable;

public class ActiveResourceMonitor extends BaseVo implements Serializable{

	private long houseid;
	
	private String houseidstr;
	
	private int date;
	
	private int type;

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
