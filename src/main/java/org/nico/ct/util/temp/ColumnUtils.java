package org.nico.ct.util.temp;

public class ColumnUtils {
	
	/**
	 * 数据库字段名转Java字段名, 首字母小写
	 * 
	 * @param mysqlColumn
	 * @return
	 */
	public static String mysqlColumnConvertToJavaFieldNameLower(String mysqlColumn) {
		String[] ts = mysqlColumn.split("_");
		String javaFieldName = ts[0];
		for(int index = 1; index < ts.length; index ++){
			javaFieldName += ts[index].toUpperCase().substring(0, 1) + ts[index].substring(1);
		}
		return javaFieldName;
	}
	
	/**
	 * 数据库字段名转Java字段名, 首字母大写
	 * 
	 * @param mysqlColumn
	 * @return
	 */
	public static String mysqlColumnConvertToJavaFieldNameUpper(String mysqlColumn) {
		String javaFieldName = mysqlColumnConvertToJavaFieldNameLower(mysqlColumn);
		return javaFieldName.substring(0, 1).toUpperCase() + javaFieldName.substring(1);
	}
	
}
