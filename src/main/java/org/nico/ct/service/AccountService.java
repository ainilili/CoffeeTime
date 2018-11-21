package org.nico.ct.service;

import java.util.List;
import java.util.Map;

import org.nico.ct.domain.Account;
import org.nico.db.page.DBPage;

/** 
 * 
 * @author nico
 * @version createAccountime：2018年4月15日 上午12:38:39
 */

public interface AccountService {

	Account get(Object id);
	
	List<Account> get(Map<String, Object> criterias, DBPage page);
	
	long update(Account entity);
	
	Account insert(Account entity);
	
	boolean delete(Account entity);
	
	boolean deleteById(Object id);
	
	Account login(String accountName, String accountWord);
	
	Account register(String accountName, String accountWord);
}
