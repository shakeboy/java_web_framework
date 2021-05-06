package com.shakeboy.connpool;

import com.shakeboy.util.DataSourceUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataSourceUtilsTest {
    public static void main(String[] args){
        /**
         * 使用连接池工具类
         */
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            // 1.获取连接
            connection = DataSourceUtils.getConnection();
            // 2.定义SQL和数据库操作对象
            String sql = "select * from users where user_id=?";
            preparedStatement = connection.prepareStatement(sql);
            // 赋值
            preparedStatement.setInt(1,1);
            // 3.执行sql
            resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet.next());
            System.out.println(resultSet.getString("user_avator"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
