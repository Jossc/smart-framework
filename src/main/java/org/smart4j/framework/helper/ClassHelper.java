package org.smart4j.framework.helper;

import org.smart4j.cxp.util.ClassUtil;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Service;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mac on 2017/5/24
 * User: cxp
 * PACKAGE_Name : org.smart4j.framework.helper
 * Project_Name : tomcatwork
 * Date: 2017/5/24
 * Time: 17:58
 * 获取所有注解类
 */
public final class ClassHelper {
    private static  final Set<Class<?>> CLASS_SET;

    static {
        String basePackage = ConfigHelper.getAppBasePackage();
        CLASS_SET = ClassUtil.getClassSet(basePackage);
    }

    /**
     * 获取包下所有类
     * @return
     */
    public static  Set<Class<?>> getClassSet(){
        return  CLASS_SET;
    }

    /**
     * 获取service
     * @return
     */
    public static  Set<Class<?>> getServiceClass(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        if(null !=CLASS_SET){
            for(Class<?> c : CLASS_SET){
                if(c.isAnnotationPresent(Service.class)){
                     classSet.add(c);
                }
            }
        }
        return  classSet;
    }

    /**
     * 获取包下所有controller类
     * @return
     */
    public static  Set<Class<?>> getControllerClass(){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        if(null != CLASS_SET){
            for(Class<?> c : CLASS_SET ){
                if(c.isAnnotationPresent(Controller.class)){
                    classSet.add(c);
                }
            }
        }
        return  classSet;
    }

    /**
     * 获取所有bean类
     * @return
     */
    public static  Set<Class<?>> getBeanClassSet(){
        Set<Class<?>> beanClassSet = new HashSet<Class<?>>();
        beanClassSet.addAll(getControllerClass());
        beanClassSet.addAll(getServiceClass());
        return  beanClassSet;
    }

    /**
     * 获取应用包下的某类的父类
     * @param superClass
     * @return
     */
    public static  Set<Class<?>> getClassSetBySuper(Class<?> superClass){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> cls :CLASS_SET){
            if (superClass.isAssignableFrom(cls)
                    && !superClass.equals(cls)){
                classSet.add(cls);
            }
        }
        return  classSet;
    }

    /**
     * 获取应用包下带有某注解的所有类
     * @param anntationClass
     * @return
     */
    public static  Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> anntationClass){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        for(Class<?> cla: CLASS_SET){
            if(cla.isAnnotationPresent(anntationClass)){
                classSet.add(cla);
            }
        }
        return  classSet;
    }
}
