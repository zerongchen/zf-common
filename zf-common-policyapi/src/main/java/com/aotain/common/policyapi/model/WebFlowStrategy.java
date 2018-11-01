package com.aotain.common.policyapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.policyapi.model.base.BaseVO;
import com.aotain.common.policyapi.model.msg.WebTypeMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * <p>web 流量管理策略</p>
 * @author Chenzr
 *
 key: Strategy_0_2
 field：123
 value: {
     "messageNo":123,
     "messageSequenceNo":122,
     "messageType":133,
     "operationType":1,
     "cType":3,
     "advUrl":“http://www.baidu.com”,
     "cTime":9,
     "cList":[
         {
         "cWebType":4
         },
         {
         "cWebType":6
         }
    ]
 }
 */

@Getter
@Setter
public class WebFlowStrategy extends BaseVO {

    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 5355375904767852851L;

    private Integer cType;

    private String advUrl;

    private long cTime;

    private List<Integer> cWebType;
    
    @JSONField(serialize = false)
    private Integer webFlowId;

}
