package com.aotain.common.policyapi.constant;

import java.io.Serializable;

import lombok.Getter;

/**
 *
 * @ClassName: MessageTypeConstants
 * @Description: 策略类型常类(这里用一句话描述这个类的作用)
 * @author DongBoye
 * @date 2018年1月15日 下午4:59:32
 *
 */
@Getter
public class MessageTypeConstants implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 8084200447744266762L;
    // DPI/EU设备通用信息下发
    public static final Integer MESSAGE_TYPE_DPI_INFO =  192;

    //应用流量向策略
    public static final Integer MESSAGE_TYPE_APPLICATION_FLOW = 69;
    //应用层 DDoS 异常流量管理策略
    public static final Integer MESSAGE_TYPE_DDOS_EXCEPT_FLOW = 67;
    //用户组归属策略
    public static final Integer MESSAGE_TYPE_USER_GROUP = 64;
    //Web信息推送策略
    public static final Integer MESSAGE_TYPE_WEB_PUSH_POLICY = 65;
    //指定应用用户管理策略
    public static final Integer MESSAGE_TYPE_APPLICATION_USER = 8;
    //流量分析结果上报策略
    public static final Integer MESSAGE_TYPE_FLOW_UPLOAD_ANALYSIS = 1;

    //Web 类流量管理策略
    public static final Integer MESSAGE_TYPE_WEB_FLOW_POLICY = 2;
    //VoIP 类流量管理策略
    public static final Integer MESSAGE_TYPE_VOIP_FLOW_POLICY = 5;

    //通用流量管理策略
    public static final Integer MESSAGE_TYPE_GENERAL_FLOW_POLICY = 6;

    //用户应用策略信息下发
    public static final Integer MESSAGE_TYPE_USER_BIND_POLICY = 133;
   
    //设备状态查询
    public static final Integer MESSAGE_TYPE_DPI_STATUS = 196;
    //http get报文清洗策略
    public static final Integer MESSAGE_TYPE_HTTP_GET = 207;
    //（自定义）http get报文清洗策略:清洗域名白名单
    public static final Integer MESSAGE_TYPE_HTTPGET_HFDW = 207001;
    //（自定义）http get报文清洗策略:清洗域名黑名单
    public static final Integer MESSAGE_TYPE_HTTPGET_HFDB = 207002;
    //（自定义）http get报文清洗策略:清洗URL黑名单
    public static final Integer MESSAGE_TYPE_HTTPGET_HFUB = 207003;

    //IP地址库下发策略
    public static final Integer MESSAGE_TYPE_IP_ADDRESS_ALLOCATION = 202;
    //Web分类库更新策略
    public static final Integer MESSAGE_TYPE_WEB_CLASS_TABLE = 200;
    //应用名称对应表下发策略
    public static final Integer MESSAGE_TYPE_APP_NAME_TABLE = 201;

    //信息推送触发网站策略
    public static final Integer MESSAGE_TYPE_TRIGGER_HOST = 203;

    //信息推送触发关键字列表策略
    public static final Integer MESSAGE_TYPE_TRIGGER_KEY_WORDS = 204;
}
