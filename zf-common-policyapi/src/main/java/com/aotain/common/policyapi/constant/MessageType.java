package com.aotain.common.policyapi.constant;

public enum MessageType {

	COMMON_PARAMETER_SET(0,"通用参数策略"),
    FLOW_UPLOAD_POLICY(1,"流量分析结果上报策略"),
	WEB_FLOW_POLICY(2,"Web 类流量管理策略"),
	VOIP_FLOW_POLICY(5,"VoIP 类流量管理策略"),
	GENERAL_FLOW_POLICY(6,"通用流量管理策略"),
    USER_GROUP_POLICY(64,"用户组归属分配策略"),
    WEB_PUSH_POLICY(65,"Web信息推送策略"),
    SHARE_MANAGE_POLICY(66,"一拖N用户管理策略"),
    USER_STATICIP_POLICY(130,"IP 地址段用户信息下发"),
    USER_POLICY_BIND(133,"用户/应用策略信息下发"),
    DEVICE_INFO_POLICY(192,"DPI设备通用信息下发"),
    INFORMATION_TRIGGER_HOST_POLICY(203,"信息推送触发网站列表定义"),
    INFORMATION_TRIGGER_KW_POLICY(204,"信息推送触发关键字列表定义"),
	DPI_DEVICE_QUERY_POLICY(196,"DPI设备状态查询请求"),
	DPI_DEVICE_QUERY_POLICY_STATIC(196001,"DPI设备状态静态查询请求"),
	DPI_DEVICE_QUERY_POLICY_DYNAMIC(196002,"DPI设备状态动态查询请求"),
	HTTP_GET_POLICY(207,"HTTPGET报文清洗策略"),
	HTTP_GET_POLICY_HFDW(207001,"HTTPGET报文清洗策略:清洗域名白名单"),
	HTTP_GET_POLICY_HFDB(207002,"HTTPGET报文清洗策略:清洗域名黑名单"),
	HTTP_GET_POLICY_HFUB(207003,"HTTPGET报文清洗策略:清洗URL黑名单"),
	WEB_CLASS_TABLE_POLICY(200,"Web分类库更新策略"),
	APP_NAME_TABLE_POLICY(201,"应用名称对应表下发策略"),
	IP_ADDRESS_ALLOCATION_POLICY(202,"IP地址库下发策略"),
    MESSAGE_TYPE_DDOS_EXCEPT_FLOW(67,"应用层 DDoS 异常流量管理策略"),
	APP_FLOW_DIRECTION_POLICY(69,"应用流量流向策略"),

    APP_FLOW_MARK(7,"流量标记策略"),
    APP_USER_DEFINED(8,"指定应用用户统计策略"),
    APP_FLOW_MIRROR(9,"流量镜像策略"),
    APP_DEFINED(10,"用户特征自定义策略");

    private int id;
    private String messageType;

    MessageType(int id,String messageType){
        this.id =id;
        this.messageType = messageType;
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType( String messageType ) {
        this.messageType = messageType;
    }
    
    public static String getTypeById(int id) {
    	for(MessageType tem:MessageType.values()) {
    		if(tem.getId()==id) {
    			return tem.getMessageType();
    		}
    	}
    	return null;
    }
}
