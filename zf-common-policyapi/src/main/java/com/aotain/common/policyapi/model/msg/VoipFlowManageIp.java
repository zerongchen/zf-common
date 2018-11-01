package com.aotain.common.policyapi.model.msg;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Table;

@Setter
@Getter
@Table(name="zf_v2_voipflow_manage_ip")
public class VoipFlowManageIp {

    @JSONField(serialize = false)
    private Integer voipflowId;

    @JSONField(serialize = false)
    private Integer voipType;

    private String voipIp;
}
