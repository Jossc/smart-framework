package org.smart4j.cxp.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by mac on 2017/5/23
 * User: cxp
 * PACKAGE_Name : org.smart4j.cxp.util
 * Project_Name : tomcatwork
 * Date: 2017/5/23
 * Time: 18:05
 * 字符
 */
public class StringUtil {

    /**
     * 是否为空
     * @param string
     * @return
     */
    public static  boolean isEmpty(String string){
        if(null != string){
            string = string.trim();
        }
        return StringUtils.isEmpty(string);
    }

    /**
     * 判断是否飞空
     * @param string
     * @return
     */
    public static  boolean isNotEmpty(String string){
        return  !isEmpty(string);
    }

    public  static  String[] splitsplit(String value ,String l){
        return StringUtils.split(value,l);
    }

}
