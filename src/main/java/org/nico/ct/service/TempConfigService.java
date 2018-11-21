package org.nico.ct.service;

import java.util.List;
import java.util.Map;

import org.nico.ct.domain.template.TempConfig;
import org.nico.db.page.DBPage;

/** 
 * 
 * @author nico
 * @version createTempConfigime：2018年4月15日 上午12:38:39
 */

public interface TempConfigService {

	TempConfig get(Object id);
	
	List<TempConfig> get(Map<String, Object> criterias, DBPage page);
	
	long update(TempConfig entity);
	
	TempConfig insert(TempConfig entity);
	
	boolean delete(TempConfig entity);
	
	boolean deleteById(Object id);
	
	long count(Map<String, Object> criterias);
	
}
