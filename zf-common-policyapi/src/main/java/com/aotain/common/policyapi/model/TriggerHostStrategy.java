package com.aotain.common.policyapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.policyapi.model.base.BaseVO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 信息推送触发网站策略
 *
 * @author daiyh@aotain.com
 * @date 2018/03/21
 */
@Getter
@Setter
public class TriggerHostStrategy extends BaseVO{

    @JSONField(name = "triggerHostListId")
    private Long  triggerHostListId;

    private List<TriggerHostName> hostInfo;


    @JSONField(serialize = false)
    private String triggerHostListName;

    @JSONField(serialize = false)
    private Integer hostListType;

    @JSONField(serialize = false)
    private Integer hostNum;

    @JSONField(serialize = false)
    private String createOper;

    @JSONField(serialize = false)
    private String modifyOper;

    @JSONField(serialize = false)
    private Date createTime;

    @JSONField(serialize = false)
    private Date modifyTime;
}
