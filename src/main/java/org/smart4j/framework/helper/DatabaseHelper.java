package org.smart4j.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 * User: mac
 * PACKAGE_NAME: org.smart4j.framework.helper
 * MONTH_NAME_SHORT: 五月
 * DAY_NAME_SHORT: 星期二
 * PROJECT_NAME: tomcat-work
 * TIME: 23:07
 */
public final  class DatabaseHelper {

    private static  final ThreadLocal<Connection> thradLocl = new ThreadLocal<Connection>();
    private static  final Logger looger = LoggerFactory.getLogger(DatabaseHelper.class);

    /**
     * 开始事务
     * @throws SQLException
     */
    public static  void beginTransaction() throws SQLException {
        Connection connection = (Connection) getConnection();
        if(null != connection){
            try {
                connection.setAutoCommit(false);
            }catch (Exception e){
                looger.error("begin transaction",e.getMessage());
                throw  new RuntimeException(e.getMessage());
            }finally {
                thradLocl.remove();
            }
        }
    }

    /***
     * 获取连接
     * @return
     * @throws SQLException
     */
    public static  ThreadLocal<Connection> getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(String.valueOf(Thread.currentThread().
                getContextClassLoader().getResource("rece")));
        thradLocl.set(connection);
        return thradLocl;
    }

    /**
     * 提交事务
     * @throws SQLException
     */
    public static  void commitTransaction() throws SQLException {
        Connection connection = (Connection) getConnection();
        if(null != connection){
            try {
                connection.commit();
                connection.close();
            }catch (Exception e){
                looger.error("commit transaction failure",e.getMessage());
                throw  new RuntimeException(e.getMessage());
            }finally {
                thradLocl.remove();
            }
        }
    }

    public static void rollbacTreansction() throws SQLException {
        Connection connection = (Connection) getConnection();
        if(null != connection){
            try {
                connection.rollback();
                connection.close();
            }catch (Exception e){
                looger.error("rollback transaction failure",e.getMessage());
                throw  new RuntimeException(e.getMessage());
            }finally {
                thradLocl.remove();
            }
        }
    }

}
