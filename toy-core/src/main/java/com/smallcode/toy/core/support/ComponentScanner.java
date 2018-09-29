package com.smallcode.toy.core.support;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.smallcode.toy.core.annotation.Component;
import com.smallcode.toy.core.config.BeanDefinition;
import com.smallcode.toy.core.consts.Strings;
import com.sun.jndi.toolkit.url.UrlUtil;

/**
 * 基于注解的扫码Component 扫描器
 * @author niele
 * @date 2018/9/29
 */
public class ComponentScanner implements Scanner {


	private List<String> classNames = new ArrayList<>();

	private BeanDefinitionRegistry registry;

	private String packageName = "";

	private ClassLoader classLoader;


	public ComponentScanner(BeanDefinitionRegistry registry, String packageName) {
		this.registry = registry;
		this.packageName = packageName;
	}

	public ComponentScanner(BeanDefinitionRegistry registry) {
		this.registry = registry;
	}

	public ComponentScanner() {

	}


	private void doScan(String packageName) {
		URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
		File classDir = new File(url.getFile());
		for (File file : classDir.listFiles()) {
			if (file.isDirectory()) {
				doScan(packageName + "." + file.getName());
			}
			else {
				String className = packageName + "." + file.getName().replace(".class", "");
				classNames.add(className);
			}
		}
	}

	@Override
	public void scan() {
		// todo  扫描得到 beanDefinition

		if (packageName.endsWith(Strings.DOT)) {
			packageName = packageName.substring(0, packageName.length() - 1);
		}

		doScan(packageName);

		if (!classNames.isEmpty()) {
			for (String className : classNames) {
				try {
					Class<?> clazz = Class.forName(className);
					if (clazz.isAnnotationPresent(Component.class)) {
						Component component = clazz.getAnnotation(Component.class);
						if (component != null) {
							String val = component.value();
							if (val.equals("")) {
								String clazzName = clazz.getSimpleName();
								val = lowFirstCase(clazzName);
							}
							BeanDefinition beanDefinition = new BeanDefinition(className);
							registry.registerBeanDefinition(val, beanDefinition);
						}
					}
					else {
						continue;
					}

				}
				catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private String lowFirstCase(String str) {
		char[] chars = str.toCharArray();
		chars[0] += 32;
		return String.valueOf(chars);
	}
}
