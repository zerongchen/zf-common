package com.aotain.common.utils.tools;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件基础操作工具
 * 
 * @author liuz@aotian.com
 * @date 2017年11月10日 上午11:05:43
 */
public class FileUtils {

	private static Logger LOG = LoggerFactory.getLogger(FileUtils.class);
	/**
	 * 将文件读取成为数据流
	 */
	public static InputStream read2Stream(String path) throws FileNotFoundException {
		if (StringUtils.isBlank(path)) {
			return null;
		}

		File f = new File(path);
		if (!f.exists()) {
			return null;
		}

		return new FileInputStream(f);
	}

	/**
	 * 将文件读取成为byte[]
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	@Deprecated
	public static byte[] read2Bytes(String path) throws IOException {
		InputStream is = read2Stream(path);
		if (is == null) {
			return null;
		}
		org.apache.commons.io.FileUtils.readFileToByteArray(new File(path));
//		return StreamUtils.getBytes(is);
		return null;
	}

	/**
	 * 文件重命名
	 * 
	 * @param path1
	 * @param path2
	 */
	public static boolean renameFile(String path1, String path2) throws Exception {
		File oldFile = new File(path1);
		File newFile = new File(path2);
		if (oldFile.exists() && !newFile.exists()) {
			return oldFile.renameTo(newFile);
		}
		return false;
	}

	/**
	 * 将数据流写入文件
	 * 
	 * @param data
	 * @param path
	 * @throws Exception
	 */
	public static void writeToFile(byte[] data, String path) throws Exception {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(path);
			fos.write(data);
			fos.flush();
		} finally {
/*			if(fos.close();){

			}
			org.springframework.util.StreamUtils.nonClosing(fos);*/
		}

	}

	/**
	 * 安静的删除指定的文件路径列表指向的文件
	 * 
	 * @param copiedTmpFilePaths
	 */
	public static void deleteFilesQuietly(List<String> copiedTmpFilePaths) {
		if (copiedTmpFilePaths == null || copiedTmpFilePaths.size() == 0) {
			return;
		}
		try {
			for (String path : copiedTmpFilePaths) {
				File file = new File(path);
				if (file.exists() && file.isFile()) {
					file.delete();
				}
			}
		} catch (Exception e) {

		}
	}

	/**
	 * 从环境中加载配置文件
	 * 
	 * @param dir
	 * @param name
	 */
	public static Properties loadPropertiesFromConfig(String dir, String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}
		name = name.trim();
		if (!name.endsWith(".properties")) {
			name += ".properties";
		}
		String path = null;
		path = name;
		if (!StringUtils.isBlank(dir)) {
			path = dir.trim() + File.separator + path;
		}

		System.out.println("try to load properties from path : " + path);
		Properties p = new Properties();
		File file = new File(path);
		if (file.exists()) {
			try {
				FileInputStream fis = new FileInputStream(file);
				p.load(fis);
				return p;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 从class path中加载使用绝对路径加载
		path = name;
		if (path.contains("\\")) {
			path = path.replace("\\", "/");
		}
		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		System.out.println("load properties from classpath : " + path);
		try {
			InputStream is = FileUtils.class.getResourceAsStream(path);
			if (is != null) {
				p.load(is);
			}
			return p;
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("fail to load properties: " + path);
		return null;
	}

	/**
	 * 复制文件夹
	 *
	 * @param oldPath
	 * @param newPath
	 */
	public static void copyFolder(String oldPath, String newPath) {

		(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
		File a = new File(oldPath);
		String[] file = a.list();
		File temp = null;

		try {
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}
				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}

		} catch (Exception e) {
			LOG.error(" copyFolder error ",e);
			e.printStackTrace();
		}
	}

	/**
	 * 压缩文件
	 *
	 * @param srcfile
	 */
	public static void zipFiles(File srcfile, File targetFile) {

		ZipOutputStream out = null;
		try {
			out = new ZipOutputStream(new FileOutputStream(targetFile));

			if (srcfile.isFile()) {
				zipFile(srcfile, out, "");
			} else {
				File[] list = srcfile.listFiles();
				for (int i = 0; i < list.length; i++) {
					compress(list[i], out, "");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 压缩文件夹里的文件 起初不知道是文件还是文件夹--- 统一调用该方法
	 *
	 * @param file
	 * @param out
	 * @param basedir
	 */
	private static void compress(File file, ZipOutputStream out, String basedir) {
		/* 判断是目录还是文件 */
		if (file.isDirectory()) {
			zipDirectory(file, out, basedir);
		} else {
			zipFile(file, out, basedir);
		}
	}

	/**
	 * 压缩单个文件
	 *
	 * @param srcfile
	 */
	public static void zipFile(File srcfile, ZipOutputStream out, String basedir) {
		if (!srcfile.exists())
			return;

		byte[] buf = new byte[1024];
		FileInputStream in = null;

		try {
			int len;
			in = new FileInputStream(srcfile);
			out.putNextEntry(new ZipEntry(basedir + srcfile.getName()));

			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.closeEntry();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 压缩文件夹
	 *
	 * @param dir
	 * @param out
	 * @param basedir
	 */
	public static void zipDirectory(File dir, ZipOutputStream out, String basedir) {
		if (!dir.exists())
			return;

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			/* 递归 */
			compress(files[i], out, basedir + dir.getName() + "/");
		}
	}

	public static List<String> readFileByLines(File file) {
		List<String> list = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
			reader.close();
		} catch (IOException e) {
			LOG.error("readFileByLines error ", e);
			MonitorStatisticsUtils.addEvent(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					MonitorStatisticsUtils.addEvent(e1);
				}
			}
		}
		return list;
	}
}
