package com.dd.util.result;

import java.io.Serializable;

/**
 * 统一格式的返回值
 * 
 * @author dingpc
 * 
 * @param <T>
 *            类型
 */
public class UnifiedResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private String stateCode;// 状态编码

	private String retMessage;// 编码对应中文

	private T data;// 对应数据

	/**
	 * 构造方法
	 * 
	 * @param StatusCodeEnum
	 *            状态枚举
	 * @param data
	 *            对应数据
	 */
	protected UnifiedResult(StatusCodeEnum statusCode, T data) {
		super();
		this.stateCode = statusCode.getCode();
		this.retMessage = statusCode.getMessage();
		this.data = data;
	}

	/**
	 * 赋值状态
	 * 
	 * @param statusCode
	 *            状态枚举
	 */
	public UnifiedResult<T> fillStatusCode(StatusCodeEnum statusCode) {
		this.stateCode = statusCode.getCode();
		this.retMessage = statusCode.getMessage();
		return this;
	}

	/**
	 * 构造方法
	 */
	private UnifiedResult() {

	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getRetMessage() {
		return retMessage;
	}

	public void setRetMessage(String retMessage) {
		this.retMessage = retMessage;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "CommonResultBean [stateCode=" + stateCode + ", retMessage=" + retMessage + ", data=" + data + "]";
	}

	/**
	 * 返回数据+返回码&描述信息
	 * 
	 * @param data
	 * @param statusCode
	 * @return
	 */
	public static <T> UnifiedResult<T> getInstance(T data, StatusCodeEnum statusCode) {
		UnifiedResult<T> confirmBean = new UnifiedResult<T>();
		confirmBean.setData(data);
		confirmBean.setStateCode(statusCode.getCode());
		confirmBean.setRetMessage(statusCode.getMessage());
		return confirmBean;
	}

	/**
	 * 返回数据+返回码&描述信息
	 * 
	 * @param data
	 * @param statusCode
	 * @return
	 */
	public static <T> UnifiedResult<T> getInstance(StatusCodeEnum statusCode) {
		UnifiedResult<T> confirmBean = new UnifiedResult<T>();
		confirmBean.setStateCode(statusCode.getCode());
		confirmBean.setRetMessage(statusCode.getMessage());
		return confirmBean;
	}

	/**
	 * 获取成功的结果
	 * 
	 * @return
	 */
	public static <T> UnifiedResult<T> getSuccessResult() {
		return getInstance(StatusCodeEnum.SUCCESS);
	}

	/**
	 * 获取成功的结果
	 * 
	 * @return
	 */
	public static <T> UnifiedResult<T> getSuccessResult(T data) {
		return getInstance(data, StatusCodeEnum.SUCCESS);
	}

	/**
	 * 获取未知错误的结果
	 * 
	 * @return
	 */
	public static <T> UnifiedResult<T> getUnkownErrorResult() {
		return getInstance(StatusCodeEnum.UNKNOWN_ERROR);
	}

}
