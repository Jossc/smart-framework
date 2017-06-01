package org.smart4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: mac
 * PACKAGE_NAME: org.smart4j.framework.annotation
 * MONTH_NAME_SHORT: 五月
 * DAY_NAME_SHORT: 星期二
 * PROJECT_NAME: tomcat-work
 * TIME: 22:58
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Transaction {

}
