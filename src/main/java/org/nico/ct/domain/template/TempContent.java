package org.nico.ct.domain.template;

import org.nico.db.annotation.Param;
import org.nico.db.annotation.Primary;
import org.nico.db.annotation.Table;
import org.nico.noson.annotations.JsonIgnore;

@Table("ct_temp_content")
public class TempContent {

	@Primary
	private String id;
	
	private String content;
	
	private String name;
	
	@Param("temp_config_id")
	private String tempConfigId;
	
	@Param("account_id")
	private String accountId;
	
	@Param("file_name")
	private String fileName;
	
	@Param("file_format")
	private String fileFormat;
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTempConfigId() {
		return tempConfigId;
	}

	public void setTempConfigId(String tempConfigId) {
		this.tempConfigId = tempConfigId;
	}
	
}
