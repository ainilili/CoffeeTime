package org.nico.ct.controller.template;

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
import org.nico.ct.domain.template.TempConfig;
import org.nico.ct.exception.AuthException;
import org.nico.ct.service.TempConfigService;
import org.nico.ct.service.TempContentService;
import org.nico.ct.util.BaseUtils;
import org.nico.ct.util.CenteringUtils;
import org.nico.ct.vo.ResponseVo;
import org.nico.ct.vo.domain.template.TempConfigVo;


@RestCinema
@Lobby(mapping = CtConfig.APP_AUTHC + "/template/config")
public class TemplateConfigController {

	@Label
	private TempContentService tempContentService;
	
	@Label
	private TempConfigService tempConfigService;
	
	@Lobby(requestMethod = RequestMethod.PUT)
	public ResponseVo configPut(
			@Body @Verify @NotNull TempConfigVo tempConfigVo,
			VerifyResult verifyResult
		) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = BaseUtils.getCurrentAccount();
			tempConfigVo.setId(CenteringUtils.productionID());
			tempConfigVo.setAccountId(account.getId());
			tempConfigService.insert(BaseUtils.copyObject(tempConfigVo, TempConfig.class));
			return result.setResponseCode(ResponseCode.CODE_SUCCESS).setData(tempConfigVo);
		}catch(Exception e) {
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(null);
		}
	}
	
	@Lobby(requestMethod = RequestMethod.POST)
	public ResponseVo configPost(
			@Body @Verify @NotNull TempConfigVo tempConfigVo,
			@QueryParam @NotNull String id,
			VerifyResult verifyResult
		) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = BaseUtils.getCurrentAccount();
			
			TempConfig tempConfig = tempConfigService.get(id);
			if(tempConfig == null) {
				return result.setResponseCode(ResponseCode.CODE_ENTITY_IS_NULL);
			}
			
			if(! account.getId().equals(tempConfig.getAccountId())) {
				throw new AuthException(MessageConstant.MESSAGE_AUTH_FAIL);
			}
			
			tempConfig.setName(tempConfigVo.getName());
			tempConfig.setPrefix(tempConfigVo.getPrefix());
			tempConfig.setSuffix(tempConfigVo.getSuffix());
			tempConfig.setPropertiesJson(tempConfigVo.getPropertiesJson());
			long row = tempConfigService.update(tempConfig);
			
			if(row < 1) {
				return result.setResponseCode(ResponseCode.CODE_ERROR);
			}
			
			return result.setResponseCode(ResponseCode.CODE_SUCCESS).setData(tempConfig);
		}catch(Exception e) {
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(null);
		}
	}
	
	@Lobby(mapping = "/{id}", requestMethod = RequestMethod.DELETE)
	public ResponseVo configDelete(
			@PathParam @NotNull String id,
			VerifyResult verifyResult
		) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = BaseUtils.getCurrentAccount();
			
			TempConfig tempConfig = tempConfigService.get(id);
			if(tempConfig == null) {
				return result.setResponseCode(ResponseCode.CODE_ENTITY_IS_NULL);
			}
			
			if(! account.getId().equals(tempConfig.getAccountId())) {
				throw new AuthException(MessageConstant.MESSAGE_AUTH_FAIL);
			}
			
			boolean deled = tempConfigService.deleteById(id);
			
			if(! deled) {
				return result.setResponseCode(ResponseCode.CODE_ERROR);
			}
			
			return result.setResponseCode(ResponseCode.CODE_SUCCESS);
		}catch(Exception e) {
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(null);
		}
	}
	
	@Lobby(mapping = "/{id}", requestMethod = RequestMethod.GET)
	public ResponseVo configGet(
			@PathParam @NotNull String id,
			VerifyResult verifyResult
		) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = BaseUtils.getCurrentAccount();
			TempConfig entity = tempConfigService.get(id);
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
	public ResponseVo configQueries(
			@Body @NotNull TempConfigVo tempConfigVo,
			VerifyResult verifyResult
		) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = BaseUtils.getCurrentAccount();
			tempConfigVo.getQuery().put("accountId", account.getId());
			List<TempConfig> datas = tempConfigService.get(tempConfigVo.getQuery(), tempConfigVo.getPage());
			long count = tempConfigService.count(tempConfigVo.getQuery());
			return result.setResponseCode(ResponseCode.CODE_SUCCESS).setData(datas).setCount(count);
		}catch(Exception e) {
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(null);
		}
	}
	
}
