package org.smart4j.framework.util.ClassUtil;

import org.smart4j.cxp.util.ClassUtil;
import org.smart4j.framework.helper.*;

/**
 * Created by mac on 2017/5/25
 * User: cxp
 * PACKAGE_Name : org.smart4j.framework.util.ClassUtil
 * Project_Name : tomcatwork
 * Date: 2017/5/25
 * Time: 00:15
 */
public final class HelperLoader {

    public static  void init(){
        Class<?> [] classes = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };

        for(Class<?> aClass : classes){
            ClassUtil.loadClass(aClass.getName());
        }
    }


}
