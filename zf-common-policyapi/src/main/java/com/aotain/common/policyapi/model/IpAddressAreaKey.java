package com.aotain.common.policyapi.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IpAddressAreaKey implements Serializable {
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 8704389909787649306L;

	private Byte areaType;

    private String areaId;
    
}