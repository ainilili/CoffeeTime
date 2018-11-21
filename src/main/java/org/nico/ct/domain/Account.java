package org.nico.ct.domain;

import org.nico.db.annotation.Param;
import org.nico.db.annotation.Primary;
import org.nico.db.annotation.Table;
import org.nico.noson.annotations.JsonIgnore;

@Table("ct_account")
public class Account {
	
	@Primary
	private String id;
	
	@Param("account_name")
	private String accountName;
	
	@Param("account_word")
	@JsonIgnore
	private String accountWord;
	
	@Param("nick_name")
	private String nickName;
	
	@Param("token")
	@JsonIgnore
	private String token;
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountWord() {
		return accountWord;
	}

	public void setAccountWord(String accountWord) {
		this.accountWord = accountWord;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
}
