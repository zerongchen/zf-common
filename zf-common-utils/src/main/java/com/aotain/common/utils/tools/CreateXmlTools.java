package com.aotain.common.utils.tools;

import com.aotain.common.config.LocalConfig;
import com.aotain.common.utils.ismi.ConstantParams;
import com.aotain.common.utils.ismi.validate.UploadFileXsdValidator;
import com.aotain.common.utils.kafka.NoticeQueueUtils;
import com.aotain.common.utils.model.msg.FileUploadInfo;
import com.aotain.common.utils.model.msg.NoticeQueueMessage;
import com.aotain.common.utils.model.msg.RedisTaskStatus;
import com.aotain.common.utils.model.smmsupload.*;
import com.aotain.common.utils.redis.TaskIdUtil;
import com.aotain.common.utils.redis.TaskMessageUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 上报管局xml文件创建与校验工具
 * 
 * @author liuz@aotian.com
 * @date 2017年11月18日 上午9:38:41
 */
public class CreateXmlTools {
	private static Logger logger = LoggerFactory.getLogger(CreateXmlTools.class);

	/**
	 * 创建上报xml文件，并使用xml校验文件内容，成功向redis添加一个ack上报任务
	 * 
	 * @param type 文件类型参见:com.aotain.common.ConstantParams.DT_*
	 * @param idcId 经营者ID
	 * @param bean 待转换的数据，只能是type指定文件类型所对应的数据类型
	 * @param topTaskId 父任务的ID
	 * @param recordCounts bean数据中对应的记录条数，传入null或者小于0的值，将转换成-1(表示未知条数)
	 * @return 失败返回error信息，成功返回创建FileUpload xml的路径
	 */
	public static Result validateAndCreateXml(int type, String idcId, Object bean, Long topTaskId,Integer... recordCounts) {
		Result result = new Result();
		// 0. 入参格式校验
		String error = validateParams(type, idcId, bean);
		if (!StringUtils.isBlank(error)) {
			result.setError(error);
			logger.error(result.getError());
			return result;
		}
		String identify = "type=" + type + ",data-class=" + bean.getClass().getName();

		String xmlStr = null;
		// 1. 在内存中创建xml文件
		try {
			xmlStr = XmlUtils.toXmlString(bean);
		} catch (Exception e) {
			result.setError("create xml exception : " + identify);
			logger.error(result.getError(), e);
			return result;
		}
		if (StringUtils.isBlank(xmlStr)) {
			result.setError("xml-file is empty after create xml : " + identify);
			logger.error(result.getError());
			return result;
		}

		// 2. 对xmlStr进行xsd校验
		try {
			error = UploadFileXsdValidator.getInstance().doValidate(type, new ByteArrayInputStream(xmlStr.getBytes("UTF-8")));
			if (!StringUtils.isBlank(error)) {
				result.setError("xml-file xsd validate fail - " + error + " : " + identify);
				logger.error(result.getError());
				return result;
			}
		} catch (Exception e) {
			result.setError("xml-file xsd validate exception : " + identify);
			logger.error(result.getError(), e);
			return result;
		}

		// 3. xml压缩
		byte[] data = null;
		Long taskId = TaskIdUtil.getInstance().getTaskId();
		String fileName = createFileName(taskId);

		try {
			data = ZipUtils.zip(xmlStr, fileName + ".xml", "UTF-8");
			if (data == null || data.length == 0) {
				result.setError("xml-file data is empty after zip compress : " + identify);
				logger.error(result.getError());
				return result;
			}
		} catch (IOException e) {
			result.setError("xml-file zip compress exception : " + identify);
			logger.error(result.getError(), e);
			return result;
		}

		// 4. 对压缩后的xml进行加密等处理
		FileLoad upload = new FileLoad();
		try {
			String aesKey = (String) LocalConfig.getInstance().getHashValueByHashKey(ConstantParams.AES_KEY);
			String aesIv = (String) LocalConfig.getInstance().getHashValueByHashKey(ConstantParams.AES_IV);
			String rzKey = (String) LocalConfig.getInstance().getHashValueByHashKey(ConstantParams.RZ_KEY);
			String wsVersion = (String) LocalConfig.getInstance().getHashValueByHashKey(ConstantParams.VERSION);
			upload.setDataUpload(AES.Encrypt(data, aesKey, aesIv));
			byte[] beianBytes = Tools.joinBytes(data, rzKey.getBytes("UTF-8"));
			upload.setDataHash(MD5.Encrypt2(beianBytes));

			upload.setIdcId(idcId);
			upload.setEncryptAlgorithm(new BigInteger("1"));
			upload.setCompressionFormat(new BigInteger("1"));
			upload.setHashAlgorithm(new BigInteger("1"));
			upload.setCommandVersion(wsVersion);
		} catch (Exception e) {
			result.setError("xml-zip-file  encrypt exception : " + identify);
			logger.error(result.getError(), e);
			return result;
		}
		if (upload == null || StringUtils.isBlank(upload.getDataUpload())
				|| StringUtils.isBlank(upload.getDataHash())) {
			result.setError("xml-zip-encrypt-fileload contains empty datanode  : " + identify);
			logger.error(result.getError());
			return result;
		}

		// 5. 创建结果xml文件
		try {
			xmlStr = XmlUtils.toXmlString(upload);
		} catch (Exception e) {
			result.setError("create result-xml exception : " + identify);
			logger.error(result.getError(), e);
			return result;
		}
		if (StringUtils.isBlank(xmlStr)) {
			result.setError("result-xml-file is empty after create xml : " + identify);
			logger.error(result.getError());
			return result;
		}

		// 6. 将xml文件写入上传目录
		String resultFileName = null;
		FileOutputStream outStream = null;
		try {
			resultFileName = getUploadDir(String.valueOf(type)) + fileName + ".tmp";
			outStream = new FileOutputStream(resultFileName);
			// 写入数据
			outStream.write(xmlStr.getBytes("UTF-8"));
			outStream.flush();
		} catch (Exception e) {
			result.setError("result-xml-file write to upload dir exception : " + identify);
			logger.error(result.getError(), e);
			return result;
		} finally {
			if (outStream != null) {
				try {
					outStream.close();
				} catch (Exception e1) {
				}
			}
		}

		// 添加到任务hash
		String destFilePath = resultFileName.replaceAll("\\.tmp$", ".xml");
		addTaskToRedis(taskId, fileName+".xml", destFilePath, topTaskId,recordCounts);

		// 完成后修改文件名
		try {
			if (FileUtils.renameFile(resultFileName, destFilePath)) {
				result.setXmlPath(destFilePath);
				logger.info("result-xml-file create success - " + result.getXmlPath() + " : " + identify);
			} else {
				result.setError("result-xml-file create success but rename to .tmp to .xml fail : " + identify);
				logger.error(result.getError());
			}
		} catch (Exception e) {
			result.setError("result-xml-file create success but rename to .tmp to .xml exception : " + identify);
			logger.error(result.getError(), e);
		}
		return result;
	}

