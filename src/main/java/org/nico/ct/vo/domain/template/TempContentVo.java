package org.nico.ct.vo.domain.template;

import org.nico.cat.mvc.scan.annotations.NotNull;
import org.nico.ct.vo.domain.BaseVo;
import org.nico.noson.annotations.JsonIgnore;

public class TempContentVo extends BaseVo{

	private String id;
	
	@NotNull(message = "名称不能为空")
	private String name;
	
	@NotNull(message = "内容不能为空")
	private String content;
	
	@NotNull(message = "模板设置ID不能为空")
	private String tempConfigId;
	
	@NotNull(message = "文件名不能为空")
	private String fileName;
	
	@NotNull(message = "文件格式不能为空")
	private String fileFormat;
	
	private String accountId;
	
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
