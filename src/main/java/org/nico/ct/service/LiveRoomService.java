package org.nico.ct.service;

import java.util.List;
import java.util.Map;

import org.nico.ct.domain.live.LiveRoom;
import org.nico.db.page.DBPage;


public interface LiveRoomService {

	LiveRoom get(Object id);
	
	List<LiveRoom> get(Map<String, Object> criterias, DBPage page);
	
	long update(LiveRoom entity);
	
	LiveRoom insert(LiveRoom entity);
	
	boolean delete(LiveRoom entity);
	
	boolean deleteById(Object id);
	
	long count(Map<String, Object> criterias);
}
