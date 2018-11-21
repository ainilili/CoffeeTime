package org.nico.ct.vo.domain.template;

import java.util.List;

import org.nico.cat.mvc.scan.annotations.NotNull;

/** 
 * 
 * @author nico
 */

public class TempBuildVo {
	
	@NotNull
	private String configId;
	
	@NotNull
	private String propertiesJson;
	
	@NotNull
	private List<TempTableInfoVo> tables;
	
	public String getConfigId() {
		return configId;
	}

	public void setConfigId(String configId) {
		this.configId = configId;
	}

	public String getPropertiesJson() {
		return propertiesJson;
	}

	public void setPropertiesJson(String propertiesJson) {
		this.propertiesJson = propertiesJson;
	}

	public List<TempTableInfoVo> getTables() {
		return tables;
	}

	public void setTables(List<TempTableInfoVo> tables) {
		this.tables = tables;
	}
	
}
