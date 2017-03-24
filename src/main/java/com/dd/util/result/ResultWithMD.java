package com.dd.util.result;

/**
 * 带有
 * 
 * @author dingpc
 * @date 2017年3月24日
 */
public class ResultWithMD<T> extends UnifiedResult<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8867907994033995821L;

	private Long modifyDate;// 数据修改时间

	protected ResultWithMD(StatusCodeEnum statusCode, T data) {
		super(statusCode, data);
	}

	public Long getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Long modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public static <T> UnifiedResult<T> getInstance(StatusCodeEnum statusCode, T data, long modifyDate) {
		ResultWithMD<T> confirmBean = new ResultWithMD<T>(statusCode, data);
		confirmBean.setModifyDate(modifyDate);
		return confirmBean;
	}

	/**
	 * 获取成功的结果
	 * 
	 * @return
	 */
	public static <T> UnifiedResult<T> getSuccessResult(T data, long modifyDate) {
		return getInstance( StatusCodeEnum.SUCCESS, data,modifyDate);
	}

}
