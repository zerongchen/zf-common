package com.aotain.common.utils.tools;

import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class HttpUtils {
	public static final String UTF8 = "UTF-8";  // 定义编码格式 UTF-8
	public static final String GBK = "GBK";  // 定义编码格式 GBK
	private static final String EMPTY = "";

	/**
	 * post请求
	 * 
	 * @param url
	 * @param formparams
	 * @param encodeCharset
	 * @return
	 * @throws IOException
	 */
	public static String postRequest(String url, List<NameValuePair> formparams, String encodeCharset)
			throws IOException {
		String postContent = EMPTY;
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		// 创建参数队列
		UrlEncodedFormEntity uefEntity;
		try {
			uefEntity = new UrlEncodedFormEntity(formparams, encodeCharset);
			httppost.setEntity(uefEntity);
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					postContent = EntityUtils.toString(entity, encodeCharset);
				}
			} finally {
				response.close();
			}
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				LogFactory.getLog(HttpUtils.class).error("post请求失败", e);
			}
		}
		return postContent;

	}
}
