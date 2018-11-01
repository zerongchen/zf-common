package com.aotain.common.policyapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.policyapi.model.base.BaseVO;

import com.aotain.common.policyapi.model.msg.UserMessage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 
* @ClassName: UserGroupStrategy 
* @Description:  用户组归属策略(这里用一句话描述这个类的作用) 
* @author DongBoye
* @data
{
	"messageNo": 123,
	"messageSequenceNo": 122,
	"messageType": 1,
	"operationType": 1,
	"userGroupNo": 2,
	"userGroupName": "特殊用户组",
	"action": 1,
	"userInfo": [{
			"userName": "192.101.1.10",
			"userType": 3
		},
		{
			"userName": "192.101.1.11",
			"userType": 4
		}
	]
}
* @date 2018年1月18日 下午5:21:21 
*
 */
@Getter
@Setter
public class UserGroupStrategy extends BaseVO {

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 2592228320918863432L;

	private Long userGroupNo;

	private String userGroupName;
	/**
	 * 见constant包里的UserGroupAction 1=add，2=delete
	 */
	private Integer action;
	@JSONField(serialize = false)
	private List<UserMessage> userInfo;

	@JSONField(serialize = false)
	private Long userGroupId;
}
