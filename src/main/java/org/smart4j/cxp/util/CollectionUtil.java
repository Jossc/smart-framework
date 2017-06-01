package org.smart4j.cxp.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.Collection;
import java.util.Map;

/**
 * Created by mac on 2017/5/24
 * User: cxp
 * PACKAGE_Name : org.smart4j.cxp.util
 * Project_Name : tomcatwork
 * Date: 2017/5/24
 * Time: 11:28
 * 封装apache
 */
public class CollectionUtil {

    /**
     * 是否为空
     * @param collection
     * @return
     */
    public static  boolean isEmpty(Collection<?> collection){
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * 判断非空
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection){
        return  !isEmpty(collection);
    }

    /**
     * 判断map
     * @param map
     * @return
     */
    public static  boolean isEmpty(Map<?,?> map){
        return MapUtils.isEmpty(map);
    }

    /**
     * 判断map
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?,?> map){
        return  !isEmpty(map);
    }

}
