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
 * @Date : 2018年7月18日 上午10:06:34
 */
package com.aotain.common.utils.model.etl;

public class EtlRowErrorMsg {
	
	String fileName;	//文件名
	long fileId;		//文件ID
	String fieldName;	//字段名
	String Content;		//记录内容
	String errInfo;		//错误信息
	int errCode;		//错误代码:1-字段错误,2-IP错误,3-域名错误,4-时间错误,5-url错误
	int happenTime;		//发生时间， UTC格式
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
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getErrInfo() {
		return errInfo;
	}
	public void setErrInfo(String errInfo) {
		this.errInfo = errInfo;
	}
	public int getErrCode() {
		return errCode;
	}
	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
	public int getHappenTime() {
		return happenTime;
	}
	public void setHappenTime(int happenTime) {
		this.happenTime = happenTime;
	}
	@Override
	public String toString() {
		return "EtlRowErrorMessage [fileName=" + fileName + ", fileId=" + fileId + ", fieldName=" + fieldName
				+ ", Content=" + Content + ", errInfo=" + errInfo + ", errCode=" + errCode + ", happenTime="
				+ happenTime + "]";
	}

}
