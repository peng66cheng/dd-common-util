package com.dd.util.result;

public enum StatusCodeEnum {

	/** 全局状态码 **/
	SUCCESS("200", "成功"), 
	NO_MODIFY("304", "数据未更新"),
	
	SUCCESS_EMPTY("5001", "请求成功，返回没有数据"), 
	ILLEGAL_ARGUMENT("5002", "请求参数非法"), 
	UNKNOWN_ERROR("5009", "未知错误"), 
	FAILED("5999", "失败"),

	/** 登录状态码 **/
	UN_LOGIN("5101", "未登录"), 
	KICKED("5102", "被踢下线"), 
	USER_PWD_ERROR("5103", "用户密码错误"),
	USER_INVALID("5104", "帐号无效")
	
	;

	private String code;

	private String message;

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	StatusCodeEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public static StatusCodeEnum getCodeMessage(String code) {
		for (StatusCodeEnum codeMsg : StatusCodeEnum.values()) {
			if (codeMsg.getCode() == code) {
				return codeMsg;
			}
		}
		return null;
	}
}