	/**
	 * 创建一个缓存管理器
	 * 
	 * @param cacheDirName
	 * @return
	 * @throws Exception
	 */
	public static CacheDirManager createCacheDirManager(String cacheDirName, Long topTaskId) throws Exception {
		return new CacheDirManager(cacheDirName, topTaskId);
	}

	/**
	 * 在缓存目录创建上报xml文件，并使用xml校验文件内容
	 * 
	 * @param type 文件类型参见:com.aotain.common.ConstantParams.DT_*
	 * @param idcId 经营者ID
	 * @param bean 待转换的数据，只能是type指定文件类型所对应的数据类型
	 * @param cacheDirManager 缓存管理器，用户创建一个缓存管理器用于同一批上报文件的上报
	 * @param recordCounts bean数据中对应的记录条数，传入null或者小于0的值，将转换成-1(表示未知条数)
	 * @return
	 */
	public static Result validateAndCreateXmlToCacheDir(int type, String idcId, Object bean,
			CacheDirManager cacheDirManager,Integer... recordCounts) {
		Result result = new Result();
		// 入参格式校验
		String error = validateParams(type, idcId, bean, cacheDirManager);
		if (!StringUtils.isBlank(error)) {
			result.setError(error);
			return result;
		}
		String identify = "type=" + type + ",data-class=" + bean.getClass().getName();

		String xmlStr = null;
		// 1. 在内存中创建xml文件
		try {
			xmlStr = XmlUtils.toXmlString(bean);
		} catch (Exception e) {
			result.setError("create xml exception : " + identify);
			logger.error(result.getError(), e);
			return result;
		}
		if (StringUtils.isBlank(xmlStr)) {
			result.setError("xml-file is empty after create xml : " + identify);
			logger.error(result.getError());
			return result;
		}

		// 2. 对xmlStr进行xsd校验
		try {
			error = UploadFileXsdValidator.getInstance().doValidate(type, new ByteArrayInputStream(xmlStr.getBytes("UTF-8")));
			if (!StringUtils.isBlank(error)) {
				result.setError("xml-file xsd validate fail - " + error + " : " + identify);
				logger.error(result.getError());
				return result;
			}
		} catch (Exception e) {
			result.setError("xml-file xsd validate exception : " + identify);
			logger.error(result.getError(), e);
			return result;
		}

		// 3. xml压缩
		byte[] data = null;
		Long taskId = TaskIdUtil.getInstance().getTaskId();
		String fileName = createFileName(taskId);
		try {
			data = ZipUtils.zip(xmlStr, fileName + ".xml", "UTF-8");
			if (data == null || data.length == 0) {
				result.setError("xml-file data is empty after zip compress : " + identify);
				logger.error(result.getError());
				return result;
			}
		} catch (IOException e) {
			result.setError("xml-file zip compress exception : " + identify);
			logger.error(result.getError(), e);
			return result;
		}

		// 4. 对压缩后的xml进行加密等处理
		FileLoad upload = new FileLoad();
		try {
			String aesKey = (String) LocalConfig.getInstance().getHashValueByHashKey(ConstantParams.AES_KEY);
			String aesIv = (String) LocalConfig.getInstance().getHashValueByHashKey(ConstantParams.AES_IV);
			String rzKey = (String) LocalConfig.getInstance().getHashValueByHashKey(ConstantParams.RZ_KEY);
			String wsVersion = (String) LocalConfig.getInstance().getHashValueByHashKey(ConstantParams.VERSION);
			upload.setDataUpload(AES.Encrypt(data, aesKey, aesIv));
			byte[] beianBytes = Tools.joinBytes(data, rzKey.getBytes("UTF-8"));
			upload.setDataHash(MD5.Encrypt2(beianBytes));

			upload.setIdcId(idcId);
			upload.setEncryptAlgorithm(new BigInteger("1"));
			upload.setCompressionFormat(new BigInteger("1"));
			upload.setHashAlgorithm(new BigInteger("1"));
			upload.setCommandVersion(wsVersion);
		} catch (Exception e) {
			result.setError("xml-zip-file  encrypt exception : " + identify);
			logger.error(result.getError(), e);
			return result;
		}
		if (upload == null || StringUtils.isBlank(upload.getDataUpload())
				|| StringUtils.isBlank(upload.getDataHash())) {
			result.setError("xml-zip-encrypt-fileload contains empty datanode  : " + identify);
			logger.error(result.getError());
			return result;
		}

		// 5. 创建结果xml文件
		try {
			xmlStr = XmlUtils.toXmlString(upload);
		} catch (Exception e) {
			result.setError("create result-xml exception : " + identify);
			logger.error(result.getError(), e);
			return result;
		}
		if (StringUtils.isBlank(xmlStr)) {
			result.setError("result-xml-file is empty after create xml : " + identify);
			logger.error(result.getError());
			return result;
		}

		// 6. 将xml文件写入缓存目录
		String resultFileName = null;
		FileOutputStream outStream = null;
		try {
			// 缓存目录不存在时，创建缓存目录
			if (!cacheDirManager.isExist() && !cacheDirManager.createDirs()) {
				result.setError(
						"result-xml-file write to cache dir fail , cause by create cache directory fail : " + identify);
				logger.error(result.getError());
				return result;
			}
			resultFileName = cacheDirManager.getCacheDirPath() + fileName + ".xml";
			outStream = new FileOutputStream(resultFileName);
			// 写入数据
			outStream.write(xmlStr.getBytes("UTF-8"));
			outStream.flush();
			result.setXmlPath(resultFileName);
			cacheDirManager.addRecordCount(taskId, recordCounts); // 将记录条数添加到缓存
		} catch (Exception e) {
			result.setError("result-xml-file write to cache dir exception : " + identify);
			logger.error(result.getError(), e);
			return result;
		} finally {
			if (outStream != null) {
				try {
					outStream.close();
				} catch (Exception e1) {
				}
			}
		}
		return result;
	}

