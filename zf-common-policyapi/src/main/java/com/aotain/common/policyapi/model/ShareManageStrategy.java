package com.aotain.common.policyapi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Table;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.policyapi.model.base.BaseVO;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table(name="zf_v2_share_manage")
public class ShareManageStrategy extends BaseVO{

    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 7481465230335435633L;

	@JSONField(serialize = false)
	private Integer shareId;

	@JSONField(name="cType")
	private Integer ctype;
	
    @JSONField(serialize = false)
	private String createOper;

    @JSONField(serialize = false)
	private String modifyOper;

    @JSONField(serialize=false)
	private Long startTime;

    @JSONField(serialize=false)
	private Long endTime;

    @JSONField(serialize = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")  
    private Date createTime;

    @JSONField(serialize = false)
    private Date modifyTime;

    private String advUrl;

    private Integer dropProtocol;

	private Integer packetDropRatio;

	 @JSONField(name="cTime")
    private Long time;

    @JSONField(serialize=false)
    private List<ShareManageUserGroup> userGroups;
    
    @JSONField(serialize=false)
    private String appPolicy;
    
    @JSONField(serialize=false)
    private String bindPolicy;
    
    @JSONField(serialize=false)
	private String startTimeShow;

    @JSONField(serialize=false)
	private String endTimeShow;
    
    @JSONField(serialize=false)
    private String appTypeName;
    
    @JSONField(serialize=false)
    private Integer type;
}
