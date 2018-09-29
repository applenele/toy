package com.smallcode.toy.core.support;

import com.smallcode.toy.core.config.BeanDefinition;

/**
 *
 * @author niele
 * @date 2018/9/29
 */
public interface BeanDefinitionRegistry {

	void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

	void removeBeanDefinition(String beanName);

	BeanDefinition getBeanDefinition(String beanName);

	boolean containsBeanDefinition(String beanName);

	String[] getBeanDefinitionNames();

	int getBeanDefinitionCount();
}
