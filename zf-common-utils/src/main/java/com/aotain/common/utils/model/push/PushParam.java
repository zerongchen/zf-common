package com.aotain.common.utils.model.push;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Setter
@Getter
public class PushParam {
	private String type;
	private String commandValue;
	private String dailtestValue;
	private String systemValue;
	private String value;

	@Override
	public String toString() {
		return "PushParam [type=" + type + ", commandValue=" + commandValue + ", dailtestValue=" + dailtestValue
				+ ", systemValue=" + systemValue + ", value=" + value + "]";
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
		if (!StringUtils.isBlank(value) && value.contains(",")) {
			String[] vals = value.split(",");
			if (vals.length > 2) {
				this.commandValue = StringUtils.trim(vals[0]);
				this.dailtestValue = StringUtils.trim(vals[1]);
				this.systemValue = StringUtils.trim(vals[2]);
			}
		}
	}
}
