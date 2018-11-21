package org.nico.ct.vo.domain.template;

import org.nico.cat.mvc.scan.annotations.NotNull;

/** 
 * 
 * @author nico
 */

public class TempBuildFormVo {
	
	@NotNull
	private String json;

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	
	
}
