package com.aotain.common.policyapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.policyapi.model.base.BaseVO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * 流量标记策略实体类
 *
 * @author daiyh@aotain.com
 * @date 2018/04/03
 */
@Getter
@Setter
public class FlowSignStrategy extends BaseVO{
    @JSONField(serialize = false)
    private String appTypeName;

    private Integer appType;

    private Integer appId;

    private String appName;

    private Integer qosLabelUp;

    private Integer qosLabelDn;

    @JSONField(serialize = false)
    private String createOper;

    @JSONField(serialize = false)
    private String modifyOper;

    @JSONField(serialize = false)
    private Date createTime;

    @JSONField(serialize = false)
    private Date modifyTime;

    @JSONField(serialize = false)
    private Integer userType;

    @Transient
    @JSONField(serialize = false)
    /**  此字段只用于接收前端传递参数或展示  */
    private List<UserPolicyBindStrategy> bindUser;

    @Transient
    @JSONField(serialize = false)
    /**  此字段只用于接收前端传递参数或展示  */
    private List<String> userName;

    @Transient
    @JSONField(serialize = false)
    /**  此字段只用于接收前端传递参数或展示  */
    private List<Long> userGroupId;

    @JSONField(serialize = false)
    /**  此字段只用于接收前端传递参数或展示  */
    private String appPolicy;

    @JSONField(serialize = false)
    /**  此字段只用于接收前端传递参数或展示  */
    private String bindPolicy;
}
