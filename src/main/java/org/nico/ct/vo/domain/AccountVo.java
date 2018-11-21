package org.nico.ct.vo.domain;

import org.nico.cat.mvc.scan.annotations.Match;
import org.nico.ct.constant.RegexConstant;
import org.nico.ct.domain.Account;

/** 
 * 
 * @author nico
 * @version createTime：2018年5月20日 下午11:00:15
 */

public class AccountVo extends Account{

	private String id;
	
	@Match(regex = RegexConstant.REGEX_EMAIL, message = "用户名不标准")
	private String accountName;
	
	@Match(regex = RegexConstant.REGEX_PASSWORD, message = "密码必须6~12位")
	private String accountWord;
	
	private String nickName;
	
	private String reAccountWord;
	
	public String getReAccountWord() {
		return reAccountWord;
	}

	public void setReAccountWord(String reAccountWord) {
		this.reAccountWord = reAccountWord;
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
