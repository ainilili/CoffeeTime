package org.nico.ct.vo;

import org.nico.ct.constant.ResponseCode;

public class ResponseVo {

	private ResponseCode responseCode;
	
	private int code;
	
	private Object data;
	
	private long count;
	
	private String msg;

	public ResponseCode getResponseCode() {
		return responseCode;
	}

	public ResponseVo setResponseCode(ResponseCode responseCode) {
		this.responseCode = responseCode;
		this.code = responseCode.getCode();
		this.msg = responseCode.getMsg();
		return this;
	}

	public int getCode() {
		return code;
	}

	public ResponseVo setCode(int code) {
		this.code = code;
		return this;
	}

	public Object getData() {
		return data;
	}

	public ResponseVo setData(Object data) {
		this.data = data;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public ResponseVo setMsg(String msg) {
		this.msg = msg;
		if(this.msg == null) {
			this.msg = "系统异常";
		}
		return this;
	}

	public long getCount() {
		return count;
	}

	public ResponseVo setCount(long count) {
		this.count = count;
		return this;
	}
	
}
