package org.smart4j.framework.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by mac on 2017/5/24
 * User: cxp
 * PACKAGE_Name : org.smart4j.framework.bean
 * Project_Name : tomcatwork
 * Date: 2017/5/24
 * Time: 23:52
 * 封装请求信息
 */
public class Request {
    /**
     * 请求方法
     */
    private String requestMethod;

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    /**
     * 请求路径
     */
    private String requestPath;

    public Request(String requestMethod,String requestPath){
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    @Override
    public int hashCode(){
        return HashCodeBuilder.reflectionHashCode(this);
    }
    @Override
    public boolean equals(Object object){
        return EqualsBuilder.reflectionEquals(this,object);
    }
}

