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
import org.nico.ct.domain.template.TempContent;
import org.nico.ct.exception.AuthException;
import org.nico.ct.service.TempConfigService;
import org.nico.ct.service.TempContentService;
import org.nico.ct.util.BaseUtils;
import org.nico.ct.util.CenteringUtils;
import org.nico.ct.vo.ResponseVo;
import org.nico.ct.vo.domain.template.TempContentVo;


@RestCinema
@Lobby(mapping = CtConfig.APP_AUTHC + "/template/content")
public class TemplateContentController {

	@Label
	private TempConfigService tempConfigService;
	
	@Label
	private TempContentService tempContentService;
	
	@Lobby(requestMethod = RequestMethod.PUT)
	public ResponseVo contentPut(
			@Body @Verify @NotNull TempContentVo tempContentVo,
			VerifyResult verifyResult
		) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = BaseUtils.getCurrentAccount();
			TempConfig tempConfig = tempConfigService.get(tempContentVo.getTempConfigId());
			
			if(tempConfig == null) {
				return result.setResponseCode(ResponseCode.CODE_ENTITY_IS_NULL);
			}
			
			if(! account.getId().equals(tempConfig.getAccountId())) {
				throw new AuthException(MessageConstant.MESSAGE_AUTH_FAIL);
			}
			
			tempContentVo.setId(CenteringUtils.productionID());
			tempContentVo.setAccountId(account.getId());
			tempContentService.insert(BaseUtils.copyObject(tempContentVo, TempContent.class));
			return result.setResponseCode(ResponseCode.CODE_SUCCESS).setData(tempContentVo);
		}catch(Exception e) {
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(null);
		}
	}
	
	@Lobby(requestMethod = RequestMethod.POST)
	public ResponseVo contentPost(
			@Body @Verify @NotNull TempContentVo tempContentVo,
			@QueryParam @NotNull String id,
			VerifyResult verifyResult
		) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = BaseUtils.getCurrentAccount();
			TempContent tempContent = tempContentService.get(id);
			
			if(tempContent == null) {
				return result.setResponseCode(ResponseCode.CODE_ENTITY_IS_NULL);
			}
			
			if(! account.getId().equals(tempContent.getAccountId())) {
				throw new AuthException(MessageConstant.MESSAGE_AUTH_FAIL);
			}
			
			tempContent.setName(tempContentVo.getName());
			tempContent.setContent(tempContentVo.getContent());
			tempContent.setFileFormat(tempContentVo.getFileFormat());
			tempContent.setFileName(tempContentVo.getFileName());
			long row = tempContentService.update(tempContent);
			
			if(row < 1) {
				return result.setResponseCode(ResponseCode.CODE_ERROR);
			}
			
			return result.setResponseCode(ResponseCode.CODE_SUCCESS).setData(tempContent);
		}catch(Exception e) {
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(null);
		}
	}
	
	
	@Lobby(mapping = "/{id}", requestMethod = RequestMethod.DELETE)
	public ResponseVo contentDelete(
			@PathParam @NotNull String id,
			VerifyResult verifyResult
		) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = BaseUtils.getCurrentAccount();
			
			TempContent tempContent = tempContentService.get(id);
			if(tempContent == null) {
				return result.setResponseCode(ResponseCode.CODE_ENTITY_IS_NULL);
			}
			
			if(! account.getId().equals(tempContent.getAccountId())) {
				throw new AuthException(MessageConstant.MESSAGE_AUTH_FAIL);
			}
			
			boolean deled = tempContentService.deleteById(id);
			
			if(! deled) {
				return result.setResponseCode(ResponseCode.CODE_ERROR);
			}
			
			return result.setResponseCode(ResponseCode.CODE_SUCCESS);
		}catch(Exception e) {
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(null);
		}
	}
	
	@Lobby(mapping = "/{id}", requestMethod = RequestMethod.GET)
	public ResponseVo contentGet(
			@PathParam @NotNull String id,
			VerifyResult verifyResult
		) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = BaseUtils.getCurrentAccount();
			TempContent entity = tempContentService.get(id);
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
	public ResponseVo contentQueries(
			@Body @NotNull TempContentVo tempContentVo,
			VerifyResult verifyResult
		) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = BaseUtils.getCurrentAccount();
			
			tempContentVo.getQuery().put("accountId", account.getId());
			List<TempContent> datas = tempContentService.get(tempContentVo.getQuery(), tempContentVo.getPage());
			long count = tempContentService.count(tempContentVo.getQuery());
			
			return result.setResponseCode(ResponseCode.CODE_SUCCESS).setData(datas).setCount(count);
		}catch(Exception e) {
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(null);
		}
	}
	
}
