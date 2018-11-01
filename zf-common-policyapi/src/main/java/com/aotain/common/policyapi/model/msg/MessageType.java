package com.aotain.common.policyapi.model.msg;

import lombok.Getter;
import lombok.Setter;

/**
 * 
* @ClassName: MessageType 
* @Description: messageType字典实体类
 *              tableName = zf_v2_policy_messagetype(这里用一句话描述这个类的作用) 
* @author DongBoye
* @date 2018年1月15日 下午5:07:26 
*
 */
@Getter
@Setter
public class MessageType {

    private Integer messageType;

    private String messageTitle;

    private Long messageSequenceNo;

    private Integer flag;
}
