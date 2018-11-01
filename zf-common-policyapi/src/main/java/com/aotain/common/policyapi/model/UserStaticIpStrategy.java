package com.aotain.common.policyapi.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.aotain.common.policyapi.model.base.BaseVO;
import com.aotain.common.policyapi.model.msg.IpMsg;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName: UserStaticIpStrategy
 * @Description: Ip用户地址(这里用一句话描述这个类的作用)
 * {
 "messageNo":123,
 "messageSequenceNo":122,
 "messageType":133,
 "operationType":1,
 "bindAction":1,
 "userName":"bang",
 "userIpInfo":[
 {
 "userIp":”192.10.1.10”,
 "userIpPrefix":18
 },
 {
 "userIp":”ABCD:EF01:2345:6789:ABCD:EF01:2345:6789”,
 "userIpPrefix":47
 }
 ]
 }

 */
@Setter
@Getter
public class UserStaticIpStrategy extends BaseVO {

    private Integer bindAction;

    private String userName;

    private List<IpMsg> userIpInfo;

    @JSONField(serialize = false)
    private Long userId;
}
