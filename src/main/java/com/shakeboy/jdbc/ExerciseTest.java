package com.shakeboy.jdbc;

import com.shakeboy.domain.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExerciseTest {
    public List<Admin> findAllAdmin(){
        Statement stmt=null;
        Connection conn=null;
        ResultSet rs=null;
        List<Admin> adminList=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql:///sharebook?useSSL=true","root","root");
            stmt=conn.createStatement();
            String sql = "select * from admins";
            rs=stmt.executeQuery(sql);
            Admin admin = new Admin();
            adminList= new ArrayList<>();
            // 遍历结果集，封装对象
            while (rs.next()){
                String a_name = rs.getString("a_name");
                String a_password = rs.getString("a_password");
                String a_role = rs.getString("a_role");
                // 创建Admin对象并赋值
                admin.setA_name(a_name);
                admin.setA_password(a_password);
                admin.setA_role(a_role);
                // 装载集合
                adminList.add(admin);
                System.out.println(admin);
            }
            System.out.println(adminList);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
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
        return null;
    }

    public static void main(String[] args) {
        new ExerciseTest().findAllAdmin();
    }
}
