package com.aotain.common.policyapi.model;


import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.policyapi.model.base.BaseVO;
import com.aotain.common.policyapi.model.msg.AreaGroup;

import lombok.Getter;
import lombok.Setter;

/**
 * 
* @ClassName: ApplicationFlowStrategy 
* @Description: 应用流量流向策略(这里用一句话描述这个类的作用) 
 * @data 
{
	"messageNo": 123,
	"messageSequenceNo": 122,
	"messageType": 69,
	"operationType": 1,
	"internalAreaGroupInfo": [{
			"areaGroupId": "124",
			"asIds": [123, 124],
			"areaNames": ["CutE7gm7", "Afc455D"]
		},
		{
			"areaGroupId": "125",
			" asIds": [123, 124],
			"areaNames": ["CutE7gm7", "Afc455D"]
		}
	],
	"externalAreaGroupInfo": [{
		"areaGroupId": "130",
		" asIds": [123, 124],
		"areaNames": ["CutE7gm7", "Afc455D"]
	}]
}
* @author DongBoye
* @date 2018年1月17日 下午4:21:07 
*
 */
@Getter
@Setter
public class ApplicationFlowStrategy extends BaseVO {
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = -1266117673314059691L;
	
	private List<AreaGroup> internalAreaGroupInfo;//来源
	
    private List<AreaGroup> externalAreaGroupInfo;//目的
    
    @JSONField(serialize = false)
    private String internalAreaGroupInfoStr;//来源
	
    @JSONField(serialize = false)
    private String externalAreaGroupInfoStr;//目的
    
    @JSONField(serialize = false)
    private String modifyTime;
    
    @JSONField(serialize = false)
    private String createyTime;
    
    
    @JSONField(serialize = false)
    private String modifyOper;
    
    @JSONField(serialize = false)
    private List<IpAddressArea> internalIdcGroupList;//IDC来源
    
    @JSONField(serialize = false)
    private List<IpAddressArea> internalAreaGroupList;//城域网来源
    
    @JSONField(serialize = false)
    private List<IpAddressArea> externalAreaGroupList;//城域网目的
    
    @JSONField(serialize = false)
    private List<IpAddressArea> externalIdcGroupList;//IDC目的
    
    @JSONField(serialize = false)
    private List<IpAddressArea> externalCommunicationsGroupList;//运营商目的
    
    @JSONField(serialize = false)
    private String internalIdcGroupJson;//IDC来源
    
    @JSONField(serialize = false)
    private String internalAreaGroupJson;//城域网来源
    
    @JSONField(serialize = false)
    private String externalAreaGroupJson;//城域网目的
    
    @JSONField(serialize = false)
    private String externalIdcGroupJson;//IDC目的
    
    @JSONField(serialize = false)
    private String externalCommunicationsGroupJson;//运营商目的
    
    //    应用策略下发成功数/下发异常数
    @JSONField(serialize = false)
    private String policyCount;

    //   流量上报 绑定策略下发成功数/下发异常数
    @JSONField(serialize = false)
    private String bindPolicyCount;
    
}
