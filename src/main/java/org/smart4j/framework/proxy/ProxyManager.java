package org.smart4j.framework.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by mac on 2017/5/26
 * User: cxp
 * PACKAGE_Name : org.smart4j.framework.proxy
 * Project_Name : tomcatwork
 * Date: 2017/5/26
 * Time: 18:28
 * 代理管理
 */
public class ProxyManager {


    public  static <T> T crreateProxy(final  Class<?> targetClass,
                                      final List<Proxy> proxyList){
        return (T)Enhancer.create(targetClass, new MethodInterceptor() {
            public Object intercept(Object targetObject, Method targetMethod, Object[] methodParams, MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(targetClass,
                        targetObject,targetMethod,
                        methodParams,methodProxy,proxyList).
                        doProxyChain();
            }

        });
    }

}
