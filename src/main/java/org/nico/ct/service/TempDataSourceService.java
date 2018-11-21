package org.nico.ct.service;

import java.util.List;
import java.util.Map;

import org.nico.ct.domain.template.TempDataSource;
import org.nico.db.page.DBPage;

/** 
 * 
 * @author nico
 * @version createTempDataSourceime：2018年4月15日 上午12:38:39
 */

public interface TempDataSourceService {

	TempDataSource get(Object id);
	
	List<TempDataSource> get(Map<String, Object> criterias, DBPage page);
	
	long update(TempDataSource entity);
	
	long count(Map<String, Object> criterias);
	
	TempDataSource insert(TempDataSource entity);
	
	boolean delete(TempDataSource entity);
	
	boolean deleteById(Object id);
	
}
