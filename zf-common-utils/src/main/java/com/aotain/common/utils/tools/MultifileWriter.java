package com.aotain.common.utils.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultifileWriter{
	
	/**
     * 写日志
     */
    private Logger logger = LoggerFactory.getLogger(MultifileWriter.class);
    
	private static MultifileWriter instance;

	private MultifileWriter(){
		Thread thread = new Thread() {
			
			@Override
			public void run() {
				while(true){
					Date date = new Date(System.currentTimeMillis());
	
				    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
					String dateString = formatter.format(date);
					int iDate = Integer.parseInt(dateString);
				    
					flush(iDate);
					
					try {
						Thread.sleep(30000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}
	
	public synchronized static MultifileWriter getInstance() {

		if (instance == null) {
			instance = new MultifileWriter();
		}
		return instance;
	}
	
	// the map's key is path
	private final static ConcurrentHashMap<String, BufferedWriter> writersMap = new ConcurrentHashMap<String, BufferedWriter>();

	public void writeLine(String path, String line) {
		try {
			BufferedWriter bw = writersMap.get(path);
			if (null == bw) {
				
				File file = new File(path);
				if(file.exists()) file.delete();
				bw = new BufferedWriter(new OutputStreamWriter(FileUtils.openOutputStream(file,
						true),"utf-8"));
				writersMap.put(path, bw);
			}
			bw.write(line);
			bw.newLine();
			bw.flush();
		} catch (Exception e) {
			logger.error("Write data to file exception.path=" + path ,e);
		} 
	}

	public void flush(int date) {
		String filename = "";
		try {
			for (Entry<String, BufferedWriter> entry : writersMap.entrySet()) {
				String path = entry.getKey();
				BufferedWriter bw = entry.getValue();
				bw.flush();
				
				filename = entry.getKey();
				String time = filename.substring(filename.lastIndexOf("_")+1,filename.lastIndexOf("."));
				
				if(date> Integer.parseInt(time)){
					bw.close();	
					writersMap.remove(path);
				}
				
			}
		} catch (Exception e) {
			logger.error("Failed to flush file.filename=" + filename, e);
		}
	}
	
	public void close(String filename) {
		try {
			BufferedWriter bw = writersMap.get(filename);
			if(bw != null) {
				bw.flush();
				bw.close();	
				writersMap.remove(filename);
			}
		} catch (Exception e) {
			logger.error("Failed to close file.filename=" + filename , e);
		}
	}
	
	public void closeAndRename(String filename,String newFilename) {
		try {
			BufferedWriter bw = writersMap.get(filename);
			if(bw != null) {
				bw.flush();
				bw.close();	
				writersMap.remove(filename);
			}
			File file = new File(filename);
			file.renameTo(new File(newFilename));
			
		} catch (Exception e) {
			logger.error("Failed to close And rename file.filename=" + filename +",newFilename=" + newFilename, e);
		}
	}
	
}
