package org.nico.ct.controller.live;

import java.util.List;

import org.nico.aoc.scan.annotations.Label;
import org.nico.cat.mvc.parameter.RequestMethod;
import org.nico.cat.mvc.router.RouterForNocat.VerifyResult;
import org.nico.cat.mvc.scan.annotations.Body;
import org.nico.cat.mvc.scan.annotations.Lobby;
import org.nico.cat.mvc.scan.annotations.NotNull;
import org.nico.cat.mvc.scan.annotations.PathParam;
import org.nico.cat.mvc.scan.annotations.QueryParam;
import org.nico.cat.mvc.scan.annotations.RestCinema;
import org.nico.cat.mvc.scan.annotations.Verify;
import org.nico.ct.config.CtConfig;
import org.nico.ct.constant.MessageConstant;
import org.nico.ct.constant.ResponseCode;
import org.nico.ct.domain.Account;
import org.nico.ct.domain.live.LiveRoom;
import org.nico.ct.exception.AuthException;
import org.nico.ct.service.LiveRoomService;
import org.nico.ct.util.BaseUtils;
import org.nico.ct.util.CenteringUtils;
import org.nico.ct.vo.ResponseVo;
import org.nico.ct.vo.domain.live.LiveRoomVo;


@RestCinema
@Lobby(mapping = CtConfig.APP_AUTHC + "/live/room")
public class LiveRoomController {

	@Label
	private LiveRoomService liveRoomService;
	
	@Lobby(requestMethod = RequestMethod.PUT)
	public ResponseVo put(
			@Body @Verify @NotNull LiveRoomVo liveRoomVo,
			VerifyResult verifyResult
		) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = BaseUtils.getCurrentAccount();
			liveRoomVo.setId(CenteringUtils.productionID());
			liveRoomVo.setAccountId(account.getId());
			liveRoomService.insert(BaseUtils.copyObject(liveRoomVo, LiveRoom.class));
			return result.setResponseCode(ResponseCode.CODE_SUCCESS).setData(liveRoomVo);
		}catch(Exception e) {
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(null);
		}
	}
	
	@Lobby(requestMethod = RequestMethod.POST)
	public ResponseVo post(
			@Body @Verify @NotNull LiveRoomVo liveRoomVo,
			@QueryParam @NotNull String id,
			VerifyResult verifyResult
		) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = BaseUtils.getCurrentAccount();
			
			LiveRoom liveRoom = liveRoomService.get(id);
			if(liveRoom == null) {
				return result.setResponseCode(ResponseCode.CODE_ENTITY_IS_NULL);
			}
			
			if(! account.getId().equals(liveRoom.getAccountId())) {
				throw new AuthException(MessageConstant.MESSAGE_AUTH_FAIL);
			}
			
			liveRoom.setRoomName(liveRoomVo.getRoomName());
			liveRoom.setLiveUrl(liveRoomVo.getRoomName());
			long row = liveRoomService.update(liveRoom);
			
			if(row < 1) {
				return result.setResponseCode(ResponseCode.CODE_ERROR);
			}
			
			return result.setResponseCode(ResponseCode.CODE_SUCCESS).setData(liveRoom);
		}catch(Exception e) {
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(null);
		}
	}
	
	@Lobby(mapping = "/{id}", requestMethod = RequestMethod.DELETE)
	public ResponseVo delete(
			@PathParam @NotNull String id,
			VerifyResult verifyResult
		) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = BaseUtils.getCurrentAccount();
			
			LiveRoom liveRoom = liveRoomService.get(id);
			if(liveRoom == null) {
				return result.setResponseCode(ResponseCode.CODE_ENTITY_IS_NULL);
			}
			
			if(! account.getId().equals(liveRoom.getAccountId())) {
				throw new AuthException(MessageConstant.MESSAGE_AUTH_FAIL);
			}
			
			boolean deled = liveRoomService.deleteById(id);
			
			if(! deled) {
				return result.setResponseCode(ResponseCode.CODE_ERROR);
			}
			
			return result.setResponseCode(ResponseCode.CODE_SUCCESS);
		}catch(Exception e) {
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(null);
		}
	}
	
	@Lobby(mapping = "/{id}", requestMethod = RequestMethod.GET)
	public ResponseVo get(
			@PathParam @NotNull String id,
			VerifyResult verifyResult
		) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = BaseUtils.getCurrentAccount();
			LiveRoom entity = liveRoomService.get(id);
			if(entity != null && account.getId().equals(entity.getAccountId())){
				return result.setResponseCode(ResponseCode.CODE_SUCCESS).setData(entity);
			}else{
				throw new AuthException(MessageConstant.MESSAGE_AUTH_FAIL);
			}
		}catch(Exception e) {
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(null);
		}
	}
	
	@Lobby(mapping = "/queries", requestMethod = RequestMethod.POST)
	public ResponseVo queries(
			@Body @NotNull LiveRoomVo liveRoomVo,
			VerifyResult verifyResult
		) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = BaseUtils.getCurrentAccount();
			liveRoomVo.getQuery().put("accountId", account.getId());
			List<LiveRoom> datas = liveRoomService.get(liveRoomVo.getQuery(), liveRoomVo.getPage());
			long count = liveRoomService.count(liveRoomVo.getQuery());
			return result.setResponseCode(ResponseCode.CODE_SUCCESS).setData(datas).setCount(count);
		}catch(Exception e) {
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(null);
		}
	}
	
}
