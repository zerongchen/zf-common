package com.aotain.common.policyapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.policyapi.model.base.BaseVO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 信息推送触发关键字策略
 *
 * @author daiyh@aotain.com
 * @date 2018/03/21
 */
@Getter
@Setter
public class TriggerKwStrategy extends BaseVO{

    private Long triggerKwListId;

    private List<TriggerKwName> kwInfo;

    @JSONField(serialize = false)
    private String triggerKwListName;

    @JSONField(serialize = false)
    private Long KwNum;

    @JSONField(serialize = false)
    private String createOper;

    @JSONField(serialize = false)
    private String modifyOper;

    @JSONField(serialize = false)
    private Date createTime;

    @JSONField(serialize = false)
    private Date modifyTime;
}
