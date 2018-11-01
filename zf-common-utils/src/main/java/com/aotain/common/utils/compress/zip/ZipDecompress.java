package com.aotain.common.utils.compress.zip;


import com.aotain.common.utils.compress.ICompressDecompress;
import com.aotain.common.utils.compress.IOUtil;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * zip解压
 * @author Administrator
 *
 */
public class ZipDecompress extends ICompressDecompress {

    @Override
    public void process(File inputFile, File outputFile) {
        // TODO Auto-generated method stub

        if (inputFile.exists()) {
            ZipInputStream zis = null;
            BufferedOutputStream bos = null;
            try {
                zis = new ZipInputStream(new FileInputStream(inputFile));
                ZipEntry entry = null;
                while ((entry = zis.getNextEntry()) != null && !entry.isDirectory()) {
                    File target = null;
                    if (outputFile.isFile())
                        target = outputFile;
                    else
                        target = new File(outputFile, entry.getName());

                    if (!target.getParentFile().exists()) {
                        // 创建文件父目录
                        target.getParentFile().mkdirs();
                    }
                    // 写入文件
                    bos = new BufferedOutputStream(new FileOutputStream(target));
                    int read = 0;
                    byte[] buffer = new byte[1024 * 10];
                    while ((read = zis.read(buffer, 0, buffer.length)) != -1) {
                        bos.write(buffer, 0, read);
                    }
                    bos.flush();
                }
                zis.closeEntry();

            }
            catch (IOException e) {
                logger.error("zip decompress error! file=" + inputFile.getAbsolutePath(), e);
            }
            finally {
                IOUtil.closeQuietly(zis, bos);
            }
        }
    }
}
