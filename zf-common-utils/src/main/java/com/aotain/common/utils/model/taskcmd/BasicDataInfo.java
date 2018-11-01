package com.aotain.common.utils.model.taskcmd;

import com.aotain.common.utils.model.msg.BaseVo;

public class BasicDataInfo extends BaseVo{

	
	private ReportData reportData;
	
	private Integer operationType;
	
	
	private Integer report_type;



	public Integer getReport_type() {
		return report_type;
	}

	public void setReport_type(Integer report_type) {
		this.report_type = report_type;
	}

	public ReportData getReportData() {
		return reportData;
	}

	public void setReportData(ReportData reportData) {
		this.reportData = reportData;
	}

	public Integer getOperationType() {
		return operationType;
	}

	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}
	
	
}
