package com.aotain.common.policyapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2018/03/15
 */
@Getter
@Setter
@Table(name="zf_v2_voipflow_manage_usergroup")
public class VoipFlowManageUserGroup {

    private Integer voipflowId;

    private Integer userType;

    private Long userGroupId;

    private String userName;
}
