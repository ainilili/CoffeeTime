package org.nico.ct.constant;

public enum ResponseCode {

	CODE_SUCCESS(200, "操作成功"),
	
	CODE_ERROR(500, "操作失败"),
	
	CODE_ACCOUNT_NO_LOGIN(501, "用户未登录"),
	
	CODE_ENTITY_IS_NULL(502, "找不到对应实体"),
	;
	
	private int code;
	
	private String msg;

	private ResponseCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
