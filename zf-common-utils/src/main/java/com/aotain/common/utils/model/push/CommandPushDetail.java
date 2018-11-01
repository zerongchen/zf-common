package com.aotain.common.utils.model.push;

import java.util.List;

/**
 * 指令告警推送信息详情
 *
 * @author liuz@aotian.com
 * @date 2017年8月4日 下午4:46:39
 */
public class CommandPushDetail {
	// 告警类型
	private Integer type;
	private String typeStr;
	private List<String> houseStrList; // 告警机房
	private List<Long> houseIdList;
	private String message; // 告警原因
	private String commandId; // 指令ID
	private String content; // 指令内容

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public String getCommandId() {
		return commandId;
	}

	public void setCommandId(String commandId) {
		this.commandId = commandId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<String> getHouseStrList() {
		return houseStrList;
	}

	public void setHouseStrList(List<String> houseStrList) {
		this.houseStrList = houseStrList;
	}

	public List<Long> getHouseIdList() {
		return houseIdList;
	}

	public void setHouseIdList(List<Long> houseIdList) {
		this.houseIdList = houseIdList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "CommandPushDetail [type=" + type + ", typeStr=" + typeStr + ", houseStrList=" + houseStrList + ", houseIdList=" + houseIdList + ", message=" + message + ", commandId=" + commandId
				+ ", content=" + content + "]";
	}

}
