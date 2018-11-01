package com.aotain.common.policyapi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 信息推送触发关键字列表实体
 *
 * @author daiyh@aotain.com
 * @date 2018/03/21
 */
@Getter
@Setter
public class TriggerKwList {

    private Long kwId;

    private Long triggerKwListId;

    private String kwName;

    private Integer operateType;

    private String createOper;

    private String modifyOper;

    private Date createTime;

    private Date modifyTime;
}
