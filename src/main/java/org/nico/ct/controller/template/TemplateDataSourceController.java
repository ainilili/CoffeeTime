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
import org.nico.ct.component.DatasoruceComponent;
import org.nico.ct.config.CtConfig;
import org.nico.ct.constant.MessageConstant;
import org.nico.ct.constant.ResponseCode;
import org.nico.ct.domain.Account;
import org.nico.ct.domain.template.TempConfig;
import org.nico.ct.domain.template.TempContent;
import org.nico.ct.domain.template.TempDataSource;
import org.nico.ct.exception.AuthException;
import org.nico.ct.exception.BoundingException;
import org.nico.ct.service.TempDataSourceService;
import org.nico.ct.util.BaseUtils;
import org.nico.ct.util.CenteringUtils;
import org.nico.ct.vo.ResponseVo;
import org.nico.ct.vo.domain.template.TempContentVo;
import org.nico.ct.vo.domain.template.TempDataSourceVo;
import org.nico.ct.vo.domain.template.TempTableInfoVo;


@RestCinema
@Lobby(mapping = CtConfig.APP_AUTHC + "/template/datasource")
public class TemplateDataSourceController {

	@Label
	private TempDataSourceService tempDataSourceService;
	
	@Lobby(requestMethod = RequestMethod.PUT)
	public ResponseVo datasourcePut(
			@Body @NotNull @Verify TempDataSourceVo tempDataSourceVo,
			VerifyResult verifyResult
		) {
		ResponseVo result = new ResponseVo(); 
		try {
			
			Account account = BaseUtils.getCurrentAccount();
			tempDataSourceVo.setId(CenteringUtils.productionID());
			tempDataSourceVo.setAccountId(account.getId());
			
			tempDataSourceService.insert(BaseUtils.copyObject(tempDataSourceVo, TempDataSource.class));
			return result.setResponseCode(ResponseCode.CODE_SUCCESS).setData(tempDataSourceVo);
		}catch(Exception e) {
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(null).setMsg(e.getMessage());
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
			
			TempDataSource tempDataSource = tempDataSourceService.get(id);
			if(tempDataSource == null) {
				return result.setResponseCode(ResponseCode.CODE_ENTITY_IS_NULL);
			}
			
			if(! account.getId().equals(tempDataSource.getAccountId())) {
				throw new AuthException(MessageConstant.MESSAGE_AUTH_FAIL);
			}
			
			boolean deled = tempDataSourceService.deleteById(id);
			
			if(! deled) {
				return result.setResponseCode(ResponseCode.CODE_ERROR);
			}
			
			return result.setResponseCode(ResponseCode.CODE_SUCCESS);
		}catch(Exception e) {
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(null).setMsg(e.getMessage());
		}
	}
	
	@Lobby(requestMethod = RequestMethod.POST)
	public ResponseVo contentPost(
			@Body @Verify @NotNull TempDataSourceVo tempDataSourceVo,
			@QueryParam @NotNull String id,
			VerifyResult verifyResult
		) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = BaseUtils.getCurrentAccount();
			TempDataSource entity = tempDataSourceService.get(id);
			
			if(entity == null) {
				return result.setResponseCode(ResponseCode.CODE_ENTITY_IS_NULL);
			}
			
			if(! account.getId().equals(entity.getAccountId())) {
				throw new AuthException(MessageConstant.MESSAGE_AUTH_FAIL);
			}
			
			entity.setUrl(tempDataSourceVo.getUrl());
			entity.setUsername(tempDataSourceVo.getUsername());
			entity.setPassword(tempDataSourceVo.getPassword());
			long row = tempDataSourceService.update(entity);
			
			if(row < 1) {
				return result.setResponseCode(ResponseCode.CODE_ERROR);
			}
			
			return result.setResponseCode(ResponseCode.CODE_SUCCESS).setData(entity);
		}catch(Exception e) {
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(null).setMsg(e.getMessage());
		}
	}
	
	@Lobby(mapping = "/{id}", requestMethod = RequestMethod.GET)
	public ResponseVo datasourceGet(
			@PathParam @NotNull String id,
			VerifyResult verifyResult
		) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = BaseUtils.getCurrentAccount();
			TempDataSource entity = tempDataSourceService.get(id);
			if(entity != null && account.getId().equals(entity.getAccountId())){
				return result.setResponseCode(ResponseCode.CODE_SUCCESS).setData(entity);
			}else{
				throw new AuthException(MessageConstant.MESSAGE_AUTH_FAIL);
			}
		}catch(Exception e) {
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(null).setMsg(e.getMessage());
		}
	}
	
	@Lobby(mapping = "/queries", requestMethod = RequestMethod.POST)
	public ResponseVo datasourceQueries(
			@Body @NotNull TempDataSourceVo tempDataSourceVo,
			VerifyResult verifyResult
		) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = BaseUtils.getCurrentAccount();
			tempDataSourceVo.getQuery().put("accountId", account.getId());
			List<TempDataSource> datas = tempDataSourceService.get(tempDataSourceVo.getQuery(), tempDataSourceVo.getPage());
			long count = tempDataSourceService.count(tempDataSourceVo.getQuery());
			
			return result.setResponseCode(ResponseCode.CODE_SUCCESS).setData(datas).setCount(count);
		}catch(Exception e) {
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(null).setMsg(e.getMessage());
		}
	}
	
	@Lobby(mapping = "/info/{dataSourceId}", requestMethod = RequestMethod.GET)
	public ResponseVo getDataSourceInfo(
			@PathParam @Verify @NotNull(message = "dataSource 不可为空") String dataSourceId,
			VerifyResult verifyResult
		) {
		ResponseVo result = new ResponseVo(); 
		try {
			List<TempTableInfoVo> tableInfos = DatasoruceComponent.getDataSourceInfo(dataSourceId);
			return result.setResponseCode(ResponseCode.CODE_SUCCESS).setData(tableInfos);
		}catch(Exception e) {
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(null).setMsg(e.getMessage());
		}
	}
	
}
