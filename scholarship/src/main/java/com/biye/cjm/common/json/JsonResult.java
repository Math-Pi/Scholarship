package com.biye.cjm.common.json;

/**
 *
 * 返回前端请求的结果集，
 *
 * @author Kevin
 *
 */
public class JsonResult {

	/**
	 *
	 * 返回的业务数据
	 *
	 */
	private Object data;

	/**
	 *
	 * 返回的消息内容
	 *
	 */
	private String message;

	/**
	 *
	 * 返回的异常代码
	 *
	 */
	private Integer code;

	private JsonResult(ResultCode code) {
		this.code=code.getValue();
		this.message=code.getText();
	}

	public static JsonResult success(Object data) {
		JsonResult result=new JsonResult(ResultCode.SUCCESS);
		result.data = data;
		return result;
	}

	public static JsonResult failure() {
		JsonResult result=new JsonResult(ResultCode.FATAL);
		return result;
	}

	/**
	 * 为了解决前台错误信息过于明显的问题，暂时再此处屏蔽调sql类型的报错
	 */
	public static JsonResult failure(String message) {
		JsonResult result=new JsonResult(ResultCode.FATAL);
		if(message.contains("SQL")){
			result.message="出现未经处理的db错误，请联系管理员！";
		}else{
			result.message=message;
		}
		return result;
	}

	public static JsonResult noLogin() {
		JsonResult result=new JsonResult(ResultCode.NOAUTHORIZATION);
		result.message="未登录或会话已过期，请重新登录！";
		return result;
	}

	public static JsonResult unAuthorized() {
		JsonResult result=new JsonResult(ResultCode.NOAUTHORIZATION);
		return result;
	}


	/**
	 * 获取data  
	 * @return
	 */
	public Object getData() {
		return data;
	}

	/**
	 * 设置 data  
	 * @param data
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * 获取message  
	 * @return String
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置 message  
	 * @param message String
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 获取message  
	 * @return String
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * 设置 code  
	 * @param message String
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

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
}
