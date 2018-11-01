package com.aotain.common.utils.model.msg;

public class AzkabanJobInfo extends BaseVo{

	//Azkaban Url
	private String azkabanurl;
	
	//项目名
	private String projectname;
	
	//项目ID
	private String projectid;
	
	//工作流名称
	private String flowname;
	
	//执行ID，没有立即执行填 0
	private String execid;
	
	//创建时间， UTC时间戳，精确到秒
	private String createtime;

	public String getAzkabanurl() {
		return azkabanurl;
	}

	public void setAzkabanurl(String azkabanurl) {
		this.azkabanurl = azkabanurl;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String getFlowname() {
		return flowname;
	}

	public void setFlowname(String flowname) {
		this.flowname = flowname;
	}

	public String getExecid() {
		return execid;
	}

	public void setExecid(String execid) {
		this.execid = execid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
}
