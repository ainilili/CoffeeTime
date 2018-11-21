package org.nico.ct.service.impl;

import java.util.List;
import java.util.Map;

import org.nico.aoc.scan.annotations.Label;
import org.nico.aoc.scan.annotations.Service;
import org.nico.ct.dao.TempDataSourceDao;
import org.nico.ct.domain.template.TempDataSource;
import org.nico.ct.service.TempDataSourceService;
import org.nico.db.page.DBPage;

/** 
 *
 * 
 * @author nico
 * @version createTime：2018年4月15日 上午12:40:18
 */
@Service
public class TempDataSourceServiceImpl implements TempDataSourceService{

	@Label
	private TempDataSourceDao tempDataSourceDao;

	@Override
	public TempDataSource get(Object id) {
		return tempDataSourceDao.get(id);
	}

	@Override
	public List<TempDataSource> get(Map<String, Object> criterias, DBPage page) {
		return tempDataSourceDao.get(criterias, page);
	}

	@Override
	public long update(TempDataSource entity) {
		return tempDataSourceDao.update(entity);
	}

	@Override
	public TempDataSource insert(TempDataSource entity) {
		return tempDataSourceDao.insert(entity);
	}

	@Override
	public boolean delete(TempDataSource entity) {
		return tempDataSourceDao.delete(entity);
	}

	@Override
	public boolean deleteById(Object id) {
		return tempDataSourceDao.deleteById(id);
	}


	@Override
	public long count(Map<String, Object> criterias) {
		return tempDataSourceDao.count(criterias);
	}
	
}
