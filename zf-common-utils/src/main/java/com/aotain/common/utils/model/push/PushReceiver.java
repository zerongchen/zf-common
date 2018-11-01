package com.aotain.common.utils.model.push;

import java.util.List;

/**
 * 推送信息接收者
 *
 * @author liuz@aotian.com
 * @date 2017年8月7日 上午10:43:47
 */
public class PushReceiver {
	private List<String> departmentId; // wechat 部门ID
	private List<String> userId; // wechat 用户ID
	private List<String> phoneReceiver; // 手机号
	private List<String> emailReceiver; // 邮件地址

	public List<String> getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(List<String> departmentId) {
		this.departmentId = departmentId;
	}

	public List<String> getUserId() {
		return userId;
	}

	public void setUserId(List<String> userId) {
		this.userId = userId;
	}

	public List<String> getPhoneReceiver() {
		return phoneReceiver;
	}

	public void setPhoneReceiver(List<String> phoneReceiver) {
		this.phoneReceiver = phoneReceiver;
	}

	public List<String> getEmailReceiver() {
		return emailReceiver;
	}

	public void setEmailReceiver(List<String> emailReceiver) {
		this.emailReceiver = emailReceiver;
	}

	@Override
	public String toString() {
		return "PushReceiver [departmentId=" + departmentId + ", userId=" + userId + ", phoneReceiver=" + phoneReceiver + ", emailReceiver=" + emailReceiver + "]";
	}

	public boolean isEmpty() {
		if (departmentId != null && !departmentId.isEmpty()) {
			return false;
		}
		if (userId != null && !userId.isEmpty()) {
			return false;
		}
		if( phoneReceiver != null && !phoneReceiver.isEmpty()){
			return false;
		}
		if(emailReceiver != null && !emailReceiver.isEmpty()){
			return false;
		}
		return true;
	}
}
