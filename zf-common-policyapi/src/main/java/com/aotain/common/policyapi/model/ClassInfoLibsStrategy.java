package com.aotain.common.policyapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.policyapi.model.base.BaseVO;

import lombok.Getter;
import lombok.Setter;

/**
 * 
* @ClassName: ClassInfoLibsStrategy 
* @Description: 分类库下发策略：IP地址库、应用名称对应表、协议特征库（目前暂且不做）、web分类库(这里用一句话描述这个类的作用)
* @data
* 
* {
	"messageNo": 123,
	"messageSequenceNo": 122,
	"messageType": 202,
	"operationType": 1,
	"ip": "192.168 .1 .10",
	"port": 8080,
	"userName": "bang",
	"password": "afdbc",
	"fileName": "2014_abc.txt"
} 
* @author DongBoye
* @date 2018年2月3日 下午6:09:59 
*
 */
@Getter
@Setter
public class ClassInfoLibsStrategy extends BaseVO {
	
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = -5702342313951413417L;

	private String ip;
	
	private Integer port;
	
	private String userName;
	
	private String password;
	
	private String fileName;
	//版本号
	@JSONField(serialize = false)
	private Long versionNo;
	
	@JSONField(serialize = false)
	private Integer zongfenId;
}
