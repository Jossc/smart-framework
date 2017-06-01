package org.smart4j.cxp.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by mac on 2017/5/25
 * User: cxp
 * PACKAGE_Name : org.smart4j.cxp.util
 * Project_Name : tomcatwork
 * Date: 2017/5/25
 * Time: 17:07
 * json 工具类
 */
public class JsonUtil {
    private static final Logger logger
            = LoggerFactory.getLogger(JsonUtil.class);
    private static  final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * to Json
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJson(T obj){
        String json;
        try {

            json = OBJECT_MAPPER.writeValueAsString(obj);
        }catch (Exception e){
            logger.error("convert Pojo to Json failure",e.getMessage() );
            throw  new RuntimeException(e);
        }
        return  json;
    }

    /**
     * to Pojo
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static  <T> T fromJson(String json ,Class<T> type){
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json,type);
        } catch (Exception e) {
            logger.error("convert Json to Pojo failure",e.getMessage());
            throw  new RuntimeException(e);
        }
        return  pojo;
    }
}
