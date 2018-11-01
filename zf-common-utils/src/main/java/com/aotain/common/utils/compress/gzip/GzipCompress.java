package com.aotain.common.utils.compress.gzip;


import com.aotain.common.utils.compress.ICompressDecompress;
import com.aotain.common.utils.compress.IOUtil;

import java.io.*;
import java.util.zip.GZIPOutputStream;

/**
 * gzip 压缩
 * @author Administrator
 *
 */
public class GzipCompress extends ICompressDecompress {

    @Override
    public void process(File inputFile, File outputFile) {
        // TODO Auto-generated method stub

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(inputFile);
            fos = new FileOutputStream(outputFile);

            compress(fis, fos);

        }
        catch (Exception e) {
            logger.error("gzip compress error! file=" + inputFile.getAbsolutePath(), e);
        }
        finally {
            IOUtil.closeQuietly(fis, fos);
        }
    }

    /**
     * 数据压缩
     * 
     * @param is
     * @param os
     * @throws Exception
     */
    private void compress(InputStream is, OutputStream os)
        throws Exception {

        GZIPOutputStream gos = new GZIPOutputStream(os);

        int count;
        byte data[] = new byte[1024];
        while ((count = is.read(data, 0, 1024)) != -1) {
            gos.write(data, 0, count);
        }

        IOUtil.closeQuietly(gos);
    }
}
