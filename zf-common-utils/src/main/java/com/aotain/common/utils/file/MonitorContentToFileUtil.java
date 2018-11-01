package com.aotain.common.utils.file;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.nio.channels.FileLock;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 将redis中取出的监测信息(异常、线程状态)以key=value形式写入到文件中
 *
 * @author daiyh@aotain.com
 * @date 2017/12/15
 */
public class MonitorContentToFileUtil {

    private static Logger logger = LoggerFactory.getLogger(MonitorContentToFileUtil.class);

    private static String MONITOR = "monitor";

    private static ReentrantLock lock = new ReentrantLock();

    /**
     * 将redis中取出的map信息以key=value形式写入到文件中
     * @param maps
     * @return
     */
    public static boolean writeMessageToFile (String module,Map<String,String> maps) {
        // 文件保存路径
        String home = System.getenv("ZF_HOME");
//        String path = LocalConfig.getInstance().getEventItemFilePath();
        logger.info("writeMessageToFile start...,the file path is "+home);
        if ( StringUtils.isEmpty(home) ){
            logger.error("writeMessageToFile failed,because the file path was not exist...");
            return false;
        }
        home = home + File.separator + MONITOR;
        createFolderIfNotExists(home);
        String tempPath = home + File.separator + "temp";
        createFolderIfNotExists(tempPath);

        String tempFileName = tempPath + File.separator + module + "_" + MONITOR +  ".tmp";
        createFileIfNotExists(tempFileName);
        String realFileName = home + File.separator + module + "_" + MONITOR +  ".log";
        createFileIfNotExists(realFileName);

        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferWriter = null;
        FileLock fileLock = null;
        try {
            lock.lock();
            fileOutputStream = new FileOutputStream(tempFileName);
            try {
                Random random = new Random();
                Thread.sleep(random.nextLong()%300+300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fileLock = fileOutputStream.getChannel().tryLock();
            if ( fileLock==null ) {
                logger.info("文件已被占用...");
                return true;
            }

            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            bufferWriter = new BufferedWriter(outputStreamWriter);
            String line = null;

            Iterator<String> it = maps.keySet().iterator();
            while ( it.hasNext() ) {
                String key = it.next();
                String value = maps.get(key);
                line = key + "=" + value;
                bufferWriter.write(line);
                bufferWriter.newLine();
            }


            bufferWriter.flush();
            bufferWriter.close();
            outputStreamWriter.close();
            // 关闭文件流避免文件删除失败
            fileOutputStream.close();

            FileUtils.copyFile(new File(tempFileName), new File(realFileName));
            FileUtils.deleteQuietly(new File(tempFileName));

        } catch (FileNotFoundException e) {
            logger.error("writeMessageToFile failed,because the "+tempPath+"file not found",e);
            return false;
        } catch (IOException e) {
            logger.error("writeMessageToFile failed,because the IOException",e);
            return false;
        } finally {
            try {
                if (bufferWriter!=null) {
                    bufferWriter.close();
                }
                if (outputStreamWriter!=null) {
                    outputStreamWriter.close();
                }
                if (fileOutputStream!=null){
                    if (fileLock!=null&&fileLock.isValid()){
                        fileLock.release();
                    }
                }
                if (fileOutputStream!=null) {
                    fileOutputStream.close();
                }

                lock.unlock();

            } catch (Exception e) {
                logger.error("close IOStream failed...",e);
                return false;
            }

        }
        return true;
    }

    /**
     *  根据指定路径创建文件夹
     * @param path
     */
    private static void createFolderIfNotExists(String path) {
        File file = new File(path);
        if ( file.exists() ) {
            return;
        }
        file.mkdirs();
//        if (!file.getParentFile().exists()) {
//            file.mkdirs();
//        }
    }

    /**
     * 根据指定路径创建文件
     * if parentFolder is not exist it will throw exception
     * @param path
     */
    private static void createFileIfNotExists(String path) {
        File file = new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                logger.error("create file failed",e);
            }

        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        String home = System.getenv("CU_HOME");
        System.out.println(random.nextLong()%600+600);
        System.out.println(home+"=========");
    }
}
