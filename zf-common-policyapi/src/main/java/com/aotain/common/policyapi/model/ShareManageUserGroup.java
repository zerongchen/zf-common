package com.aotain.common.policyapi.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
public class ShareManageUserGroup implements Serializable {

    private Integer shareId;

    private Integer userType;

    private Long userGroupId;

    private String userName;

    private static final long serialVersionUID = 1L;


}