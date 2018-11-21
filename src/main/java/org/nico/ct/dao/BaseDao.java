package org.nico.ct.dao;

import java.util.List;
import java.util.Map;

import org.nico.db.page.DBPage;

/** 
 * 
 * @author nico
 * @version createTime：2018年4月15日 上午12:08:11
 */

public interface BaseDao<T> {

	T get(Object id);
	
	List<T> get(Map<String, Object> criterias, DBPage page);
	
	T getSingle(Map<String, Object> criterias);
	
	long count(Map<String, Object> criterias);
	
	long update(Object entity);
	
	long update(Object entity, boolean part);
	
	T insert(Object entity);
	
	boolean delete(Object entity);
	
	boolean deleteById(Object id);
	
}
