package cn.timebusker.exception;

public class MineException extends RuntimeException {

	private static final long serialVersionUID = -1104674993657790702L;

	/**
	 * 生成序列异常时
	 */
	public static final MineException DB_GET_SEQ_NEXT_VALUE_ERROR = new MineException(10040007, "序列生成超时");

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
	public MineException(String message) {
		super(message);
	}

	public MineException(int code, String msgFormat, Object... args) {
		super(String.format(msgFormat, args));
		this.code = code;
		this.msg = String.format(msgFormat, args);
	}

	public MineException(String message, Throwable cause) {
		super(message, cause);
	}

	public MineException(Throwable cause) {
		super(cause);
	}

	/**
	 * 实例化异常
	 * 
	 * @param msgFormat
	 * @param args
	 * @return
	 */
	public MineException newInstance(String msgFormat, Object... args) {
		return new MineException(this.code, msgFormat, args);
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
