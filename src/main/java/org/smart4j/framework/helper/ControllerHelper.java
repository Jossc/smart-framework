package org.smart4j.framework.helper;

import org.smart4j.cxp.util.ArrayUtil;
import org.smart4j.cxp.util.CollectionUtil;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by mac on 2017/5/25
 * User: cxp
 * PACKAGE_Name : org.smart4j.framework.helper
 * Project_Name : tomcatwork
 * Date: 2017/5/25
 * Time: 00:00
 * 控制器助手类
 */
public final  class ControllerHelper {
    protected static final Map<Request,Handler> actionMap
            = new HashMap<Request, Handler>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClass();
        if(CollectionUtil.isNotEmpty(controllerClassSet)){
            for(Class<?> controllerClass : controllerClassSet){
                //获取cotroller类中定义的方法
                Method [] methods = controllerClass.getMethods();
                if(ArrayUtil.isNotEmpty(methods)){
                    for(Method method : methods){
                        if(method.isAnnotationPresent(Action.class)){
                            //从action 注解中获取URL
                            Action action = method.getAnnotation(Action.class);
                            String mapping = action.value();
                            if(mapping.matches("\\w+:/\\w*")){
                                String[] array  = mapping.split(".");
                                if(ArrayUtil.isNotEmpty(array)
                                        && array.length ==2){
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request =
                                            new Request(requestMethod,requestPath);
                                    Handler handler =
                                            new Handler(controllerClass, method);
                                    actionMap.put(request,handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取handler
     * @param requestMethod
     * @param requestPath
     * @return
     */
    public static  Handler getHandler(String requestMethod,
                                      String requestPath){
        Request request = new Request(requestMethod,requestPath);
        return  actionMap.get(request);
    }
}
