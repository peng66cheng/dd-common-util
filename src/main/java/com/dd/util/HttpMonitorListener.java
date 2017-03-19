package com.dd.util;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.web.context.support.ServletRequestHandledEvent;

/**
 * http执行时间
 * 
 * @see org.springframework.context.support.AbstractApplicationContext#refresh()
 * 
 * @author guangbing.dgb
 * 
 */
public class HttpMonitorListener implements
		ApplicationListener<ServletRequestHandledEvent> {

	public static final Logger logger = Logger
			.getLogger(HttpMonitorListener.class);

	public static final long WARN_TIME_OUT = 600;

	public static final long ERROR_TIME_OUT = 2000;

	/**
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(ServletRequestHandledEvent event) {
		long processTime = event.getProcessingTimeMillis();
		// has exception
		if (event.wasFailure()) {
			logger.error("invoke [" + event.getRequestUrl()
					+ "] throw " + event.getFailureCause() + " ,detail : " + event);
		} else if (processTime > ERROR_TIME_OUT) {
			logger.error("invoke [" + event.getRequestUrl()
					+ "] time out,detail : " + event);
		} else if (processTime > WARN_TIME_OUT) {
			logger.warn("invoke [" + event.getRequestUrl()
					+ "] time > " + WARN_TIME_OUT + " ,detail : " + event);
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug(event);
			}
		}
	}
	
}
