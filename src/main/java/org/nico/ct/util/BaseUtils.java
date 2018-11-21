package org.nico.ct.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.nico.cat.server.container.Container;
import org.nico.cat.server.request.Request;
import org.nico.cat.server.request.extra.Cookie;
import org.nico.ct.constant.CacheKey;
import org.nico.ct.domain.Account;
import org.nico.ct.exception.HandlerException;
import org.nico.noson.Noson;
import org.nico.noson.util.string.StringUtils;

/** 
 * 
 * @author nico
 * @version createTime：2018年5月22日 下午10:33:05
 */

public class BaseUtils {
	
	/**
	 * 获取当前登录用户
	 * 
	 * @return account
	 */
	public static Account getCurrentAccount(){
		Request request = Container.getInstance().getActivityRequest();
		String uToken = request.getHeader("token");
		
		if(StringUtils.isBlank(uToken)){
			Cookie cookie = request.getCookieMap().get("uToken");
			if(cookie != null) {
				uToken = cookie.getValue();
			}
		}
		
		if(StringUtils.isBlank(uToken)){
			throw new NullPointerException("uToken is null");
		}
		try {
			return Noson.convert(JedisUtils.get(CacheKey.ACCOUNT_TOKEN + uToken, String.class), Account.class);
		}catch(NullPointerException e) {
			return null;
		}
	}
	
	/**
	 * 将inObj的对象属性复制到outObj对象中，继承的属性不予处理
	 * 
	 * @param inObj
	 * @param outObj
	 */
	public static void copyObject(Object inObj, Object outObj) {
		if(inObj == null || outObj == null) {
			throw new NullPointerException("复制对象双方存在空对象！");
		}
		
		Class<?> inClazz = inObj.getClass();
		Field[] inFields = inClazz.getDeclaredFields();
		Map<String, Object> inFieldValueMap = new HashMap<String, Object>();
		if(inFields != null && inFields.length > 0) {
			for(Field inField: inFields) {
				Object obj = null;
				if(! inField.isAccessible()) {
					inField.setAccessible(true);
				}
				try {
					obj = inField.get(inObj);
					inFieldValueMap.put(inField.getName() + inField.getType().getName(), obj);
				} catch (Exception e) {
					throw new HandlerException(e.getMessage(), e);
				}
			}
		}
		
		Class<?> outClazz = outObj.getClass();
		Field[] outFields = outClazz.getDeclaredFields();
		if(outFields != null && outFields.length > 0) {
			for(Field outField: outFields) {
				Object val = null;
				if((val = inFieldValueMap.get(outField.getName() + outField.getType().getName())) != null) {
					if(! outField.isAccessible()) {
						outField.setAccessible(true);
					}
					try {
						outField.set(outObj, val);
					} catch (Exception e) {
						throw new HandlerException(e.getMessage(), e);
					}
				}
			}
		}
	}
	
	/**
	 * 将inObj的对象属性复制到outObj对象中，继承的属性不予处理
	 * 
	 * @param inObj
	 * @param outClazz
	 * @return
	 */
	public static <T> T copyObject(Object inObj, Class<T> outClazz) {
		try {
			T outObj = outClazz.newInstance();
			copyObject(inObj, outObj);
			return outObj;
		}catch(Exception e) {
			throw new HandlerException(e.getMessage(), e);
		}
		
	}
	
	/**
	 * 空对象维护
	 * 
	 * @param obj 要维护的对象
	 */
	public static void assertNull(Object obj){
		if(null == obj){
			throw new NullPointerException(obj.getClass().getName() + " obj is null");
		}
	}
	
}
