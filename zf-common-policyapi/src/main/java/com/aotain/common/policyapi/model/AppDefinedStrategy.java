package com.aotain.common.policyapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.policyapi.model.base.BaseVO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 应用特征自定义策略实体类
 *
 * @author daiyh@aotain.com
 * @date 2018/04/08
 */
@Getter
@Setter
public class AppDefinedStrategy extends BaseVO{

    @JSONField(serialize = false)
    private String appTypeName;

    @JSONField(serialize = false)
    private Long definedId;

    private Integer appType;
    private Integer appId;
    private String appName;

    @JSONField(name="uSignature")
    private List<AppDefinedQuintet> signature;

    @JSONField(serialize = false)
    private List<String> userNames;

    @JSONField(serialize = false)
    private Integer userType;

    @JSONField(serialize = false)
    private List<String> userGroupIds;

    @JSONField(serialize = false)
    private String appPolicy;

    @JSONField(serialize = false)
    private String bindPolicy;

    @JSONField(serialize = false)
    private List<UserPolicyBindStrategy> bindUser;

    @JSONField(serialize = false)
    private String createOper;

    @JSONField(serialize = false)
    private String modifyOper;

    @JSONField(serialize = false)
    private Date createTime;

    @JSONField(serialize = false)
    private Date modifyTime;
}
