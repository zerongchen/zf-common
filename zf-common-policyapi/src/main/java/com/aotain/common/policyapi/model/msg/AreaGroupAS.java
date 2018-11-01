package com.aotain.common.policyapi.model.msg;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * 
* @ClassName: AreaGroupAs 
* @Description: ApplicationFlowStrategy类，区域组(这里用一句话描述这个类的作用) 
* @author DongBoye
* @date 2018年1月17日 下午4:15:23 
*
 */
@Getter
@Setter
public class AreaGroupAS implements Serializable{
  
    /**
	 * 
	 */
	private static final long serialVersionUID = -8312676080081082043L;

	@JSONField(serialize = false)
    private Long areagroupId;
    
    @JSONField(serialize = false)
    private Integer asType;

    @JSONField(serialize = false)
    private String asAreaId;
    
    @JSONField(serialize = false)
    private String areaName;
    
    @JSONField(serialize = false)
    private Long areaId;


}
