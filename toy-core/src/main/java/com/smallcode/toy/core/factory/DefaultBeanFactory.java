package com.smallcode.toy.core.factory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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

	/**
	 * bean 类型和名字对应
	 */
	private final Map<Class<?>, String[]> allBeanNamesByType = new ConcurrentHashMap<>();


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

	@Override
	public Object getBean(String beanName) {
		if (beanObjects.containsKey(beanName)) {
			return beanObjects.get(beanName);
		}
		if (beanDefinitionMap.containsKey(beanName)) {
			BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
			try {
				Class<?> clazz = Class.forName(beanDefinition.getClassName());
				Object object = clazz.newInstance();
				beanObjects.put(beanName, object);
				return object;
			}
			catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public <T> T getBean(Class<T> requireType) {
		String[] candidateNames = getBeanNamesForType(requireType);

		return (T) getBean(candidateNames[0]);
	}

	private String[] getBeanNamesForType(Class<?> requireType) {
		if (allBeanNamesByType.containsKey(requireType)) {
			return allBeanNamesByType.get(requireType);
		}

		List<String> candidateNameList = new ArrayList<>();
		for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
			if (entry.getValue().getClassName().equals(requireType.getName())) {
				String beanName = entry.getKey();
				candidateNameList.add(beanName);
			}
			if (entry.getValue().getParentName().equals(requireType.getName())) {
				String beanName = entry.getKey();
				candidateNameList.add(beanName);
			}
		}
		String[] candidateNames = candidateNameList.toArray(new String[candidateNameList.size()]);
		if (candidateNameList.size() > 0) {
			allBeanNamesByType.put(requireType, candidateNames);
		}

		return candidateNames;
	}
}
