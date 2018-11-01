package com.aotain.common.policyapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2018/04/10
 */
@Getter
@Setter
@Table(name = "zf_v2_policy_appdefined_key")
public class AppDefinedKey {
    @JSONField(serialize = false)
    private Long quintetId;

    @Column(name = "U_OFFSETBASE")
    @JSONField(name = "uOffsetBase")
    private Integer offSetBase;

    @Column(name = "U_OFFSET")
    @JSONField(name = "uOffset")
    private Integer offSet;

    @Column(name = "U_KWVALUE")
    @JSONField(name = "uKWValue")
    private String kwValue;
}
