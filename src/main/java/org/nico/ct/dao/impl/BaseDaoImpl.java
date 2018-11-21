package org.nico.ct.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.nico.aoc.scan.annotations.Dao;
import org.nico.aoc.scan.annotations.Label;
import org.nico.ct.dao.BaseDao;
import org.nico.db.helper.AbstractDBHelper;
import org.nico.db.page.DBPage;

/** 
 * Base DAO
 * 
 * @author nico
 */
public class BaseDaoImpl<T> implements BaseDao<T>{

	@Label
	private AbstractDBHelper dbHelper;

	Class<T> clazz;

	public BaseDaoImpl(){
		ParameterizedType superclass =(ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] actualTypeArguments = superclass.getActualTypeArguments();
		clazz=(Class<T>) actualTypeArguments[0];
	}
	
	@Override
	public T get(Object id) {
		return dbHelper.get(clazz, id);
	}

	@Override
	public List<T> get(Map<String, Object> criterias, DBPage page) {
		return dbHelper.select(criterias, page, clazz);
	}
	
	@Override
	public T getSingle(Map<String, Object> criterias) {
		return dbHelper.selectSingle(criterias, clazz);
	}

	@Override
	public long update(Object entity) {
		return dbHelper.update(entity);
	}

	@Override
	public T insert(Object entity) {
		return (T) dbHelper.save(entity);
	}

	@Override
	public boolean deleteById(Object id) {
		return dbHelper.delete(clazz, id);
	}

	@Override
	public boolean delete(Object entity) {
		return dbHelper.delete(entity) == null;
	}

	@Override
	public long update(Object entity, boolean part) {
		return dbHelper.update(entity, part);
	}

	@Override
	public long count(Map<String, Object> criterias) {
		return dbHelper.count(criterias, clazz);
	}


}
