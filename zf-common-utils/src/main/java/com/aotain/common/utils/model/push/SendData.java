package com.aotain.common.utils.model.push;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendData {

	private String province;
	private String alarmTime;
	private String alarmMessage;
	private String alarmParameter;

	@Override
	public String toString() {
		return  "部署省份：" + (StringUtils.isBlank(province) ? "" : province) + "\n" + 
				"告警时间：" + (StringUtils.isBlank(alarmTime) ? "" : alarmTime) + "\n" + 
			    "告警信息：" + (StringUtils.isBlank(alarmMessage) ? "" : alarmMessage) + "\n" + 
			    "告警项参数：" + (StringUtils.isBlank(alarmParameter) ? "" : alarmParameter);
	}

}
