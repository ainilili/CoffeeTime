package org.nico.ct.vo.domain;

import java.util.Map;

import org.nico.db.page.DBPage;

public class BaseVo {

	protected Map<String, Object> query;
	
	protected DBPage page;

	public Map<String, Object> getQuery() {
		return query;
	}

	public void setQuery(Map<String, Object> query) {
		this.query = query;
	}

	public DBPage getPage() {
		return page;
	}

	public void setPage(DBPage page) {
		this.page = page;
	}
	
}
