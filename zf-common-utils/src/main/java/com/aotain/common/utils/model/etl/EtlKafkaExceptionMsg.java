/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Description : 
 * ---------------------------------
 * @Author : kuangbin
 * @Date : 2018年7月18日 上午10:01:41
 */
package com.aotain.common.utils.model.etl;

public class EtlKafkaExceptionMsg {

	String fileName;	//文件名
	long fileId;		//文件ID
	long recordCount;	//记录数
	String saveFilename;//转存文件名
	long happenTime;		//发生时间， UTC格式
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public long getFileId() {
		return fileId;
	}
	public void setFileId(long fileId) {
		this.fileId = fileId;
	}
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public String getSaveFilename() {
		return saveFilename;
	}
	public void setSaveFilename(String saveFilename) {
		this.saveFilename = saveFilename;
	}
	public long getHappenTime() {
		return happenTime;
	}
	public void setHappenTime(long happenTime) {
		this.happenTime = happenTime;
	}
	@Override
	public String toString() {
		return "EtlKafkaExceptionMessage [fileName=" + fileName + ", fileId=" + fileId + ", recordCount=" + recordCount
				+ ", saveFilename=" + saveFilename + ", happenTime=" + happenTime + "]";
	}
	
}
