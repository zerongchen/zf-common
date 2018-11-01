package com.aotain.common.config.model;

import java.io.Serializable;

public class CommandSmms implements Serializable {

	private static final long serialVersionUID = -1789430396367636541L;
	
	private String cmmandid;
	private int type;
	private long smmsCommandId;
	private long toptaskid;
	
	public String getCmmandid() {
		return cmmandid;
	}
	public int getType() {
		return type;
	}
	public long getSmmsCommandId() {
		return smmsCommandId;
	}
	public long getToptaskid() {
		return toptaskid;
	}
	public void setCmmandid(String cmmandid) {
		this.cmmandid = cmmandid;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setSmmsCommandId(long smmsCommandId) {
		this.smmsCommandId = smmsCommandId;
	}
	public void setToptaskid(long toptaskid) {
		this.toptaskid = toptaskid;
	}
	
}
