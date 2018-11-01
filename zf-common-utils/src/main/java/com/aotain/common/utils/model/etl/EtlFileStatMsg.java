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
 * @Date : 2018年7月18日 上午9:54:10
 */
package com.aotain.common.utils.model.etl;

public class EtlFileStatMsg {

	String fileName;	//文件名
	Long fileId;		//文件ID
	String version;		//版本号
	String module;		//模块名称
	String fileType;	//文件类型
	String location;	//设备部署位置
	String vendor;		//上报厂家名
	String devNo;		//上报设备编号
	String fileTime;	//上报日期
	int hasOkFile;		//对应OK文件是否存在  0-不存在,1-存在
	long fileSize;		//文件大小
	long fileLastModified;	//文件最后修改时间
	long dealStartTime;	//处理开始时间
	long dealEndTime;	//处理结束时间
	int dealResult;		//处理结果 0-处理成功 ,1-解压失败2-
	long totalRecord;	//总记录数
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getDevNo() {
		return devNo;
	}
	public void setDevNo(String devNo) {
		this.devNo = devNo;
	}
	public String getFileTime() {
		return fileTime;
	}
	public void setFileTime(String fileTime) {
		this.fileTime = fileTime;
	}
	public int getHasOkFile() {
		return hasOkFile;
	}
	public void setHasOkFile(int hasOkFile) {
		this.hasOkFile = hasOkFile;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public long getFileLastModified() {
		return fileLastModified;
	}
	public void setFileLastModified(long fileLastModified) {
		this.fileLastModified = fileLastModified;
	}
	public long getDealStartTime() {
		return dealStartTime;
	}
	public void setDealStartTime(long dealStartTime) {
		this.dealStartTime = dealStartTime;
	}
	public long getDealEndTime() {
		return dealEndTime;
	}
	public void setDealEndTime(long dealEndTime) {
		this.dealEndTime = dealEndTime;
	}
	public int getDealResult() {
		return dealResult;
	}
	public void setDealResult(int dealResult) {
		this.dealResult = dealResult;
	}
	public long getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}
	@Override
	public String toString() {
		return "EtlFileStatMessage [fileName=" + fileName + ", fileId=" + fileId + ", version=" + version + ", module="
				+ module + ", fileType=" + fileType + ", location=" + location + ", vendor=" + vendor + ", devNo="
				+ devNo + ", fileTime=" + fileTime + ", hasOkFile=" + hasOkFile + ", fileSize=" + fileSize
				+ ", fileLastModified=" + fileLastModified + ", dealStartTime=" + dealStartTime + ", dealEndTime="
				+ dealEndTime + ", dealResult=" + dealResult + ", totalRecord=" + totalRecord + "]";
	}

}
