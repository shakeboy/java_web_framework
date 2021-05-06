package com.shakeboy.util;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * 抽取资源
 * 1.获取连接对象的方法
 * 2.注册驱动
 * 3.释放资源
 */
public class JDBCUtils {
    /**
     * 获取连接方法
     * @author shakeboy
     */
    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    // 文件代码块，读取静态资源
    static {
        // 读取静态资源
        // 1.Properties 集合类
        Properties pro = new Properties();
        // 2.加载文件classLoader
        ClassLoader loader = JDBCUtils.class.getClassLoader();
        URL resource = loader.getResource("db.properties");
        String path=resource.getPath();
        System.out.println(path);
        try {
            pro.load(new FileReader(path));
            // 3.获取属性赋值
            user = pro.getProperty("user");
            url=pro.getProperty("url");
            password=pro.getProperty("password");
            driver = pro.getProperty("driver");
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        // 调取配置文件db.properties,无需添加参数，保持工具类的通用性
        return DriverManager.getConnection(url,user,password);
    }

    /**
     * 释放资源
     */
    public static void close(Statement stmt,Connection conn){
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    public static void close(Statement stmt, Connection conn, ResultSet rs){
        if(stmt!=null){
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
