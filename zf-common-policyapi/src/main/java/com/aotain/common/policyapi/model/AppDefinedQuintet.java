package com.aotain.common.policyapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

/**
 * Demo class
 *
 * @author daiyh@aotain.com
 * @date 2018/04/10
 */
@Getter
@Setter
public class AppDefinedQuintet {

    @JSONField(serialize = false)
    private Long quintetId;

    @JSONField(serialize = false)
    private Long definedId;

    @Column(name = "U_PROTOCOL")
    @JSONField(name = "uProtocol")
    private Integer protocol;

    @Column(name = "U_SOURCEIP")
    @JSONField(name = "uSourceIp")
    private String sourceIp;

    @Column(name = "U_SOURCEPORT")
    @JSONField(name = "uSourcePort")
    private Integer sourcePort;

    @Column(name = "U_DESTIP")
    @JSONField(name = "uDestIp")
    private String destIp;

    @Column(name = "U_DESTPORT")
    @JSONField(name = "uDestPort")
    private Integer destPort;

    @JSONField(name = "uKW")
    private List<AppDefinedKey> kw;
}
