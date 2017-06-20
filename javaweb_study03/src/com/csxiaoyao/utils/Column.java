package com.csxiaoyao.utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
/**
 * 描述一个字段
 */
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)  // 指定注解在运行时期有效
public @interface Column {
	String columnName();
}