package com.aotain.common.policyapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.policyapi.model.base.BaseVO;

import lombok.Getter;
import lombok.Setter;
/**
 * 
* @ClassName: HttpGetStrategy 
* @Description: HTTP GET报文清洗黑白名单列表下发策略(这里用一句话描述这个类的作用) 
* @data 
{
	"messageNo": 123,
	"messageType": 207,
	"operationType": 1,
	"ip": "192.168 .1 .10",
	"port": 8080,
	"userName": "bang",
	"password": "afdbc",
	"hfdwFileName": "2014_abc.txt",
	"hfdwSeqNo": 2,
	"hfdbFileName": "2014_abc22.txt",
	"hfdbSeqNo": 3,
	"hfubFileName": "2014_abc.txt",
	"hfubSeqNo": 2
}
* @author DongBoye
* @date 2018年2月3日 上午10:36:50 
*
 */
@Getter
@Setter
public class HttpGetStrategy extends BaseVO {
	
	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = 3779225556094187629L;
	private String ip;
	private Integer port;
	private String userName;
	private String password;
	/**
	 * http get 清洗域名白名单
	 */
	private String hfdwFileName;
	private Long hfdwVersionNo;
	
	/**
	 * http get 清洗域名黑名单
	 */
	private String hfdbFileName;
	private Long hfdbVersionNo;
	
	/**
	 * http get 清洗URL黑名单
	 */
	private String hfubFileName;
	private Long hfubVersionNo;

	@JSONField(serialize = false)
	private Integer zongfenId;
}
