package org.smart4j.framework.helper;

import org.smart4j.cxp.util.ReflectionUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by mac on 2017/5/24
 * User: cxp
 * PACKAGE_Name : org.smart4j.framework.helper
 * Project_Name : tomcatwork
 * Date: 2017/5/24
 * Time: 18:25
 * bean 助手
 */
public final class BeanHelper {
    private  static  final Map<Class<?>,Object> beanMap =
            new HashMap<Class<?>,Object>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        if(null != beanClassSet){
            for(Class<?> beanClass : beanClassSet){
                Object object = ReflectionUtil.newInstance(beanClass);
                beanMap.put(beanClass,object);
            }
        }
    }

    /**
     * 获取bean映射
     * @return
     */
    public static  Map<Class<?>,Object> getBeanMap(){
        return  beanMap;
    }

    /**
     * 获取bean实例
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T  getBean(Class<T> tClass){
        if(!beanMap.containsKey(tClass)){
            throw  new RuntimeException("can no get Bean class " + tClass.getName());
        }
        return (T) beanMap.get(tClass);
    }

    public static  void setBean(Class<?> cla,Object object){
        beanMap.put(cla,object);
    }


}
