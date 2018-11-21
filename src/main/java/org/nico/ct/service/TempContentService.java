package org.nico.ct.service;

import java.util.List;
import java.util.Map;

import org.nico.ct.domain.template.TempContent;
import org.nico.db.page.DBPage;

/** 
 * 
 * @author nico
 * @version createTempContentime：2018年4月15日 上午12:38:39
 */

public interface TempContentService {

	TempContent get(Object id);
	
	List<TempContent> get(Map<String, Object> criterias, DBPage page);
	
	long update(TempContent entity);
	
	TempContent insert(TempContent entity);
	
	boolean delete(TempContent entity);
	
	boolean deleteById(Object id);
	
	long count(Map<String, Object> criterias);
	
}
