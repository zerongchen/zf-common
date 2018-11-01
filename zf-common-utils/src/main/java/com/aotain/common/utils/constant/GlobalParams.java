package com.aotain.common.utils.constant;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 全局变量
 * 
 * @author liuz@aotian.com
 * @date 2018年1月2日 下午3:36:27
 */
public class GlobalParams {
	//对称加密算法-不加密
	public static final int ENCRYPT_ALGORITHM_NO 				= 0;
	//对称加密算法-AES加密算法
	public static final int ENCRYPT_ALGORITHM_AES 				= 1;
	//哈希算法-无哈希
	public static final int HASH_ALGORITHM_NO 					= 0;
	//哈希算法-MD5
	public static final int HASH_ALGORITHM_MD5 					= 1;
	//哈希算法-SHA-1
	public static final int HASH_ALGORITHM_SHA					= 2;
	//压缩格式-无压缩
	public static final int COMPRESSION_FORMAT_NO				= 0;
	//压缩格式-zip压缩格式
	public static final int COMPRESSION_FORMAT_ZIP				= 1;
	
	//违法网站列表指令根节点
	public static final String XFT_BLACK_LIST 					= "blacklist";
	//代码发布指令根节点
	public static final String XFT_CODE_LIST 					= "codeList";
	//信息安全管理指令根节点
	public static final String XFT_IDC_COMMAND 					= "command";
	//基础数据管理指令根节点
	public static final String XFT_IDC_INFO_MANAGE 				= "idcInfoManage";
	//访问日志查询指令根节点
	public static final String XFT_LOG_QUERY 					= "logQuery";
	//信息安全管理指令查询指令根节点,此指令已过期
	public static final String XFT_COMMAND_QUERY 				= "commandQuery"; 
	//免过滤网站指令根节点
	public static final String XFT_NO_FILTER 					= "noFilter";
	//基础数据核验处理指令根节点
	public static final String XFT_RETURN_INFO	 				= "returnInfo";
	//活跃资源访问量指令根节点
	public static final String XFT_QUERY_VIEW 					= "queryView";
	//违法管理指令执行记录指令根节点
	public static final String XFT_COMMAND_RECORD 				= "commandRecord";
	
	//基础数据记录类型
	public static final int DT_BASIC_INFO 						= 1;
	//基础数据监测异常记录数据类型
	public static final int DT_BASIC_MONITOR 					= 2;
	//访问日志查询记录数据类型
	public static final int DT_LOG_QUERY 						= 3;
	//违法信息监测记录数据类型
	public static final int DT_MONITOR_QUERY 					= 4;
	//违法信息过滤记录数据类型
	public static final int DT_FILTER_QUERY 					= 5;
	//山西自定义接口——基础数据及备案信息
	public static final int DT_BASIC_BEIAN	 					= 51;
	//山西自定义接口——域名访问量数据
	public static final int DT_DOMAIN_VISIT 					= 52;
	//山西自定义接口——过滤状态
	public static final int DT_FILTER_STATUS 					= 53;
	//山西自定义接口——过滤统计数据
	public static final int DT_FILTER_STATISTICS				= 54;
	//山西自定义接口——网络协议应用数据
	public static final int DT_NET_PROTOCOL						= 56;
	//基础数据查询指令数据类型
	public static final int DT_COMMAND_QUERY					= 6;
	//ISMS活动状态数据类型
	public static final int DT_ACTIVE_STATE 					= 7;
	//活跃资源监测记录数据类型
	public static final int DT_ACTIVE_RESOURCES 				= 8;
	//违法违规网站监测记录类型
	public static final int DT_ILLEGAL_WEB 						= 9;
	//指令执行情况
	public static final int DT_COMMAND_ACK 						= 10;
	//天津自定义接口-活跃资源记录类型（一期为9，二期暂定为11）
	public static final int DT_TJ_ACTIVE_RESOURCES				= 11;
	
	public static final int TYPE_ACTIVE_RESOURCES_IP	        = 81;
	
	public static final int TYPE_ACTIVE_RESOURCES_DOMAIN     	= 82;
	
