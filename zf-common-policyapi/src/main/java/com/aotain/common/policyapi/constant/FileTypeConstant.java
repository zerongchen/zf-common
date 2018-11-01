package com.aotain.common.policyapi.constant;

import lombok.Getter;

@Getter
public enum FileTypeConstant {
	PROTOCOL("协议特征库",199), 
	WEBLIB("web分类库",200),
	APPLIB("应用名称对应表",201),
	IPLIB("IP地址库",202),
	HFDW("HTTPGET清洗域名白名单",207),
	HFDB("HTTPGET清洗域名黑名单",208),
	HFUB("HTTPGET清洗URL黑名单",209);
	
    private String name;
    private Integer value;
    
    FileTypeConstant(String name,Integer value){
        this.name = name;
        this.value = value;
    }
}
