package com.smallcode.toy.sample.bean;

import com.smallcode.toy.core.annotation.Bean;


/**
 *
 * @author niele
 * @date 2018/9/29
 */
@Bean("user")
public class User extends BaseDomain{


	public void say(){
		System.out.println("hello user");
	}
}
