package com.aotain.common.policyapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.policyapi.model.base.BaseVO;

import com.aotain.common.policyapi.model.msg.VoipFlowManageIp;
import com.aotain.common.policyapi.model.msg.VoipGwKeeperIpMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * <p>VOIP流量管理策略</p>
 *  @author Chenzr
 *
 *  --format
 *key: Strategy_0_5
 field：123
 value:
     {
     "messageNo":123,
     "messageSequenceNo":122,
     "messageType":133,
     "operationType":1,
     "interfereType":1,
     "interfereDir":2,
     "cTime":10,
     "voipGwIpList":[
             {
             "voipGwIp":"192.168.1.199"
             },
             {
             "voipGwIp":"192.168.1.198"
             }
     ],
     "voipGwKeeperIpList":[
             {
             "voipGwKeeperIp":"192.168.1.2"
             },
             {
             "voipGwKeeperIp":"192.168.1.12"
             }
        ],
    }
 *
 */
@Getter
@Setter
@Table(name="zf_v2_voipflow_manage")
public class VoipFlowStrategy extends BaseVO{

    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 7481465230335435633L;

	@JSONField(serialize = false)
	private Integer voipFlowId;

    @JSONField(serialize = false)
	private String createOper;

    @JSONField(serialize = false)
	private String modifyOper;

    @JSONField(serialize = false)
	private Long rStartTime;

    @JSONField(serialize = false)
	private Long rEndTime;

    @JSONField(serialize = false)
    private Date createTime;

    @JSONField(serialize = false)
    private Date modifyTime;

    @JSONField(serialize = false)
    private String userNames;

    @JSONField(serialize = false)
    private Integer userType;

    @JSONField(serialize = false)
    private String userGroupIds;

	private Integer interfereType;

    private Integer interfereDir;

    private Long cTime;

    @JSONField(serialize = false)
    private String gwIp;

    @JSONField(serialize = false)
    private String gwKeeperIp;

    private List<VoipFlowManageIp> voipGwIpList;

    private List<VoipFlowManageIp> voipGwKeeperIpList;

    @JSONField(serialize = false)
    private String appPolicy;

    @JSONField(serialize = false)
    private String bindPolicy;

}
