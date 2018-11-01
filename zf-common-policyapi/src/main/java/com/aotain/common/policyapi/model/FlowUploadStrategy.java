package com.aotain.common.policyapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.policyapi.model.base.BaseVO;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Chenzr
 * @since 2018-02-05
 * @data
{
    "messageNo":123,
    "messageSequenceNo":122,
    "messageType":1,
    "operationType":1,
    "packetType":2,
    "packetSubType":160,
    "startTime":12485651812,
    "endTime":12485651953,
    "freq":1,
    "destIp":"192.168.0.1",
    "destPort":8080,
    "method":2,
    "userName":"bang",
    "passWord":"123"
}
 */

@Setter
@Getter
public class FlowUploadStrategy extends BaseVO {

    /** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/
	private static final long serialVersionUID = -2980924265990853966L;
	private Integer packetType;
    private Integer packetSubType;
    private Long startTime;
    private Long endTime;
    private Integer freq;

    /**
     * 0x01(1)：socket、0x02(2)：文件
     */
    private Integer method;

    /**
     * SFTP
     */
    private String userName;
    private String password;

    /**
     * socket
     */
    private String destIp;
    private Integer destPort;

    //不参与数据组装
    @JSONField(serialize = false)
    private String messageName;

    //不参与数据组装
    @JSONField(serialize = false)
    private Integer zongfenId;

}
