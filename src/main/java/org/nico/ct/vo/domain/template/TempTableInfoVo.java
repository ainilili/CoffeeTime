package org.nico.ct.vo.domain.template;

import java.util.List;

import org.nico.cat.mvc.scan.annotations.NotNull;

public class TempTableInfoVo {
	
	@NotNull
	private String tableName;
	
	@NotNull
	private List<TempFieldInfoVo> fields;
	
	@NotNull
	private List<TempFieldInfoVo> primaryFields;
	
	@NotNull
	private List<TempFieldInfoVo> normalFields;
	
	public List<TempFieldInfoVo> getPrimaryFields() {
		return primaryFields;
	}

	public void setPrimaryFields(List<TempFieldInfoVo> primaryFields) {
		this.primaryFields = primaryFields;
	}

	public List<TempFieldInfoVo> getNormalFields() {
		return normalFields;
	}

	public void setNormalFields(List<TempFieldInfoVo> normalFields) {
		this.normalFields = normalFields;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<TempFieldInfoVo> getFields() {
		return fields;
	}

	public void setFields(List<TempFieldInfoVo> fields) {
		this.fields = fields;
	}

	public static class TempFieldInfoVo{
		
		@NotNull
		private String fieldName;
		
		private String comment;
		
		@NotNull
		private String dbType;
		
		private String valueType;
		
		private String columnName;
		
		private String methodName;
		
		public String getColumnName() {
			return columnName;
		}

		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}

		public String getMethodName() {
			return methodName;
		}

		public void setMethodName(String methodName) {
			this.methodName = methodName;
		}

		public String getFieldName() {
			return fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public String getDbType() {
			return dbType;
		}

		public void setDbType(String dbType) {
			this.dbType = dbType;
		}

		public String getValueType() {
			return valueType;
		}

		public void setValueType(String valueType) {
			this.valueType = valueType;
		}
		
	}
}
