package org.smart4j.framework.ascept;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.framework.proxy.AspectProxy;

import java.lang.reflect.Method;

/**
 * User: mac
 * PACKAGE_NAME: org.smart4j.framework.ascept
 * MONTH_NAME_SHORT: 五月
 * DAY_NAME_SHORT: 星期五
 * PROJECT_NAME: tomcat-work
 * TIME: 23:44
 */
public class ControllerAscept extends AspectProxy {
    private static final Logger logger = LoggerFactory.getLogger
            (ControllerAscept.class);

    private  long beging ;

    @Override
    public void before(Class<?> cls, Method method,Object[] params) throws  Throwable{
        logger.debug("-------");
        logger.debug(String.format("class: %s",cls.getName()));
        logger.debug(String.format("method: %s",method.getName()));
        beging = System.currentTimeMillis();
    }

    @Override
    public void after(Class<?> cls, Method method,Object[] params) throws  Throwable{
        logger.debug(String.format("time: %dms",System.currentTimeMillis() - beging));
        logger.debug("---end---");
    }


}
