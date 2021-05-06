package com.shakeboy.jdbc;

import java.sql.*;

/**
 * 执行DDL create table test(id int,name varchar(20))
 */
public class SelectTestDemo1 {
    public static void main(String[] args) {
        Statement stmt=null;
        Connection conn=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost/sharebook?useSSL=true","root","root");
            stmt=conn.createStatement();
            String sql="select * from users";
            rs = stmt.executeQuery(sql);
            // 处理结果
            // 1.1 让游标向下移动一行
            while (rs.next()){
                // 1.2 获取数据
                int user_id = rs.getInt("user_id");
                String user_account = rs.getString("user_account");
                String user_signature = rs.getString("user_signature");
                System.out.println(user_id+"==="+user_account+"==="+user_signature);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
