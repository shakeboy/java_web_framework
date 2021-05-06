package com.shakeboy.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DataSourceUtils {
    /**
     * JDBC的的druid工具类
     * 1.定义一个类
     * 2.提供静态加载配置文件，初始化连接池对象
     * 3.提供方法
     */
    private static DataSource ds=null;
    static {
        try {
            // 1. 加载配置文件
            Properties pro = new Properties();
            InputStream stream = DataSourceUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(stream);
            // 2. 获取datasource
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    public static DataSource getDataSource(){return ds;}
    public static void close(Statement statement,Connection connection){
//        if(statement!=null){
//            try {
//                statement.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        if(connection!=null){
//            try {
//                connection.close();
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
        close(statement,connection,null);
    }
    public static void close(Statement statement, Connection connection, ResultSet resultSet){
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(connection!=null){
            try {
                connection.close();//归还链接
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
