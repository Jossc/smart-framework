package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Aspect;
import org.smart4j.framework.annotation.Transaction;
import org.smart4j.framework.proxy.Proxy;
import org.smart4j.framework.proxy.ProxyManager;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * User: mac
 * PACKAGE_NAME: org.smart4j.framework.helper
 * MONTH_NAME_SHORT: 五月
 * DAY_NAME_SHORT: 星期六
 * PROJECT_NAME: tomcat-work
 * TIME: 00:03
 */
public class AopHelper {
    private static  final Logger logger = LoggerFactory.getLogger(AopHelper.class);

    static {
        try {
            Map<Class<?>,Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            if(null != targetMap){
              for(Map.Entry<Class<?>, List<Proxy>> listEntry :
                      targetMap.entrySet()){
                Class<?> targetClass = listEntry.getKey();
                List<Proxy> proxyList = listEntry.getValue();
                Object proxy = ProxyManager.crreateProxy(targetClass,proxyList);
                BeanHelper.setBean(targetClass,proxy);
              }
            }
        }catch (Exception e){

        }
    }

    /**
     * 封装目标对象
     * @param aspect
     * @return
     * @throws Exception
     */
    private static Set<Class<?>> creatTargetClassSet(Aspect aspect) throws Exception{
        Set<Class<?>> tatgetClassSet = new HashSet<Class<?>>();
        Class<? extends Annotation> annotation = aspect.value();
        if(annotation != null && !annotation.equals(Aspect.class)){
            tatgetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return  tatgetClassSet;
    }

    /**
     * 创建代理映射
     * @return
     * @throws Throwable
     */
    private static Map<Class<?>,Set<Class<?>>> createProxyMap() throws Exception{
        Map<Class<?>,Set<Class<?>>> classSetMap = new HashMap<Class<?>, Set<Class<?>>>();
        addAspectMap(classSetMap);
        addTransaction(classSetMap);
        return  classSetMap;
    }

    /**
     * 添加aop映射
     * @param proxyMap
     * @throws Exception
     */
    private static void addAspectMap(Map<Class<?>,Set<Class<?>>> proxyMap) throws Exception {
        Set<Class<?>> classSet = ClassHelper.getClassSetBySuper(Aspect.class);
        if( null != classSet){
            for(Class<?> cla :classSet){
                if(cla.isAnnotationPresent(Aspect.class)){
                    Aspect aspect = cla.getAnnotation(Aspect.class);
                    Set<Class<?>> targetClass = creatTargetClassSet(aspect);
                    proxyMap.put(cla,targetClass);
                }
            }
        }
    }

    /**
     * 添加事务映射
     * @param proxyMap
     */
    private static  void addTransaction(Map<Class<?>,Set<Class<?>>> proxyMap){
        Set<Class<?>> classSet = ClassHelper.getClassSetBySuper(Transaction.class);
        proxyMap.put(Transaction.class,classSet);
    }

    /**
     * *创建目标映射
     * @param proxyMap
     * @return
     * @throws Exception
     */
    private static Map<Class<?>,List<Proxy>> createTargetMap(Map<Class<?>,
            Set<Class <?>>> proxyMap) throws  Exception{
        Map<Class<?>,List<Proxy>> targetMap = new HashMap<Class<?>, List<Proxy>>();
        if(null != proxyMap) {
            for (Map.Entry<Class<?>, Set<Class<?>>> proxy : proxyMap.entrySet()) {
                Class<?> proxtClass = proxy.getKey();
                Set<Class<?>> targetClassSet = proxy.getValue();
                for(Class<?> targetClass : targetClassSet){
                    Proxy proxyT = (Proxy) proxtClass.newInstance();
                    if(targetMap.containsKey(targetClass)){
                        targetMap.get(targetClass).add(proxyT);
                    }else {
                        List<Proxy> proxyList = new ArrayList<Proxy>();
                        proxyList.add(proxyT);
                        targetMap.put(targetClass,proxyList);
                    }
                }

            }
        }
        return  targetMap;
    }

}
