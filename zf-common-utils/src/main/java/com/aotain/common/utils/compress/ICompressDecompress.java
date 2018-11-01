package com.aotain.common.utils.compress;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


/**
 * 压缩/解压基类
 * @author Administrator
 *
 */
public abstract class ICompressDecompress {

    public Logger logger = LoggerFactory.getLogger(ICompressDecompress.class);

    /**
     * 压缩/解压文件
     * 
     * @param inputFile
     * @param outputFile
     */
    public void process(String inputFile, String outputFile) {
        process(new File(inputFile), new File(outputFile));
    }

    /**
     * 压缩/解压文件
     * 
     * @param inputFile
     * @param outputFile
     */
    public abstract void process(File inputFile, File outputFile);

}
