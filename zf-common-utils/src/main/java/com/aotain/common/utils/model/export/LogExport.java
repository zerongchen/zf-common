package com.aotain.common.utils.model.export;

import com.aotain.common.utils.model.msg.BaseVo;

/**
 * 访问日志导出、监测过滤日志导出、URL去重统计 实体类
 * @author Administrator
 *
 */
public class LogExport extends BaseVo{

	private Long transid;
	private String houseidstr;
	private String sql;
	private Long createtime;
	private Integer logType; //1:访问日志  2：监测过滤日志  3:URL去重统计
	
	public Long getTransid() {
		return transid;
	}
	public void setTransid(Long transid) {
		this.transid = transid;
	}
	public String getHouseidstr() {
		return houseidstr;
	}
	public void setHouseidstr(String houseidstr) {
		this.houseidstr = houseidstr;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public Long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}
	public Integer getLogType() {
		return logType;
	}
	public void setLogType(Integer logType) {
		this.logType = logType;
	}
	
	
}
