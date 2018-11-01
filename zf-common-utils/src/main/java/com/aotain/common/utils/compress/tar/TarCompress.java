package com.aotain.common.utils.compress.tar;


import com.aotain.common.utils.compress.ICompressDecompress;
import com.aotain.common.utils.compress.IOUtil;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


/**
 * tar 压缩
 * @author Administrator
 *
 */
public class TarCompress extends ICompressDecompress {

    @Override
    public void process(File inputFile, File outputFile) {
        // TODO Auto-generated method stub

        TarArchiveOutputStream taos = null;

        try {
            taos = new TarArchiveOutputStream(new FileOutputStream(outputFile));

            archive(inputFile, taos, "");

        }
        catch (Exception e) {
            logger.error("tar compress error! file=" + inputFile.getAbsolutePath(), e);
        }
        finally {
            IOUtil.closeQuietly(taos);
        }
    }

    /**
     * 归档
     * 
     * @param srcFile
     *            源路径
     * @param taos
     *            TarArchiveOutputStream
     * @param basePath
     *            归档包内相对路径
     * @throws Exception
     */
    private void archive(File srcFile, TarArchiveOutputStream taos, String basePath)
        throws Exception {
        if (srcFile.isDirectory()) {
            archiveDir(srcFile, taos, basePath);
        }
        else {
            archiveFile(srcFile, taos, basePath);
        }
    }

    /**
     * 目录归档
     * 
     * @param dir
     * @param taos
     *            TarArchiveOutputStream
     * @param basePath
     * @throws Exception
     */
    private void archiveDir(File dir, TarArchiveOutputStream taos, String basePath)
        throws Exception {

        File[] files = dir.listFiles();

        if (files.length < 1) {
            TarArchiveEntry entry = new TarArchiveEntry(basePath + dir.getName() + "/");

            taos.putArchiveEntry(entry);
            taos.closeArchiveEntry();
        }

        for (File file : files) {
            // 递归归档
            archive(file, taos, basePath + dir.getName() + "/");
        }
    }

    /**
     * 数据归档
     * 
     * @param data
     *            待归档数据
     * @param path
     *            归档数据的当前路径
     * @param name
     *            归档文件名
     * @param taos
     *            TarArchiveOutputStream
     * @throws Exception
     */
    private void archiveFile(File file, TarArchiveOutputStream taos, String dir)
        throws Exception {

        /**
         * 归档内文件名定义 <pre> 如果有多级目录，那么这里就需要给出包含目录的文件名 如果用WinRAR打开归档包，中文名将显示为乱码 </pre>
         */
        TarArchiveEntry entry = new TarArchiveEntry(dir + file.getName());
        entry.setSize(file.length());
        taos.putArchiveEntry(entry);

        BufferedInputStream bis = null;

        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            int count;
            byte data[] = new byte[1024];
            while ((count = bis.read(data, 0, 1024)) != -1) {
                taos.write(data, 0, count);
            }

            taos.closeArchiveEntry();
        }
        finally {
            IOUtil.closeQuietly(bis);
        }
    }

}
