package org.smart4j.cxp.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by mac on 2017/5/24
 * User: cxp
 * PACKAGE_Name : org.smart4j.cxp.util
 * Project_Name : tomcatwork
 * Date: 2017/5/24
 * Time: 23:32
 * 数组工具类，封装apache
 */
public final class ArrayUtil {
    /**
     * 判断数组是否非空
     * @param array
     * @return
     */
    public static  boolean isNotEmpty(Object [] array){
        return !ArrayUtils.isEmpty(array);
    }

    /**
     * 判断是否为空
     * @param array
     * @return
     */
    public static  boolean isEmpty(Object []array){
      return  ArrayUtils.isEmpty(array) ;
    }
}
