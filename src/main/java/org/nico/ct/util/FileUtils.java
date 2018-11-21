package org.nico.ct.util;

import java.util.Date;

public class FileUtils {

	/**
	 * 拼接文件名
	 * 
	 * @param name 名称
	 * @param suffix 后缀
	 * @return 文件全称
	 */
	public static String joint(String name, String suffix) {
		return name + (suffix.startsWith(".") ? "" : ".") + suffix;
	}
	
	/**
	 * 获取随机名
	 * 
	 * @param suffix 后缀
	 * @return 文件全称
	 */
	public static String randomFileName(String suffix) {
		return joint("CT" + new Date().getTime(), suffix);
	}
	
	public static String specificFileName(String name, String suffix) {
		return joint(name, suffix);
	}
	
}
