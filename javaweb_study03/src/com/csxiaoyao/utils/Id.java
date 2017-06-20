package com.csxiaoyao.utils;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述一个主键字段
 */
@Target({FIELD})
@Retention(RetentionPolicy.RUNTIME)  // 指定注解在运行时期有效
public @interface Id {

}
