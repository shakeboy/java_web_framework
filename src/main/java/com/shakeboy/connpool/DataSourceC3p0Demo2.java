package com.shakeboy.connpool;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceC3p0Demo2 {
    /**
     * 参数演示
     */
    public static void main(String[] args) throws SQLException {
        // 1.创建数据库连接池对象
        DataSource dataSource = new ComboPooledDataSource();

        // 2.获取连接对象（注意导入驱动包）
        for (int i = 0; i < 11; i++) { // Exception in thread "main" java.sql.SQLException: An attempt by a client to checkout a Connection has timed out.
            Connection conn = dataSource.getConnection();
            System.out.println(i+":"+conn);
            if(i==5){
                conn.close();//归还给连接池
            }
        }
        new DataSourceC3p0Demo2().testNameConfig1();
    }
    @Test
    public void testNameConfig() throws SQLException {
        // 1.1.回去指定名称的配置
        DataSource dataSource = new ComboPooledDataSource("otherc3p0");
        // 2.获取链接
        for (int i = 0; i < 20; i++) {
            Connection conn = dataSource.getConnection();
            System.out.println(i+":"+conn);
        }
    }
    @Test
    public void testNameConfig1() throws SQLException {
        // 1.1.回去指定名称的配置
        DataSource dataSource = new ComboPooledDataSource("otherc3p01");
        // 2.获取链接
        for (int i = 0; i < 10; i++) {
            Connection conn = dataSource.getConnection();
            System.out.println(i+":"+conn);
        }
    }
}
