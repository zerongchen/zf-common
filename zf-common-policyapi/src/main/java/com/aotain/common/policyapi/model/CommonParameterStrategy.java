package com.aotain.common.policyapi.model;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.policyapi.model.base.BaseVO;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * ClassName: CommonParameterStrategy
 * Description: 通用参数策略
 * date: 2018年4月9日 上午9:08:40
 * 
 * @author tanzj 
 * @version  
 * @since JDK 1.8
 */
@Setter
@Getter
public class CommonParameterStrategy extends BaseVO {

    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = -2980924265990853966L;
	
	/**
	 * 针对web类流量进行统计的点
	 */
	private Integer webHitThreshold;
	
	/**
	 * 需关注的主流搜索引擎数
	 */
    private Integer kwThreholds;
    
    List<String> seName;
    
    List<CommonCookie> cookieHost;
    
}
