package com.aotain.common.policyapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.policyapi.model.base.BaseVO;
import lombok.Getter;
import lombok.Setter;

/**
 * <P>（通用）流量管理策略</P>
 * @author Chenzr
 *
 * key: Strategy_0_6
 field：123
 value:
{
"messageNo":123,
"messageSequenceNo":122,
"messageType":6,
"operationType":1,
"appType":4,
"appId":12,
"appName":"风行视频",
"appThresholdUp":456,
"appThresholdDown":888,
"cTime":9
}


 */
@Getter
@Setter
public class GeneralFlowStrategy extends BaseVO{
    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 7267120287991572778L;
	private Integer appType;
    private Integer appId;
    private String appName;
    private Long appThresholdUp;
    private Long appThresholdDown;
    private Long cTime;

    @JSONField(serialize = false)
    private Long appflowId;
}
