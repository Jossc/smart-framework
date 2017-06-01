package org.smart4j.framework;

import org.smart4j.cxp.util.*;
import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ConfigHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.util.ClassUtil.HelperLoader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mac on 2017/5/25
 * User: cxp
 * PACKAGE_Name : org.smart4j.framework
 * Project_Name : tomcatwork
 * Date: 2017/5/25
 * Time: 17:16
 * 请求转发
 */
@WebServlet(urlPatterns = "/*",loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    /**
     * 初始化
     * @param servletConfig
     * @throws ServletException
     */
    public void  init(ServletConfig servletConfig) throws ServletException{
        HelperLoader.init();
        ServletContext servletContext = servletConfig.getServletContext();
        ServletRegistration jspServlet =
                servletContext.getServletRegistration("jsp");
        //映射路径是默认的
        jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");

        ServletRegistration dafalutServlet = servletContext.
                getServletRegistration("default");
        //添加静态资源映射
        dafalutServlet.addMapping(ConfigHelper.getAppAssetPath() +"*");
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //获取请求方法，路径
        String requestMethod = request.getMethod().toLowerCase();
        String requestPath = request.getPathInfo();

        //获取action处理器
        Handler handler = ControllerHelper.getHandler(requestMethod,requestPath);

        if(null != handler){
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);

            Map<String,Object> map = new HashMap<String ,Object>();
            Enumeration<String> paramNames = request.getParameterNames();
            while(paramNames.hasMoreElements()){
                String paramName = paramNames.nextElement();
                String parameVlue = request.getParameter(paramName);
                map.put(paramName,parameVlue);
            }
            String body = CodeUtil.decodeUrl
                    (StreamUtil.getString(request.getInputStream()));

            if(StringUtil.isNotEmpty(body)){
                String [] parames = StringUtil.splitsplit(body,"&");
                if(ArrayUtil.isNotEmpty(parames)){
                    for(String parpa : parames){
                        String [] array = StringUtil.splitsplit(parpa,"=");
                        if(ArrayUtil.isNotEmpty(array)
                                && array.length==2){
                            String paramName = array[0];
                            String paramValue = array[1];
                            map.put(paramName,paramValue);
                        }
                    }
                }
            }
            /**
             * 调用返回jsp页面
             */
            Param param  = new Param(map);
            Method actionMethod = handler.getActionMethod();
            Object result = ReflectionUtil.invokerMethod
                    (controllerBean,actionMethod,param);
            if(result instanceof View){
                View view = (View) result;
                String path = view.getPath();
                if(StringUtil.isNotEmpty(path)){
                    if(path.startsWith("/")){
                        response.sendRedirect(request.getContextPath()
                        +path);
                    }else {
                        Map<String ,Object> model = view.getModel();
                        for(Map.Entry<String,Object> entry : model.entrySet()){
                            request.setAttribute(entry.getKey(),
                                    entry.getValue());
                        }
                        request.getRequestDispatcher(ConfigHelper.getAppJspPath()
                            + path).forward(request,response);
                    }
                }else if(result instanceof Data){
                    Data data = (Data) result;
                    Object model = data.getModel();
                    if(null != model){
                        response.setContentType("application/json");
                        response.setCharacterEncoding("UTF-8");
                        PrintWriter writer = response.getWriter();
                        String json = JsonUtil.toJson(model);
                        writer.write(json);
                        writer.flush();;
                        writer.close();
                    }
                }
            }

        }
    }
}
