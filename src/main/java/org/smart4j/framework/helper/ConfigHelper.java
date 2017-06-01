package org.smart4j.framework.helper;

import com.sun.org.apache.regexp.internal.RE;
import org.smart4j.cxp.util.PropsUtil;
import org.smart4j.framework.ConfigConstant;

import java.util.Properties;

/**
 * Created by mac on 2017/5/24
 * User: cxp
 * PACKAGE_Name : org.smart4j.framework.helper
 * Project_Name : tomcatwork
 * Date: 2017/5/24
 * Time: 11:37
 * 属性文件工具类
 */
public final class ConfigHelper {

    private static final Properties Config_File =
            PropsUtil.loadProperties(ConfigConstant.Congif_File);

    /**
     * 获取jdbc驱动
     *
     * @return
     */
    public static String getJdbcDricer() {
        return PropsUtil.getString(Config_File, ConfigConstant.JDBC_Dirver);
    }

    /**
     * 获取jdbc url
     *
     * @return
     */
    public static String getURL() {
        return PropsUtil.getString(Config_File, ConfigConstant.JDBC_Url);
    }

    /**
     * 用户名
     *
     * @return
     */
    public static String getJdbcUserName() {
        return PropsUtil.getString(Config_File, ConfigConstant.JDBC_UserName);
    }

    /**
     * 获取密码
     *
     * @return
     */
    public static String getJdbcPassword() {
        return PropsUtil.getString(Config_File, ConfigConstant.JDBC_PassWord);

    }

    /**
     * 获取app应用包
     * @return
     */
    public static String getAppBasePackage(){
        return  PropsUtil.getString(Config_File,ConfigConstant.App_Base_Package);
    }

    /**
     * 获取jsp路径
     * @return
     */
    public static String getAppJspPath(){
        return  PropsUtil.getString(Config_File,ConfigConstant.App_Jsp_Path,"/WEB-INF/view");
    }

    /**
     * 获取静态资源
     * @return
     */
    public static String getAppAssetPath(){
        return PropsUtil.getString(Config_File,ConfigConstant.App_Asset_Path,"/asset/");
    }
}
