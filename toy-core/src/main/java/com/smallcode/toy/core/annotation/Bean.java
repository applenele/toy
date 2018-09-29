package com.smallcode.toy.core.annotation;

/**
 *
 * @author niele
 * @date 2018/9/29
 */
@Component
public @interface Bean {

	String value() default "";
}
