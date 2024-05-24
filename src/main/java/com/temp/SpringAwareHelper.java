package com.temp;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringAwareHelper implements ApplicationContextAware {

	private static ApplicationContext context;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}
	
	/**
	 * 依據 Interface 取得 Instance
	 * @param requiredType
	 * @return
	 */
	public static <T> T getBean(Class<T> requiredType) {
		return context.getBean(requiredType);
	}
	
	/**
	 * 依據 Bean name 取得 Instance
	 * @param name bean name
	 * @return
	 */
	public static Object getBean(String name) {
		return context.getBean(name);
	}
}
