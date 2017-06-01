package org.smart4j.framework.proxy;

/**
 * Created by mac on 2017/5/26
 * User: cxp
 * PACKAGE_Name : org.smart4j.framework.proxy
 * Project_Name : tomcatwork
 * Date: 2017/5/26
 * Time: 18:04
 * 代理接口
 */
public interface Proxy {

    /**
     * 执行链式代理
     * @param proxyChain
     * @return
     * @throws Throwable
     */
    Object doProxy(ProxyChain proxyChain ) throws  Throwable;
}
