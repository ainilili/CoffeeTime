package org.nico.ct.section;

import org.nico.aoc.aspect.point.ProcessingAspectPoint;
import org.nico.aoc.scan.annotations.aspect.Around;
import org.nico.aoc.scan.annotations.aspect.Aspect;
import org.nico.aoc.scan.entity.AspectType;
import org.nico.cat.mvc.router.RouterForNocat.VerifyResult;
import org.nico.ct.constant.ResponseCode;
import org.nico.ct.exception.BoundingException;
import org.nico.ct.vo.ResponseVo;

@Aspect(type = AspectType.CGLIB_PROXY)
public class TryExceptionSection {


	@Around(value = "expression(org.nico.ct.controller.*.*(..))")
	public Object around(ProcessingAspectPoint point) {
		Object result = null;
		try {
			Object[] args = point.getArgs();
			if(args != null && args.length > 0){
				for(Object obj: args){
					if(obj != null && obj instanceof VerifyResult){
						VerifyResult vr = (VerifyResult) obj;
						if(vr.hasError()){
							throw new BoundingException(String.valueOf(vr.getErrorMessages()));
						}
					}
				}
			}
			result = point.process();
		} catch (Throwable e) {
			result = new ResponseVo().setResponseCode(ResponseCode.CODE_ERROR).setData(null).setMsg(e.getMessage());
		}
		return result;
	}


}
