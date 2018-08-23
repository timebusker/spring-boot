package com.timebusker.exception;

public class CommonException extends RuntimeException {

	private static final long serialVersionUID = -1104674993657790702L;

	/**
	 * 生成序列异常时
	 */
	public static final CommonException DB_GET_SEQ_NEXT_VALUE_ERROR = new CommonException(10040007, "序列生成超时");

	/**
	 * 具体异常码
	 */
	protected int code;

	/**
	 * 异常信息
	 */
	protected String msg;

	/**
	 * 
	 * @param message
	 */
	public CommonException(String message) {
		super(message);
	}

	public CommonException(int code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
		this.msg = String.format(msgFormat, args);
	}

	public CommonException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommonException(Throwable cause) {
		super(cause);
	}

	/**
	 * 实例化异常
	 * 
	 * @param msgFormat
	 * @param args
	 * @return
	 */
	public CommonException newInstance(String msgFormat, Object... args) {
		return new CommonException(this.code, msgFormat, args);
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
