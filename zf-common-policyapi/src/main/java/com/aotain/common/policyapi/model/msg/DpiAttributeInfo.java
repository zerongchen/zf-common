package com.aotain.common.policyapi.model.msg;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DpiAttributeInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8551076812773440084L;
	private String euip;
	private int probeType;
	private String areaId;
	private String softwareProvider;
}
