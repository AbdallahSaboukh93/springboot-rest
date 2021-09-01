package com.restfullProject.restfullProject;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringApplicationContext  implements ApplicationContextAware{

	private static ApplicationContext  context ;
	
	@Override
	public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext)
			throws BeansException {
			context=applicationContext;	
	}
	
	public static Object getBean(String beanName) {
		return context.getBean(beanName);
 	}
	
}
