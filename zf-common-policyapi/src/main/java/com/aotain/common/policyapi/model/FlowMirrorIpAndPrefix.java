package com.aotain.common.policyapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2018/04/08
 */
@Getter
@Setter
public class FlowMirrorIpAndPrefix {

    @JSONField(name="mIpAddr")
    private String ipAddr;

    @JSONField(name="mIpPrefixLen")
    private Integer ipPrefixLen;
}
