package com.aotain.common.policyapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.policyapi.model.base.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * 流量镜像策略实体类
 *
 * @author daiyh@aotain.com
 * @date 2018/04/08
 */
@Getter
@Setter
public class FlowMirrorStrategy extends BaseVO{

    @JSONField(serialize = false)
    private String appTypeName;

    private Long policyId;

    @JSONField(name = "mAppType")
    private Integer appType;

    @JSONField(name = "mAppId")
    private Integer appId;

    @JSONField(name = "mAppName")
    private String appName;

    @JSONField(name = "mStartTime")
    private Long startTime;

    @JSONField(name = "mEndTime")
    private Long endTime;

    @JSONField(name = "mGroupNo")
    private Integer groupNo;

    @JSONField(name = "mDirection")
    private Integer direction;

    @JSONField(name = "mFlowAdd")
    private Integer flowAdd;

    @JSONField(name = "mCutLength")
    private Integer cutLength;

    @JSONField(name = "mSrcIpSegment")
    private List<FlowMirrorIpAndPrefix> srcIpSegment;

    @JSONField(name = "mDstIpSegment")
    private List<FlowMirrorIpAndPrefix> dstIpSegment;

    @JSONField(name = "mSrcPort")
    private List<FlowMirrorPort> srcPort;

    @JSONField(name = "mDstPort")
    private List<FlowMirrorPort> destPort;

    @JSONField(name = "mDataMatch")
    private List<FlowMirrorMatchContent> dataMatch;

    @JSONField(serialize = false)
    private List<String> userNames;

    @JSONField(serialize = false)
    private Integer userType;

    @JSONField(serialize = false)
    private List<String> userGroupIds;

    @Transient
    @JSONField(serialize = false)
    /**  此字段只用于接收前端传递参数或展示  */
    private String createOper;

    @Transient
    @JSONField(serialize = false)
    /**  此字段只用于接收前端传递参数或展示  */
    private String modifyOper;

    @JSONField(serialize = false)
    private Date createTime;

    @JSONField(serialize = false)
    private Date modifyTime;

    @JSONField(serialize = false)
    private List<UserPolicyBindStrategy> bindUser;

    @JSONField(serialize = false)
    private String appPolicy;

    @JSONField(serialize = false)
    private String bindPolicy;
}