	//指令类型-违法信息监测指令
	public static final int COMMAND_ACK_MONITOR 				= 1;
	public static final String COMMAND_ACK_MONITOR_CN 			= "违法信息监测指令ack";
	//指令类型-违法信息过滤指令
	public static final int COMMAND_ACK_FILTER 					= 2;
	public static final String COMMAND_ACK_FILTER_CN 			= "违法信息过滤指令ack";
	//指令类型-代码发布指令
	public static final int COMMAND_ACK_CODE_LIST				= 4;
	public static final String COMMAND_ACK_CODE_LIST_CN			= "代码发布指令ack";
	//指令类型-免过滤网站列表指令
	public static final int COMMAND_ACK_NOFILTER				= 5;
	public static final String COMMAND_ACK_NOFILTER_CN			= "免过滤网站列表指令ack";
	//指令类型-违法网站列表指令
	public static final int COMMAND_ACK_ILLEGAL_WEB				= 6;
	public static final String COMMAND_ACK_ILLEGAL_WEB_CN		= "违法网站列表指令ack";
	//指令类型-活跃资源访问量查询指令
	public static final int COMMAND_ACK_QUERY_VIEW				= 7;
	public static final String COMMAND_ACK_QUERY_VIEW_CN		= "活跃资源访问量查询指令ack";
	//指令类型-违法信息管理查询指令
	public static final int COMMAND_ACK_COMMAND_RECORD			= 8;
	public static final String COMMAND_ACK_COMMAND_RECORD_CN	= "违法信息管理查询指令ack";
	
	//上报文件处理结果代码
	//处理完成
	public static final int RESULT_CODE_SUCCESS 				= 0;
	//文件解密失败
	public static final int RESULT_CODE_DECRYPT_FAILURE			= 1;
	//文件校验失败
	public static final int RESULT_CODE_CHECK_FAILURE			= 2;
	//文件解压缩失败
	public static final int RESULT_CODE_UZIP_FAILURE 			= 3;
	//文件格式异常
	public static final int RESULT_CODE_ABNORMAL_FORMAT			= 4;
	//文件内容异常——版本错误
	public static final int RESULT_CODE_CONTENT_VERSION 		= 5;
	//文件内容异常——上报类型异常
	public static final int RESULT_CODE_CONTENT_REPORT_TYPE   	= 51;
	//文件内容异常——节点/子节点长度错误
	public static final int RESULT_CODE_CONTENT_NODE_LENGTH   	= 52;
	//文件内容异常——节点/子节点类型错误
	public static final int RESULT_CODE_CONTENT_NODE_TYPE     	= 53;
	//文件内容异常——节点/子节点内容错误
	public static final int RESULT_CODE_CONTENT_NODE_CONTENT  	= 54;
	//文件内容异常——节点/子节点缺漏
	public static final int RESULT_CODE_CONTENT_NODE_OMISSION 	= 55;
	//其他异常——存在其他错误，需重新上报
	public static final int RESULT_CODE_OTHERS_REPORT 			= 900;
	//其他异常——处理中
	public static final int RESULT_CODE_OTHERS_PROCESSING 		= 999;
	
	//数据库连接异常类型
	public static final int RESULT_DB_EXCEPTION = 601;
	public static final String RESULT_DB_EXCEPTION_CN = "连接db异常";
	
	//ftp连接异常类型
	public static final int RESULT_FTP_EXCEPTION = 602;
	public static final String RESULT_FTP_EXCEPTION_CN = "连接ftp异常";
	public static final String RESULT_FTP_CHANGE_DIR_EXCEPTION_CN = "切换ftp目录异常";
	public static final String RESULT_FTP_MKDIR_DIR_EXCEPTION_CN = "ftp目录创建异常";
	public static final String RESULT_FTP_UPLOAD_EXCEPTION_CN = "ftp上传文件异常";
	
	//hadoop连接异常类型
	public static final int RESULT_Hadoop_EXCEPTION = 603;
	public static final String RESULT_HADOOP_EXCEPTION_CN = "连接Hadoop异常";
	
