package com.aotain.common.policyapi.model.msg;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 
* @ClassName: UserMessage 
* @Description: 用户组归属策略的用户信息(这里用一句话描述这个类的作用) 
* @author DongBoye
* @date 2018年1月18日 下午5:04:33 
*
 */
@Getter
@Setter
@EqualsAndHashCode
public class UserMessage implements Serializable{/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 6456736576570720238L;
	/**
	 * 1=帐号用户,2=IP地址段用户,3=根据DPI监控链路来设置组,4=根据用户上线时所在BRAS IP地址(NAS_IP_Address)来设置组 
	 */
	private Integer userType;
	
	private String userName;
	 /**
     * 实体转JSON字符串
     * @return
     */
    public String objectToJson() {
        return JSON.toJSONString(this);
    }
    
}
