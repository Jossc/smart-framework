package org.smart4j.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by mac on 2017/5/24
 * User: cxp
 * PACKAGE_Name : org.smart4j.framework.annotation
 * Project_Name : tomcatwork
 * Date: 2017/5/24
 * Time: 17:51
 * Action
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    /**
     * 获取请求类型和路径
     * @return
     */
    String value();

}
