package com.aotain.common.policyapi.model.msg;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IpMsg {

    @JSONField(serialize = false)
    private Long ipId;
    private String userIp;
    private Integer userIpPrefix;

    @Override
    public String toString() {
        return "IpMsg{" +
                "ipId=" + ipId +
                ", userIp='" + userIp + '\'' +
                ", userIpPrefix=" + userIpPrefix +
                '}';
    }
}
