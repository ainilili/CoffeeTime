package org.nico.ct.component;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.nico.aoc.scan.annotations.Book;
import org.nico.aoc.scan.annotations.Label;
import org.nico.ct.constant.MessageConstant;
import org.nico.ct.domain.Account;
import org.nico.ct.domain.template.TempDataSource;
import org.nico.ct.exception.AuthException;
import org.nico.ct.exception.CtException;
import org.nico.ct.service.TempDataSourceService;
import org.nico.ct.util.BaseUtils;
import org.nico.ct.util.temp.ColumnUtils;
import org.nico.ct.vo.domain.template.TempTableInfoVo;
import org.nico.ct.vo.domain.template.TempTableInfoVo.TempFieldInfoVo;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据源组件，用来获取指定数据源的相关信息
 * 
 * @author nico
 * @time 2018-06-19 14:00
 */

@Book
public class DatasoruceComponent {

	@Label
	private static TempDataSourceService tempDataSourceService;
	
	/**
	 * 获取数据源表和字段信息
	 * 
	 * @param dataSourceId 数据源ID
	 * @return 数据源内部信息
	 */
	public static List<TempTableInfoVo> getDataSourceInfo(String dataSourceId) {
		List<TempTableInfoVo> tableInfos = new ArrayList<TempTableInfoVo>();
		TempDataSource tempDataSource = tempDataSourceService.get(dataSourceId);
		if(null == tempDataSource) {
			throw new NullPointerException("Template datasource is null with id " + dataSourceId);
		}

		Account account = BaseUtils.getCurrentAccount();
		if(! account.getId().equals(tempDataSource.getAccountId())) {
			throw new AuthException(MessageConstant.MESSAGE_AUTH_FAIL);
		}

		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(tempDataSource.getUsername());
		dataSource.setPassword(tempDataSource.getPassword());
		dataSource.setJdbcUrl(tempDataSource.getUrl());

		try {
			Connection conn = dataSource.getConnection();
			if(conn == null) {
				throw new NullPointerException("Can not get sql connection !");
			}
			DatabaseMetaData metaData = conn.getMetaData();
			ResultSet resultSet = metaData.getTables(null, "%", null, null);
			
			while(resultSet.next()){  
				String tableName=resultSet.getString("TABLE_NAME");  
				TempTableInfoVo tableInfo = new TempTableInfoVo();
				tableInfo.setTableName(tableName);
				tableInfo.setFields(new ArrayList<TempFieldInfoVo>());
				tableInfo.setNormalFields(new ArrayList<TempFieldInfoVo>());
				tableInfo.setPrimaryFields(new ArrayList<TempFieldInfoVo>());
				
				
				//主键信息
				ResultSet pks = conn.getMetaData().getPrimaryKeys(null, getSchema(conn), tableName);
				List<String> primaryKeys = new ArrayList<String>();
				while(pks.next()) {
					//处理字段
					String colName = pks.getString("COLUMN_NAME");  
					primaryKeys.add(colName);
				}
				
				//字段信息
				ResultSet rs = conn.getMetaData().getColumns(null, getSchema(conn), tableName, null);
				
				while(rs.next()){
					TempFieldInfoVo fieldInfo = new TempFieldInfoVo();
					
					//处理字段
					String colName = rs.getString("COLUMN_NAME");  
					fieldInfo.setFieldName(ColumnUtils.mysqlColumnConvertToJavaFieldNameLower(colName));
					fieldInfo.setMethodName(ColumnUtils.mysqlColumnConvertToJavaFieldNameUpper(colName));
					fieldInfo.setColumnName(colName);
					
					//字段注释
					String remarks = rs.getString("REMARKS");  
					if(remarks == null || remarks.equals("")){  
						remarks = colName;  
					}  
					fieldInfo.setComment(remarks);
					
					//字段类型
					String dbType = rs.getString("TYPE_NAME");
					dbType = dbType.split(" ")[0];
					fieldInfo.setDbType(fileterDbType(dbType));
					fieldInfo.setValueType(convertJavaType(changeDbType(dbType)));
					
					tableInfo.getFields().add(fieldInfo);
					if(primaryKeys.contains(colName.intern())) {
						tableInfo.getPrimaryFields().add(fieldInfo);
					}else {
						tableInfo.getNormalFields().add(fieldInfo);
					}
				}  
				tableInfos.add(tableInfo);
			}  
		} catch (Exception e) {
			throw new CtException(e.getMessage(), e);
		}

		return tableInfos;

	}

	private static String fileterDbType(String dbType) {
		switch(dbType){  
		case "INT":
			dbType = "INTEGER";
			break;
		case "LONGTEXT":
			dbType = "LONGVARCHAR";
			break;
		}
		return dbType;
	}
	
	private static int changeDbType(String dbType) {  
		dbType = dbType.toUpperCase();  
		switch(dbType){  
		case "VARCHAR":  
		case "VARCHAR2":  
		case "CHAR": 
		case "LONGTEXT":
			return 1;  
		case "NUMBER":  
		case "DECIMAL":  
			return 4;  
		case "INT":
		case "BIT":
		case "TINYINT":
		case "SMALLINT":  
		case "INTEGER":  
			return 2;  
		case "BIGINT":  
			return 6;  
		case "DATETIME":  
		case "TIMESTAMP":  
		case "DATE":  
			return 7;  
		default:  
			return 1;  
		}  
	}  
	
	private static String convertJavaType(int dbType) {
		switch(dbType) {
		case 1:
			return "String";
		case 2:
			return "Integer";
		case 4:
			return "BigDecimal";
		case 6:
			return "Long";
		case 7:
			return "Date";
		default:
			return "String";
		}
	}


	private static String getSchema(Connection conn) throws Exception {  
		String schema;  
		schema = conn.getMetaData().getUserName();  
		if ((schema == null) || (schema.length() == 0)) {  
			throw new Exception("ORACLE数据库模式不允许为空");  
		}  
		return schema.toUpperCase().toString();  
	}  

}
