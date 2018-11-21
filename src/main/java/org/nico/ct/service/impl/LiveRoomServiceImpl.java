package org.nico.ct.service.impl;

import java.util.List;
import java.util.Map;

import org.nico.aoc.scan.annotations.Label;
import org.nico.aoc.scan.annotations.Service;
import org.nico.ct.dao.LiveRoomDao;
import org.nico.ct.domain.live.LiveRoom;
import org.nico.ct.service.LiveRoomService;
import org.nico.db.page.DBPage;

@Service
public class LiveRoomServiceImpl implements LiveRoomService{

	@Label
	private LiveRoomDao liveRoomDao;

	@Override
	public LiveRoom get(Object id) {
		return liveRoomDao.get(id);
	}

	@Override
	public List<LiveRoom> get(Map<String, Object> criterias, DBPage page) {
		return liveRoomDao.get(criterias, page);
	}

	@Override
	public long update(LiveRoom entity) {
		return liveRoomDao.update(entity);
	}

	@Override
	public LiveRoom insert(LiveRoom entity) {
		return liveRoomDao.insert(entity);
	}

	@Override
	public boolean delete(LiveRoom entity) {
		return liveRoomDao.delete(entity);
	}

	@Override
	public boolean deleteById(Object id) {
		return liveRoomDao.deleteById(id);
	}

	@Override
	public long count(Map<String, Object> criterias) {
		return liveRoomDao.count(criterias);
	}


}
