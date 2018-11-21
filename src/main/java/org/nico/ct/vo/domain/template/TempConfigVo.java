package org.nico.ct.vo.domain.template;

import org.nico.cat.mvc.scan.annotations.NotNull;
import org.nico.ct.vo.domain.BaseVo;

public class TempConfigVo extends BaseVo{

	private String id;
	
	@NotNull(message = "前缀不能为空")
	private String prefix;
	
	@NotNull(message = "后缀不能为空")
	private String suffix;
	
	@NotNull(message = "名称不能为空")
	private String name;
	
	@NotNull(message = "Json参数不能为空")
	private String propertiesJson;
	
	private String accountId;
	
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

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getPropertiesJson() {
		return propertiesJson;
	}

	public void setPropertiesJson(String propertiesJson) {
		this.propertiesJson = propertiesJson;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

}
