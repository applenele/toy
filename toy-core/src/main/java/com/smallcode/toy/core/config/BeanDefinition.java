package com.smallcode.toy.core.config;

/**
 *
 * @author niele
 * @date 2018/9/29
 */
public class BeanDefinition {

	private String className;

	private String parentName;

	public BeanDefinition(String className, String parentName) {
		this.className = className;
		this.parentName = parentName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}
