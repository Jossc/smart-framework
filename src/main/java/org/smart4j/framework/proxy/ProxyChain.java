package org.smart4j.framework.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2017/5/26
 * User: cxp
 * PACKAGE_Name : org.smart4j.framework.proxy
 * Project_Name : tomcatwork
 * Date: 2017/5/26
 * Time: 18:07
 * 代理链
 */
public class ProxyChain {

    //目标对象
    private final Class<?> targetClass;
    //目标方法
    private final Object targetObject;
    //代理方法
    private final Method targetMethod;
    //方法代理
    private final MethodProxy methodProxy;
    //方法参数
    private final Object[] methoParams;

    private List<Proxy> proxyList = new ArrayList<Proxy>();

    private int proxyIndex  = 0;



    public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod, Object[] methodParams, MethodProxy methodProxy, List<Proxy> proxyList) {
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methoParams = methodParams;
        this.proxyList = proxyList;
    }

    public Object[] getMethoParams(){
        return  methoParams;
    }
    public Class<?> getTargetClass(){
        return  targetClass;
    }

    public Method getTargetMethod(){
        return  targetMethod;
    }
    public Object doProxyChain() throws Throwable {
        Object methodResult;
        if(proxyIndex<proxyList.size()){
            methodResult = proxyList.get(proxyIndex++);
        }else {
            methodResult = methodProxy.invokeSuper(targetObject,methoParams);

        }
        return  methodResult;
    }

}
