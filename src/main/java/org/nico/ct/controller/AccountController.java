package org.nico.ct.controller;

import org.nico.aoc.scan.annotations.Label;
import org.nico.aoc.scan.annotations.aspect.Section;
import org.nico.cat.mvc.parameter.RequestMethod;
import org.nico.cat.mvc.router.RouterForNocat.VerifyResult;
import org.nico.cat.mvc.scan.annotations.Body;
import org.nico.cat.mvc.scan.annotations.Lobby;
import org.nico.cat.mvc.scan.annotations.RestCinema;
import org.nico.cat.mvc.scan.annotations.Verify;
import org.nico.cat.server.request.extra.Cookie;
import org.nico.cat.server.response.Response;
import org.nico.ct.config.CtConfig;
import org.nico.ct.constant.ResponseCode;
import org.nico.ct.domain.Account;
import org.nico.ct.service.AccountService;
import org.nico.ct.vo.ResponseVo;
import org.nico.ct.vo.domain.AccountVo;

@RestCinema
@Lobby(mapping = CtConfig.APP_NORMAL + "/account")
public class AccountController {

	@Label
	private AccountService accountService;
	
	@Lobby(mapping = "/register", requestMethod = RequestMethod.POST)
	public ResponseVo register(
			@Body @Verify AccountVo accountVo,
			VerifyResult verifyResult
	) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = accountService.register(accountVo.getAccountName(), accountVo.getAccountWord());
			return result.setResponseCode(ResponseCode.CODE_SUCCESS).setData(account);
		}catch(Exception e) {
			return result.setResponseCode(ResponseCode.CODE_ERROR).setData(null);
		}
	}
	
	@Lobby(mapping = "/login", requestMethod = RequestMethod.POST)
	public ResponseVo login(
			Response response, 
			@Body @Verify AccountVo accountVo,
			VerifyResult verifyResult
	) {
		ResponseVo result = new ResponseVo(); 
		try {
			Account account = accountService.login(accountVo.getAccountName(), accountVo.getAccountWord());
			String currentToken = account.getToken();
			Cookie uTokenCookie = new Cookie("uToken", currentToken);
			uTokenCookie.setPath("/");
			Cookie accountCookie = new Cookie("account", account.getNickName());
			accountCookie.setPath("/");
			response.addCookie(uTokenCookie);
			response.addCookie(accountCookie);
			return result.setResponseCode(ResponseCode.CODE_SUCCESS).setData(account);
		}catch(Exception e) {
			e.printStackTrace();
			return result.setResponseCode(ResponseCode.CODE_ERROR).setMsg(e.getLocalizedMessage()).setData(null);
		}
	}
}
