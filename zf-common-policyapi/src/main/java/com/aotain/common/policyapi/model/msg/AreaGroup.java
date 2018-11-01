package com.aotain.common.policyapi.model.msg;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 
* @ClassName: AreaGroup 
* @Description: ApplicationFlowStrategy类， 区域组(这里用一句话描述这个类的作用) 
* @author DongBoye
* @date 2018年1月17日 下午4:16:21 
*
 */
@Getter
@Setter
public class AreaGroup implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5276076402385983941L;

	private Long areaGroupId;//等同area_id

    @JSONField(serialize = false)
    private Long messageNo;
    
    @JSONField(serialize = false)
    private String areagroupName;//等同area_name
   
    @JSONField(serialize = false)
    private Byte areaType;//区域类型,0=源区域，1=目的区域

    @JSONField(serialize = false)
    private String areaSubid1;//数据类别
    
    @JSONField(serialize = false)
    private String areaSubid2;//区域类别/运营商ID
    
    @JSONField(serialize = false)
    private String areaSubid3;//省份ID/大洲ID
    
    @JSONField(serialize = false)
    private String areaSubid4;//城域网ID/国家ID

    @JSONField(serialize = true)
    private List<Long> asIds;

    @JSONField(serialize = true)
    private List<String> areaNames;

    @JSONField(serialize = false)
    private List<AreaGroupAS> areaGroupAsList;
    
    @JSONField(serialize = false)
    private String internalAreaStr;//来源
	
    @JSONField(serialize = false)
    private String externalAreaStr;//目的
}
