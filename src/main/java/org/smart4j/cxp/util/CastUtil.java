package org.smart4j.cxp.util;

/**
 * Created by mac on 2017/5/23
 * User: cxp
 * PACKAGE_Name : org.smart4j.cxp.util
 * Project_Name : tomcatwork
 * Date: 2017/5/23
 * Time: 17:49
 * 转型工具类
 */
public class CastUtil {

    /**
     * 转为String
     * @param object
     * @return
     */
    public static  String castString(Object object){
       return  castString(object,"");
    }

    /**
     * 转为String
     * @param object
     * @param key
     * @return
     */
    public static String castString(Object object,String key){
        return  object!=null?String.valueOf(object):key;
    }

    /**
     * 转为double (有默认值)
     * @param object
     * @param dvalue
     * @return
     */
    public static  double castDouble(Object object,double dvalue){
        double value  = dvalue;
        if(null != object){
            String sValue = castString(object);
            if(StringUtil.isNotEmpty(sValue)){
                try {
                    value  = Double .parseDouble(sValue);
                }catch (NumberFormatException e){
                    value = dvalue;
                }
            }
        }
        return  value;
    }

    /**
     * 转为double
     * @param object
     * @return
     */
    public static  double castDouble(Object object){
        return  castDouble(object,0);
    }

    /**
     * 转为long
     * @param object
     * @return
     */
    public static long castLong(Object object){
        return  castLong(object,0);
    }

    /**
     * 转为long
     * @param object
     * @param dvalue
     * @return
     */
    public static  long castLong(Object object,long dvalue){
        long  value  = dvalue;
        if(null != object){
            String sValue = castString(object);
            if(StringUtil.isNotEmpty(sValue)){
                try {
                    value  = Long.parseLong(sValue);
                }catch (NumberFormatException e){
                    value = dvalue;
                }
            }
        }
        return  value;
    }

    /**
     * int
     * @param object
     * @return
     */
    public static  int castInt(Object object){
        return  castInt(object,0);
    }

    /**
     *  int
     * @param object
     * @param dvalue
     * @return
     */
    public static  int castInt(Object object,int dvalue){
        int value = dvalue;
        if(null != object){
            String sValue =  castString(object);
            if(StringUtil.isNotEmpty(sValue)){
                try {
                    value  = Integer.parseInt(sValue);
                }catch (NumberFormatException e){
                    value = dvalue;
                }
            }
        }
        return  value;
    }

    public static  boolean castBoolean (Object object){
        return  false;
    }
    public static  boolean castBoolean(Object o,boolean dvalue){
        boolean value = dvalue;
        if(null != o){
            value = Boolean.parseBoolean(castString(o));
        }
        return  value;
    }
}
