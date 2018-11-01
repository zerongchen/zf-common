package com.aotain.common.utils.model.msg;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.utils.tools.Tools;
import lombok.Getter;
import lombok.Setter;

/**
 * 通知消息队列对象
 * 
 * @author liuz@aotian.com
 * @date 2017年11月30日 下午4:24:42
 */
@Getter
@Setter
public class NoticeQueueMessage extends BaseVo {
	private static final long serialVersionUID = 7549984222755603207L;

	/** 任务类型:1-任务状态消息，2-EU策略发送状态 */
	private Integer type;
	/** 消息内容，json字符串 */
	private String message;
	/** 创建时间， UTC时间戳，精确到秒 */
	@JSONField(name = "createtime")
	private Long createTime;
	private String createip = Tools.getHostAddressAndIp();

	public NoticeQueueMessage(){
		super();
	}

	public NoticeQueueMessage(int type,String message,long createTime){
		this.type = type;
		this.message = message;
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "NoticeQueueMessage [type=" + type + ", message=" + message + ", createTime=" + createTime
				+ ", createip=" + createip + "]";
	}

	public static void main(String[] args) {
		NoticeQueueMessage noticeQueueMessage = new NoticeQueueMessage(2,"aaa",1512);
		System.out.println(noticeQueueMessage);
	}

}
