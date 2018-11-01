package com.aotain.common.policyapi.model;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class CommonCookie implements Serializable {
	@JSONField(serialize = false)
    private Long cookieId;

    private String cookieHostName;
    
    @JSONField(serialize = false)
    private Long messageNo;
    
    private String cookieKeyValue;

    @JSONField(serialize = false)
    private String createOper;

    @JSONField(serialize = false)
    private String modifyOper;

    @JSONField(serialize = false)
    private Date createTime;

    @JSONField(serialize = false)
    private Date modifyTime;
    
    @JSONField(serialize = false)
    private Integer operateType;
    
    private static final long serialVersionUID = 1L;

}