package com.aotain.common.utils.compress.tar;


import com.aotain.common.utils.compress.ICompressDecompress;
import com.aotain.common.utils.compress.IOUtil;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;

import java.io.*;


/**
 * tar解压
 * @author Administrator
 *
 */
public class TarDecompress extends ICompressDecompress {

    @Override
    public void process(File inputFile, File outputFile) {
        // TODO Auto-generated method stub

        TarArchiveInputStream tais = null;

        try {
            tais = new TarArchiveInputStream(new FileInputStream(inputFile));
            dearchive(outputFile, tais);

        }
        catch (Exception e) {
            logger.error("tar compress error! file=" + inputFile.getAbsolutePath(), e);
        }
        finally {
            IOUtil.closeQuietly(tais);
        }
    }

    /**
     * 文件 解归档
     * 
     * @param destFile
     *            目标文件
     * @param tais
     *            ZipInputStream
     * @throws Exception
     */
    private void dearchive(File destFile, TarArchiveInputStream tais)
        throws Exception {

        TarArchiveEntry entry = null;
        while ((entry = tais.getNextTarEntry()) != null) {

            File target = null;

            if (destFile.isFile())
                target = destFile;
            else
                target = new File(destFile, entry.getName());

            // 文件检查
            fileProber(target);

            if (entry.isDirectory()) {
                target.mkdirs();
            }
            else {
                dearchiveFile(target, tais);
            }

        }
    }

    /**
     * 文件解归档
     * 
     * @param destFile
     *            目标文件
     * @param tais
     *            TarArchiveInputStream
     * @throws Exception
     */
    private void dearchiveFile(File destFile, TarArchiveInputStream tais)
        throws Exception {

        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(destFile));

            int count;
            byte data[] = new byte[1024];
            while ((count = tais.read(data, 0, 1024)) != -1) {
                bos.write(data, 0, count);
            }
        }
        finally {
            IOUtil.closeQuietly(bos);
        }
    }

    /**
     * 文件探针 <pre> 当父目录不存在时，创建目录！ </pre>
     * 
     * @param dirFile
     */
    private void fileProber(File dirFile) {

        File parentFile = dirFile.getParentFile();
        if (!parentFile.exists()) {

            // 递归寻找上级目录
            fileProber(parentFile);

            parentFile.mkdir();
        }

    }

    public void decompress(InputStream is, OutputStream os)
            throws Exception {
    	TarArchiveInputStream tais = new TarArchiveInputStream(is);
    	TarArchiveEntry tarArchiveEntry = null;
		if((tarArchiveEntry = tais.getNextTarEntry()) != null){
            int read = -1;
            byte[] buffer = new byte[1024];
            while((read = tais.read(buffer)) != -1){
            	os.write(buffer, 0, read);
            }
        }
        tais.close();
    }
}
