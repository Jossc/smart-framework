package org.smart4j.framework.bean;

/**
 * Created by mac on 2017/5/25
 * User: cxp
 * PACKAGE_Name : org.smart4j.framework.bean
 * Project_Name : tomcatwork
 * Date: 2017/5/25
 * Time: 16:48
 * 返回数据对象
 */
public class Data {

    /**
     * 模型对象
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel(){
        return  model;
    }
}