	/**
	 * 提交缓存区中的所有文件到响应的
	 * 
	 * @param type 文件类型参见:com.aotain.common.ConstantParams.DT_*
	 * @param cacheDirManager 缓存管理工具，此处用于指定一个对应的文件夹
	 * @return 返回提交成功的文件数量（提交成功是指，写入成功，并写入任务hash成功）
	 */
	public static int submitCacheFiles(int type, CacheDirManager cacheDirManager) throws Exception {
		if (type < 1 || type > 9) {
			throw new Exception("nonsupport type : " + type);
		}

		if (cacheDirManager == null) {
			throw new Exception("cacheDirManager is empty");
		}

		// 没有文件（包括目录不存在，目录为空的情况）
		File[] files = cacheDirManager.listXmlFiles();
		if (files == null || files.length == 0) {
			return 0;
		}

		List<String> moviedTmpFilePaths = new ArrayList<String>();
		int renameSuccessCount = 0;
		// 循环拷贝文件到正式目录,创建hash,写入redis
		try {
			// 先上报文件
			for (File file : files) {
				String path = getUploadDir(String.valueOf(type)) + file.getName(); // 正式路径
				// 写任务hash
				Long taskId = parseTaskId(file.getName());
				if(taskId != null){
					addTaskToRedis(taskId, file.getName(), path, cacheDirManager.topTaskId,cacheDirManager.getRecordCount(taskId));
				}
			}
			// 后移动文件
			for (File file : files) {
				// 移动文件
				String path = getUploadDir(String.valueOf(type)) + file.getName(); // 正式路径
				File destFile = new File(path);
				org.apache.commons.io.FileUtils.moveFile(file, destFile);
				moviedTmpFilePaths.add(path);
				renameSuccessCount++;
			}
			logger.info("success to sumit " + renameSuccessCount + " cache files  in  " + cacheDirManager.cacheDirName);
		} catch (Exception e) { // 拷贝与写redis过程中的任何失败，都会导致提交缓存失败
			// 删除正式目录中的缓存文件
			FileUtils.deleteFilesQuietly(moviedTmpFilePaths);
			throw new Exception("submit cache files exception , cause by move files or write task hash exception", e);
		}

		return renameSuccessCount;
	}

