/**   
* @Title: DdosExceptFlowStrategy.java 
* @Package com.aotain.zf.common.policy.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author DongBoye   
* @date 2018年1月18日 上午9:07:21   
*/
package com.aotain.common.policyapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.policyapi.model.base.BaseVO;
import com.aotain.common.policyapi.model.msg.DdosFlowAppAttachNormal;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/** 
* @ClassName: DdosExceptFlowStrategy 
* @Description: DDOS异常流量策略(这里用一句话描述这个类的作用) 
* @data
{
	"messageNo": 123,
	"messageSequenceNo": 122,
	"messageType": 133,
	"operationType": 1,
	"appAttackType": 2,
	"attackThreshold": 15,
	"attackControl": 10
}
* @author DongBoye
* @date 2018年1月18日 上午9:07:21 
*  
*/
@Getter
@Setter
public class DdosExceptFlowStrategy extends BaseVO {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 3013479687781076240L;

	private List<Integer> ddosId;
	/**
	 * 攻击类型
	 */
	private List<DdosFlowAppAttachNormal> appAttachNormal;

	@Transient
	@JSONField(serialize = false)
	/**  此字段只用于接收前端传递参数或展示  */
	private String createOper;

	@Transient
	@JSONField(serialize = false)
	/**  此字段只用于接收前端传递参数或展示  */
	private String modifyOper;

	@Transient
	@JSONField(serialize = false)
	/**  此字段只用于接收前端传递参数或展示  */
	private Date createTime;

	@Transient
	@JSONField(serialize = false)
	/**  此字段只用于接收前端传递参数或展示  */
	private Date modifyTime;

	@JSONField(serialize = false)
	/**  应用策略下发成功数/下发异常数  */
	private String ddosPolicy;

	@JSONField(serialize = false)
	/**  绑定策略下发成功数/下发异常数  */
	private String bindPolicy;

	private Integer userType;

	@JSONField(serialize = false)
	private List<String> userName;

	@JSONField(serialize = false)
	private List<String> userGroupId;

}
