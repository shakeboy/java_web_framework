package com.shakeboy.jdbc;

import com.shakeboy.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransactionTest {
    public static void main(String[] args) {
        Connection connection=null;
        PreparedStatement preparedStatement1=null;
        PreparedStatement preparedStatement2=null;
        try {
            connection = JDBCUtils.getConnection();
            // 开启事务管理 https://blog.csdn.net/lidew521/article/details/53192774==注意不起作用的原因
            // ALTER TABLE test ENGINE = InnoDB;
            connection.setAutoCommit(false);
            String sql1="update test set balance=balance-? where id=?";
            String sql2="update test set balance=balance+? where id=?";
            preparedStatement1=connection.prepareStatement(sql1);
            preparedStatement2=connection.prepareStatement(sql2);
            preparedStatement1.setInt(1,500);
            preparedStatement1.setInt(2,1);
            preparedStatement2.setInt(1,500);
            preparedStatement2.setInt(2,2);
            preparedStatement1.executeUpdate();
            // 手动制造异常
            int a = 1/0;
            preparedStatement2.executeUpdate();
            // 提交事务
            connection.commit();
            System.out.println("事务提交了");
        } catch (Exception exception) {
            // 事务回滚
            try {
                if (connection!=null){
                    connection.rollback();
                    System.out.println("事务回滚");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            exception.printStackTrace();
        }finally {
            JDBCUtils.close(preparedStatement1,connection);
            JDBCUtils.close(preparedStatement2,null);
        }
    }
}
