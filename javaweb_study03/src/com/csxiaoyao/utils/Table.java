package com.csxiaoyao.utils;

import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解，描述表名称
 */
@Target({TYPE})
@Retention(RetentionPolicy.RUNTIME)  // 指定注解在运行时期有效
public @interface Table {

	String tableName();
}
