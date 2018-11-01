package com.aotain.common.policyapi.model.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 
* @ClassName: BaseVO 
* @Description: 策略实体基类(这里用一句话描述这个类的作用) 
* @author DongBoye
* @date 2018年1月15日 下午5:04:26 
*
 */
@Getter
@Setter
@ToString
public class BaseVO implements Serializable {
	
    private static final long serialVersionUID = 1L;

    private Integer messageType;

    private Long messageNo;

    private Long messageSequenceNo;

    /**
     * 策略类型， 0-DPI、1-EU 0
     */
    private Integer probeType;

    /**
     * 操作类型：1-新增、2-修改、3-删除
     */
    private Integer operationType;

    @JSONField(serialize = false)
    private Long topTaskId;
    
    @JSONField(serialize = false)
    private String messageName;

    /**
     * 实体转JSON字符串
     * @return
     */
    public String objectToJson() {
        return JSON.toJSONString(this);
    }
    
    /**
     *  字符串转消息实体
     * @param json
     * @param cls
     * @return
     */
    public static <T extends BaseVO> T parseFromJson(String json, Class<T> cls) {
    	return JSON.parseObject(json,cls);
    }
}
