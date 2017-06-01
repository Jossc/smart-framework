package org.smart4j.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * User: mac
 * PACKAGE_NAME: org.smart4j.framework.proxy
 * MONTH_NAME_SHORT: 五月
 * DAY_NAME_SHORT: 星期五
 * PROJECT_NAME: tomcat-work
 * TIME: 18:56
 * 切面代理
 */
public abstract class AspectProxy implements Proxy{
    private static  final Logger logger = LoggerFactory.
            getLogger(AspectProxy.class);

    @Override
    public final Object doProxy(ProxyChain proxyChain) throws Throwable{
        Object result ;
        Class<?> clas= proxyChain.getTargetClass();
        Method method = proxyChain.getTargetMethod();
        Object[] params = proxyChain.getMethoParams();

        try{
            if(intercept(clas,method,params)){
                before(clas,method,params);
                result = proxyChain.doProxyChain();
                after(clas,method,params);
            }else {
                result = proxyChain.doProxyChain();
            }
        }catch (Exception e){
            logger.error("proxy failure,",e.getMessage());
            error(clas,method,params);
            throw e;
        }finally {
            end();
        }
        return  result;
    }

    public void begin(){
        System.err.println(" begin ---");
    }

    public boolean intercept(Class<?> cla,Method method,Object[] params){
        return  true;
    }
    public void  before(Class<?> cla,Method method,Object[] params) throws Throwable {
    }
    public void  after(Class<?> cla,Method method,Object[] params) throws Throwable {
    }
    public void  error(Class<?> cla,Method method,Object[] params){
    }

    public void end(){
        System.err.println(" end ---");
    }
}
