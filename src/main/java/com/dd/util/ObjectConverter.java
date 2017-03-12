package com.dd.util;

/**
 * 对象转换接口
 * 
 * @author dingpc
 *
 */
public interface ObjectConverter<F, T> {

	/**
	 * 对象属性转换
	 * 
	 * @param source
	 * @param target
	 */
	void convert(F source, T target);
}
