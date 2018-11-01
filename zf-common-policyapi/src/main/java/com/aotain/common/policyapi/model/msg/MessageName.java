package com.aotain.common.policyapi.model.msg;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 
* @ClassName: MessageName 
* @Description: 策略名称类(这里用一句话描述这个类的作用) 
* @author DongBoye
* @date 2018年1月15日 下午5:07:04 
*
 */
@Getter
@Setter
public class MessageName {

    private Long messageNo;

    private Integer messageType;

    private String messageName;

    private Integer operationType;

    private Date updateTime;
}
