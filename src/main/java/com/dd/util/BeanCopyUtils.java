package com.dd.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;


/**
 * 对象 拷贝工具
 * 
 * @author dingpc
 */
public class BeanCopyUtils {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BeanCopyUtils.class);

	/**
	 * 通用对象转换方法
	 * 
	 * @param from
	 *            转换源对象
	 * @param to
	 *            转换目标对象
	 * @param ignoreProperties
	 *            忽略的屬性 (目标对象属性)
	 * @param converter
	 *            转化接口,用户特殊属性处理
	 * @return
	 */
	public static <T, V> T convertClass(V from, Class<T> to, String[] ignoreProperties,
			ObjectConverter<V, T> converter) {
		T t = null;
		try {
			if (from != null) {
				t = to.newInstance();
				BeanUtils.copyProperties(from, t, ignoreProperties);
				if (converter != null) {
					converter.convert(from, t);
				}
			}
		} catch (Exception e) {
			logger.error("转换出错:目标类" + to.getName(), e);
		}
		return t;
	}

	/**
	 * 通用对象转换方法
	 * 
	 * @param from
	 *            转换源对象
	 * @param to
	 *            转换目标对象
	 * @param ignoreProperties
	 *            忽略的屬性 (目标对象属性)
	 */
	public static <T, V> T convertClass(V from, Class<T> to, String[] ignoreProperties) {
		return convertClass(from, to, ignoreProperties, null);
	}

	/**
	 * 通用对象转换方法
	 * 
	 * @param from
	 *            转换源对象
	 * @param to
	 *            转换目标对象
	 * @param converter
	 *            转化接口,用户特殊属性处理
	 * @return
	 */
	public static <T, V> T convertClass(V from, Class<T> to, ObjectConverter<V, T> converter) {
		return convertClass(from, to, null, converter);
	}

	/**
	 * 通用对象转换方法
	 * 
	 * @param from
	 *            转换源对象
	 * @param to
	 *            转换目标对象
	 * @return
	 */
	public static <T> T convertClass(Object from, Class<T> to) {
		return convertClass(from, to, null, null);
	}

	/**
	 * 通用列表转换方法
	 * 
	 * @param from
	 *            转换源列表
	 * @param to
	 *            转换目标对象类
	 * @param ignoreProperties
	 *            忽略属性(目标对象属性)
	 * @param converter
	 *            转化接口,用户特殊属性处理
	 * @return
	 */
	public static <T, V> List<T> convertList(List<V> from, Class<T> to, String[] ignoreProperties,
			ObjectConverter<V, T> converter) {
		if (CollectionUtils.isEmpty(from)) {
			return null;
		}
		List<T> targets = new ArrayList<T>();
		for (V v : from) {
			T target = convertClass(v, to, ignoreProperties);
			if (target == null) {
				continue;
			}
			targets.add(target);
		}
		return targets;
	}

	public static <T, V> List<T> convertList(List<V> from, Class<T> to, ObjectConverter<V, T> converter) {
		return convertList(from, to, null, converter);
	}

	/**
	 * 通用列表转换方法
	 * 
	 * @param from
	 *            转换源列表
	 * @param to
	 *            转换目标对象类
	 * @return
	 */
	public static <T, V> List<T> convertList(List<V> from, Class<T> to) {
		return convertList(from, to, null, null);
	}

}
