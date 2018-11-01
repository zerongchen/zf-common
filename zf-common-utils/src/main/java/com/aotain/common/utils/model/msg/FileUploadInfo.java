package com.aotain.common.utils.model.msg;

import java.util.Arrays;

/**
 * 文件上报信息
 * 
 * @author liuz@aotian.com
 * @date 2017年12月2日 下午4:39:04
 */
public class FileUploadInfo extends BaseVo {
	private static final long serialVersionUID = 5200257412843860665L;

	private String filename; // 文件名
	private String ip;	// 服务器局域网IP
	private String filepath; // 文件在上报目录下的全路径
	private Integer status; // 文件状态，初始化-1， 处理状态见ISMI接口
	private Long createtime; // 上报时间
	private Integer[] recordcount; // 文件上报记录数
	private String dealresult; // 处理结果描述

	public String getDealresult() {
		return dealresult;
	}

	public void setDealresult(String dealresult) {
		this.dealresult = dealresult;
	}

	public Integer[] getRecordcount() {
		return recordcount;
	}

	public void setRecordcount(Integer[] recordcount) {
		this.recordcount = recordcount;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "FileUploadInfo [filename=" + filename + ", ip=" + ip + ", filepath=" + filepath + ", status=" + status
				+ ", createtime=" + createtime + ", recordcount=" + Arrays.toString(recordcount) + ", dealresult="
				+ dealresult + "]";
	}

}
