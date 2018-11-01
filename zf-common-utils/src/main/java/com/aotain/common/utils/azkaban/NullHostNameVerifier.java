package com.aotain.common.utils.azkaban;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * @date 2017-11-28
 * @author i
 *
 */
public class NullHostNameVerifier implements HostnameVerifier {

	public boolean verify(String arg0, SSLSession arg1) {
		return true;
	}
}
