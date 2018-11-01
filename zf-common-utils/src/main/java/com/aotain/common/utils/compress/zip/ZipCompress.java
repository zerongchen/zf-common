package com.aotain.common.utils.compress.zip;


import com.aotain.common.utils.compress.ICompressDecompress;
import com.aotain.common.utils.compress.IOUtil;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * zip压缩
 * @author Administrator
 *
 */
public class ZipCompress extends ICompressDecompress {

    @Override
    public void process(File inputFile, File outputFile) {
        // TODO Auto-generated method stub

        if (inputFile.exists()) {
            // 压缩文件名=源文件名.zip
            if (outputFile.exists()) {
                outputFile.delete(); // 删除旧的文件
            }
            FileOutputStream fos = null;
            ZipOutputStream zos = null;
            try {
                fos = new FileOutputStream(outputFile);
                zos = new ZipOutputStream(new BufferedOutputStream(fos));
                // 添加对应的文件Entry
                addEntry("/", inputFile, zos);
            }
            catch (IOException e) {
                logger.error("zip compress error! file=" + inputFile.getAbsolutePath(), e);
            }
            finally {
                IOUtil.closeQuietly(zos, fos);
            }
        }
    }

    /**
     * 扫描添加文件Entry
     * 
     * @param base
     *            基路径
     * @param source
     *            源文件
     * @param zos
     *            Zip文件输出流
     * @throws IOException
     */
    private void addEntry(String base, File source, ZipOutputStream zos)
        throws IOException {
        // 按目录分级，形如：/aaa/bbb.txt
        String entry = base + source.getName();
        if (source.isDirectory()) {
            for (File file : source.listFiles()) {
                // 递归列出目录下的所有文件，添加文件Entry
                addEntry(entry + "/", file, zos);
            }
        }
        else {
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                byte[] buffer = new byte[1024 * 10];
                fis = new FileInputStream(source);
                bis = new BufferedInputStream(fis, buffer.length);
                int read = 0;
                zos.putNextEntry(new ZipEntry(entry));
                while ((read = bis.read(buffer, 0, buffer.length)) != -1) {
                    zos.write(buffer, 0, read);
                }
                zos.closeEntry();
            }
            finally {
                IOUtil.closeQuietly(bis, fis);
            }
        }
    }
}
