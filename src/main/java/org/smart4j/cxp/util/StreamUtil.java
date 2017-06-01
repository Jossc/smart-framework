package org.smart4j.cxp.util;

import org.omg.PortableInterceptor.INACTIVE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by mac on 2017/5/25
 * User: cxp
 * PACKAGE_Name : org.smart4j.cxp.util
 * Project_Name : tomcatwork
 * Date: 2017/5/25
 * Time: 16:52
 * 流工具
 */
public  final class StreamUtil {
    private static  final Logger logger = LoggerFactory.getLogger(StreamUtil.class);


    /**
     * 从输入流中获取字符串
     * @param inputStream
     * @return
     */
    public static String getString(InputStream inputStream){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(inputStream));
            String line ;
            while((line = reader.readLine())!=null){
                stringBuilder.append(line);
            }
        }catch (Exception e){
            logger.error("get String failure", e.getMessage());
            throw  new RuntimeException();
        }
        return  stringBuilder.toString();
    }
}
