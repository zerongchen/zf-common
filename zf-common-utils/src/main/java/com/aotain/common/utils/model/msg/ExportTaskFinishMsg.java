package com.aotain.common.utils.model.msg;

import java.util.List;

public class ExportTaskFinishMsg {

	/**
	 * 导出流水ID
	 */
	private long transid;
	/**
	 * 所属任务ID
	 */
	private long toptaskid;
	/**
	 * 状态：0-成功；1-失败
	 */
	private int status = -1;
	/**
	 * 导出文件信息数组
	 */	
	private List<FileInfo> fileinfo;
	
	
	public long getTransid() {
		return transid;
	}

	public long getToptaskid() {
		return toptaskid;
	}

	public List<FileInfo> getFileinfo() {
		return fileinfo;
	}

	public void setTransid(long transid) {
		this.transid = transid;
	}

	public void setToptaskid(long toptaskid) {
		this.toptaskid = toptaskid;
	}



	public void setFileinfo(List<FileInfo> fileinfo) {
		this.fileinfo = fileinfo;
	}



	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}



	public static class FileInfo{
		/**
		 * 导出文件路径
		 */	
		private String filepath;
		/**
		 * 导出文件记录条数
		 */	
		private long recordcount;
		/**
		 * 导出时间
		 */	
		private long createtime;
		public String getFilepath() {
			return filepath;
		}
		public long getRecordcount() {
			return recordcount;
		}
		public long getCreatetime() {
			return createtime;
		}
		public void setFilepath(String filepath) {
			this.filepath = filepath;
		}
		public void setRecordcount(long recordcount) {
			this.recordcount = recordcount;
		}
		public void setCreatetime(long createtime) {
			this.createtime = createtime;
		}
		
	}
}
