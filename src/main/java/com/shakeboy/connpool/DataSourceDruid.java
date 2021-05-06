package com.shakeboy.connpool;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DataSourceDruid {
    /**
     * druid
     *  1.导入jar包
     *  2.定义配置文件 druid.properties 可以放在任何地方，需要手动加载
     *  3.获取数据库连接池对象DataSource:通过一个工厂类DruidDataSourceFactory
     *  4.获取连接
     */
    public static void main(String[] args) throws Exception {
        // 1.导入jar包
        // 2.编写配置文件
        // 3.创建连接池对象（加载配置文件）
        Properties pro = new Properties();
        InputStream stream = DataSourceDruid.class.getClassLoader().getResourceAsStream("druid.properties");
        pro.load(stream);
        DataSource source = DruidDataSourceFactory.createDataSource(pro);
        // 4.获取连接
        Connection conn = source.getConnection();
        System.out.println(conn);
    }
}
