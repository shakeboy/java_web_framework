package com.shakeboy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 添加一条记录：insert
 */
public class InsertTest {
    public static void main(String[] args) {
        Statement stmt = null;
        Connection conn = null;
        try {
            // 1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 定义SQL
            String sql = "insert into admins values('test','123','admin')";
            // 2.获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost/sharebook?useSSL=true","root","root");
            // 3.获取执行对象
            stmt = conn.createStatement();
            // 4.执行sql
            int count = stmt.executeUpdate(sql);//返回影响行数
            // 5.处理结果
            if(count>0){
                System.out.println(count);
                System.out.println("添加成功");
            }else {
                System.out.println("添加失败");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            //避免空指针异常，判断stmt
            if (stmt!=null){
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
    }
}
