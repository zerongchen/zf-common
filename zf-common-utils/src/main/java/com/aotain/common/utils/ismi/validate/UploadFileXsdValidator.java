package com.aotain.common.utils.ismi.validate;

import com.aotain.common.utils.ismi.ConstantParams;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.XMLErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * 上传管局文件xsd校验工具
 * 
 * @author liuz@aotian.com
 * @date 2017年11月18日 上午10:39:35
 */
public class UploadFileXsdValidator {

	private static Logger logger = LoggerFactory.getLogger(UploadFileXsdValidator.class);

	private static InputStream xsdFile = null;
	private static UploadFileXsdValidator instance;

	private UploadFileXsdValidator() {
	}

	public synchronized static UploadFileXsdValidator getInstance() {
		if (instance == null) {
			instance = new UploadFileXsdValidator();
		}
		return instance;
	}

	/**
	 * 校验通过返回null，校验失败返回异常提示
	 * 
	 * @param type
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	public String doValidate(int type, InputStream inputStream) throws Exception {
		String xsdName = "";
		switch (type) {
		case ConstantParams.DT_BASIC_INFO:
			xsdName = "basicInfo.xsd";
			break;
		case ConstantParams.DT_BASIC_MONITOR:
			xsdName = "idcMonitor.xsd";
			break;
		case ConstantParams.DT_LOG_QUERY:
			xsdName = "logQueryResult.xsd";
			break;
		case ConstantParams.DT_MONITOR_QUERY:
			xsdName = "monitorResult.xsd";
			break;
		case ConstantParams.DT_FILTER_QUERY:
			xsdName = "filterResult.xsd";
			break;
		case ConstantParams.DT_COMMAND_QUERY:
			xsdName = "commandQueryResult.xsd";
			break;
		case ConstantParams.DT_ACTIVE_STATE:
			xsdName = "activeState.xsd";
			break;
		case ConstantParams.DT_ACTIVE_RESOURCES:
			xsdName = "activeResources.xsd";
			break;
		case ConstantParams.DT_ILLEGAL_WEB:
			xsdName = "illegalWeb.xsd";
			break;
		default:
			xsdName = null;
			break;
		}
		if (xsdName == null) {
			return "nonsupport type : " + type;
		}

		xsdFile = this.getClass().getResourceAsStream(xsdName);
		if (xsdFile == null) {
			return "xsd file not found : "+xsdName;
		}

		try {
			return doValidate(inputStream, xsdFile);
		} finally {
			inputStream.reset();
		}
	}

	private String doValidate(InputStream xmlStream, InputStream xsdFile) {
		if (xmlStream == null) {
			return "xml数据为空";
		}
		try {
			XMLErrorHandler errorHandler = new XMLErrorHandler();
			SAXParserFactory factory = SAXParserFactory.newInstance();
			factory.setValidating(true);
			factory.setNamespaceAware(true);
			SAXParser parser = factory.newSAXParser();
			SAXReader xmlReader = new SAXReader();
			Document xmlDocument = (Document) xmlReader.read(xmlStream);
			parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
					"http://www.w3.org/2001/XMLSchema");
			parser.setProperty("http://java.sun.com/xml/jaxp/properties/schemaSource", xsdFile);
			SAXValidator validator = new SAXValidator(parser.getXMLReader());
			validator.setErrorHandler(errorHandler);
			validator.validate(xmlDocument);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			XMLWriter writer = new XMLWriter(bos, OutputFormat.createPrettyPrint());
			if (errorHandler.getErrors().hasContent()) {
				writer.write(errorHandler.getErrors());
				writer.flush();
				return new String(bos.toByteArray(), "UTF-8");
			}
			return null;
		} catch (Exception ex) {
			logger.error("xsd格式校验发生异常", ex);
			return "格式校验发生异常";
		}
	}

}
