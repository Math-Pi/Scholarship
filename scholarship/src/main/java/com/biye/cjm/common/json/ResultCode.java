package com.biye.cjm.common.json;

public enum ResultCode{
	SUCCESS(200, "操作成功"),
	ERROR(500, "系统异常"),
	FATAL(500, "操作失败"),
	NOAUTHORIZATION(401, "尝试访问未授权资源"),
	INVALID_PARAM(500, "非法参数"),
	NOT_FOUND(404, "页面未找到");

	private int value;
	private String text;

	private ResultCode(int value, String text) {
		this.value = value;
		this.text = text;
	}

	/**
	 * 获取value  
	 * @return int
	 */
	public int getValue() {
		return value;
	}

	/**
	 * 设置 value  
	 * @param value int
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * 获取text  
	 * @return String
	 */
	public String getText() {
		return text;
	}

	/**
	 * 设置 text  
	 * @param text String
	 */
	public void setText(String text) {
		this.text = text;
	}
}