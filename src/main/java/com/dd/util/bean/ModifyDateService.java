package com.dd.util.bean;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import redis.clients.jedis.JedisCluster;

/**
 * 数据修改时间  服务
 * 
 * @author dingpc
 * @date 2017年3月24日
 */
@Service
public class ModifyDateService {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ModifyDateService.class);

	@Autowired
	private JedisCluster jedisCluster;

	/**
	 * 数据 在某个时间之后 是否更新
	 * 
	 * @param modifyDate
	 *            开始时间
	 * @param key
	 *            数据Key
	 * @return
	 */
	public boolean isDataModified(Long modifyDate, String key) {
		if (modifyDate == null || modifyDate < 0) {
			return true;
		}
		long serverDateModDate = getDataModifyDate(key);
		if (serverDateModDate != 0 && serverDateModDate == modifyDate) {
			return false;
		}
		return true;
	}

	/**
	 * 获取数据 修改时间
	 * 
	 * @param key
	 *            数据Key
	 * @return
	 */
	public long getDataModifyDate(String key) {
		String serverDateModDate = jedisCluster.get(key);
		if (!StringUtils.hasText(serverDateModDate)) {
			setDataModifyDate(key);
			return getDataModifyDate(key);
		}
		if (!NumberUtils.isNumber(serverDateModDate)) {
			logger.warn("Type is not Long,key={}", key);
			return 0;
		}
		return Long.valueOf(serverDateModDate);
	}

	/**
	 * 修改“数据修改时间”
	 * 
	 * @param key
	 */
	public void setDataModifyDate(String key) {
		jedisCluster.set(key, String.valueOf((new java.util.Date()).getTime()));
	}

}
