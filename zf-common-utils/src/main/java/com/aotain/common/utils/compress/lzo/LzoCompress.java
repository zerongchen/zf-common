package com.aotain.common.utils.compress.lzo;


import com.aotain.common.utils.compress.ICompressDecompress;
import com.aotain.common.utils.compress.IOUtil;
import com.hadoop.compression.lzo.LzopCodec;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.compress.CompressionOutputStream;

import java.io.*;


/**
 * lzo压缩
 * @author Administrator
 *
 */
public class LzoCompress extends ICompressDecompress {

    @Override
    public void process(File inputFile, File outputFile) {
        // TODO Auto-generated method stub

        FileInputStream fis = null;
        FileOutputStream fos = null;
        CompressionOutputStream lzoOut = null;

        try {
            fis = new FileInputStream(inputFile);
            fos = new FileOutputStream(outputFile);

            // LzoAlgorithm algorithm = LzoAlgorithm.LZO1X;
            // LzoCompressor compressor = LzoLibrary.getInstance().newCompressor(algorithm, null);
            // LzoOutputStream lzoOut = new LzoOutputStream(fos, compressor, 256);

            Configuration conf = new Configuration();
            conf.set("mapred.job.tracker", "local");
            // conf.set("fs.default.name", "file:///");
            conf.set("io.compression.codecs", "com.hadoop.compression.lzo.LzoCodec");

            LzopCodec lzo = new LzopCodec();
            lzo.setConf(conf);

            lzoOut = lzo.createOutputStream(fos);

            int count;
            byte[] data = new byte[1024];
            while ((count = fis.read(data, 0, 1024)) != -1) {
                lzoOut.write(data, 0, count);
            }

        }
        catch (Exception e) {
            logger.error("lzo compress error! file=" + inputFile.getAbsolutePath(), e);
        }
        finally {
            IOUtil.closeQuietly(lzoOut, fis, fos);
        }
    }
    
    
    public static void process(InputStream inputStream, OutputStream outputStream) {

        CompressionOutputStream lzoOut = null;

        try {
            Configuration conf = new Configuration();
            conf.set("mapred.job.tracker", "local");
            // conf.set("fs.default.name", "file:///");
            conf.set("io.compression.codecs", "com.hadoop.compression.lzo.LzoCodec");

            LzopCodec lzo = new LzopCodec();
            lzo.setConf(conf);

            lzoOut = lzo.createOutputStream(outputStream);

            int count;
            byte[] data = new byte[1024];
            while ((count = inputStream.read(data, 0, 1024)) != -1) {
                lzoOut.write(data, 0, count);
            }

        }
        catch (Exception e) {
            System.out.println("lzo compress error! "+ e.toString());
        }
        finally {
            IOUtil.closeQuietly(lzoOut);
        }
    }

}
