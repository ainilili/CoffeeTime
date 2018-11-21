package org.nico.ct.controller.template;


import java.io.ByteArrayInputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nico.aoc.scan.annotations.Label;
import org.nico.cat.mvc.parameter.RequestMethod;
import org.nico.cat.mvc.router.RouterForNocat.VerifyResult;
import org.nico.cat.mvc.scan.annotations.Body;
import org.nico.cat.mvc.scan.annotations.Lobby;
import org.nico.cat.mvc.scan.annotations.NotNull;
import org.nico.cat.mvc.scan.annotations.PathParam;
import org.nico.cat.mvc.scan.annotations.RestCinema;
import org.nico.cat.mvc.scan.annotations.Verify;
import org.nico.cat.server.response.Response;
import org.nico.cat.server.response.parameter.ContentType;
import org.nico.ct.component.DatasoruceComponent;
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
import org.nico.ct.util.ExpressionHelper;
import org.nico.ct.util.FileUtils;
import org.nico.ct.util.ZipUtils;
import org.nico.ct.util.ZipUtils.ZipEntity;
import org.nico.ct.util.temp.ColumnUtils;
import org.nico.ct.vo.ResponseVo;
import org.nico.ct.vo.domain.template.TempBuildFormVo;
import org.nico.ct.vo.domain.template.TempBuildVo;
import org.nico.ct.vo.domain.template.TempTableInfoVo;
import org.nico.noson.Noson;
import org.nico.noson.entity.NoType;
import org.nico.util.collection.CollectionUtils;
import org.nico.util.date.DateFormatUtils;
import org.nico.util.string.StringUtils;


@RestCinema
@Lobby(mapping = CtConfig.APP_AUTHC + "/template/build")
public class TemplateBuildController {

	@Label
	private TempContentService tempContentService;

	@Label
	private TempConfigService tempConfigService;

	@Lobby(requestMethod = RequestMethod.POST)
	public ResponseVo building(
			@Body @Verify @NotNull TempBuildFormVo tempBuildFormVo,
			Response response,
			VerifyResult verifyResult
			) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = BaseUtils.getCurrentAccount();
			
			String json = URLDecoder.decode(tempBuildFormVo.getJson());
			
			TempBuildVo tempBuildVo = Noson.convert(json, TempBuildVo.class);
			
			TempConfig tempConfig = tempConfigService.get(tempBuildVo.getConfigId());
			BaseUtils.assertNull(tempConfig);
			
			ExpressionHelper eh = new ExpressionHelper(tempConfig.getPrefix(), tempConfig.getSuffix());
			
			if(! account.getId().equals(tempConfig.getAccountId())) {
				throw new AuthException(MessageConstant.MESSAGE_AUTH_FAIL);
			}
			
			Map<String, Object> query = new HashMap<String, Object>();
			query.put("tempConfigId", tempConfig.getId());
			List<TempContent> tempContents = tempContentService.get(query, null);
			
			//装载参数
			Map<String, Object> datas = Noson.convert(tempBuildVo.getPropertiesJson(), new NoType<Map<String, Object>>(){});
			if(CollectionUtils.isNotBlank(tempBuildVo.getTables()) && CollectionUtils.isNotBlank(tempContents)) {
				boolean isBatch = tempBuildVo.getTables().size() > 1;
				List<ZipEntity> zesBatch = new ArrayList<ZipEntity>();
				
				for(TempTableInfoVo tableInfo: tempBuildVo.getTables()) {
					//全局参数定义
					datas.put("tableName", tableInfo.getTableName());
					datas.put("className", ColumnUtils.mysqlColumnConvertToJavaFieldNameUpper(tableInfo.getTableName()));
					datas.put("classNameLower", ColumnUtils.mysqlColumnConvertToJavaFieldNameLower(tableInfo.getTableName()));
					datas.put("fields", tableInfo.getFields());
					datas.put("normalFields", tableInfo.getNormalFields());
					datas.put("primaryFields", tableInfo.getPrimaryFields());
					datas.put("date", DateFormatUtils.getDateFormat(new Date(), 5));
					
					//zip压缩
					List<ZipEntity> zes = new ArrayList<ZipEntity>();
					for(TempContent tempContent: tempContents) {
						String target = eh.render(datas, tempContent.getContent().replaceAll("\\\\n", "\n").replaceAll("\\\\t", "\t"));
						byte[] buf = target.getBytes();
						
						String name = (String) datas.get("className");
						if(StringUtils.isNotBlank(tempContent.getFileName())) {
							name = eh.render(datas, tempContent.getFileName());
						}
						
						zes.add(new ZipEntity(buf, FileUtils.joint(name, tempContent.getFileFormat())));
					}
					byte[] buf = ZipUtils.compress(zes);
					String zipFileName = FileUtils.specificFileName(tableInfo.getTableName(), "zip");
					if(! isBatch) {
						response.setResponseBody(new ByteArrayInputStream(buf));
						response.setContentType(ContentType.APPLICATION);
						response.getHeaders().putLast("Content-Disposition", "attachment;filename=\"" + zipFileName +  "\"");
						response.getHeaders().putLast("Content-Type", "application/octet-stream");
						response.getHeaders().putLast("Content-Length", String.valueOf(buf.length));
						response.push();
						break;
					}else {
						zesBatch.add(new ZipEntity(buf, zipFileName));
					}
				}
				if(isBatch) {
					byte[] buf = ZipUtils.compress(zesBatch);
					String zipFileName = FileUtils.randomFileName("zip");
					response.setResponseBody(new ByteArrayInputStream(buf));
					response.setContentType(ContentType.APPLICATION);
					response.getHeaders().putLast("Content-Disposition", "attachment;filename=\"" + zipFileName +  "\"");
					response.getHeaders().putLast("Content-Type", "application/octet-stream");
					response.getHeaders().putLast("Content-Length", String.valueOf(buf.length));
					response.push();
				}
			}
			
			return result.setResponseCode(ResponseCode.CODE_SUCCESS).setData(null);
		}catch(Exception e) {
			e.printStackTrace();
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(e.getMessage());
		}
	}
}
