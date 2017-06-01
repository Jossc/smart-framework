package org.smart4j.cxp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by mac on 2017/5/25
 * User: cxp
 * PACKAGE_Name : org.smart4j.cxp.util
 * Project_Name : tomcatwork
 * Date: 2017/5/25
 * Time: 16:59
 * 编解码
 */
public final class CodeUtil {
    private static  final Logger logger
            = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 编码
     * @param source
     * @return
     */
    public static  String encodeUrl(String source){
        String target;
        try {
            target = URLEncoder.encode(source,"UTF-8");
        }catch (Exception e){
            logger.error("encode URL failure",e.getMessage());
            throw  new RuntimeException(e);
        }
        return  target;
    }

    /**
     * 解码
     * @param source
     * @return
     */
    public static  String decodeUrl(String source){
        String target;
        try{
            target = URLDecoder.decode(source,"UTF-8");
        }catch (Exception e){
            logger.error("decode url failure",e.getMessage());
            throw  new RuntimeException(e);
        }
        return  target;
    }
}
