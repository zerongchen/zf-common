package com.aotain.common.utils.file;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SftpClientUtil {
	
	private static Logger logger = LoggerFactory.getLogger(MonitorContentToFileUtil.class);
	
    
    private static Channel channel;
    
    private static Session session;
	
    /**
     * 获得SFTP Channel
     * @return ChannelSftp Instance
     * @throws JSchException
     */
    public static ChannelSftp getChannel(String ftpUserName,String ftpHost,String ftpPassword,int ftpPort) throws JSchException {
        // 创建JSch对象
        JSch jsch = new JSch();
        session = jsch.getSession(ftpUserName, ftpHost, ftpPort);
        if (ftpPassword != null) {
            session.setPassword(ftpPassword);
        }
        Properties configTemp = new Properties();
        configTemp.put("StrictHostKeyChecking", "no");
        session.setConfig(configTemp);
        session.setTimeout(10000);
        session.connect();
        channel = session.openChannel("sftp");
        channel.connect();
        logger.info("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = " + ftpUserName + ", returning: " + channel);
        return (ChannelSftp) channel;
    }

    /**
     * 断开SFTP Channel、Session连接
     * @throws Exception
     */
    public static void closeChannel() throws Exception {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
        logger.info("disconnected SFTP successfully!");
    }

    public static void main(String[] arg0) {
    	try {
    		SftpClientUtil chSftp = new SftpClientUtil();
    		ChannelSftp chSftp1 = chSftp.getChannel("", null, null, 0);
			chSftp1.put("", "", ChannelSftp.OVERWRITE);
			chSftp1.quit();
	    	chSftp.closeChannel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
}
