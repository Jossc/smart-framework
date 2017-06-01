package org.smart4j.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.annotation.Transaction;
import org.smart4j.framework.helper.DatabaseHelper;

import java.lang.reflect.Method;

/**
 * User: Clement
 * PACKAGE_NAME: org.smart4j.framework.proxy
 * MONTH_NAME_SHORT: 五月
 * DAY_NAME_SHORT: 星期三
 * PROJECT_NAME: tomcat-work
 * TIME: 17:59
 */
public class TransactionProxy implements  Proxy{
    private  static  final Logger logger =
            LoggerFactory.getLogger(TransactionProxy.class);


    public static final ThreadLocal<Boolean> threanLocal =
            new ThreadLocal<Boolean>(){
                @Override
                protected  Boolean initialValue(){
                    return  false;
                }
            };
    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        boolean flag = threanLocal.get();
        Method method = proxyChain.getTargetMethod();
        if(!flag &&method.isAnnotationPresent(Transaction.class)){
            threanLocal.set(true);
            try {
                DatabaseHelper.beginTransaction();
                logger.debug("begin transaction");
                result = proxyChain.doProxyChain();
                DatabaseHelper.commitTransaction();
                logger.debug("commit transaction");
            }catch (Exception e){
                DatabaseHelper.rollbacTreansction();
                logger.debug("rollback transaction");
                throw  new RuntimeException(e.getMessage());
            }finally {
                threanLocal.remove();
            }
        }else {
            result = proxyChain.doProxyChain();
        }
        return result;
    }
}
