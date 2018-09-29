package com.smallcode.toy.sample;

import java.util.Map;

import com.smallcode.toy.core.config.BeanDefinition;
import com.smallcode.toy.core.factory.DefaultBeanFactory;

/**
 *
 * @author niele
 * @date 2018/9/29
 */
public class Main {

	public static void main(String[] args) {

		String packageName = "com.smallcode.toy.sample";
		DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory(packageName);

	}
}
