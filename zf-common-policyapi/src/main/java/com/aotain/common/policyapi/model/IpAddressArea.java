package com.aotain.common.policyapi.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IpAddressArea extends IpAddressAreaKey implements Serializable {
	private String pareaId;

    private String areaName;

    private Boolean isParent;

	private static final long serialVersionUID = 1L;

}