package com.aotain.common.utils.model.push;

/**
 * 短信推送记录
 *
 * @author liuz@aotian.com
 * @date 2017年8月7日 上午11:03:06
 */
public class SMSPushMessageRecord extends PushMessageRecord {
	private Integer gatewayType; // 网关类型：1-电信；2-联通；3-移动；4-铁通；9-其他
	private String smgpVersion; // 短信网关协议版本，符合本文件定义的网关协议版本号为“v3.0”
	private String sendData; // 短信内容

	public Integer getGatewayType() {
		return gatewayType;
	}

	public void setGatewayType(Integer gatewayType) {
		this.gatewayType = gatewayType;
	}

	public String getSmgpVersion() {
		return smgpVersion;
	}

	public void setSmgpVersion(String smgpVersion) {
		this.smgpVersion = smgpVersion;
	}

	public String getSendData() {
		return sendData;
	}

	public void setSendData(String sendData) {
		this.sendData = sendData;
	}

	@Override
	public String toString() {
		return "SMSPushMessageRecord [gatewayType=" + gatewayType + ", smgpVersion=" + smgpVersion + ", sendData=" + sendData + ", toString()=" + super.toString() + "]";
	}

}
