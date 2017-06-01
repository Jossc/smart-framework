package org.smart4j.framework.helper;

import org.smart4j.cxp.util.ArrayUtil;
import org.smart4j.cxp.util.CollectionUtil;
import org.smart4j.cxp.util.ReflectionUtil;
import org.smart4j.framework.annotation.Inject;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by mac on 2017/5/24
 * User: cxp
 * PACKAGE_Name : org.smart4j.framework.helper
 * Project_Name : tomcatwork
 * Date: 2017/5/24
 * Time: 23:37
 * 依赖注入
 * map 加载映射关系然后，
 * 然后取出bean实例，通过反射获取当前类是否带有
 * Inject注解
 */
public final class IocHelper {
    /**
     * 加载直接生成
     */
    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if(CollectionUtil.isNotEmpty(beanMap)){
            for(Map.Entry<Class<?>,Object> beanEnrty : beanMap.entrySet()){
                //获取实例对象
                Class<?> beanClass = beanEnrty.getKey();
                Object beanInstance = beanEnrty.getValue();
                //获取所有成员变量
                Field[] beanFields = beanClass.getDeclaredFields();
                if(ArrayUtil.isNotEmpty(beanFields)){

                    for(Field field : beanFields){
                        //当前类是不是有 @Inject 注解
                        if(field.isAnnotationPresent(Inject.class)){
                            Class<?> beanFiledClass =  field.getType();
                            Object beanFieldInstance = beanMap.
                                    get(beanFiledClass);
                            if(null != beanFieldInstance){
                                ReflectionUtil.setField(beanInstance,
                                        field,beanFieldInstance);
                            }
                        }
                    }
                }
            }
        }
    }
}
