package com.smallcode.toy.core.factory;

/**
 *
 * @author niele
 * @date 2018/9/29
 */
public interface BeanFactory {

	Object getBean(String beanName);

	<T> T getBean(Class<T> requireType);
}
