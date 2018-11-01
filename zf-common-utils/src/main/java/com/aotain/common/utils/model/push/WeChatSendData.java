package com.aotain.common.utils.model.push;

/**
 * 微信消息内容
 *
 * @author liuz@aotian.com
 * @date 2017年8月7日 上午10:54:23
 */
public class WeChatSendData {
	// 发送的消息类型 text（文本）、image（图片）、voice（声音）、video（视频）、
	// file（文件）、textcard（文本卡片）、news（图文）本版本只实现文本消息
	private String msgType;

	private String content; // 消息内容

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "WeChatSendData [msgType=" + msgType + ", content=" + content + "]";
	}

}
