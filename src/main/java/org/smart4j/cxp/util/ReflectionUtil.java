package org.smart4j.cxp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by mac on 2017/5/24
 * User: cxp
 * PACKAGE_Name : org.smart4j.cxp.util
 * Project_Name : tomcatwork
 * Date: 2017/5/24
 * Time: 18:14
 * 反射工具类
 */
public class ReflectionUtil {
    private static  final Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);


    /**
     * 创建实例
     * @param cla
     * @return
     */
    public static  Object newInstance(Class<?> cla){
        Object instance;
        try {
            instance = cla.newInstance();
        } catch (Exception e) {
            logger.error("new instance failure",e.getMessage());
            throw  new RuntimeException();
        }
        return  instance;
    }

    /**
     * 调用方法
     * @param object
     * @param method
     * @param args
     * @return
     */
    public static Object invokerMethod(Object object, Method method,
                                       Object args){
        Object resulet;
        try {
            method.setAccessible(true);
            resulet = method.invoke(object,args);
        } catch (Exception e) {
            logger.error("invoke method failure",e.getMessage());
            throw  new RuntimeException();
        }
        return resulet;
    }

    /**
     * 设置成员变量的值
     * @param object
     * @param field
     * @param value
     */
    public static  void setField(Object object, Field field,
                                 Object value){
        try {
            field.setAccessible(true);
            field.set(object,value);
        } catch (Exception e) {
           logger.error("set field failure",e.getMessage());
           throw  new RuntimeException();
        }

    }
}
