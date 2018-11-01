package com.aotain.common.utils.model.push;

/**
 * 邮件推送消息记录
 *
 * @author liuz@aotian.com
 * @date 2017年8月7日 上午10:59:50
 */
public class EmailPushMessageRecord extends PushMessageRecord {
	private String subject; // 邮件主题
	private String sendData; // 邮件内容

	public String getSendData() {
		return sendData;
	}

	public void setSendData(String sendData) {
		this.sendData = sendData;
	}

	@Override
	public String toString() {
		return "EmailPushMessageRecord [subject=" + subject + ", sendData=" + sendData + ", toString()=" + super.toString() + "]";
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
