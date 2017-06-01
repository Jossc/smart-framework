package org.smart4j.cxp.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by mac on 2017/5/24
 * User: cxp
 * PACKAGE_Name : org.smart4j.cxp.util
 * Project_Name : tomcatwork
 * Date: 2017/5/24
 * Time: 12:04
 * 类操作工具
 */
public class ClassUtil {
    private static  final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * 获取类加载器
     * @return
     */
    public static  ClassLoader getClassLoader(){
        return  Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类，
     * 暂时不加载静态快
     * @param className
     * @param isInitialize
     * @return
     */
    public static Class<?> loadClass(String className, boolean isInitialize){
        Class<?> clas;
        try {
            clas = Class.forName(className,isInitialize,getClassLoader());
        } catch (ClassNotFoundException e) {
            logger.error("loading class failure",e.getMessage());
            throw new RuntimeException();
        }
        return  clas;
    }

    /**
       加载类，
     * 暂时不加载静态快
     * @param className
     * @return
     */
    public static  Class<?> loadClass(String className){
        return  loadClass(className,false);
    }

    /**
     * 获取指定包下的所有类
     * @param packageName
     * @return
     */
    public static Set<Class<?>> getClassSet(String packageName){
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        try {
            Enumeration<URL> urlEnumeration
                    = getClassLoader().getResources(packageName.replace(".","/"));
            while(urlEnumeration.hasMoreElements()){
                URL url = urlEnumeration.nextElement();
                if(null != url){
                    String protocol = url.getProtocol();
                    System.out.println(protocol);
                    if("file".equals(protocol)){
                        String packagePath = url.getPath().replaceAll("%20","");
                        addClass(classSet,packagePath,packageName);
                    }else  if("jar".equals(protocol)){
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        if(null != jarURLConnection){
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if(null != jarFile){
                                Enumeration<JarEntry> jarEntryEnumeration
                                        = jarFile.entries();
                                while(jarEntryEnumeration.hasMoreElements()){
                                    JarEntry jarEntry = jarEntryEnumeration.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if(jarEntryName.endsWith(".class")){
                                        String calssName = jarEntryName.substring(0,
                                                jarEntryName.lastIndexOf("."))
                                                .replaceAll("/",".");
                                        doAddClass(classSet,calssName);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error("get class faliure",e.getMessage());
            throw  new RuntimeException();
        }
        return  classSet;
    }

    public static  void addClass(Set<Class<?>> classSet,String packagePath,String packageName){
        final File[] files = new File(packagePath).listFiles(new FileFilter() {
            public boolean accept(File file) {
                return (file.isFile() &&file.getName().endsWith(".class")
                || file.isDirectory());
            }
        });
        if(null != files){
            for(File file : files){
                String fileName= file.getName();
                if(file.isFile()){
                    String className = fileName.substring(0,fileName.lastIndexOf("."));
                    if (StringUtil.isNotEmpty(packageName)){
                        className = packageName + "." + className;
                    }
                    doAddClass(classSet,className);
                }else {
                    String subPackagePath =  fileName;
                    if(StringUtil.isNotEmpty(packagePath)){
                        subPackagePath = packagePath +"/" +subPackagePath;
                    }
                    String subPackageName = fileName;
                    if(StringUtil.isNotEmpty(packageName)){
                        subPackageName = packageName + "." + subPackageName;
                    }
                    addClass(classSet, subPackagePath, subPackageName);
                }
            }
        }
    }

    private static  void doAddClass(Set<Class<?>> classSet,String className){
        Class<?> cla = loadClass(className,false);
        classSet.add(cla);
    }

    @Test
    public void tt(){
        Set<Class<?>> set = getClassSet("org.smart4j.cxp.util");
        for(Class c :set){
            System.out.println(c.getName());
        }
    }
}
