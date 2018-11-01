package com.aotain.common.utils.tools;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * Xml工具
 * 
 * @author liuz@aotian.com
 * @date 2017年11月10日 下午2:20:13
 */
public class XmlUtils {
	/**
	 * 获取xml的根节点名
	 * 
	 * @param istream
	 * @return
	 * @throws DocumentException
	 * @throws IOException
	 */
	public static String getRootName(InputStream istream) throws Exception {
		if (istream == null) {
			return null;
		}
		String ret = "";
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(istream);
			Element root = doc.getRootElement();
			ret = StringUtils.trim(root.getName());
		} finally {
			istream.reset();	// 重置流
		}
		return ret;
	}

	/**
	 * 将xml stream转成漂亮模式的xml String
	 * @param istream
	 * @return
	 * @throws Exception
	 */
	public static String parseXmlStr(InputStream istream) throws Exception {
		if (istream == null) {
			return null;
		}
		SAXReader reader = new SAXReader();
		StringWriter sw = new StringWriter();
		XMLWriter writer = new XMLWriter(sw,OutputFormat.createPrettyPrint());
		
		try {
			Document doc = reader.read(new InputStreamReader(istream),"UTF-8");
			writer.write(doc);
			writer.close();
			return sw.toString();
		} finally {
			istream.reset();
		}
	}

	/**
	 * 有xml流创建Java对象
	 * 
	 * @param istream
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createBean(InputStream istream, Class<T> cls) throws Exception {
		T ret = null;
		JAXBContext context = JAXBContext.newInstance(cls);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		BufferedReader reader = new BufferedReader(new InputStreamReader(istream, "UTF-8"));
		ret = (T) unmarshaller.unmarshal(reader);
		return ret;
	}

	/**
	 * 将对象转成Xml字符串
	 * 
	 * @param data
	 * @return
	 * @throws JAXBException
	 * @throws UnsupportedEncodingException
	 */
	public static <T> String toXmlString(T data) throws JAXBException, UnsupportedEncodingException {
		if (data == null) {
			return null;
		}
		String tmp = "";
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		try {
			JAXBContext context = JAXBContext.newInstance(data.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.marshal(data, buffer);
			tmp = buffer.toString("UTF-8");
			return tmp;
		} finally {
			if (buffer != null) {
				try {
					buffer.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
