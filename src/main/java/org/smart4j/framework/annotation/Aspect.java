package org.smart4j.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by mac on 2017/5/26
 * User: cxp
 * PACKAGE_Name : org.smart4j.framework.annotation
 * Project_Name : tomcatwork
 * Date: 2017/5/26
 * Time: 18:02
 * 切面注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    Class<? extends Annotation> value();
}
