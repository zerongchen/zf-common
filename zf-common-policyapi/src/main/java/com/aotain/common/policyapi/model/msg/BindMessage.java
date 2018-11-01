package com.aotain.common.policyapi.model.msg;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 
* @ClassName: BindMessage 
* @Description: 见UserPolicyBindStrategy类，绑定消息(这里用一句话描述这个类的作用) 
* @author DongBoye
* @date 2018年1月15日 下午5:04:38 
*
 */
@Getter
@Setter
@EqualsAndHashCode
public class BindMessage implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6086489133735471166L;

	private Integer bindMessageType;

    private Long bindMessageNo;

}
