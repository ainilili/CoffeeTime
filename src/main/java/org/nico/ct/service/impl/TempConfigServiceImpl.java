package org.nico.ct.service.impl;

import java.util.List;
import java.util.Map;

import org.nico.aoc.scan.annotations.Label;
import org.nico.aoc.scan.annotations.Service;
import org.nico.ct.dao.TempConfigDao;
import org.nico.ct.dao.TempContentDao;
import org.nico.ct.dao.TempDataSourceDao;
import org.nico.ct.domain.template.TempConfig;
import org.nico.ct.domain.template.TempContent;
import org.nico.ct.domain.template.TempDataSource;
import org.nico.ct.service.TempConfigService;
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
public class TempConfigServiceImpl implements TempConfigService{

	@Label
	private TempConfigDao tempConfigDao;

	@Override
	public TempConfig get(Object id) {
		return tempConfigDao.get(id);
	}

	@Override
	public List<TempConfig> get(Map<String, Object> criterias, DBPage page) {
		return tempConfigDao.get(criterias, page);
	}

	@Override
	public long update(TempConfig entity) {
		return tempConfigDao.update(entity);
	}

	@Override
	public TempConfig insert(TempConfig entity) {
		return tempConfigDao.insert(entity);
	}

	@Override
	public boolean delete(TempConfig entity) {
		return tempConfigDao.delete(entity);
	}

	@Override
	public boolean deleteById(Object id) {
		return tempConfigDao.deleteById(id);
	}

	@Override
	public long count(Map<String, Object> criterias) {
		return tempConfigDao.count(criterias);
	}
	
}