	/**
	 * 入参基本格式校验
	 * 
	 * @param type
	 * @param idcId
	 * @param bean
	 * @param cacheDirManager
	 * @return
	 */
	private static String validateParams(int type, String idcId, Object bean, CacheDirManager cacheDirManager) {
		String result = validateParams(type, idcId, bean);
		if (result == null && cacheDirManager == null) {
			return "cacheDirManager is empty";
		}
		return result;
	}

	/**
	 * 入参基本格式校验
	 * 
	 * @param type
	 * @param idcId
	 * @param bean
	 * @return
	 */
	private static String validateParams(int type, String idcId, Object bean) {
		if (StringUtils.isBlank(idcId)) {
			return "idcId is empty";
		}
		if (bean == null) {
			return "data is empty";
		}
		String error = null;
		switch (type) {
		// 基础数据记录类型
		case ConstantParams.DT_BASIC_INFO:
			if (!(bean instanceof BasicInfo)) {
				error = "file type DT_BASIC_INFO[" + type + "] need " + BasicInfo.class.getName() + " rather than "
						+ bean.getClass().getName();
			}
			break;
		// 基础数据监测异常记录数据类型
		case ConstantParams.DT_BASIC_MONITOR:
			if (!(bean instanceof IdcMonitor)) {
				error = "file type DT_BASIC_MONITOR[" + type + "] need " + IdcMonitor.class.getName() + " rather than "
						+ bean.getClass().getName();
			}
			break;
		// 访问日志查询记录数据类型
		case ConstantParams.DT_LOG_QUERY:
			if (!(bean instanceof LogQueryResult)) {
				error = "file type DT_LOG_QUERY[" + type + "] need " + LogQueryResult.class.getName() + " rather than "
						+ bean.getClass().getName();
			}
			break;
		// 违法信息监测记录数据类型
		case ConstantParams.DT_MONITOR_QUERY:
			if (!(bean instanceof MonitorResult)) {
				error = "file type DT_MONITOR_QUERY[" + type + "] need " + MonitorResult.class.getName()
						+ " rather than " + bean.getClass().getName();
			}
			break;
		// 违法信息过滤记录数据类型
		case ConstantParams.DT_FILTER_QUERY:
			if (!(bean instanceof FilterResult)) {
				error = "file type DT_FILTER_QUERY[" + type + "] need " + FilterResult.class.getName() + " rather than "
						+ bean.getClass().getName();
			}
			break;
		// 基础数据查询指令数据类型
		case ConstantParams.DT_COMMAND_QUERY:
			if (!(bean instanceof CommandQueryResult)) {
				error = "file type DT_COMMAND_QUERY[" + type + "] need " + CommandQueryResult.class.getName()
						+ " rather than " + bean.getClass().getName();
			}
			break;
		// ISMS活动状态数据类型
		case ConstantParams.DT_ACTIVE_STATE:
			if (!(bean instanceof ActiveState)) {
				error = "file type DT_ACTIVE_STATE[" + type + "] need " + ActiveState.class.getName() + " rather than "
						+ bean.getClass().getName();
			}
			break;
		// 活跃资源监测记录数据类型
		case ConstantParams.DT_ACTIVE_RESOURCES:
			if (!(bean instanceof ActiveResources)) {
				error = "file type DT_ACTIVE_RESOURCES[" + type + "] need " + ActiveResources.class.getName()
						+ " rather than " + bean.getClass().getName();
			}
			break;
		// 违法违规网站监测记录类型
		case ConstantParams.DT_ILLEGAL_WEB:
			if (!(bean instanceof IllegalWeb)) {
				error = "file type DT_ILLEGAL_WEB[" + type + "] need " + IllegalWeb.class.getName() + " rather than "
						+ bean.getClass().getName();
			}
			break;
		default:
			return "nonsupport type : " + type;
		}
		return error;
	}

