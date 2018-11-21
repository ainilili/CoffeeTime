package org.nico.ct.section;

import org.nico.cat.config.ConfigKey;
import org.nico.cat.server.container.moudle.realize.entry.Filter;
import org.nico.cat.server.request.Request;
import org.nico.cat.server.request.extra.Cookie;
import org.nico.cat.server.response.Response;
import org.nico.cat.server.response.parameter.HttpCode;
import org.nico.ct.constant.ResponseCode;
import org.nico.ct.domain.Account;
import org.nico.ct.util.BaseUtils;
import org.nico.ct.vo.ResponseVo;
import org.nico.noson.Noson;

public class AuthFilter extends Filter{

	@Override
	public void filter(Request request, Response response) {
		
		Account account = BaseUtils.getCurrentAccount();
		
		if(account == null) {
			ResponseVo result = new ResponseVo(); 
			result.setResponseCode(ResponseCode.CODE_ACCOUNT_NO_LOGIN);
			
			response.getHeaders().putLast("Content-Type", "application/json;charset=" + ConfigKey.server_charset);
			response.getHeaders().putLast("Location", "/index.html");
			
			//清除uToken
			Cookie uTokenCookie = new Cookie("uToken", "deleted");
			uTokenCookie.setPath("/");
			uTokenCookie.setMaxAge(0);
			//清除用户缓存
			Cookie accountCookie = new Cookie("account", "deleted");
			accountCookie.setPath("/");
			accountCookie.setMaxAge(0);
			
			response.addCookie(uTokenCookie);
			response.addCookie(accountCookie);
			
			
			response.setHttpcode(HttpCode.HS200);
			response.setResponseBody(Noson.reversal(result));
			response.push();
		}
		
	}

}
