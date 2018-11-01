package com.aotain.common.policyapi.model;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.policyapi.model.base.BaseVO;

import lombok.Getter;
import lombok.Setter;


/**
 * 
 * ClassName: DpiDeviceInfoStrategy
 * Description: DPI设备通用信息策略
 * date: 2018年2月5日 上午9:07:37
 * 
 * @author tzj 
 * @version  
 * @since JDK 1.8
 */
@Getter
@Setter
public class DpiDeviceInfoStrategy extends BaseVO {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JSONField(serialize = false)
    private Integer dpiId;

	@JSONField(name = "devName")
    private String dpiDevName;

	@JSONField(name="ip")
    private String dpiIp;

	@JSONField(name="port")
    private Integer dpiPort;

	@JSONField(name="deploySiteName")
    private String dpiSiteName;

	@JSONField(serialize = false)
    private Integer areaCode;

	@JSONField(serialize = false)
    private String idcHouseId;

	@JSONField(name="zfDeviceIp")
	private String policyIp;
	
	private Integer radiusFlag;
	
    private List<String> idcHouseIds;
    
    /**
     * 关联综分设备（页面展示）
     */
    @JSONField(serialize = false)
    private String zongFenServer;
    
    /**
     * 所属区域（页面展示）
     */
    @JSONField(serialize = false)
    private String areaName;
    
    /**
     * IP:端口（页面展示）
     */
    @JSONField(serialize = false)
    private String ipPort;
    
    @JSONField(serialize = false)
    private String connectFlag;
    
    @JSONField(serialize = false)
    private String[] idchouses;


}

