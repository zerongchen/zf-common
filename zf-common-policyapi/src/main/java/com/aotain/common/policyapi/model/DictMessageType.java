package com.aotain.common.policyapi.model;

import lombok.Getter;
import lombok.Setter;

/**
 * messageType字典实体类
 * tableName = DPI_V1_DICT_MESSAGETYPE
 * @author daiyh@aotain.com
 * @date 2018/01/02
 */
@Getter
@Setter
public class DictMessageType {

    private Integer messageType;

    private String messageTitle;

    private Long messageSequenceNo;

    private Integer flag;
}
