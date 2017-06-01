package org.smart4j.framework.bean;

import org.smart4j.cxp.util.CastUtil;

import java.util.Map;

/**
 * Created by mac on 2017/5/25
 * User: cxp
 * PACKAGE_Name : org.smart4j.framework.bean
 * Project_Name : tomcatwork
 * Date: 2017/5/25
 * Time: 16:36
 * 请求参数对象
 */
public class Param {
    private Map<String,Object> map;

    public Param(Map<String, Object> map) {
        this.map = map;
    }

    public long getLong(String name){
        return CastUtil.castLong(map.get(name));
    }

    public Map<String,Object> getMap(){
        return  map;
    }
}
