package com.aotain.common.policyapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2018/03/21
 */
@Getter
@Setter
public class TriggerHostList {

    private Long hostId;

    private Long triggerHostListid;

    private String hostName;

    private Integer operateType;

    private String createOper;

    private String modifyOper;

    private Date createTime;

    private Date modifyTime;
}
