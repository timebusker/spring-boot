package cn.timebusker.model;

import java.io.Serializable;

public class MessageEntity implements Serializable {

	private static final long serialVersionUID = -6686366912012590475L;

	/**
	 * 消息编号
	 */
	private Long messgeId;

	/**
	 * 消息内容
	 */
	private String context;

	/**
	 * 消息备注
	 */
	private String remark;

	public MessageEntity() {
		this.messgeId = System.currentTimeMillis();
		this.context = "今天一直下雨，在家编代码，时间戳为：" + messgeId;
		this.remark = "这是一个测试消息";
	}

	public Long getMessgeId() {
		return messgeId;
	}

	public void setMessgeId(Long messgeId) {
		this.messgeId = messgeId;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "消息信息为：\t 消息编号" + messgeId + "\t消息内容" + context + "\t消息备注信息" + remark;
	}
}
