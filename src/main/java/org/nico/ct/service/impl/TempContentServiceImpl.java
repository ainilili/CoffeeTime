package org.nico.ct.service.impl;

import java.util.List;
import java.util.Map;

import org.nico.aoc.scan.annotations.Label;
import org.nico.aoc.scan.annotations.Service;
import org.nico.ct.dao.TempContentDao;
import org.nico.ct.dao.TempDataSourceDao;
import org.nico.ct.domain.template.TempContent;
import org.nico.ct.domain.template.TempDataSource;
import org.nico.ct.service.TempContentService;
import org.nico.ct.service.TempDataSourceService;
import org.nico.db.page.DBPage;

/** 
 *
 * 
 * @author nico
 * @version createTime：2018年4月15日 上午12:40:18
 */
@Service
public class TempContentServiceImpl implements TempContentService{

	@Label
	private TempContentDao tempContentDao;
	
	@Override
	public TempContent get(Object id) {
		return tempContentDao.get(id);
	}

	@Override
	public List<TempContent> get(Map<String, Object> criterias, DBPage page) {
		return tempContentDao.get(criterias, page);
	}

	@Override
	public long update(TempContent entity) {
		return tempContentDao.update(entity);
	}

	@Override
	public TempContent insert(TempContent entity) {
		return tempContentDao.insert(entity);
	}

	@Override
	public boolean delete(TempContent entity) {
		return tempContentDao.delete(entity);
	}

	@Override
	public boolean deleteById(Object id) {
		return tempContentDao.deleteById(id);
	}

	@Override
	public long count(Map<String, Object> criterias) {
		return tempContentDao.count(criterias);
	}

	
}
