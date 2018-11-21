package org.nico.ct.domain.template;

import org.nico.db.annotation.Param;
import org.nico.db.annotation.Primary;
import org.nico.db.annotation.Table;

@Table("ct_temp_config")
public class TempConfig {

	@Primary
	private String id;
	
	private String prefix;
	
	private String suffix;
	
	private String name;
	
	@Param("properties_json")
	private String propertiesJson;
	
	@Param("account_id")
	private String accountId;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPropertiesJson() {
		return propertiesJson;
	}

	public void setPropertiesJson(String propertiesJson) {
		this.propertiesJson = propertiesJson;
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

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
}
