package com.aotain.common.utils.azkaban;

import com.aotain.common.config.LocalConfig;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author cym
 * 
 */
public class AzkabanUtils {

	private static Logger logger = LoggerFactory.getLogger(AzkabanUtils.class);

	private String azkabanUrl;

	private String azUserName;

	private String azPassword;

	RestTemplate restTemplate = null;

	public AzkabanUtils() {
		try {
			this.azkabanUrl = LocalConfig.getInstance().getHashValueByHashKey("azkaban.url");
			this.azUserName = LocalConfig.getInstance().getHashValueByHashKey("azkaban.username");
			this.azPassword = LocalConfig.getInstance().getHashValueByHashKey("azkaban.password");
			SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
			requestFactory.setConnectTimeout(2000);
			requestFactory.setReadTimeout(2000);
			restTemplate = new RestTemplate();
		} catch (Exception e) {
			logger.error(" constr azkaban error",e);
		}
	}

	public AzkabanUtils(String houseIdstr) {
		try {
			// "GD_ATKJ_IDC"
			this.azkabanUrl = LocalConfig.getInstance().getAzkabanUrlByHouseIdStr(houseIdstr);
			this.azUserName = LocalConfig.getInstance().getHashValueByHashKey("azkaban.username");
			this.azPassword = LocalConfig.getInstance().getHashValueByHashKey("azkaban.password");
			SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
			requestFactory.setConnectTimeout(2000);
			requestFactory.setReadTimeout(2000);
			restTemplate = new RestTemplate();
		} catch (Exception e) {
			logger.error("houseIdstr={" + houseIdstr + "} get azkabanUrl={" + azkabanUrl + "} error ",e);
		}
	}

	public AzkabanUtils(String azkabanUrl,String type) {
		try {
			// "GD_ATKJ_IDC"
			this.azkabanUrl = azkabanUrl;
			this.azUserName = LocalConfig.getInstance().getHashValueByHashKey("azkaban.username");
			this.azPassword = LocalConfig.getInstance().getHashValueByHashKey("azkaban.password");
			SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
			requestFactory.setConnectTimeout(2000);
			requestFactory.setReadTimeout(2000);
			restTemplate = new RestTemplate();
		} catch (Exception e) {
			logger.error("houseIdstr={delete azkaban project} get azkabanUrl={" + azkabanUrl + "} error ",e);
		}
	}
	
