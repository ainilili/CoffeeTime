package org.nico.ct.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nico.aoc.scan.annotations.Label;
import org.nico.aoc.scan.annotations.Service;
import org.nico.aoc.scan.annotations.aspect.Section;
import org.nico.aoc.scan.entity.AspectType;
import org.nico.ct.constant.CacheKey;
import org.nico.ct.constant.MessageConstant;
import org.nico.ct.dao.AccountDao;
import org.nico.ct.domain.Account;
import org.nico.ct.exception.QueryException;
import org.nico.ct.section.TransationSection;
import org.nico.ct.service.AccountService;
import org.nico.ct.util.CenteringUtils;
import org.nico.ct.util.JedisUtils;
import org.nico.ct.util.Md5Utils;
import org.nico.db.page.DBPage;
import org.nico.noson.Noson;
import org.nico.util.collection.CollectionUtils;

/** 
 *
 * 
 * @author nico
 */
@Service
public class AccountServiceImpl implements AccountService{

	@Label
	private AccountDao accountDao;
	
	@Override
	public Account get(Object id) {
		return accountDao.get(id);
	}

	@Override
	public List<Account> get(Map<String, Object> criterias, DBPage page) {
		return accountDao.get(criterias, page);
	}

	@Override
	public long update(org.nico.ct.domain.Account entity) {
		return accountDao.update(entity);
	}

	@Override
	public Account insert(org.nico.ct.domain.Account entity) {
		return accountDao.insert(entity);
	}

	@Override
	public boolean delete(Account entity) {
		return accountDao.delete(entity);
	}

	@Override
	public boolean deleteById(Object id) {
		return accountDao.delete(id);
	}

	@Override
	public Account login(String accountName, String accountWord) {
		String pwd = Md5Utils.makePassword(accountWord);
		Map<String, Object> criterias = new HashMap<String, Object>();
		criterias.put("accountName", accountName);
		criterias.put("accountWord", pwd);
		Account account = null;
		if((account = accountDao.getSingle(criterias)) != null) {
			String currentToken = CenteringUtils.productionID();
			account.setToken(currentToken);
			accountDao.update(account);
			JedisUtils.setex(CacheKey.ACCOUNT_TOKEN + currentToken, Noson.reversal(account), 60 * 60 * 2);
			return account;
		}else {
			throw new QueryException(MessageConstant.MESSAGE_ACCOUNT_ERROR);
		}
	}

	@Override
	public Account register(String accountName, String accountWord) {
		
		Map<String, Object> criterias = new HashMap<String, Object>();
		criterias.put("accountName", accountName);
		if(CollectionUtils.isNotBlank(this.get(criterias, null))){
			throw new QueryException(MessageConstant.MESSAGE_ACCOUNT_EXIST);
		}
		
		Account newAccount = new Account();
		newAccount.setId(CenteringUtils.productionID());
		newAccount.setAccountName(accountName);
		newAccount.setNickName(accountName);
		newAccount.setAccountWord(Md5Utils.makePassword(accountWord));
		
		return accountDao.insert(newAccount);
	}

	

}
