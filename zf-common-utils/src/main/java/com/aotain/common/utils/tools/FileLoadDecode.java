package com.aotain.common.utils.tools;

import com.aotain.common.utils.model.smmsupload.FileLoad;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 上报文件解码工具
 * 
 * @author liuz@aotian.com
 * @date 2017年12月13日 下午6:11:59
 */
public class FileLoadDecode {
	private static LocalConfig cfg;

	static {
		cfg = new LocalConfig();
	}

	public static void main(String[] args) throws IOException {
		String path = ".";
		if (args != null && args.length > 0) {
			path = args[0];
		}

		File f = new File(path);
		List<File> files = new ArrayList<File>();
		if (f.isDirectory()) {
			File[] list = f.listFiles(new FileFilter() {

				public boolean accept(File pathname) {
					if (pathname.isDirectory()) {
						return false;
					}
					return pathname.getAbsolutePath().endsWith(".xml") && !pathname.getAbsolutePath().endsWith("_data.xml");
				}
			});
			files.addAll(Arrays.asList(list));
		}else{
			files.add(f);
		}

		for (File file : files) {
			byte[] data = org.apache.commons.io.FileUtils.readFileToByteArray(new File(path));
			FileLoad fload = null;
			try {
				fload = XmlUtils.createBean(new ByteArrayInputStream(data), FileLoad.class);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}

			if (fload == null) {
				System.out.println("解析文件成xml对象失败:" + path);
				return;
			}

			InputStream is = decodeXml(fload);
			if (is == null) {
				return;
			}
			try {

				FileUtils.writeToFile(XmlUtils.parseXmlStr(is).getBytes("UTF-8"), file.getAbsolutePath().replace(".xml", "_data.xml"));
				System.out.println("解密成功：" + file.getAbsolutePath().replace(".xml", "_data.xml"));
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("解密失败" + path);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("解密失败" + path);
			}
		}
	}

	private static class LocalConfig {
		private String aesKey;
		private String aesIv;
		private String rzKey;

		public LocalConfig() {
			Properties p = new Properties();
			try {
				p.load(new FileInputStream("config/config.properties"));
				aesKey = (String) p.get("aes.key");
				aesIv = (String) p.get("aes.iv");
				rzKey = (String) p.get("rzkey");
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}
	}

	private static InputStream decodeXml(FileLoad fload) {
		try {
			String data = fload.getDataUpload();
			byte[] raw = cfg.aesKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(cfg.aesIv.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(data);// 先用base64解密
			try {
				byte[] original = cipher.doFinal(encrypted1);

				if (original == null) {
					System.out.println("解密后未空");
					return null;
				}

				return ZipUtils.unZip2Stream(original);

			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
}