	/**
	 * 登陆azkaban
	 * 
	 * @throws Exception
	 */
	public String loginAzkaban() throws Exception {
		SSLUtil.turnOffSslChecking();
		HttpHeaders hs = new HttpHeaders();
		hs.add("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		hs.add("X-Requested-With", "XMLHttpRequest");

		LinkedMultiValueMap<String, String> linkedMultiValueMap = new LinkedMultiValueMap<String, String>();
		linkedMultiValueMap.add("action", "login");
		linkedMultiValueMap.add("username", azUserName);
		linkedMultiValueMap.add("password", azPassword);
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(
				linkedMultiValueMap, hs);
		return restTemplate.postForObject(azkabanUrl, httpEntity, String.class);
	}

	/**
	 * 创建一个project
	 * 
	 * @param sessionId
	 * @param projectName
	 * @param description
	 * @return
	 * @throws Exception
	 */
	public String createAzProject(AzkabanUtils azkabanUtils, String sessionId, String projectName, String description)
			throws Exception {
		SSLUtil.turnOffSslChecking();
		HttpHeaders hs = new HttpHeaders();
		hs.add("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		hs.add("X-Requested-With", "XMLHttpRequest");

		azkabanUtils.deleteProject(sessionId, projectName);

		LinkedMultiValueMap<String, String> linkedMultiValueMap = new LinkedMultiValueMap<String, String>();
		linkedMultiValueMap.add("session.id", sessionId);
		linkedMultiValueMap.add("action", "create");
		linkedMultiValueMap.add("name", projectName);
		linkedMultiValueMap.add("description", description);
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(
				linkedMultiValueMap, hs);
		String postForObject = restTemplate.postForObject(azkabanUrl + "/manager", httpEntity, String.class);
		return postForObject;
	}

	/**
	 * 上传zip包
	 * 
	 * @param zipPath
	 * @param sessionId
	 * @param projectName
	 * @throws Exception
	 */
	public String uploadZip(String zipPath, String sessionId, String projectName) throws Exception {
		SSLUtil.turnOffSslChecking();
		if (!new File(zipPath).exists()) {
			throw new RuntimeException("zip file is not exists");
		}
		FileSystemResource resource = new FileSystemResource(new File(zipPath));
		LinkedMultiValueMap<String, Object> linkedMultiValueMap = new LinkedMultiValueMap<String, Object>();
		linkedMultiValueMap.add("session.id", sessionId);
		linkedMultiValueMap.add("ajax", "upload");
		linkedMultiValueMap.add("project", projectName);
		linkedMultiValueMap.add("file", resource);
		String postForObject = restTemplate.postForObject(azkabanUrl + "/manager", linkedMultiValueMap, String.class);
		return postForObject;
	}

	/**
	 * 删除一个project
	 * 
	 * @param sessionId
	 * @param projectName
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity<String> deleteProject(String sessionId, String projectName) throws Exception {
		SSLUtil.turnOffSslChecking();
		HttpHeaders hs = new HttpHeaders();
		hs.add("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		hs.add("X-Requested-With", "XMLHttpRequest");
		hs.add("Accept", "text/plain;charset=utf-8");

		Map<String, String> map = new HashMap<String, String>();
		map.put("id", sessionId);
		map.put("project", projectName);
		ResponseEntity<String> exchange = restTemplate.exchange(azkabanUrl
				+ "/manager?session.id={id}&delete=true&project={project}", HttpMethod.GET, new HttpEntity<String>(hs),
				String.class, map);
		return exchange;
	}

	/**
	 * 获取一个project的流ID
	 * 
	 * @param sessionId
	 * @param projectName
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	public ResponseEntity<String> getFlowIdByProject(String sessionId, String projectName)
			throws KeyManagementException, NoSuchAlgorithmException {

		SSLUtil.turnOffSslChecking();
		HttpHeaders hs = new HttpHeaders();
		hs.add("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		hs.add("X-Requested-With", "XMLHttpRequest");
		hs.add("Accept", "text/plain;charset=utf-8");

		Map<String, String> map = new HashMap<String, String>();
		map.put("id", sessionId);
		map.put("project", projectName);
		ResponseEntity<String> exchange = restTemplate.exchange(azkabanUrl
				+ "/manager?session.id={id}&ajax=fetchprojectflows&project={project}", HttpMethod.GET,
				new HttpEntity<String>(hs), String.class, map);
		return exchange;
	}

	/**
	 * 获取一个job的流结构 依赖关系
	 * 
	 * @param sessionId
	 * @param projectName
	 * @param flowId
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	public ResponseEntity<String> getFlowDepied(String sessionId, String projectName, String flowId)
			throws KeyManagementException, NoSuchAlgorithmException {
		SSLUtil.turnOffSslChecking();
		HttpHeaders hs = new HttpHeaders();
		hs.add("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		hs.add("X-Requested-With", "XMLHttpRequest");
		hs.add("Accept", "text/plain;charset=utf-8");

		Map<String, String> map = new HashMap<String, String>();
		map.put("id", sessionId);
		map.put("project", projectName);
		map.put("flow", flowId);

		ResponseEntity<String> exchange = restTemplate.exchange(azkabanUrl
				+ "/manager?session.id={id}&ajax=fetchflowgraph&project={project}&flow={flow}", HttpMethod.GET,
				new HttpEntity<String>(hs), String.class, map);
		return exchange;
	}

	/**
	 * 执行一个工作流
	 * 
	 * @param sessionId
	 * @param projectName
	 * @param flowId
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	public ResponseEntity<String> executeFlow(String sessionId, String projectName, String flowId)
			throws KeyManagementException, NoSuchAlgorithmException {
		SSLUtil.turnOffSslChecking();
		HttpHeaders hs = new HttpHeaders();
		hs.add("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
		hs.add("X-Requested-With", "XMLHttpRequest");
		hs.add("Accept", "text/plain;charset=utf-8");

		Map<String, String> map = new HashMap<String, String>();
		map.put("id", sessionId);
		map.put("project", projectName);
		map.put("flow", flowId);
		ResponseEntity<String> exchange = restTemplate.exchange(azkabanUrl
				+ "/executor?session.id={id}&ajax=executeFlow&project={project}&flow={flow}", HttpMethod.GET,
				new HttpEntity<String>(hs), String.class, map);
		return exchange;
	}

	/**
	 * 
	 * @param sessionId
	 * @param projectName
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws Exception
	 */
	public ResponseEntity<String> execute(AzkabanUtils azkabanUtils, String sessionId, String projectName)
			throws Exception {
		String flowId = "";

		ResponseEntity<String> s = azkabanUtils.getFlowIdByProject(sessionId, projectName);
		if ("200".equals(s.getStatusCode() + "")) {
			JsonParser parser = new JsonParser();
			JsonObject object = (JsonObject) parser.parse(s.getBody());
			JsonObject flowsObject = object.get("flows").getAsJsonArray().get(0).getAsJsonObject();
			flowId = flowsObject.get("flowId").getAsString();
		} else {
			throw new RuntimeException("azkaban getFlowIdByProject error");
		}

		ResponseEntity<String> sExecute = azkabanUtils.executeFlow(sessionId, projectName, flowId);
		return sExecute;
	}

	public  String scheduleByCronEXEaFlow(String sessionId,String projectName,String corn,String flowName)
	{

		try {
			HttpHeaders hs = new HttpHeaders();
			hs.add("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
			hs.add("X-Requested-With", "XMLHttpRequest");
			LinkedMultiValueMap<String, String> linkedMultiValueMap = new LinkedMultiValueMap<String, String>();
			linkedMultiValueMap.add("session.id", sessionId);
			linkedMultiValueMap.add("ajax", "scheduleCronFlow");
			linkedMultiValueMap.add("projectName", projectName);
			linkedMultiValueMap.add("cronExpression",corn);
			linkedMultiValueMap.add("flow", flowName);
			linkedMultiValueMap.add("flowName", flowName);


			HttpEntity<LinkedMultiValueMap<String, String>> httpEntity =   new HttpEntity<>(linkedMultiValueMap, hs);

			String postForObject = restTemplate.postForObject(azkabanUrl + "/schedule", httpEntity, String.class);
			return postForObject;
		} catch (Exception e) {
			logger.error("scheduleByCronEXEaFlow error ",e);
		}
		return null;
	}

	public String getAzkabanUrl() {
		return azkabanUrl;
	}

	public String getAzUserName() {
		return azUserName;
	}

	public String getAzPassword() {
		return azPassword;
	}

}
