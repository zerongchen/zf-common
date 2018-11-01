package com.aotain.common.utils.model.push;

import lombok.Data;

@Data
public class PushMessageRecord {
	private Long pushId;
	private Integer pushType;//推送类型，1-邮件、2-短信、3-微信
	private String returnAddress; // 返回ack接收地址
	private String timeStamp;     // 时间戳
	private PushReceiver receiver; // 推送目标

	@Override
	public String toString() {
		return "PushMessageRecord [pushId=" + pushId + ", returnAddress=" + returnAddress + ", timeStamp=" + timeStamp + ", receiver=" + receiver+ "]";
	}

}
