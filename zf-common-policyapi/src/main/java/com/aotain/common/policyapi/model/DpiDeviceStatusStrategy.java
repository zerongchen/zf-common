package com.aotain.common.policyapi.model;


import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.policyapi.model.base.BaseVO;

import lombok.Getter;
import lombok.Setter;

/**
 * 
* @ClassName: DpiDeviceStatus 
* @Description: DPI设备状态查询策略(这里用一句话描述这个类的作用) 
* @author DongBoye
* @date 2018年1月15日 下午5:06:29 
*
 */
@Getter
@Setter
public class DpiDeviceStatusStrategy extends BaseVO {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3146151692573249831L;

	/**
	 * @Fields rType : =0x01:设备静态信息，=0x02: 设备动态信息
	 */
    @JSONField(name="type")
    private Integer rType;
    /**
	 * @Fields rFreq :
	 *      =0x00:仅即时上报一次，
	 *  	=0x01:每5分钟上报，
	 *  	=0x02:每1小时上报(整点时间)，
	 *    	=0x03:每3小时上报(整点时间)，
	 *     	=0x04:每天上报(每天0点/上报一天的平均值)
	 */
    @JSONField(name="frequence")
    private Integer rFreq;

}
