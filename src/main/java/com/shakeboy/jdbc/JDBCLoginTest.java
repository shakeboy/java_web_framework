package com.shakeboy.jdbc;

import com.shakeboy.util.JDBCUtils;

import java.sql.*;
import java.util.Scanner;

public class JDBCLoginTest {

    public static void main(String[] args) {
        // 1.键盘录入，接受用户名和密码
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = scanner.nextLine();
        System.out.println("请输入密码");
        String password = scanner.next();
        // 2.调用方法
        boolean login = new JDBCLoginTest().login(username, password);
        System.out.println(username+"==="+password);
        // 3.输出语句
        if (login==false){
            System.out.println("登陆失败");
        }else {
            System.out.println("登陆成功");
        }
    }
    /**
     * 登陆方法
     */
    public boolean login(String username,String password){
        if(username==null||password==null){
            return false;
        }
        // 连接数据库判断是否登陆成功
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection = JDBCUtils.getConnection();
//            String sql = "select * from admins where a_name='"+username+"' and a_password='"+password+"'";
            String sql = "select * from admins where a_name=? and a_password=?";
//            statement= connection.createStatement();
            preparedStatement = connection.prepareStatement(sql);
            // 传参 1.用setXxx() ?位置/名称
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            resultSet= preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            JDBCUtils.close(preparedStatement,connection,resultSet);
        }
        return false;
    }
}
