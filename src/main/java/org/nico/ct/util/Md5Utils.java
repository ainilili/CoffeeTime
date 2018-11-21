package org.nico.ct.util;

import java.security.MessageDigest;

import org.nico.util.string.StringUtils;

/** 
 * 
 * @author nico
 * @version createTime�?2018�?5�?6�? 上午9:56:48
 */

public class Md5Utils {

	 //十六进制下数字到字符的映射数�? 
    private final static String[] hexDigits = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"}; 
 
    /**把inputString加密*/ 
    public static String md5(String inputStr){ 
        return encodeByMD5(inputStr); 
    } 
    
    /**
     * 获取密码
     * 
     * @param password
     * @return
     */
    public static String makePassword(String password){
    	String target = null;
    	if(StringUtils.isNotBlank(password)){
    		target = md5(password + "." + password);
    	}else{
    		throw new NullPointerException();
    	}
    	return target;
    }
    
	private static String encodeByMD5(String originString){ 
		if (originString!=null) { 
			try { 
				//创建具有指定算法名称的信息摘�? 
				MessageDigest md5 = MessageDigest.getInstance("MD5"); 
				//使用指定的字节数组对摘要进行�?后更新，然后完成摘要计算 
				byte[] results = md5.digest(originString.getBytes()); 
				//将得到的字节数组变成字符串返�?  
				String result = byteArrayToHexString(results); 
				return result; 
			} catch (Exception e) { 
				e.printStackTrace(); 
			} 
		} 
		return null; 
	} 

	/** 
	 * 轮换字节数组为十六进制字符串 
	 * @param b 字节数组 
	 * @return 十六进制字符�? 
	 */ 
	private static String byteArrayToHexString(byte[] b){ 
		StringBuffer resultSb = new StringBuffer(); 
		for(int i=0;i<b.length;i++){ 
			resultSb.append(byteToHexString(b[i])); 
		} 
		return resultSb.toString(); 
	} 
	
	 //将一个字节转化成十六进制形式的字符串 
    private static String byteToHexString(byte b){ 
        int n = b; 
        if(n<0) 
        n=256+n; 
        int d1 = n/16; 
        int d2 = n%16; 
        return hexDigits[d1] + hexDigits[d2]; 
    } 
}