	public static final String RESULT_XML_FILE_EXCEPTION_CN = "创建xml文件失败";
	public static final String RESULT_DATA_UPLOAD_EXCEPTION_CN = "数据上报失败"; 
	public static final String RESULT_SYSTEM_PARAM_UNSET_CN = "系统参数没设置"; 
	public static final String RESULT_SYSTEM_PARAM_INCORRECT_CN = "系统参数不正确"; 
	
	public static final String RESULT_FILE_EXCEPTION_CN = "文件操作异常"; 
	public static final String RESULT_FILE_NOTFOUND_EXCEPTION_CN = "文件不存在"; 
	
	public static final String INTERFACE_DEFINE_SX				= "shanxi";
	public static final String INTERFACE_DEFINE_TJ				= "tianjin";
	
	public static final int COMMAND_TYPE_MONITOR = 1;
	public static final int COMMAND_TYPE_FILTER  = 2;
	
	public static final long LEVEL_FILTER_DEST_IP  		= 401;
	public static final long LEVEL_FILTER_DEST_PORT  	= 402;
	public static final long LEVEL_FILTER_TCP  			= 403;
	public static final long LEVEL_FILTER_UDP  			= 404;
	public static final long LEVEL_FILTER_SOURCE_IP		= 405;
	public static final long LEVEL_FILTER_SOURCE_PORT	= 406;
	public static final long LEVEL_FILTER_DOMAIN		= 407;
	public static final long LEVEL_FILTER_URL			= 408;
	public static final long LEVEL_FILTER_KEYWORD		= 409;
	
	public static final long LEVEL_MONITOR_DEST_IP  	= 501;
	public static final long LEVEL_MONITOR_DEST_PORT  	= 502;
	public static final long LEVEL_MONITOR_TCP  		= 503;
	public static final long LEVEL_MONITOR_UDP  		= 504;
	public static final long LEVEL_MONITOR_SOURCE_IP	= 505;
	public static final long LEVEL_MONITOR_SOURCE_PORT	= 506;
	public static final long LEVEL_MONITOR_DOMAIN		= 507;
	public static final long LEVEL_MONITOR_URL			= 508;
	public static final long LEVEL_MONITOR_KEYWORD		= 509;
	
	//管局指令类型
	public static final int COMMAND_BASIC_DATA_QUWERY_TYPE = 1000;//基础数据查询指令
	public static final int COMMAND_LOG_QUERY_TYPE = 1001;//访问日志
	public static final int COMMAND_INFO_MANAGE_TYPE = 1002;//违法信息安全管理指令
	public static final int COMMAND_CODE_RELEASE_TYPE = 1003;//代码表发布指令
	public static final int COMMAND_BASIC_DATA_VALIDATE_TYPE = 1004;//基础数据核验处理指令
	public static final int COMMAND_INFO_NO_FILTER_TYPE = 1005;//免过滤网站列表指令
	public static final int COMMAND_INFO_BLACK_TYPE = 1006;//违法网站列表指令
	public static final int COMMAND_INFO_QUERY_VIEW_TYPE = 1007;//活跃资源访问量查询指令
	public static final int COMMAND_INFO_BLACK_RECORD_TYPE = 1008;//违法管理指令执行记录指令
	
	//主动上报类型
	public static final int UPLOAD_ACTIVE_STATE_TYPE = 2000;//ISMS活动状态上报
	public static final int UPLOAD_ACTIVE_RESOURCE_MONITOR_TYPE = 2001;//活跃资源监测数据上报
	public static final int UPLOAD_ILLEGAL_WEB_MONITOR_TYPE = 2002;//违法违规网站监测数据上报
	public static final int UPLOAD_BASIC_MONITOR_TYPE = 2003;//基础数据异常监测上报
	public static final int UPLOAD_BASIC_DATA_TYPE = 2004;//基础数据上报
	
