package com.aotain.common.utils.compress.gzip;


import com.aotain.common.utils.compress.ICompressDecompress;
import com.aotain.common.utils.compress.IOUtil;

import java.io.*;
import java.util.zip.GZIPInputStream;


/**
 * gzip 解压
 * @author Administrator
 *
 */
public class GzipDecompress extends ICompressDecompress {

    @Override
    public void process(File inputFile, File outputFile) {
        // TODO Auto-generated method stub

        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(inputFile);
            fos = new FileOutputStream(outputFile);

            decompress(fis, fos);

        }
        catch (Exception e) {
            logger.error("gzip compress error! file=" + inputFile.getAbsolutePath(), e);
        }
        finally {
            IOUtil.closeQuietly(fis, fos);
        }
    }

    /**
     * 数据解压缩
     * 
     * @param is
     * @param os
     * @throws Exception
     */
    public void decompress(InputStream is, OutputStream os)
        throws Exception {

        GZIPInputStream gis = new GZIPInputStream(is);

        int count;
        byte data[] = new byte[1024];
        while ((count = gis.read(data, 0, 1024)) != -1) {
            os.write(data, 0, count);
        }

        IOUtil.closeQuietly(gis);
    }
}
