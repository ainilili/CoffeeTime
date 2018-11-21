package org.nico.ct.util;

import java.util.UUID;

/** 
 * Centering Utils
 * 
 * @author nico
 * @version createTime�?2018�?4�?14�? 下午10:43:57
 */

public class CenteringUtils {
	
	/**
	 * 中心化生产ID
	 * 
	 * @return ID
	 */
	public static String productionID(){
		return UUID.randomUUID().toString();
	}
}
