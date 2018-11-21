package org.nico.ct.exception;

/** 
 * 
 * @author nico
 * @version createTime：2018年5月20日 下午11:06:05
 */

public class CtException extends RuntimeException{

	private static final long serialVersionUID = 7262333891757760664L;

	public CtException() {
		super();
	}

	public CtException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CtException(String message, Throwable cause) {
		super(message, cause);
	}

	public CtException(String message) {
		super(message);
	}

	public CtException(Throwable cause) {
		super(cause);
	}

}
