package org.smart4j.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mac on 2017/5/25
 * User: cxp
 * PACKAGE_Name : org.smart4j.framework.bean
 * Project_Name : tomcatwork
 * Date: 2017/5/25
 * Time: 16:43
 * 返回视图对象
 */
public class View {
    /**
     * 路径
     */
    private String path;

    /**
     * 模型数据
     */
    private Map<String ,Object> model;

    public View(String path) {
        this.path = path;
        model = new HashMap<String,Object>();
    }

    public View addModel(String key,Object  value){
        model.put(key,value);
        return  this;
    }

    public String  getPath(){
        return  path;
    }

    public Map<String,Object> getModel(){
        return  model;
    }
}
