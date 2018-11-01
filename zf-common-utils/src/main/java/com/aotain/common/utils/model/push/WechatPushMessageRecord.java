package com.aotain.common.utils.model.push;

/**
 * 微信推送接口记录
 *
 * @author liuz@aotian.com
 * @date 2017年8月7日 上午10:41:15
 */
public class WechatPushMessageRecord extends PushMessageRecord {
	private WeChatSendData sendData; // 消息内容

	public WeChatSendData getSendData() {
		return sendData;
	}

	public void setSendData(WeChatSendData sendData) {
		this.sendData = sendData;
	}

	@Override
	public String toString() {
		return "WechatPushMessageRecord [sendData=" + sendData + ", toString()=" + super.toString() + "]";
	}

}
