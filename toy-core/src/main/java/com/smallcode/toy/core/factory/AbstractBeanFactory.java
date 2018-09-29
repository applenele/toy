package com.smallcode.toy.core.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.smallcode.toy.core.config.BeanDefinition;

/**
 *
 * @author niele
 * @date 2018/9/29
 */
public abstract class AbstractBeanFactory implements BeanFactory {



	@Override
	public Object getBean(String beanName) {
		return null;
	}


	@Override
	public <T> T getBean(Class<T> requireType) {
		return null;
	}
}
