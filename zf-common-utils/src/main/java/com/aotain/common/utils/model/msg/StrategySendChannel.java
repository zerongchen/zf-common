package com.aotain.common.utils.model.msg;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * EU策略下发channel实体
 * @author Administrator
 *
 */
@Setter
@Getter
public class StrategySendChannel extends BaseVo {
    
    /**
     * 
     */
    private static final long serialVersionUID = 991868717214443187L;

    /**
     * 下发的EU IP地址
     */
    private List<String> ip = new ArrayList<String>();

    /**
     * 策略类型， 0-DPI、1-EU
     */
    private int ProbeType; 
    
    private int MessageType;

    @JSONField(jsonDirect = true)
    private String messageContent;

}
