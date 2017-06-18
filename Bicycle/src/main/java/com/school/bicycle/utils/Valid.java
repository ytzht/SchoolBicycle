package com.school.bicycle.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表单注解
 * @author zouooh
 *
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Valid {
	/**
	 * 提交值时用到.
	 * @return
	 */
	String jsonField() default "";
	/**
	 *.
	 * @return
	 */
	String hint() default "";
	/**
	 * 是否必填
	 * @return
	 */
	boolean required() default false;
	
	/**
	 * input 类型
	 * @return
	 */
	InputType inputType() default InputType.Text;
	/**
	 * 检验值规则
	 * @return
	 */
	String valid() default "";
	/**
	 * 出错提示
	 * @return
	 */
	String tip() default "";
}