	/**
	 * 根据文件类型构造上传目录
	 * 
	 * @param type
	 * @return
	 */
	private static String getUploadDir(String type) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.SIMPLIFIED_CHINESE);
		String timeStr = sdf.format(new Date());
		String filepath = LocalConfig.getInstance().getHashValueByHashKey(ConstantParams.UPLOAD_FILE_PATH) + type + "/"
				+ timeStr + "/";
		File dir = new File(filepath);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		return filepath;
	}

	/**
	 * 获取本机IP
	 * 
	 * @return
	 */
	private static String getLocalIP() {
		InetAddress addr;
		try {
			addr = (InetAddress) InetAddress.getLocalHost();
			return addr.getHostAddress().toString();
		} catch (UnknownHostException e) {
			return "-";
		}
	}

	/**
	 * 创建文件名，文件名规则：时间戳（精确到秒）+10位任务ID（不足10位补0）
	 * 
	 * @param taskId
	 * @return
	 */
	private static String createFileName(Long taskId) {
		if (taskId == null) {
			return null;
		}
		String prefix = String.valueOf(System.currentTimeMillis() / 1000);
		return prefix + String.format("%010d", taskId);
	}

	/**
	 * 从文件名中解析出taskid
	 * 
	 * @param name
	 * @return
	 */
	private static Long parseTaskId(String name) {
		if (StringUtils.isBlank(name) || name.length() < 10) {
			return null;
		}
		String tname = name;
		if (name.endsWith(".xml")) {
			tname = tname.substring(0, tname.length() - 4);
		}
		int length = tname.length();
		String taskIdStr = tname.substring(length - 10, length);
		return Long.parseLong(taskIdStr);
	}

	/**
	 * 是否满足上报文件的命名规则
	 * 
	 * @param name
	 * @return
	 */
	private static boolean isUploadFile(String name) {
		if (StringUtils.isBlank(name) || name.length() < 10) {
			return false;
		}
		if (name.endsWith(".xml")) {
			return name.matches("^\\d{9,12}\\d{10}\\.xml$");
		} else {
			return name.matches("^\\d{9,12}\\d{10}$");
		}
	}

	/**
	 * 向redis jobstatus添加一个文件上报任务,并返回任务ID
	 * 
	 * @param fileName
	 * @param filePath
	 * @param toptaskid 父任务ID
	 * @param recordcounts 记录条数
	 */
	private static Long addTaskToRedis(Long taskId, String fileName, String filePath, Long toptaskid,Integer[] recordcounts) {
		// 添加任务文件到redis
		RedisTaskStatus task = new RedisTaskStatus();
		task.setTaskId(taskId);
		task.setTaskType(CommonConstant.REDIS_TASK_TYPE_FILEUPLOAD);
		task.setTopTaskId(toptaskid == null ? 0 : toptaskid);

		int  mt = 3, it = 600;
		long et = 0;
		try {
			String cfgStr = (String) LocalConfig.getInstance().getHashValueByHashKey("upload_smms_file"); // 从配置加载
			String[] args = cfgStr.split(",");
			mt = Integer.parseInt(args[0]);
			et = Integer.parseInt(args[1]);
			it = Integer.parseInt(args[3]);
		} catch (Exception e) {

		}
		task.setMaxTimes(mt);	// 文件上报3次
		task.setExpireTime(et); // 无效
		task.setInterval(it);	// 10分钟后重试
		task.setStatus(1);

		FileUploadInfo fu = new FileUploadInfo();
		fu.setCreatetime(System.currentTimeMillis() / 1000);
		fu.setFilename(fileName);
		fu.setIp(getLocalIP());
		fu.setFilepath(filePath);
		// 设置文件记录数
		if (recordcounts == null || recordcounts.length == 0) {
			Integer[] recordcount = new Integer[1];
			recordcount[0] = 0; // 表示未知条数
			fu.setRecordcount(recordcount);
		} else {
			for(int i = 0 ; i < recordcounts.length; i++){
				if(recordcounts[i] == null || recordcounts[i] < 0){
					recordcounts[i] = 0;
				}
			}
			fu.setRecordcount(recordcounts);
		}
		fu.setStatus(-1); // 初始化状态为-1
		task.setContent(fu.objectToJson());
		task.setCreateTime(System.currentTimeMillis() / 1000);
		task.setTimes(1);
		TaskMessageUtil.getInstance().setTask(taskId, task);
		// 同时写入消息通知队列，便于及时前台统计出上报文件、记录数量
		NoticeQueueUtils.sendMessage(buildNoticeMessage(task));
		return task.getTaskId();
	}
	
	/**
	 * 构造通知消息队列信息
	 * 
	 * @param task
	 * @return
	 */
	private static NoticeQueueMessage buildNoticeMessage(RedisTaskStatus task) {
		NoticeQueueMessage message = new NoticeQueueMessage();
		message.setType(CommonConstant.NOTICE_MESSAGE_TYPE_TASKSTATUS);
		message.setCreateTime(System.currentTimeMillis() / 1000);
		message.setMessage(task.objectToJson());
		return message;
	}

	public static void main(String[] args) {
		System.out.println(getLocalIP());
		String fileName = createFileName(9234567288L) + ".xml";
		System.out.println(fileName);
		System.out.println(parseTaskId(fileName));
		System.out.println(isUploadFile(fileName));
	}

	/**
	 * 校验与创建xml文件的结果
	 * 
	 * @author liuz@aotian.com
	 * @date 2017年11月18日 上午9:47:40
	 */
	public static class Result {
		private String error; // 校验错误或者异常提示信息,为空时表示创建xml成功

		private String xmlPath; // 成功创建的xml文件路径

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

		public String getXmlPath() {
			return xmlPath;
		}

		public void setXmlPath(String xmlPath) {
			this.xmlPath = xmlPath;
		}

		@Override
		public String toString() {
			return "Result [error=" + error + ", data=" + xmlPath + "]";
		}

	}

	/**
	 * 缓存目录管理器
	 * 
	 * @author liuz@aotian.com
	 * @date 2017年12月2日 下午1:46:26
	 */
	public static class CacheDirManager {
		private String cacheDirName;  // 用户缓存目录
		private File cacheDirFile; // 用户缓存目录文件
		private String cacheDirPath; // 用户缓存目录文件
		private String cacheRootPath; // 缓存文件存放根目录路径
		private Long topTaskId;
		
		private Map<Long, Integer[]> recordCountMap;

		private final static String ROOT_DIR_NAME = "upload_cache"; // 缓存根目录在备份目录下，此处定义其名称

		private CacheDirManager(String cacheDirName, Long topTaskId) throws Exception {
			if (StringUtils.isBlank(cacheDirName)) {
				throw new Exception("cacheDirName is empty");
			}
			this.topTaskId = topTaskId == null ? 0 : topTaskId;
			cacheDirName = cacheDirName.trim();
			this.cacheDirName = cacheDirName;
			this.cacheRootPath = LocalConfig.getInstance().getHashValueByHashKey(ConstantParams.UPLOAD_FILE_BAK_PATH)
					+ ROOT_DIR_NAME + "/";
			this.cacheDirPath = this.cacheRootPath + this.cacheDirName + "/";
			this.cacheDirFile = new File(this.cacheRootPath + this.cacheDirName);
			// 如果目录已存在，但不是目录需要抛出异常
			if (isExist() && !this.cacheDirFile.isDirectory()) {
				throw new Exception("cacheDirName exist and is not a directory");
			}
			recordCountMap = new HashMap<Long, Integer[]>();
		}

		/**
		 * 获取各个文件的记录数
		 * @param taskId
		 * @return
		 */
		public Integer[] getRecordCount(Long taskId) {
			return recordCountMap.get(taskId);
		}
		
		/**
		 * 新增记录数量
		 * @param taskId
		 * @param recordCount
		 */
		private void addRecordCount(Long taskId,Integer[] recordCounts){
			recordCountMap.put(taskId, recordCounts);
		}

		/**
		 * 判断缓存目录是否存在
		 * 
		 * @return
		 */
		public boolean isExist() {
			return this.cacheDirFile.exists();
		}

		/**
		 * 缓存目录不存在时创建目录
		 * 
		 * @return 如果目录已存在，或者目录创建成功返回true；否则返回false
		 */
		public boolean createDirs() {
			if (isExist()) {
				return true;
			}

			return this.cacheDirFile.mkdirs();
		}

		/**
		 * 查询缓存目录下上报文件数量
		 * 
		 * @return 目录不存在，或者没有.xml文件返回0;否则返回.xml文件数量（不包含子目录）
		 */
		public int count() {
			if (!isExist()) {
				return 0;
			}
			File[] files = listXmlFiles();
			return files == null ? 0 : files.length;
		}

		/**
		 * 清空目录，即删除目录下的所有.xml文件
		 * 
		 * @return 目录不存在，目录下无.xml文件，或者清空成功返回true；失败返回false
		 */
		public boolean clear() {
			if (!isExist()) {
				return true;
			}
			File[] files = listXmlFiles();
			if (files == null || files.length == 0) {
				return true;
			}

			for (File file : files) {
				if (!file.delete()) { // 删除失败返回失败
					return false;
				}
			}
			this.recordCountMap.clear();
			return true;
		}

		/**
		 * 清空并删除整个目录
		 * 
		 * @return 目录不存在，或者目录删除成功返回true；失败返回false
		 */
		public boolean delete() {
			if (!isExist()) {
				return true;
			}
			try {
				org.apache.commons.io.FileUtils.deleteDirectory(this.cacheDirFile);
				this.recordCountMap.clear();
				return true;
			} catch (IOException e) {
				logger.error("delete cache dir '" + this.cacheDirFile.getAbsolutePath() + "' exception", e);
				return false;
			}
		}

		/**
		 * 获取缓存目录路径
		 * 
		 * @return
		 */
		private String getCacheDirPath() {
			return this.cacheDirPath;
		}

		/**
		 * 列出目录下所有xml文件
		 * 
		 * @return
		 */
		private File[] listXmlFiles() {
			if (!isExist()) {
				return null;
			}
			return this.cacheDirFile.listFiles(new FileFilter() {

				public boolean accept(File file) {
					if (file.isDirectory()) { // 过滤目录，只扫描当前目录，不统计子目录
						return false;
					}

					return isUploadFile(file.getName());
				}
			});
		}
	}
}
