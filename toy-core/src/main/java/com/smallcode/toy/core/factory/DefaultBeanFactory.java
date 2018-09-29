package com.smallcode.toy.core.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.smallcode.toy.core.config.BeanDefinition;
import com.smallcode.toy.core.support.BeanDefinitionRegistry;
import com.smallcode.toy.core.support.ComponentScanner;

/**
 *
 * @author niele
 * @date 2018/9/29
 */
public class DefaultBeanFactory extends AbstractBeanFactory implements BeanDefinitionRegistry {

	public ComponentScanner componentScanner;


	private final Map<String, Object> beanObjects = new HashMap<String, Object>();

	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();


	public DefaultBeanFactory(String packageNames) {
		componentScanner = new ComponentScanner(this, packageNames);
		componentScanner.scan();
	}

	public DefaultBeanFactory() {
	}

	@Override
	public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
		beanDefinitionMap.put(beanName, beanDefinition);
	}

	@Override
	public void removeBeanDefinition(String beanName) {
		// todo
	}

	@Override
	public BeanDefinition getBeanDefinition(String beanName) {
		// todo
		return null;
	}

	@Override
	public boolean containsBeanDefinition(String beanName) {
		// todo
		return false;
	}

	@Override
	public String[] getBeanDefinitionNames() {
		// todo
		return new String[0];
	}

	@Override
	public int getBeanDefinitionCount() {
		// todo
		return 0;
	}
}
