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
 * @Date : 2018年7月18日 上午9:27:59
 */
package com.aotain.common.utils.model.etl;

public class EtlMsg {

	/**
	 * 消息类型
		1-	文件预处理——文件处理消息
		2-	文件预处理——发送kafka异常消息
		3-	数据清洗——数据格式错误消息
	 */
	int msgType;
	/**
	 * 处理机器IP
	 */
	String sendIp;
	/**
	 * 创建时间， UTC格式
	 */
	long createtime;
	/**
	 * 消息内容，json字符串
	 */
	String message;
	public int getMsgType() {
		return msgType;
	}
	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}
	public String getSendIp() {
		return sendIp;
	}
	public void setSendIp(String sendIp) {
		this.sendIp = sendIp;
	}
	public long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "EtlMessage [msgType=" + msgType + ", sendIp=" + sendIp + ", createtime=" + createtime + ", message="
				+ message + "]";
	}
	
}
