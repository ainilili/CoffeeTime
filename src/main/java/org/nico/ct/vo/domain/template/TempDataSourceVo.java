package org.nico.ct.vo.domain.template;

import org.nico.cat.mvc.scan.annotations.NotNull;
import org.nico.ct.vo.domain.BaseVo;

public class TempDataSourceVo extends BaseVo{

	private String id;
	
	@NotNull(message = "数据源用户名不能为空")
	private String username;
	
	@NotNull(message = "数据源密码不能为空")
	private String password;
	
	private String interference;
	
	@NotNull(message = "数据源url不能为空")
	private String url;
	
	private Integer offset;
	
	private Integer length;
	
	private String accountId;
	
	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

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
