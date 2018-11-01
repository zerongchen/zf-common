package com.aotain.common.utils.tools;

import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 文件解压缩工具
 * 
 * @author liuz@aotian.com
 * @date 2017年11月10日 上午10:35:21
 */
public class ZipUtils {
	/**
	 * 解压zip
	 *
	 * @param zipStream zip输入流
	 * @param outDir 输出目录
	 * @param isabs 是否为绝对路径
	 * @param charset 编码
	 */
	public static void unZip(InputStream zipStream, String outDir, boolean isabs, String charset) throws Exception {
		byte[] buffer = new byte[1024];
		ZipInputStream zis = new ZipInputStream(zipStream, Charset.forName(charset));
		ZipEntry ze = zis.getNextEntry();
		while (ze != null) {
			if (ze.getName().equals("/")) {
				ze = zis.getNextEntry();
				continue;
			}
			File newFile = null;
			if (isabs) {
				newFile = new File(outDir);
			} else {
				String name = ze.getName();
				name = name.replace("\\", File.separator);
				newFile = new File(outDir + File.separator + name);
			}

			File folder = newFile.getParentFile();
			if (!folder.exists()) {
				if (!folder.mkdirs()) {
					throw new IOException("目录创建失败:" + folder.getAbsolutePath());
				}
			}

			if (ze.isDirectory()) {
				if (!newFile.exists() && !newFile.mkdir()) {
					throw new IOException("目录创建失败:" + folder.getAbsolutePath());
				}
			} else {
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				closeQuietly(fos);
			}
			ze = zis.getNextEntry();
		}
		zis.closeEntry();
		zis.close();
	}

	/**
	 * 解压文件到当前目录下
	 *
	 * @param zipFile
	 * @param charset
	 * @param deleteZip 是否删除源文件
	 * @throws Exception
	 */
	public static void unZip(File zipFile, String charset, boolean deleteZip) throws Exception {
		FileInputStream fis = new FileInputStream(zipFile);
		try {
			String outDir = zipFile.getParent();
			unZip(fis, outDir, false, charset);
			if (deleteZip) {
				if (!zipFile.delete()) {
					throw new IOException("文件删除失败:" + zipFile.getAbsolutePath());
				}
			}
		} finally {
			closeQuietly(fis);
		}
	}

	/**
	 * 解压文件到当前目录下
	 * 
	 * @param zipFile
	 * @param charset
	 * @throws Exception
	 */
	public static void unZip(File zipFile, String charset) throws Exception {
		unZip(zipFile, charset, false);
	}

	/**
	 * 将gzip压缩后的单个文件到InputStream中
	 * 
	 * @param data 单个文件的压缩流
	 * @return
	 * @throws IOException
	 */
	public static InputStream unGZip2Stream(byte[] data) throws IOException {
		if (null == data || data.length <= 0) {
			return null;
		}
		// 创建一个新的 byte 数组输出流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// 创建一个 ByteArrayInputStream，使用 buf 作为其缓冲区数组
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		try {
			// 使用默认缓冲区大小创建新的输入流
			GZIPInputStream gzip = new GZIPInputStream(in);
			byte[] buffer = new byte[1024];
			int n = 0;
			while ((n = gzip.read(buffer)) >= 0) {// 将未压缩数据读入字节数组
				// 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此 byte数组输出流
				out.write(buffer, 0, n);
			}
			return new ByteArrayInputStream(out.toByteArray());
		} finally {
			closeQuietly(in, out);
		}
	}

	/**
	 * 将zip压缩后的单个文件到InputStream中
	 * 
	 * @param data 单个文件的压缩流
	 * @return
	 * @throws IOException
	 */
	public static InputStream unZip2Stream(byte[] data) throws IOException {
		if (null == data || data.length <= 0) {
			return null;
		}
		ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(data));
		ByteArrayOutputStream bis = new ByteArrayOutputStream();
		try {
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				if (entry.isDirectory()) {
				} else {
					int readedBytes;
					byte[] buffer = new byte[512];
					while ((readedBytes = zis.read(buffer)) > 0) {
						bis.write(buffer, 0, readedBytes);
					}
					bis.close();
					zis.closeEntry();
					return new ByteArrayInputStream(bis.toByteArray());
				}
			}
			return null;
		} finally {
			closeQuietly(zis, bis);
		}
	}
	
	/**
	 * 将文件内容压缩成zip格式的字节数组
	 * 
	 * @param content
	 * @param charset
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static byte[] zip(String content, String fileName, String charset) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ZipOutputStream zipOut = null;
		try {
			zipOut = new ZipOutputStream(bos);
			zipOut.putNextEntry(new org.apache.tools.zip.ZipEntry(fileName));
			zipOut.setEncoding(charset);
			zipOut.write(content.getBytes());
			zipOut.close();
			return bos.toByteArray();
		} finally {
			closeQuietly(zipOut);
		}
	}
	
	public static void close(Closeable... closeables) throws IOException {
		if (closeables != null) {
			for (Closeable closeable : closeables) {
				if (closeable != null) {
					closeable.close();
				}
			}
		}
	}

	public static void closeQuietly(Closeable... closeables) {
		try {
			close(closeables);
		} catch (IOException e) {
		}
	}

}
