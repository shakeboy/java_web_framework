package com.shakeboy.connpool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceC3p0 {
    /**
     * 1.数据库连接池
     * 2.Spring JDBC
     *
     * 数据库连接池：就是一个连接池（集合）：存放数据库连接对象
     *      当系统初始化后，连接池会被创建，容器会申请一些连接对象，当用户来访问数据库时，从容器中获取连接对象，用户访问完之后归还给容器
     *      节约了系统资源，高效
     * 3.实现数据库连接池
     *      a.标准接口：DataSource  javax.sql包下
     *          1.方法
     *              获取连接：getConnection()
     *              归还连接：如果连接对象是从connection中获取的，调用close方法不会关闭连接，而是归还链接
     *      b.一般我们不去实现它，由数据库厂商去实现
     *          1.c3p0:数据库连接池
     *          2.Druid:数据库连接池实现技术，由阿里巴巴提供的
     *  4.数据库连接技术
     *      1.导入jar包 c3p0
     *      2.编写配置文件 c3p0-config.xml c3px.properties 直接放在src目录下(这里放在resources下)
     *      3.创建核心对象 数据库连接池对象 ComboPooledDataSource
     *      4.获取连接
     */
    public static void main(String[] args) throws SQLException {
        // 1.创建数据库连接池对象
        DataSource dataSource = new ComboPooledDataSource();
        // 2.获取连接对象（注意导入驱动包）
        Connection conn = dataSource.getConnection();
        // 3.打印
        System.out.println(conn);
    }
}
