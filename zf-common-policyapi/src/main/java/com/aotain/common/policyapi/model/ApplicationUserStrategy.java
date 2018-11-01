/**   
* @Title: ApplicationUserStrategy.java 
* @Package com.aotain.zf.common.policy.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author DongBoye   
* @date 2018年1月17日 下午5:33:42   
*/
package com.aotain.common.policyapi.model;


import com.aotain.common.policyapi.model.base.BaseVO;
import lombok.Getter;
import lombok.Setter;

/** 
* @ClassName: ApplicationUserStrategy 
* @Description: 指定应用用户管理策略(这里用一句话描述这个类的作用) 
* @data 
{
	"messageNo": 123,
	"messageSequenceNo": 122,
	"messageType": 133,
	"operationType": 1,
	"rStartTime": 1515640726,
	"rEndTime": 1515640799,
	"appType": 4,
	"appId": 12,
	"appName": "风行视频",
	"userType": 0
}
* @author DongBoye
* @date 2018年1月17日 下午5:33:42 
*  
*/
@Getter
@Setter
public class ApplicationUserStrategy extends BaseVO {
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = -6494075774668543449L;
	

	private Long rStartTime;

	private Long rEndTime;
	
	private Integer appType;
	
	private Integer appId;
	
	private String appName;
	
	private Integer userType;
}
