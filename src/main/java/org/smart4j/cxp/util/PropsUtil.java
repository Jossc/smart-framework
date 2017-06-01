package org.smart4j.cxp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by mac on 2017/5/22
 * User: cxp
 * PACKAGE_Name : org.smart4j.cxp.util
 * Project_Name : tomcatwork
 * Date: 2017/5/22
 * Time: 23:27
 * 属性文件工具类
 */
public class PropsUtil {
    private static  final Logger  logger = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载属性文件
     * @param fileName
     * @return
     */
    public static Properties loadProperties(String fileName){
        Properties properties = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().
                    getResourceAsStream(fileName);
            if(null == is){
                throw  new FileNotFoundException(fileName + "IS NOT Found");
            }
            properties = new Properties();
            properties.load(is);
        }catch (Exception e){
            logger.error(e.getMessage());

        }finally {
            if(null != is){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  properties;
    }

    /**
     * 获取字符型属性
     * @param properties
     * @param key
     * @return
     */
    public static  String getString(Properties properties,String key){
        return  getString(properties,key,"");
    }

    /**
     * 获取字符型属性
     * @param properties
     * @param key
     * @param value
     * @return
     */
    public static  String getString(Properties properties,String key,String value){
        String v = value;
        if(properties.containsKey(key)){
            v = properties.getProperty(key);
        }
        return  v;
    }

    /**
     * 获取数值型属性
     * @param properties
     * @param key
     * @return
     */
    public static  int getInt(Properties properties,String key){
        return  getInt(properties,key,0);
    }

    /**
     * 获取数值型属性（可有默认值）
     * @param properties
     * @param key
     * @param dvalue
     * @return
     */
    public static  int getInt(Properties properties,String key,int dvalue){
        int value = dvalue;
        if(properties.containsKey(key)){
            value = CastUtil.castInt(properties.getProperty(key));
        }
        return value;
    }

    /**
     * 获取布尔型属性
     * @param properties
     * @param key
     * @return
     */
    public static boolean getBoolean(Properties properties,String key){
        return  getBoolean(properties,key,false);
    }

    /**
     * 获取布尔型属性
     * @param properties
     * @param key
     * @param dvalue
     * @return
     */
    public static  boolean getBoolean(Properties properties,String key,boolean dvalue){
        boolean value =  dvalue;
        if(properties.containsKey(key)){
            value = CastUtil.castBoolean(properties.getProperty(key));
        }
        return  value;
    }
}
