package org.smart4j.framework.bean;

import java.lang.reflect.Method;

/**
 * Created by mac on 2017/5/24
 * User: cxp
 * PACKAGE_Name : org.smart4j.framework.bean
 * Project_Name : tomcatwork
 * Date: 2017/5/24
 * Time: 23:56
 */
public class Handler {
    private Class<?> controllerClass;

    /**
     * Action method
     */
    private Method actionMethod;

    public Handler(Class<?> controllerClass ,Method actionMethod){
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public void setControllerClass(Class<?> controllerClass) {
        this.controllerClass = controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }

    public void setActionMethod(Method actionMethod) {
        this.actionMethod = actionMethod;
    }
}
