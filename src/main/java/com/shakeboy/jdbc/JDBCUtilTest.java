package com.shakeboy.jdbc;

import com.shakeboy.domain.Admin;
import com.shakeboy.util.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 演示工具类
 */
public class JDBCUtilTest {
    public static void main(String[] args) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Statement statement = connection.createStatement();
        String sql = "select * from admins";
        ResultSet resultSet = statement.executeQuery(sql);
        Admin admin = new Admin();
        List<Admin> adminList = new ArrayList<>();
        while (resultSet.next()){
            String a_name = resultSet.getString("a_name");
            String a_password = resultSet.getString("a_password");
            String a_role = resultSet.getString("a_role");
            // 创建Admin对象并赋值
            admin.setA_name(a_name);
            admin.setA_password(a_password);
            admin.setA_role(a_role);
            // 装载集合
            adminList.add(admin);
        }
        System.out.println(adminList);
        JDBCUtils.close(statement,connection,resultSet);
    }
}