	//定时上报根KEY值
	public static final String UPLOAD_ACTIVE_STATE_KEY = "upload_activeState";//ISMS活动状态
	public static final String UPLOAD_ACTIVE_RESOURCE_MONITOR_KEY = "upload_activeResources_monitor";//活跃资源监测
	public static final String UPLOAD_ILLEGAL_MONITOR_KEY = "upload_illegal_monitor";//违法违规网站监测
	public static final String UPLOAD_IDC_MONITOR_KEY = "upload_idc_monitor";//基础数据异常监测	
		
	
	public static final Map<Long, Long> commandView 	= new HashMap<Long, Long>();
	public static final Map<Long, String> houses 		= new HashMap<Long, String>();
	public static final Map<Long, Set<Long>> SPECIAL_LINE_IP_DATA 	= new HashMap<Long, Set<Long>>();
	
	public static final String CLIENT_VERSION = "client_version";
	public static final String ENCRYPT_ALGORITHM = "encrypt_algorithm";
	public static final String HASH_ALGORITHM = "hash_algorithm";
	public static final String COMMPRESSION_FORMAT = "compression_format";
	public static final String BASE64_TRANSCODE = "base64_transcode";
	public static final String SERVER_URL = "service_url";
	public static final String PASSWORD = "password";
	public static final String VERSION = "version";
	public static final String AES_KEY = "aes_key";
	public static final String AES_IV = "aes_iv";
	public static final String RZ_KEY = "rz_key";
	public static final String FTP_IP = "ftp_ip";
	public static final String FTP_PORT = "ftp_port";
	public static final String FTP_USER = "ftp_user";
	public static final String FTP_PASSWORD = "ftp_passpord";
	public static final String FTP_PATH = "ftp_path";
	public static final String HADOOP_DRIVER = "hadoop_driver";
	public static final String HADOOP_URL = "hadoop_url";
	public static final String HADOOP_USER = "hasoop_user";
	public static final String HADOOP_PASSWORD = "hadoop_password";
	public static final String HIVE_DRIVER = "hive_driver";
	public static final String HIVE_URL = "hive_url";
	public static final String HIVE_USER = "hive_user";
	public static final String HIVE_PASSWORD = "hive_password";
	public static final String LIMIT_NUM = "limit_num";
	public static final String HADOOP_MULTIPLE_URL = "hadoop_multiple_url";
	public static final String MULTIPLE_ENABLED = "multiple_hadoop";
	public static final String MONITOR_FILTER_RESULT_PATH = "monitor_filter_result_path";
	public static final String MONITOR_FILTER_BAK_PATH = "monitor_filter_bak_path";
	public static final String SMMS_REQUEST_URL = "smms_request_url";
	public static final String RESOURCE_MONITOR_FLAG = "resource_monitor_flag";
	public static final String TIANJIN_BASE_UNIT_ID = "tianjin_base_unit_id";
	public static final String TIME_SPLIT_INTERVAL = "time_split_interval";
	public static final String UPLOAD_FILE_PATH = "upload_file_path";
	public static final String UPLOAD_FILE_BAK_PATH = "upload_file_bak_Path";
	public static final String UPLOAD_ZIP_FILE_PATH = "upload_zip_file_path";
	public static final String UPLOAD_WS_FILE_PATH = "upload_ws_file_path";
	public static final String UPLOAD_WS_FILE_BAK_PATH = "upload_ws_file_bak_path";
	public static final String UPLOAD_WS_ZIP_FILE_PATH = "upload_ws_zip_file_path";
	public static final String DOWNLOAD_FILE_PATH = "download_file_path";
	public static final String DOWNLOAD_FILE_BAK_PATH = "download_file_bak_path";
	public static final String DOWNLOAD_ZIP_FILE_PATH = "download_zip_file_path";
	public static final String ATTACHMENT_DATA_PATH = "attachment_data_path";
	public static final String IMPALA_DATABASE = "impala_database";
	public static final String HADOOP_LOGNUM="hadoop_lognum";
	public static final String MONITORITEM_FILE_PATH="monitoritem_file_path";
	public static final String EVENTITEM_FILE_PATH="eventitem_file_path";
	public static final String EXPORT_FILE_PATH="export_filePath";
	
}
