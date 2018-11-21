package org.nico.ct.domain.template;

import org.nico.db.annotation.Param;
import org.nico.db.annotation.Primary;
import org.nico.db.annotation.Table;

@Table("ct_temp_datasource")
public class TempDataSource {

	@Primary
	private String id;
	
	private String username;
	
	private String password;
	
	private String interference;
	
	private String url;
	
	@Param("account_id")
	private String accountId;
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getInterference() {
		return interference;
	}

	public void setInterference(String interference) {
		this.interference = interference;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
